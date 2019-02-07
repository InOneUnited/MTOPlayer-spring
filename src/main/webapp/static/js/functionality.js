'use strict';


/* SONGS LIBRARY */

var songsNames = [];
var songsPaths = [];
var currentSong = 0;
function loadMusic(){
	dropbox.filesListFolder({path: ''})
        .then(function(response) {
          console.log('response', response)
          displayFiles(response.entries)
      	})
        .catch(function(error) {
          console.error(error);
        });
}

function displayFiles(files) {
    for(let i = 0; i < files.length; i++){
    
        if(files[i][".tag"] === "folder"){
    
            let path_lower = files[i]["path_lower"];
            dropbox.filesListFolder({path: path_lower})
    
            .then(function(response) {
                displayFiles(response.entries);
            })
    
            .catch(function(error) {
                console.error(error);
            });
    
        } else if(files[i][".tag"] === "file" && files[i]["name"].endsWith(".mp3")) {
            songsNames.push(files[i]["name"]);
            songsPaths.push(files[i]["path_lower"]);
        }
    }
}

function createList(filesToList){
    let filesList = document.getElementById("files");
    console.log("THIS IS LENGTH" + filesToList.length);
    for (var i = 0; i < filesToList.length; i++) {
    let li = document.createElement('li');
    li.appendChild(document.createTextNode(filesToList[i].slice(0,-4)));
    filesList.appendChild(li);
    }
}

/* PROGRESS BAR */

function updateProgressBar() {
    let player = document.getElementById('player');
    let length = player.duration;
    let current_time = player.currentTime;

    let totalLength = calculateTotalTime(length);
    document.getElementById("end-time").innerHTML = totalLength;

    let currentTime = calculateCurrentTime(current_time);
    document.getElementById("start-time").innerHTML = currentTime;

    let progressbar = document.getElementById('seekBar');
    if (isNaN(player.currentTime / player.duration)) {
        progressbar.value = 0;
    } else {
        progressbar.value = (player.currentTime / player.duration);
    }
    progressbar.addEventListener("click", seek);

    if (player.currentTime == player.duration) {
        document.getElementById('play-song').className = "";
    }

    function seek(event) {
        let percent = event.offsetX / this.offsetWidth;
        player.currentTime = percent * player.duration;
        progressbar.value = percent / 100;
    }
};

function calculateTotalTime(length) {

    let minutes = Math.floor(length / 60);
    let seconds_int = length - minutes * 60;
    let seconds_str = seconds_int.toString();
    let second = seconds_str.substr(0, 2);

    if (isNaN(minutes)) {
        minutes = "00";
    }
    if ((second === "Na") || (second === "0.")) {
        second = "00";
    }
    return minutes + ':' + second;
}

function calculateCurrentTime(currentTime) {
    let current_minute = parseInt(currentTime / 60) % 60;
    let current_seconds_long = currentTime % 60;
    let current_seconds = current_seconds_long.toFixed();
    let current_time = (current_minute < 10 ? "0" + current_minute : current_minute) +
        ":" + (current_seconds < 10 ? "0" + current_seconds : current_seconds);

    return current_time;
}

/* PLAYER */

/* OPERATING MUSIC */

function setEventPlay(player) {
	document.getElementById('play-song').addEventListener('click', function () {
        playMusic(player, true);
    });
}

function setEventPause(player) {
	document.getElementById('pause-song').addEventListener('click', function () {
        playMusic(player, false);
    });
}

function setEventStop(player) {
	document.getElementById('stop-song').addEventListener('click', function () {
        playMusic(player, false);
        player.load();

        document.getElementById('seekBar').value = 0;

        let length = document.getElementById('player').duration;
        let totalLength = calculateTotalTime(length);
        document.getElementById("end-time").innerHTML = totalLength;
        player.removeAttribute('autoplay');
    });
}

function setEventPrev(player, songList, changeState) {
	document.getElementById('prev-song').addEventListener('click', function () {
        
    	changeState();

        document.getElementById('seekBar').value = 0;
        playMusic(player, false);
        if (currentSong === 0) {
            currentSong = songList.length - 1;
        } else {
            currentSong -= 1;
        }
        let prev = 1;
        downloadSong(dropbox, songList, prev, () => changeToLoading(false));
        
        playMusic(player, true);
        
        player.setAttribute('autoplay', true);
    });
}

function setEventNext(player, songList, changeState) {
	document.getElementById('next-song').addEventListener('click', function () {
        
    	changeState();

        document.getElementById('seekBar').value = 0;
        playMusic(player, false);
        if (currentSong === songList.length - 1) {
            currentSong = 0;
        } else {
            currentSong += 1;
        }

        let next = -1;

        downloadSong(dropbox, songList, next, () => changeToLoading(false));

        playMusic(player, true);
        
        player.setAttribute('autoplay', true);
    });
}

function playMusic(player, isPlaying) {
	if(isPlaying){
		player.play();
        document.getElementById('play-song').style.display = "none";
        document.getElementById('pause-song').style.display = "block";	
	} else {
		player.pause();
        document.getElementById('play-song').style.display = "block";
        document.getElementById('pause-song').style.display = "none";	
	}
}
function findRow(node)
{
    var i = 1;
    while (node = node.previousSibling) {
        if (node.nodeType === 1) { ++i }
    }
    return i;
}
function initPlayer(dropbox, songList, changeState) {

    let player = document.getElementById('player');

    setEventPlay(player);
    setEventPause(player);
    setEventStop(player);
    setEventNext(player, songList, changeState);
    setEventPrev(player, songList, changeState);
    for(let i=0; i<document.getElementsByTagName("li").length; i++){
        document.getElementsByTagName("li")[i].addEventListener("click", function(){
            let oldSong = currentSong;
            let num = findRow(this)-2; // 1 - array index, 2 - next button is skipping one song
            currentSong = num;
            document.getElementById("next-song").click();
            document.getElementsByTagName("li")[oldSong].classList.remove("active");
        })
    }
        
}

function changeToLoading(isLoadingNow){
	if(isLoadingNow){
		document.getElementById('loading').style.display = "flex";
        document.getElementById('buttons').style.display = "none";
	} else {
		document.getElementById('loading').style.display = "none";
        document.getElementById('buttons').style.display = "flex";
	}
}

/* load player */

function player(songList) {

    downloadSong(dropbox, songList, 0, () => changeToLoading(false));
    initPlayer(dropbox, songList, () => changeToLoading(true));

    document.getElementById('logout').addEventListener('click', function () {
        dropbox = null;
        document.location.href = "index.html"
    }, false);
}


/* DOWNLOAD SONG */

function readBlob(file) {

    let reader = new FileReader();
    let playerIndex = 0;

    reader.onloadend = function (event) {
        if (event.target.readyState == FileReader.DONE) {
            var player = document.getElementsByTagName('audio')[playerIndex];
            player.src = "data:audio/wav;base64," + btoa(event.target.result);
            player.load();
        }
    }
    reader.readAsBinaryString(file);
}

function changeSongDisplay(lastSongNumber){
    console.log("CURRENT: " + currentSong);
    console.log("Last: " + lastSongNumber);
    let oldSongNumber = currentSong+lastSongNumber;
    
    if(lastSongNumber === 0){
        document.getElementsByTagName("li")[currentSong].classList.add("active");
    } else {
        if(currentSong === songsNames.length-1 && lastSongNumber === 1){
            debugger;
            document.getElementsByTagName("li")[0].classList.remove("active");
            document.getElementsByTagName("li")[currentSong].classList.add("active");
        }else if (currentSong === 0 && lastSongNumber === -1){
            debugger;
            document.getElementsByTagName("li")[songsNames.length-1].classList.remove("active");
            document.getElementsByTagName("li")[currentSong].classList.add("active");     
        } else {
            document.getElementsByTagName("li")[oldSongNumber].classList.remove("active");
            document.getElementsByTagName("li")[currentSong].classList.add("active");
        }
    }
}

function downloadSong(dropbox, songList, lastSongNumber, callback) {

    let songPath = songList[currentSong];
    console.log(songPath);
    let songObject = new Object();
    songObject.path = songPath;
    dropbox.filesDownload(songObject)
        .then(function (response) {
            readBlob(response.fileBlob);
            changeSongDisplay(lastSongNumber);
            callback();
        })
        .catch(function (response) {
            console.log(response);
        });
}

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

async function demo() {
  
	loadMusic();
  await sleep(5000); 
    createList(songsNames);
	player(songsPaths);
}


demo();



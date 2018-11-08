'use strict';


/* SONGS LIBRARY */


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

function setEventPrev(player, songList, currentSong, changeState) {
	document.getElementById('prev-song').addEventListener('click', function () {

    	changeState();

        document.getElementById('seekBar').value = 0;
        playMusic(player, false);
        if (currentSong === 0) {
            currentSong = songList.length - 1;
        } else {
            currentSong -= 1;
        }
        downloadSong(dropbox, songList, currentSong, () => changeToLoading(false));
        playMusic(player, true);
        player.setAttribute('autoplay', true);
    });
}

function setEventNext(player, songList, currentSong, changeState) {
	document.getElementById('next-song').addEventListener('click', function () {
    	changeState();

        document.getElementById('seekBar').value = 0;
        playMusic(player, false);
        if (currentSong === songList.length - 1) {
            currentSong = 0;
        } else {
            currentSong += 1;
        }
        downloadSong(dropbox, songList, currentSong, () => changeToLoading(false));
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

function initPlayer(dropbox, songList, currentSong, changeState) {

    let player = document.getElementById('player');

    setEventPlay(player);
    setEventPause(player);
    setEventStop(player);
    setEventNext(player, songList, currentSong, changeState);
    setEventPrev(player, songList, currentSong, changeState);    
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

function player() {

    let songList = ["/starfucker - bury us alive.mp3",
        "/stoned jesus - indian.mp3",
        "/dee_yan-key_-_02_-_winter_is_coming_adagio_-_first_snow.mp3"];

    let currentSong = 0;

    downloadSong(dropbox, songList, currentSong, () => changeToLoading(false));
    initPlayer(dropbox, songList, currentSong, () => changeToLoading(true));

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

function downloadSong(dropbox, songList, number, callback) {

    let songPath = songList[number];
    console.log(songPath);
    let songObject = new Object();
    songObject.path = songPath;

    dropbox.filesDownload(songObject)
        .then(function (response) {
            readBlob(response.fileBlob);
            callback();
        })
        .catch(function (response) {
            console.log(response);
        });
}

player();
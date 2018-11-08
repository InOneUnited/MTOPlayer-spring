var Dropbox = Dropbox.Dropbox;

function initDropbox(storageDboxKeyObj) {

    let dropbox = new Dropbox(storageDboxKeyObj);

    console.log(dropbox);
    /*
        { accessToken: 'ZP2-tmvGcPAAAAAAAAAACR6nmTFyT8--8lLvhiLR_YL29SCy1ES4HSMNP69cu77d' });
    */
    dropbox.filesListFolder({ path: '' })
        .then(function (response) {
            console.log(response);
        })
        .catch(function (error) {
            console.log(error);
        });
    return dropbox;
}

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

function downloadSong(dropbox, songList, number) {

    let songPath = songList[number];
    console.log(songPath);
    let songObject = new Object();
    songObject.path = songPath;

    dropbox.filesDownload(songObject)
        .then(function (response) {
            readBlob(response.fileBlob);
        })
        .catch(function (response) {
            console.log(response);
        });
}


// PASTED AND INITIALLY CUSTOMIZED FROM 
// https://gist.githubusercontent.com/lukewduncan/3e041e4b22a50855f9faaf01dec40137/raw/b03fff5a96584e69ba1bf9e25eafc1ea20b58c09/audio-player.js


function initProgressBar() {
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

function initPlayer(dropbox, songList, currentSong) {

    // var playerContainer = document.getElementById('player-container');
    let player = document.getElementById('player');
    // let isPlaying = false;
    let playBtn = document.getElementById('play-song');
    let pauseBtn = document.getElementById('pause-song');
    let stopBtn = document.getElementById('stop-song');
    let prevBtn = document.getElementById('prev-song');
    let nextBtn = document.getElementById('next-song');

    playBtn.addEventListener('click', function () {
        togglePlayOn();
    });

    pauseBtn.addEventListener('click', function () {
        togglePlayOff();
    });

    stopBtn.addEventListener('click', function () {
        togglePlayOff();
        player.load();

        document.getElementById('seekBar').value = 0;

        let length = document.getElementById('player').duration;
        let totalLength = calculateTotalTime(length);
        document.getElementById("end-time").innerHTML = totalLength;
        player.removeAttribute('autoplay');
    });

    prevBtn.addEventListener('click', function () {

        document.getElementById('seekBar').value = 0;
        togglePlayOff();
        if (currentSong === 0) {
            currentSong = songList.length - 1;
        } else {
            currentSong -= 1;
        }
        // isPlaying = false;
        downloadSong(dropbox, songList, currentSong);
        togglePlayOn();
        player.setAttribute('autoplay', true);
    });

    nextBtn.addEventListener('click', function () {

        document.getElementById('seekBar').value = 0;
        togglePlayOff();
        if (currentSong === songList.length - 1) {
            currentSong = 0;
        } else {
            currentSong += 1;
        }
        // isPlaying = true;
        downloadSong(dropbox, songList, currentSong);
        togglePlayOn();
        player.setAttribute('autoplay', true);
    });

    function togglePlayOn() {

        player.play();
        // isPlaying = true;
        document.getElementById('play-song').style.display = "none";
        document.getElementById('pause-song').style.display = "block";
    }

    function togglePlayOff() {

        player.pause();
        // isPlaying = false;
        document.getElementById('play-song').style.display = "block";
        document.getElementById('pause-song').style.display = "none";
    }
}

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

function main() {

    // let's play with localStorage:p
    let storageDboxKeyObj = JSON.parse(localStorage.getItem('key'));

    let dropbox = initDropbox(storageDboxKeyObj);

    let songList = ["/starfucker - bury us alive.mp3",
        "/stoned jesus - indian.mp3",
        "/dee_yan-key_-_02_-_winter_is_coming_adagio_-_first_snow.mp3"]

    // initialize with first song
    let currentSong = 0;
    downloadSong(dropbox, songList, currentSong);
    initPlayer(dropbox, songList, currentSong);

    document.getElementById('logout').addEventListener('click', function () {
        dropbox = null;
        document.location.href = "index.html"
    }, false);
}

main();


var Dropbox = Dropbox.Dropbox;
/*
function initDropbox() {

    let dropbox = new Dropbox({ accessToken: 'ZP2-tmvGcPAAAAAAAAAACR6nmTFyT8--8lLvhiLR_YL29SCy1ES4HSMNP69cu77d' });

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
    let firstSong = 0;

    reader.onloadend = function (event) {
        if (event.target.readyState == FileReader.DONE) {
            var player = document.getElementsByTagName('audio')[firstSong];
            player.src = "data:audio/wav;base64," + btoa(event.target.result);
            player.load();
        }
    }
    reader.readAsBinaryString(file);
}

function downloadSongs(dropbox) {

    dropbox.filesDownload({ path: "/dee_yan-key_-_02_-_winter_is_coming_adagio_-_first_snow.mp3" })
        .then(function (response) {
            readBlob(response.fileBlob);
        })
        .catch(function (response) {
            console.log(response);
        });
}

downloadSongs(initDropbox());
*/
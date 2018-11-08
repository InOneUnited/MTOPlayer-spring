var Dropbox = Dropbox.Dropbox;

function initDropbox() {

    let dropbox = new Dropbox({ accessToken: 'ZP2-tmvGcPAAAAAAAAAACR6nmTFyT8--8lLvhiLR_YL29SCy1ES4HSMNP69cu77d' });

    // dropbox.filesListFolder({ path: '' })
    //     .then(function (response) {
    //         console.log(response);
    //     })
    //     .catch(function (error) {
    //         console.log(error);
    //     });
    return dropbox;
}

var dropbox = initDropbox();


function loadMusic(){
	dropbox.filesListFolder({path: ''})
        .then(function(response) {
          console.log('response', response)
          displayFiles(response.entries);
          console.log(response);
        })
        .catch(function(error) {
          console.error(error);
        });
}

var songsNames = [];
var songsPaths = [];
function displayFiles(files) {
    var filesList = document.getElementById('files');
    var li;

    for(let i = 0; i < files.length; i++){
    
        if(files[i][".tag"] === "folder"){
    
            let path_lower = files[i]["path_lower"];
            dropbox.filesListFolder({path: path_lower})
    
            .then(function(response) {
                console.log('response', response)
                displayFiles(response.entries);
                console.log(response);
            })
    
            .catch(function(error) {
                console.error(error);
            });
    
        } else if(files[i][".tag"] === "file" && files[i]["name"].endsWith(".mp3")) {
            songsNames.push(files[i]["name"]);
            songsPaths.push(files[i]["path_lower"]);
        }
    }

    console.log(songsNames.length);
    for(let i=0; i<songsNames.length; i++){
        console.log(songsNames[i]);
    }
    
}

function createList(){
    loadMusic();
    console.log("THIS IS LENGTH" + filesToList.length);
    for (var i = 0; i < filesToList.length; i++) {
    li = document.createElement('li');
    li.appendChild(document.createTextNode(filesToList[i]));
    filesList.appendChild(li);
    }
}

window.onload = function() {
	createList()

}
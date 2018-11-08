'use strict';

var Dropbox = Dropbox.Dropbox;

function initDropbox() {
    let storageDboxKeyObj = JSON.parse(localStorage.getItem('key'));

    let dropbox = new Dropbox(storageDboxKeyObj);

    return dropbox;
}

var dropbox = initDropbox();
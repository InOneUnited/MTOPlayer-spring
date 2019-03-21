// var Dropbox = Dropbox.Dropbox;


function initListeners() {
    // document.getElementById("submit").addEventListener("click", logWithDropBox);
    $("#firstPage").click(setFirstPage);
    $("#secondPage").click(setSecondPage);
    $("#thirdPage").click(setThirdPage);

}


// function logWithDropBox() {
//
//     const clientId = "ca2peppfwuoyxjm";
//     const redirectURL = "https://inoneunited.github.io/KMK_Dbox_Player/"
//     window.location.href = `https://www.dropbox.com/oauth2/authorize?client_id=${clientId}&redirect_uri=${redirectURL}&response_type=token`;
//
// }
//
// function getAccessToken() {
//     var currentLocation = window.location.href;
//     debugger;
//     if (currentLocation.indexOf("access_token") != -1) {
//
//         let accessToken = createAccessTokenFromLink(currentLocation);
//         saveDBoxObjectInLocalStorage(accessToken);
//     }
// }
//
// function createAccessTokenFromLink(link) {
//     let startPosition = link.indexOf("=") + 1;
//     let endPosition = link.indexOf("&");
//     return link.slice(startPosition, endPosition);
// }
//
//
// function saveDBoxObjectInLocalStorage(access_token) {
//     var dbox = new Dropbox({ accessToken: access_token });
//     let JsonToSave = JSON.stringify(dbox);
//
//     localStorage.removeItem("key");
//     localStorage.setItem("key", JsonToSave);
//     window.location.href = "player.html";
// }


function setFirstPage() {
    $("#first-text").text("Best music player for DropBox Users!");
    $("#second-text").text("Listen Your favorite music online!");
}

function setSecondPage() {
    $("#first-text").text("Application created by:");
    $("#second-text").text("Krzysztof Jodlowski, Mateusz Mazurczak, Krzysztof Przybylowicz");
}

function setThirdPage() {
    $("#first-text").text("Contact to autors:");
    $("#second-text").text("krzysztof1.przybylowicz@gmail.com, krzysztof.jodlowski@gmail.com, mateusz.mazurczak@gmail.com");

}


initListeners();
// getAccessToken();

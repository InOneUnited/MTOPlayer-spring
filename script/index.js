var Dropbox = Dropbox.Dropbox;


function initListeners() {
    document.getElementById("submit").addEventListener("click", logWithDropBox);
    document.getElementById("firstPage").addEventListener("click", setFirstPage);
    document.getElementById("secondPage").addEventListener("click", setSecondPage);
    document.getElementById("thirdPage").addEventListener("click", setThirdPage);

}


function logWithDropBox() {

    const clientId = "ca2peppfwuoyxjm";
    const redirectURL = "https://krzysiekjodlowski.github.io/KMK_Dbox_Player/"
    window.location.href = `https://www.dropbox.com/oauth2/authorize?client_id=${clientId}&redirect_uri=${redirectURL}&response_type=token`;

}

function getAccessToken() {
    var currentLocation = window.location.href;
    debugger;
    if (currentLocation.indexOf("access_token") != -1) {

        let accessToken = createAccessTokenFromLink(currentLocation);
        saveDBoxObjectInLocalStorage(accessToken);
    }
}

function createAccessTokenFromLink(link) {
    let startPosition = link.indexOf("=") + 1;
    let endPosition = link.indexOf("&");
    return link.slice(startPosition, endPosition);
}


function saveDBoxObjectInLocalStorage(access_token) {
    var dbox = new Dropbox({ accessToken: access_token });
    let JsonToSave = JSON.stringify(dbox);

    localStorage.removeItem("key");
    localStorage.setItem("key", JsonToSave);
    window.location.href = "player.html";
}


function setFirstPage() {

    document.getElementById("first-text").textContent = "Best music player for DropBox Users!";
    document.getElementById("second-text").textContent = "Listen Your favorite music online!";
    document.getElementById("first-text").style.fontSize = "30px";
    document.getElementById("second-text").style.fontSize = "30px";
}

function setSecondPage() {


    document.getElementById("first-text").textContent = "Application created by:";
    document.getElementById("second-text").textContent = "Krzysztof Jodlowski, Mateusz Mazurczak, Krzysztof Przybylowicz";

    document.getElementById("first-text").style.fontSize = "30px";
    document.getElementById("second-text").style.fontSize = "20px";

}

function setThirdPage() {

    document.getElementById("first-text").textContent = "Contact to autors:";
    document.getElementById("second-text").textContent = "krzysztof1.przybylowicz@gmail.com, krzysztof.jodlowski@gmail.com, mateusz.mazurczak@gmail.com";

    document.getElementById("first-text").style.fontSize = "30px";
    document.getElementById("second-text").style.fontSize = "15px";
}


initListeners();
getAccessToken();
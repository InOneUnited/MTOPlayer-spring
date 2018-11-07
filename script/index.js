var Dropbox = Dropbox.Dropbox;


function initListeners() {
    document.getElementById("submit").addEventListener("click", submit);
    document.getElementById("firstPage").addEventListener("click",setFirstPage);
    document.getElementById("secondPage").addEventListener("click",setSecondPage);
    document.getElementById("thirdPage").addEventListener("click",setThirdPage);
    
}


function submit() {
    let login = document.getElementById("login").value;
    let password = document.getElementById("password").value;

    if (login == loginPattern && password == passwordPattern) {
        var dbox = new Dropbox({ accessToken: 'ZP2-tmvGcPAAAAAAAAAAD4J9jcQkIzzzb8q0QdCC5ssPk6RkSMQnA0t4CxzLXpqd' });
        let JsonToSave = JSON.stringify(dbox);
        console.log(JsonToSave);
        localStorage.removeItem("key");
        localStorage.setItem("key", JsonToSave);
        window.location.href = "player.html";
        console.log(localStorage.getItem("key"));
    }
}


function setFirstPage() {
    // document.getElementById("firstPage").style.hover.color = "aliceblue";
    // document.getElementById("SecondPage").style.hover.color = "#999999";
    // document.getElementById("thirdPage").style.hover.color =  "#999999";
    
    document.getElementById("first-text").textContent = "Best music player for DropBox Users!";
    document.getElementById("second-text").textContent = "Listen Your favorite music online!";
    document.getElementById("first-text").style.fontSize = "30px";
    document.getElementById("second-text").style.fontSize = "30px";
}

function setSecondPage() {
    // document.getElementById("SecondPage").style.hover.color = "aliceblue";
    // document.getElementById("firstPage").style.hover.color = "#999999";
    // document.getElementById("thirdPage").style.hover.color =  "#999999";
    

    document.getElementById("first-text").textContent = "Application created by:";
    document.getElementById("second-text").textContent = "Krzysztof Jodlowski, Mateusz Mazurczak, Krzysztof Przybylowicz";

    document.getElementById("first-text").style.fontSize = "30px";
    document.getElementById("second-text").style.fontSize = "20px";

}

function setThirdPage() {
    // document.getElementById("thirdPage").style.hover.color = "aliceblue";
    // document.getElementById("firstPage").style.hover.color = "#999999";
    // document.getElementById("secondPage").style.hover.color =  "#999999";

    document.getElementById("first-text").textContent = "Contact to autors:";
    document.getElementById("second-text").textContent = "krzysztof1.przybylowicz@gmail.com, krzysztof.jodlowski@gmail.com, mateusz.mazurczak@gmail.com";
    
    document.getElementById("first-text").style.fontSize = "30px";
    document.getElementById("second-text").style.fontSize = "15px";
}
 

var loginPattern = "j";  
// "jodlaskrzychu";
var passwordPattern = "j";
// "jodla2018";

var jsonKey = '{"key": "ZP2-tmvGcPAAAAAAAAAAD4J9jcQkIzzzb8q0QdCC5ssPk6RkSMQnA0t4CxzLXpqd"}';
console.log("wchodzi");
initListeners();
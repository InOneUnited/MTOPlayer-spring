var Dropbox = Dropbox.Dropbox;


function initListeners() {
    document.getElementById("submit").addEventListener("click", submit)
    
}

function submit() {
    let login = document.getElementById("login").value;
    let password = document.getElementById("password").value;

    if (login == loginPattern && password == passwordPattern) {
        var dbox = new Dropbox({ key: 'ZP2-tmvGcPAAAAAAAAAAD4J9jcQkIzzzb8q0QdCC5ssPk6RkSMQnA0t4CxzLXpqd' });
        localStorage.setItem("key", dbox);
        window.location.href = "player.html";
    }
 
        
    
}
 

var loginPattern = "j";  
// "jodlaskrzychu";
var passwordPattern = "j";
// "jodla2018";

var jsonKey = '{"key": "ZP2-tmvGcPAAAAAAAAAAD4J9jcQkIzzzb8q0QdCC5ssPk6RkSMQnA0t4CxzLXpqd"}';
console.log("wchodzi");
initListeners();
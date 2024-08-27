function displaytime(){
    var date = new Date();
    var time = date.toLocaleTimeString();
    var date = date.toLocaleDateString();
    document.getElementById("clock").innerHTML = time+"<br>"+date;
}
setInterval(displaytime, 1000);
displaytime(); 
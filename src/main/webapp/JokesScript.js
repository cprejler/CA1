/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
let AllJokes = "https://casperprejler.xyz/CA1/api/joke/all";
let JokeByID = "https://casperprejler.xyz/CA1/api/joke/jokes/";
let RandomJoke = "https://casperprejler.xyz/CA1/api/joke/random";
document.getElementById('getJokeByID').addEventListener("click", getJokeByID);
document.getElementById('getRandomJoke').addEventListener("click", getRandomJoke);
afetch();
function afetch(){
    fetch(AllJokes)
            .then(res => res.json())
            .then(data => {
                jokeList = (data)
        console.log(jokeList)
        table1(jokeList)
    }
)
}
function table1 (list) {
    var headers = (Object.getOwnPropertyNames(list[0]));
    var headerString = "<tr>" +
            headers.map(function (a) {
                return "<th>" + a + "</th>"
            }).join("") +
            "</tr>";
    console.log(headerString);
    var htmlRows = "<tr>"
    list.forEach(e => {
        var temp = Object.values(e).map(function (a) {
            return "<td>" + a + "</td>";
        }).join("") + "</tr>";
        htmlRows += temp;
    })
    console.log(htmlRows);
    document.getElementById("jokeList").innerHTML = "<table class='table table-bordered table-hover table-sm'>" + headerString + htmlRows; 
}
function getJokeByID () {
     
    id = document.getElementById('jokeID').value; 
    fetch(JokeByID+id)
.then(res => res.json()) //in flow1, just do it
.then(data => {
// Inside this callback, and only here, the response data is available
   console.log("data",data); 
   
   let result = data.map(data => data = `JokeText: ${data.jokeText}<br> Type: ${data.type}<br> JokeID: ${data.id}`);
   document.getElementById('jokeByID').innerHTML = result ;
})}
function getRandomJoke () {
      
    fetch(RandomJoke)
.then(res => res.json()) //in flow1, just do it
.then(data => {
// Inside this callback, and only here, the response data is available
   console.log("data",data); 
   
   let result = data.map(data => data = `JokeText: ${data.jokeText}<br> Type: ${data.type}<br> JokeID: ${data.id}`);
   document.getElementById('randomJoke').innerHTML = result ;
})}



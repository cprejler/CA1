let AllCars = "http://localhost:8080/CA1/api/cars/all";

HTMLPrint();
myfetch();
let carList = [];

function myfetch(){
    fetch(AllCars)
.then(res => res.json()) //in flow1, just do it
.then(data => { 
        carList = data.forEach(e =>{
                    return Object.values(data[e]) + Object.getOwnPropertyNames(data[e]);
    })
    console.log(carList)
    }
)
}



function HTMLPrint() {
fetch(AllCars)
.then(res => res.json()) //in flow1, just do it
.then(data => {
    
    let headers = (Object.getOwnPropertyNames(data[0]));
    console.log(headers);
        
    let headerString = "<tr>" +
            headers.map(function (a) {
                return "<th>" + a + "</th>"
            }).join("") +
            "</tr>";
    console.log(headerString);
    
    
    let htmlRows = "<tr>"
        data.forEach(e => {
        let temp = Object.values(e).map(function (a) {
            return "<td>" + a + "</td>";
        }).join("") + "</tr>";
            
        htmlRows += temp;
        })
    document.getElementById("div1").innerHTML = "<table border='1'>" + headerString + htmlRows; 
   })
}    

function filter(evt) {
    evt.preventDefault();
    let filteredcars = carsList.filter(function (a) {
        return a.price < document.getElementById("lablePrice").value;

    })
    HTMLPrint(filteredcars);
}
    
  var button = document.getElementById("price").onsubmit = filter;      


function filterPrint(list) {
    let headers = (Object.getOwnPropertyNames(list[0]));
    let headerString = "<tr>" +
            headers.map(function (a) {
                return "<th>" + a + "</th>"
            }).join("") +
            "</tr>";
    console.log(headerString);
    let htmlRows = "<tr>"
        list.forEach(e => {
        let temp = Object.values(e).map(function (a) {
            return "<td>" + a + "</td>";
        }).join("") + "</tr>";
        htmlRows += temp;
    })
    console.log(htmlRows);
    document.getElementById("div2").innerHTML = "<table border='1'>" + headerString + htmlRows;
}

 
    
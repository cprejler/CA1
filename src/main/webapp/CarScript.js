let AllCars = "http://localhost:8080/CA1/api/cars/all";
afetch();
function afetch(){
fetch(AllCars)
  .then(res => res.json())
  .then(data => {

                carList = (data)
                console.log(carList)
                table1(carList); 
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
    document.getElementById("CarList").innerHTML = "<table border='1'>" + headerString + htmlRows; 
}
function filter(evt) {
    evt.preventDefault();
    let filtedcars = carList.filter(function (a) {
        return a.price < document.getElementById("lablePrice").value;

    })
    table1(filtedcars);
}
   var button = document.getElementById("price").onsubmit = filter;
;


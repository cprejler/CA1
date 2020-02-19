let Allurl = "http://localhost:8080/CA1/api/groupmembers/all"; 
let UserById = "http://localhost:8080/CA1/api/groupmembers/members/"; 

document.getElementById('getAllUsers').addEventListener("click", getAllUsers);
document.getElementById('getUserById').addEventListener("click", getUserById);


function getUserById () {
     
    id = document.getElementById('userId').value; 
    fetch(UserById+id)
.then(res => res.json()) //in flow1, just do it
.then(data => {
// Inside this callback, and only here, the response data is available
   console.log("data",data); 
   
   let result = data.map(data => data = `Name: ${data.name}, Github: ${data.github}, StudentID: ${data.studentID}`);
   document.getElementById('userById').innerText = result ;
})}


function getAllUsers () {
fetch(Allurl)
.then(res => res.json()) //in flow1, just do it
.then(data => {
// Inside this callback, and only here, the response data is available
   console.log("data",data);
   
   let tableString = "<table> <tr> <th>ID</th><th>Email</th><th>Github</th><th>Name</th><th>StudentID</th></tr>";
                        let userTableArray = data.map(data => data = `<tr><td>${data.id} </td><td>${data.email} </td><td>${data.github} </td><td>${data.name} </td><td>${data.studentID} </td></tr>`);
                        userTableArray.forEach(data => {
                            tableString += data;
                        });
                        tableString += "</table>";
                        document.getElementById("allUsers").innerHTML = tableString;
                        console.log(tableString);
})}
 

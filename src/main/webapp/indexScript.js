let Allurl = "https://casperprejler.xyz/CA1/api/groupmembers/all"; 
let UserById = "https://casperprejler.xyz/CA1/api/groupmembers/members/"; 

document.getElementById('getAllUsers').addEventListener("click", getAllUsers);
document.getElementById('getUserById').addEventListener("click", getUserById);


function getUserById () {
     
    id = document.getElementById('userId').value; 
    fetch(UserById+id)
.then(res => res.json()) //in flow1, just do it
.then(data => {
// Inside this callback, and only here, the response data is available
   console.log("data",data); 
   
   let result = data.map(data => data = `Name: ${data.name}<br> Github: ${data.github}<br> StudentID: ${data.studentID}`);
   document.getElementById('userById').innerHTML = result ;
})}


function getAllUsers () {
fetch(Allurl)
.then(res => res.json()) //in flow1, just do it
.then(data => {
// Inside this callback, and only here, the response data is available
   console.log("data",data);
   
   let tableString = "<table class='table table-bordered table-hover table-sm'> <tr> <th>Github</th><th>Name</th><th>StudentID</th></tr>";
                        let userTableArray = data.map(data => data = `<tr><td>${data.github} </td><td>${data.name} </td><td>${data.studentID} </td></tr>`);
                        userTableArray.forEach(data => {
                            tableString += data;
                        });
                        tableString += "</table>";
                        document.getElementById("allUsers").innerHTML = tableString;
                        console.log(tableString);
})}
 

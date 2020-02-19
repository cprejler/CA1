let Allurl = "http://localhost:8080/CA1/api/groupmembers/all"; 

document.getElementById('getAllUsers').addEventListener("click", getAllUsers);


function getAllUsers () {
fetch(Allurl)
.then(res => res.json()) //in flow1, just do it
.then(data => {
// Inside this callback, and only here, the response data is available
   console.log("data",data);
   
   let tableString = "<table> <tr> <th>ID</th><th>Email</th><th>Github</th><th>Name</th><th>StudentID</th></tr>";
                        let userTableArray = data.map(data => data = `<tr><td>${data.id} </td><td>${data.name} </td><td>${data.studentID} </td><td>${data.github} </td><td>${data.email} </td></tr>`);
                        userTableArray.forEach(data => {
                            tableString += data;
                        });
                        tableString += "</table>";
                        document.getElementById("userFromId").innerHTML = tableString;
                        console.log(tableString);
})



} 

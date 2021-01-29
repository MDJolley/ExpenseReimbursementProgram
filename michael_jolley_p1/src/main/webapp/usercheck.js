// window.onload = checkLoggedIn();

// function checkLoggedIn(){
//     $.ajax({
//         url: '/ERP/api/logCheck',
//         type: "GET",
//         contentType: 'application/json; charset=utf-8',
//         success: function(data){
//             console.log(data);
//             changeLoginToLogout (data);
//         },
//         error: function(error){
//             alert("Error: "+ error.message);
//         }
//     });
// }

// function changeLoginToLogout (data) {
//         $("#log:last-child").append(
//             '<a class="nav-link" href="http://localhost:8080/ERP/api/logout">Logout</a>');
//     }

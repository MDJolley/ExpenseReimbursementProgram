function checkPass()
{
    //Store the password field objects into variables ...
    var password = document.getElementById('Password1');
    var confirm  = document.getElementById('Password2');
    //Store the Confirmation Message Object ...
    var message = document.getElementById('confirm-message');
    //Set the colors we will be using ...
    var good_color = "#66cc66";
    var bad_color  = "#ff6666";
    //Compare the values in the password field 
    //and the confirmation field
    if(password.value == confirm.value){
        //The passwords match. 
        //Set the color to the good color and inform
        //the user that they have entered the correct password 
        confirm.style.backgroundColor = good_color;
        message.style.color           = good_color;
        message.innerHTML             =' ';
    }else{
        //The passwords do not match.
        //Set the color to the bad color and
        //notify the user.
        confirm.style.backgroundColor = bad_color;
        message.style.color           = bad_color;
        message.innerHTML             = 'Passwords Do Not Match!';
    }
}  

function getWelcome(){
   $.ajax({
    url: 'http://localhost:8080/ERP/me/getCurrentUser',
    type: "GET",
    contentType: 'application/json; charset=utf-8',
    success: function(data){
        console.log(data);
        welcomeUser (data);
    },
    error: function(error){
        alert('How did you get here?');
    }
   })
}

function welcomeUser(data){
    $("#welcome-user").append(
        'Welcome, ' + data.fName + '!')
    }


$( document ).ready(function() {     getWelcome(); });
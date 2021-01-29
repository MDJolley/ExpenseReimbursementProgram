$( document ).ready(function() {     getTickets(); });

function getTickets(){
    $.ajax({
        url: 'http://localhost:8080/ERP/me/ticket/getMyTickets',
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        success: function(data){
            console.log(data);
            populateTable (data);
        },
        error: function(error){
            alert('You must be logged in to populate this table.');
        }
    });
}

function populateTable (data) {
    //var ticketBody = document.querySelector("#my-tickets > tbody");
    for (let i = 0; i < data.length; i++){
        $("#my-tickets > tbody:last-child").append(
            "<tr><td>" + 
            data[i].reqDate +"</td><td>"+ 
            // data[i].name +"</td><td>"+
            data[i].amount +"</td><td>"+
            data[i].type +"</td><td>"+
            data[i].description +"</td><td>"+
            data[i].approved + 
            "</td></tr>"); 
    }
} 
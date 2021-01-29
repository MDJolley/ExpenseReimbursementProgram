$( document ).ready(function() {     getTickets(); });

function getTickets(){
    $.ajax({
        url: 'http://localhost:8080/ERP/me/ticket/getUnresponded',
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
        let id = data[i].id;
        $("#respond-tickets > tbody:last-child").append(
            "<tr><td>" + 
            data[i].reqDate +"</td><td>"+ 
            data[i].name +"</td><td>"+
            data[i].amount +"</td><td>"+
            data[i].type +"</td><td>"+
            data[i].description +"</td><td>"+
            '<form method="PUT" action="http://localhost:8080/ERP/me/ticket/approve">' +
            '<button class="btn btn-primary" name="id" value="'+id+'">Accept</button></form>'+"</td><td>"+
            '<form method="PUT" action="http://localhost:8080/ERP/me/ticket/deny">' +
            '<button class="btn btn-primary" name="id" value="'+id+'">Decline</button></form>'+"</td><td>"+
            "</td></tr>"); 
    }
} 
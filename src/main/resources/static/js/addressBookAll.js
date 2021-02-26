$(document).ready(function() {

    $("#showAllBooks").click(function(event){
        event.preventDefault();
        ajaxGet();
    });

    // GET REQUEST
    function ajaxGet() {
        $.ajax(
            {
                type:'GET',
                url:'/addressBooksViewAll',
                success: function(result){
                    if (result.status === "OK"){
                        $('#addressBookView ul').empty();
                        for (let bookID of result.data){
                            var addressBook = bookID.id
                            var output = ""
                            for(let data of bookID.friends){
                                output += '<span>Address Book ID:  </span>' + addressBook + "<br>"
                                output += '<span>Friend ID: </span>' + data.id + '<span>, </span>' +
                                    '<span>Name: </span>' + data.name + '<span>, </span>' +
                                    '<span>Phone Number: </span>' + data.phoneNumber + '<span>, </span>' +
                                    '<span>Home Address: </span>' + data.homeAddress + '<p> </p>';
                            }
                            $('#addressBookView .list-group').append(output)
                        }
                    }
                },
                error : function(e) {
                    console.log("ERROR: ", e);
                }
            }
        );
    }
});


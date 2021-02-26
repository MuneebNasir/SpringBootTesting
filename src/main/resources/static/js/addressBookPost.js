$( document ).ready(function() {

    // SUBMIT FORM
    $("#addressBookForm").submit(function (event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost() {

        // PREPARE FORM DATA
        var formData = {
            name: $("#name").val(),
            phoneNumber: $("#phoneNumber").val(),
            homeAddress: $("#homeAddress").val(),
            bookId: parseInt($("#bookId").val())
        }

        // DO POST
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/addressBook-addNewFriend",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function (result) {
                if (result.status === "OK") {
                    alert("Form was successfully sent!")
                }else{
                    console.log(result);
                }
            },
            error : function(e) {
                alert("Error - Form Submission Failed")
                console.log("ERROR: ", e);
            }
        });
    }
})
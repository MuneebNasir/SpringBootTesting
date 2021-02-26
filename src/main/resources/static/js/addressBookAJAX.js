$(document).ready(function() {

    $("#getAllBooks").click(function(event){
        $("#bookIDs td").remove();
        event.preventDefault();
        ajaxGet();
    });

    // GET REQUEST
    function ajaxGet() {
        $.ajax(
            {
                type:'GET',
                url:'/addressBook-all',
                success: function(result){
                    if (result.status === "OK"){
                        console.log(result.data)
                        $.each(result.data, function(index){
                            $("#bookIDs tbody").append(
                                "<tr>"
                                +"<td align=\"center\">"+result.data[index]+"</td>"
                                +"</tr>"
                            )
                        });$(document).ready(function() {

                            $("#getAllBooks").click(function(event){
                                $("#bookIDs td").remove();
                                event.preventDefault();
                                ajaxGet();
                            });

                            // GET REQUEST
                            function ajaxGet() {
                                $.ajax(
                                    {
                                        type:'GET',
                                        url:'/addressBook-all',
                                        success: function(result){
                                            if (result.status === "OK"){
                                                console.log(result.data)
                                                $.each(result.data, function(index){
                                                    $("#bookIDs tbody").append(
                                                        "<tr>"
                                                        +"<td align=\"center\">"+result.data[index]+"</td>"
                                                        +"</tr>"
                                                    )
                                                });
                                                $('#bookIDs').toggle('slow');
                                                console.log("Success: ", result);
                                            }
                                        },
                                        error : function(e) {
                                            console.log("ERROR: ", e);
                                        }
                                    }
                                );
                            }
                        });

                        $('#bookIDs').toggle('slow');
                        console.log("Success: ", result);
                    }
                },
                error : function(e) {
                    console.log("ERROR: ", e);
                }
            }
        );
    }
});

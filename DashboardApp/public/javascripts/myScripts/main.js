$(document).ready(function() {
    $('#get_name').click(function() {
        jsRoutes.controllers.ProductController.showJson().ajax({
            success: function(result) {
                var data = JSON.stringify(result)
                alert("Hello: " + data)
               // $("#name").text(result);
            },
            failure: function(err) {
                var errorText = 'There was an error';
                $("#name").text(errorText);
            }
        });
    });

    listBox($('#inputExample5'));
});





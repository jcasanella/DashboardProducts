var helpers = {
    buildDropDown: function(result, dropdown, emptyMessage) {
        // Clean listBox
        dropdown.html('');

        // Add default msg
        dropdown.append('<option value="">' + emptyMessage + '</option>');

        // check result is not empty
        if (result != '') {
            $.each(result, function(k, v) {
                dropdown.append('<option value="' + v.id + '">' + v.name + '</option>');
            });
        }
    }
}

function listBox(idList) {
    jsRoutes.controllers.ProductController.showJson().ajax({
        success: function(result) {

            var data = JSON.stringify(result)
            helpers.buildDropDown(
                jQuery.parseJSON(data),
                idList,
                'Select an option'
            );
        },
        failure: function(err) {
            var errorText = 'There was an error - listBox';
            $("#name").text(errorText)
        }
    });
}
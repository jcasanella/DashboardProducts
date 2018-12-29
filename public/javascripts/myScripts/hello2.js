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
});

$(document).ready(function() {
  var slider = document.getElementById('slider');
  noUiSlider.create(slider, {
   start: [20, 80],
   connect: true,
   step: 1,
   range: {
     'min': 0,
     'max': 100
   },
   format: wNumb({
     decimals: 0
   })
  });
});


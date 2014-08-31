$('.datepicker').datepicker({
     dateFormat: 'dd.mm.yy'
});

                                //switcher tabs of table with orders
 $('#myTab a[href="#Today"]').tab('show') // Select tab by name
 $('#myTab a:first').tab('show') // Select first tab

                //change class the clicked row (active or warning)
 $(document).ready(function(){
     $('input[type="checkbox"]').mousedown(function () {
         ind = $(this).attr("value")-3;
         $("tr:eq("+ind+")").addClass('success');
     }).mouseup(function () {
         $("tr:eq("+ind+")").removeClass('success');
         $("tr:eq("+ind+")").toggleClass('warning');
     });
 });



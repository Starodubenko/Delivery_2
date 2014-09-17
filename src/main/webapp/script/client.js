$('#Date').datepicker({
    format: 'mm.dd.yyyy',
    startDate: '-3d'
});
//change class the clicked row (active or warning)
$(document).ready(function () {
    $('#Today').children('input[type="checkbox"]').mousedown(function () {
        ind = $(this).parents('tr').get(0).rowIndex;
        $("tr:eq(" + ind + ")").addClass('bg-success');
    }).mouseup(function () {
        $("tr:eq(" + ind + ")").removeClass('bg-success');
        $("tr:eq(" + ind + ")").toggleClass('bg-info');
    });
});



$('#Date').datepicker({
    format: "dd.mm.yyyy",
//    daysOfWeekDisabled: "0,6",
//    todayHighlight: true,
    startDate: '+0d'
});

$('#create').click(function () {

    var deliverydate = $('#Date').val();
    var deliverytime = $('#PeriodTime').val();
    var goodsname = $('#GoodsName').val();
    var goodscount = $('#Count').val();
    var additionalinformation = $('#AdditionalInformation').val();
    var paymenttype = $(':radio[name="PaymentType"]').val();

    alert(paymenttype);

    $.get("fastCreateOrder", {deliverydate: deliverydate, deliverytime: deliverytime,
            goodsname: goodsname, goodscount: goodscount, additionalinformation: additionalinformation, paymenttype: paymenttype},
        function (data) {
            $('#errorCreatingOrder').html(data.errorMessage);
        });
});

$(document).ready(function () {

    $('#cBack').click(function () {
        var page = $('#clientsPageNumber').val() - 1;

        $('.cNumbered').removeClass("active");
        $("li.cNumbered[value=" + page + "]").addClass("active");
        $.get("ajaxChangeClientsPage",
            {
                clientspage: page
            },
            function (data) {
                $('#clientsTable').html(data);
            })
    });

    $('li[class="cNumbered " ]').click(function () {
        $('.cNumbered').removeClass("active");
        $(this).addClass("active");

        var page = $(this).attr('value');
        $.get("ajaxChangeClientsPage",
            {
                clientspage: page
            },
            function (data) {
                $('#clientsTable').html(data);
            })
    });


    $('#cNext').click(function () {
        var page = $('#clientsPageNumber').val() - 1 + 2;

        $('.cNumbered').removeClass("active");
        $("li.cNumbered[value=" + page + "]").addClass("active");
        $.get("ajaxChangeClientsPage",
            {
                clientspage: page
            },
            function (data) {
                $('#clientsTable').html(data);
            })
    });

    $('#oBack').click(function () {
        var page = $('#ordersPageNumber').val() - 1;

        $('.oNumbered').removeClass("active");
        $("li.oNumbered[value=" + page + "]").addClass("active");
        $.get("ajaxChangeOrdersPage",
            {
                orderspage: page
            },
            function (data) {
                $('#ordersTable').html(data);
            })
    });

    $('li[class="oNumbered "]').click(function () {
        $('.oNumbered').removeClass("active");
        $(this).addClass("active");

        var page = $(this).attr('value');
        $.get("ajaxChangeOrdersPage",
            {
                orderspage: page
            },
            function (data) {
                $('#ordersTable').html(data);
            })
    });

    $('#oNext').click(function () {
        var page = $('#ordersPageNumber').val() - 1 + 2;

        $('.oNumbered').removeClass("active");
        $("li.oNumbered[value=" + page + "]").addClass("active");
        $.get("ajaxChangeOrdersPage",
            {
                orderspage: page
            },
            function (data) {
                $('#ordersTable').html(data);
            })
    });

    var OrderID = $('table#clientsTable').on('click', 'button', function () {
        var rowIndex = $(this).parents('tr').get(0).rowIndex;
        var val = $("table:eq(0) tr:eq(" + rowIndex + ") td:eq(0)").html();
        alert(val);
        return val;
    });

    $('#check').click(function () {

        alert(OrderID);

        var deliverydate = $('#Date').val();
        var deliverytime = $('#PeriodTime').val();
        var goodsname = $('#GoodsName').val();
        var goodscount = $('#Count').val();
        var additionalinformation = $('#AdditionalInformation').val();
        var paymenttype = $('PaymentType').val();

        $.get("fastCreateOrder", {id: ID, deliverydate: deliverydate, deliverytime: deliverytime,
                goodsname: goodsname, goodscount: goodscount, additionalinformation: additionalinformation, paymenttype: paymenttype},
            function (data) {
                $('#errorCreatingOrder').html(data.errorMessage);
            });
    });

    $('#cancel').click(function () {
        var stringValues = $('input:checked[name="IdOrder"]').map(function () {
            return $(this).val();
        }).get();
        stringValues = stringValues.toString();

        $.get("cancel", {stringIdOrders: stringValues},
            function (data) {
                $('#errorCreatingOrder').html(data.errorMessage);
            });
    });

    $('#accept').click(function () {
        var stringValues = $('input:checked[name="IdOrder"]').map(function () {
            return $(this).val();
        }).get();
        stringValues = stringValues.toString();

        $.get("accept", {stringIdOrders: stringValues},
            function (data) {
                $('#errorCreatingOrder').html(data.errorMessage);
            });
    });

    $('#restore').click(function () {
        var stringValues = $('input:checked[name="IdOrder"]').map(function () {
            return $(this).val();
        }).get();
        stringValues = stringValues.toString();

        $.get("restore", {stringIdOrders: stringValues},
            function (data) {
                $('#errorCreatingOrder').html(data.errorMessage);
            });
    });

    $('#switchStatusOrser').change(function () {
        var e = document.getElementById("switchStatusOrser");
        var status = e.options[e.selectedIndex].value;
        console.log(status);

        $.get("selectStatus", {status: status},
            function (data) {
                $('#Orders').html(data);
            });
    });

});


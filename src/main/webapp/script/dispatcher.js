$(document).ready(function () {
    $('#cBack').click(function () {
        var page = $('#clientsPageNumber').val() - 1;
        $.get("ajaxChangeClientsPage",
            {
                clientspage: page
            },
            function (data) {
                $('#clientsTable').html(data);
            })
    });

    $('li[class="cNumbered"]').click(function () {
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
        $.get("ajaxChangeOrdersPage",
            {
                orderspage: page
            },
            function (data) {
                $('#ordersTable').html(data);
            })
    });

    $('li[class="oNumbered"]').click(function () {
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
        $.get("ajaxChangeOrdersPage",
            {
                orderspage: page
            },
            function (data) {
                $('#ordersTable').html(data);
            })
    });

    $.fn.toggleCheckbox = function () {
        this.attr('checked', !this.attr('checked'));
    };

    $('#checkAll').find(':checkbox').toggleCheckbox();
});


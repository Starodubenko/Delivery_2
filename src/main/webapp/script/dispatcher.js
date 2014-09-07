$(document).ready(function(){
    $('li[id="t1"]').click(function () {
//        $.session("table","clients");
        document.set.session("table","clients");
    })

    $('li[id="t2"]').click(function () {
//        $.session("table","orders");
        document.set.session("table","orders");

    })

    alert($.session("table"));
//    if ($(document).getAttribute("table") == "clients"){
//        $('div[id="Clients"]').addClass('active');
//        $('div[id="Orders"]').removeClass('active');
//    }
//
//    if ($(document).getAttribute("table") == "orders"){
//        $('div[id="Clients"]').removeClass('active');
//        $('div[id="Orders"]').addClass('active');
//    }


});


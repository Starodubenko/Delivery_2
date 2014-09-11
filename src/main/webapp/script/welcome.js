$(document).ready(function () {
    $('#registration').click(function () {
        var login = $('#inputLogin').val();
        var password = $('#inputPassword').val();

        $.post("ajaxLogin", {authenticationLogin: login, authenticationPassword: password}, function (data) {
            if (data.errorView == "") location.href = data.roleView;
            else $('#errorRegistration').html(data.errorView);
        })
    });

    $('#Gotoclientpage').click(function () {
        var login = $('#inputLogin').val();
        var password = $('#inputPassword').val();

        $.post("ajaxLogin", {authenticationLogin: login, authenticationPassword: password}, function (data) {
            console.log("ok");
            console.log(data);
            console.log(data.Json);
//            if (data.json.errorView == "") location.href = data.json.roleView;
//            else $('#errorRegistration').html(data.json.errorView);
        })
    })
});
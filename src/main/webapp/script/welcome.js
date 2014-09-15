$(document).ready(function () {
    $('#goLogin').click(function () {
        var login = $('#inputLogin').val();
        var password = $('#inputPassword').val();

        $.post("ajaxLogin", {authenticationLogin: login, authenticationPassword: password},
            function (data) {
                console.log(data.roleView);
                console.log(data.errorMessage);
                if (data.errorMessage == null) location.href = data.roleView;
                else $('#errorLogin').html(data.errorMessage);
            });
//        location.href = data.roleView;
//    }).fail(function(data){
//        $('#errorLogin').html(data.errorMessage);
//    })
    });

    $('#goRegistration').click(function () {
        var login = $('#Login').val();
        var password = $('#Password').val();
        var firstname = $('#Firstname').val();
        var lastname = $('#Lastname').val();
        var middlename = $('#Middlename').val();
        var address = $('#Address').val();
        var telephone = $('#Telephone').val();
        var mobilephone = $('#Mobilephone').val();

        $('div[class="registration"]').children('div').removeClass("has-error");
        $('div[class="registration"]').children('div').children('p').html("");

        $.post("ajaxClientRegistration", {login: login,
                password: password,
                firstname: firstname,
                lastname: lastname,
                middlename: middlename,
                address: address,
                telephone: telephone,
                mobilephone: mobilephone},
            function (data) {
                console.log(data);
                if (data.registrationSuccessful != null) $('#registrationSuccessful').html(data.registrationSuccessful);
                else {
                    for (var obj in data) {
                        console.log(obj);
                    }
                    $('#loginInput').html(data.loginNotValid);
                    if (data.loginNotValid != null) $('#loginDiv').addClass("has-error");

                    $('#passwordInput').html(data.passwordNotValid);
                    if (data.passwordNotValid != null) $('#passwordDiv').addClass("has-error");

                    $('#firstnameInput').html(data.firstnameNotValid);
                    if (data.firstnameNotValid != null) $('#firstnameDiv').addClass("has-error");

                    $('#lastnameInput').html(data.lastnameNotValid);
                    if (data.lastnameNotValid != null) $('#lastnameDiv').addClass("has-error");

                    $('#middlenameInput').html(data.middlenameNotValid);
                    if (data.middlenameNotValid != null) $('#middlenameDiv').addClass("has-error");

                    $('#addressInput').html(data.addressNotValid);
                    if (data.addressNotValid != null) $('#addressDiv').addClass("has-error");

                    $('#telephoneInput').html(data.telephoneNotValid);
                    if (data.telephoneNotValid != null) $('#telephoneDiv').addClass("has-error");

                    $('#mobilephoneInput').html(data.mobilephoneNotValid);
                    if (data.mobilephoneNotValid != null) $('#mobilephoneDiv').addClass("has-error");
                }
            })
    });


});
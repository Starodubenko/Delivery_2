$('#goLogin').click(function () {

    $.post("ajaxLogin", $('#loginform').serialize(),
        function (data) {
            if (data.errorMessage == null) location.href = data.roleView;
            else $('#errorLogin').html(data.errorMessage);
        })
});

$('#loginform').keydown(function () {

    if (event.keyCode == 13) {
        $.post("ajaxLogin", $('#loginform').serialize(),
            function (data) {
                if (data.errorMessage == null) location.href = data.roleView;
                else $('#errorLogin').html(data.errorMessage);
            })
    }
});

$('#goRegistration').click(function () {

    $('div[class="registration"]').children('div').removeClass("has-error");
    $('div[class="registration"]').children('div').children('p').html("");

    $.post("ajaxClientRegistration", $('#regForm').serialize(),
        function (data) {
            $('.modal-footer.reg').html(data);
        }).fail(function () {
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
        })
});

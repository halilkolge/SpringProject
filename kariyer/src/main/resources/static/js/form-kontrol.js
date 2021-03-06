var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
    var ulke = document.getElementById(ulke);
};

$(document).ready(function () {

    var checked ;

    $("#FormSave").validate({

        submitHandler: function (form) {
            $('#loading-screen').fadeIn();
            console.log(checked);
             var id = getUrlParameter('id');
            if(document.getElementById("inline-radio-primary").checked){
                checked=2;
            }else if(document.getElementById("inline-radio-success").checked) {
                checked=1;
            }
            if(id==null){
                var formData = {
                    "name": $("#ad").val(),
                    "surname": $("#soyad").val(),
                    "email": $("#email").val(),
                    "username": $("#username").val(),
                    "password": $("#password").val(),
                    "roles" : checked,

                }

            }
            console.log(formData);
           $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/uyelik/save",
                data: JSON.stringify(formData),
                dataType: 'json',
                cache: false,
                timeout: 60000,

                success: function (data) {
                    $('#loading-screen').fadeOut('slow');
                    if(data.result){
                        console.log(data);
                        success_noti_custom(data.message);
                        setTimeout(function() {
                            window.location.replace("/login");
                        }, 2000);
                    }
                    else{
                        error_noti_yuk(data.message);
                        console.log(data.message+" sdfasdf")
                    }
                },
                error: function (e) {
                    console.log("ERROR : ", e);
                    $("#btn-login").prop("disabled", false);
                }
            });
            return false;
        }
    });





});
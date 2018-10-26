/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    $('#apply_form').validate({
        errorElement: 'label',
        focusCleanup: true,
        errorClass: 'login-error',
        focusInvalid: false,
        ignore: "",
        rules: {
            selProvName: {
                required: true
            },
            selCityName: {
                required: true
            },
            txtCode: {
                required: true
            }
        },
        messages: {
            selProvName: {
                required: "请选择所在省份"
            },
            selCityName: {
                required: "请选择所在城市"
            },
            txtCode: {
                required: "请输入代理商代码"
            }

        },
        invalidHandler: function(event, validator) {
        },
        highlight: function(element, errorClass, validClass) {
            $(element).addClass('has-error');
        },
        success: function(label) {
            label.removeClass('has-error');
            label.remove();
        },
        errorPlacement: function(error, element) {
            error.insertAfter(element);
            $(error).css("width", element[0].clientWidth - 3);
            $(error).css("left", "5px");
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
    $("#butApply").click(function() {
        var isOK = $("#apply_form").valid();
        if (isOK) {
            addApply();
        }
    });
    document.onkeydown = function(e) {
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            var isOK = $("#butApply").valid();
            if (isOK) {
                startlogin();
            }
        }
    };
});

function addApply() {
    var emp = new Object();
    emp.provName = $("#selProvName").val();
    emp.cityName = $("#selCityName").val();
    emp.merchantCode = $("#txtCode").val();
    $.ajax({
        type: "post",
        url: "/Mobile/Cps/addApply",
        data: {'Parameter': emp},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {

            $("#butApply").attr('disabled', "true");
        },
        success: function(context, textStatus) {

            $("#butApply").removeAttr("disabled");
            if (context.status == 1) {

                popMsg(context.msg, 2, function() {
                    popPage({type: 4, url: window.location.href, title: ""});
                });
            } else if (context.status == 2) {

                popMsg(context.msg, 2, function() {
                    url = '/Mobile/User/login/returnurl/L1dlY2hhdC9DcHMvcHVibGljaXR5';
                    gotoPage(url);
                });
            } else {
                popMsg(context.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {

            $("#butApply").removeAttr("disabled");
        },
        error: function() {
            $("#butApply").removeAttr("disabled");

        }
    });
}




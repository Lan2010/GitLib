$(function(){
    var	$form = $('#form'),
        $btnUpdate = $("#btnUpdate"),
        url = "message/setting/",
        urlUpdateInfo = url + "update";

    var validatorForm = $form.validate({
        rules : {
            "mobile" : {
                required: $('#form input[name="isSendMobile"]:checked').val(),
                maxlength:64
            },
            "email" : {
                required: $('#form input[name="isSendMail"]:checked').val(),
                maxlength:32
            }
        },
        messages : {
            "mobile" : {
                required: '请输入接收手机号',
                maxlength:'请不要超过32个字符'
            },
            "email" : {
                required: '请输入接收邮箱号',
                maxlength:'请不要超过32个字符'
            }
        }
    });

    $btnUpdate.on("click", function(){
        var _data = utils.getFormJsonString($form), _url = urlUpdateInfo;
        (_url);
        validatorForm.form() && ajaxUtil.ajax(_url, _data, "POST", function(resp) {
            if (resp.code === 200) {
                layer.msg(resp.msg || "操作成功！", {icon: 6});
            } else {
                layer.msg(resp.msg || "操作失败！", {icon: 5});
            }
        }, null, null, {btn : $btnUpdate});
    });
});
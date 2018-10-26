<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Expires" CONTENT="0">
    <meta http-equiv="Cache-Control" CONTENT="no-cache">
    <meta http-equiv="Pragma" CONTENT="no-cache">
    <title>运营管理系统</title>
    <link type="image/x-icon" rel="shortcut icon" href="/statics/images/favicon.ico">
    <link href="/statics/css/lib/bootstrap/bootstrap.min.css" rel="stylesheet"/>
    <link href="/statics/css/style.css" rel="stylesheet"/>
    <link href="/statics/css/login.css" rel="stylesheet"/>
</head>
<body class="gray-bg">
<div class="login">
    <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div class="ibox-content">
                <form class="m-t">
                    <div id="error_div" class="form-group">
                        <span class="help-block m-b-none error"><i class="fa fa-times-circle"></i> <span
                                id="error_text"></span> </span>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control input-lg" id="username" autofocus
                               value="" placeholder="请输入用户名" required=""/>
                    </div>

                    <div class="form-group">
                        <input type="password" class="form-control input-lg" id="password" placeholder="请输入密码"
                               required=""/>
                    </div>
                    <button type="submit" id="btnLogin" class="btn btn-lg btn-danger block full-width m-b">立 即 登 录
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="statics/javascripts/config.js?v1.0.0"></script>
<script src="/statics/javascripts/lib/jquery/jquery.min.js"></script>
<script src="/statics/javascripts/lib/bootstrap/bootstrap.min.js"></script>
<script type="application/javascript">
    $(function () {
        var $errorText = $("#error_text"),
                $username = $("#username"),
                $password = $("#password"),
                $btnLogin = $("#btnLogin");

        $btnLogin.click(function () {
            var _username = $username.val(), _password = $password.val();
            if (!_username) {
                $errorText.text("请输入用户名！");
                $username.focus();
            } else if (!_password) {
                $errorText.text("请输入密码！");
                $password.focus();
            } else {
                $btnLogin.prop("disabled", true);
                $.get(config.api.default + "loginDo", {
                    username: _username,
                    password: _password,
                }, function (resp) {
                    if (resp.code === 200) {
                            config.setCookie(config.token, resp.token, {
                                path: "/",
                                domain: "/"
                            });

                        config.route("index");
                    } else {
                        $errorText.text(resp.msg);
                    }
                }, "json").always(function () {
                    $btnLogin.prop("disabled", false);
                });
            }
        });

        $(window).keydown(function (event) {
            event = event || window.event;
            event.keyCode === 13 && $btnLogin.click();
        });
    });
</script>
</body>
</html>

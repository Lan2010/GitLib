qhz = qhz || {};
qhz.qrLoginStatus = !1;
qhz.qrTimer = null;
qhz.qrInterval = null;
qhz.showQrLogin = false;
qhz.isimplement=false;
qhz.qrLogin = function () {
    this.showQrLogin = true;
    this.qrLoginStatus = !1;
    this.isimplement=true;
    clearInterval(this.qrInterval);
    var scaning = $("#codeLogin div.scanCode");
    var scansuc = $("#codeLogin div.scanSuc");
    var scanfail = $("#codeLogin div.scanFail");
    var qrImgs = $("#codeLogin .scanCode img[class='ewmpic']");
    var init = function () {
        var url = '/Common/showQrCode.html?size=10&t=' + (new Date).getTime();
        qrImgs[0].src = url;
        qrImgs[0].onload = function () {
            scansuc.hide();
            scanfail.hide();
            scaning.show();
            qrImgs.show();
            qhz.qrLoginStatus = !0;
        };
        qrImgs[0].onerror = function () {
            scansuc.hide();
            qrImgs.hide();
            scaning.show();
            scanfail.show();
            qhz.qrLoginStatus = !1;
        };
    };
    var check = function () {
        var url = "";
        if ($('#returnurl').length > 0) {
            url = $("#returnurl").val();
        }
        $.ajax({
            url: '/Common/checkQrCode.html?url=' + url,
            dataType: "json",
            success: function (data) {
                if (data) {
                    var code = data.status;
                    switch (l = !1, code) {
                        case 200:
                            qhz.qrLoginStatus = !1;
                            qhz.loginsuccess(data.data);
                            break;
                        case 201:
                            scaning.show();
                            scansuc.hide();
                            break;
                        case 202:
                            scaning.hide();
                            scansuc.show();
                            break;
                        case 203:
                            scansuc.hide();
                            scaning.show();
                            scanfail.show();
                            qhz.qrLoginStatus = !1;
                            break;
                        case 204:
                            scansuc.hide();
                            scaning.show();
                            scanfail.show();
                            qhz.qrLoginStatus = !1;
                            layer.msg(data.msg || "登录失败");
                            break;
                        default:
                            qhz.qrLoginStatus = !1;
                            break;
                    }
                }
            }
        });
    };
    init();
    qhz.qrInterval = setInterval(function () {
        qhz.qrLoginStatus && check();
    }, 2e3);
    $("#qrover").click(function () {
        clearTimeout(qhz.qrTimer);
        qhz.qrTimer = setTimeout(function () {
            init();
        }, 500);
    });
};
$(function () {
    $("#accLogic").click(function () {
        qhz.showQrLogin = false;
        qhz.qrLoginStatus == !1;
        qhz.isimplement=false;
        clearInterval(qhz.qrInterval);
    });

    $("#qrCodeLogin").click(function () {
        !qhz.showQrLogin && qhz.qrLogin();
    });
    $(".dxswitch_yzm").click(function(){
          !qhz.showQrLogin && !qhz.isimplement && qhz.qrLogin();
    });
});

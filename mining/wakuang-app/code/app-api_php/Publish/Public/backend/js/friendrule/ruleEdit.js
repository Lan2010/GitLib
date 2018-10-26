/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function() {
    $(".butReturn").click(function() {
        $Util.returnRefresh();
    });
    rulevalid();
    $(".butSaveRule").click(function() {
        var isOK = $("#form-Rule").valid();
        if (isOK) {
            saveRule();
        }
    });
});
function saveRule() {
    var data = {};
    data.ruleName = $("#ruleName").val();

    data.effMoney = $("#effMoney").val();
    data.inviteTender = $("#inviteTender").val();
    data.ruleID = $("#ruleID").val();
    data.startpeople = $("input[name='startpeople']").serializeArray();
    data.endpeople = $("input[name='endpeople']").serializeArray();
    data.efftalpeople = $("input[name='efftalpeople']").serializeArray();
    data.efftalmoney = $("input[name='efftalmoney']").serializeArray();
    $.ajax({
        type: "post",
        url: "/Backend/FriendActive/saveRule",
        data: {'par': data},
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {
            $(".sub-content").showLoading();
        },
        success: function(data, textStatus) {
            $(".sub-content").hideLoading();
            if (data.status == 1) {
                $("#hidAction").val("edit");
                $win.confirm(data.msg + ",是否关闭窗口？").on(function() {
                    $('.butReturn').trigger("click");
                });
            } else {
                $win.warn(data.msg);
            }
        },
        complete: function(XMLHttpRequest, textStatus) {
            $(".sub-content").hideLoading();
        },
        error: function() {
            $(".sub-content").hideLoading();
        }
    });
}

/**
 * 新增子规则
 * @param {type} type
 */
function addCondition(type) {

    if (type == "singlePeople") {
        var singleInfo = "";
        singleInfo += '<label class="col-xs-2 control-label" for="inputInfo"></label>'
                + '<div class="col-xs-10 form-inline">'
                + '            有效用户人数：&nbsp;&nbsp;&nbsp; '
                + '<input type="text"  maxlength="8" class="form-control" value="" name="startpeople" style="width:170px;"'
                + ' onkeyup="this.value = this.value.replace(/\\D/g, \'\') "  onafterpaste="this.value = this.value.replace(/\\D/g, \'\') "> '
                + '至 '
                + '<input type="text"  maxlength="8" class="form-control" value="" name="endpeople" style="width:170px;" '
                + ' onkeyup="this.value = this.value.replace(/\\D/g, \'\') "  onafterpaste="this.value = this.value.replace(/\\D/g, \'\') "> '
                + ' <a class="btn btn-warning  delete" onclick="delCondition(this);" ><span class="icon-remove"></span>删除</a>'
                + '</div>';
        $("#single div").last().after(singleInfo);
    }
    if (type == "totalPeople") {
        var totplpInfo = "";
        totplpInfo += ' <label class="col-xs-2 control-label" for="inputInfo"></label>'
                + '<div class="col-xs-9 form-inline">'
                + ' 有效用户人数达到：&nbsp;&nbsp;&nbsp; '
                + '<input type="text" class="form-control"  maxlength="8" value="" name="efftalpeople"  style="width:170px;" '
                + ' onkeyup="this.value = this.value.replace(/\\D/g, \'\') "  onafterpaste="this.value = this.value.replace(/\\D/g, \'\') "> '
                + ' <a class="btn btn-warning  delete" onclick="delCondition(this);" ><span class="icon-remove"></span>删除</a>'
                + '</div>';
        $("#totalplp div").last().after(totplpInfo);
    }
    if (type == "totalMoney") {
        var totmoyInfo = "";
        totmoyInfo += ' <label class="col-xs-2 control-label" for="inputInfo"></label>'
                + '<div class="col-xs-9 form-inline">'
                + ' 有效出借额度达到：&nbsp;&nbsp;&nbsp; '
                + '<input type="text" class="form-control"  maxlength="11" value="" name="efftalmoney"  style="width:170px;" '
                + ' onkeyup="this.value = this.value.replace(/\\D/g, \'\') "  onafterpaste="this.value = this.value.replace(/\\D/g, \'\') "> '
                + '<a class="btn btn-warning  delete" onclick="delCondition(this);" ><span class="icon-remove"></span>删除</a>'
                + '</div>';
        $("#totmoyInfo div").last().after(totmoyInfo);
    }
}
/**
 *删除子规则 
 * @param {type} obj
 */
function delCondition(obj) {
    $(obj).parent().prev().remove();
    $(obj).parent().remove();
}

function rulevalid() {
    $("#form-Rule").validate({
        rules: {
            ruleName: {required: true, maxlength: 20},
            effMoney: {required: true}
        },
        messages: {
            ruleName: {required: "规则名称不能为空!"},
            effMoney: {required: "有效注册投资额不能为空"}
        }
    })
}



<?php if (!defined('THINK_PATH')) exit();?><div class="modal-body">
    <input  type="hidden" name="hidAction" id="hidAction" value="view" >
    <form class="form-horizontal" id="form-User">       
        <input type="hidden" name='user_id' id='userID' value="<?php echo ($data["user_id"]); ?>"/>
        <div class="form-group has-info">
            <label class="control-label col-sm-2" for="inputInfo">用户名：</label>
            <div class=" col-sm-6">
                <input type="text" id="userName" name="user_name" value="<?php echo ($data["user_name"]); ?>" class="form-control"  >
            </div>
        </div>
        <div class="form-group has-info">
            <label class="control-label col-sm-2" for="inputInfo">姓名：</label>
            <div class="col-sm-6">
                <input type="text" id="realName" name="real_name" value="<?php echo ($data["real_name"]); ?>" class="form-control">
            </div>
        </div>
        <div class="form-group has-info">
            <label class="control-label col-sm-2" for="inputInfo">电话：</label>
            <div class="col-sm-6">
                <input type="text" id="phone" name="phone" value="<?php echo ($data["phone"]); ?>" class="form-control">          
            </div>
        </div>
        <div class="form-group has-info">
            <label class="control-label col-sm-2" for="inputInfo">用户类型：</label>
            <div class="col-sm-6">
                <select name="user_type" id="userType" >
                    <option  <?php if(($data["user_type"]) == "1"): ?>selected<?php endif; ?> value="1">后台用户</option>
                    <option  <?php if(($data["user_type"]) == "2"): ?>selected<?php endif; ?>  value="2">管理员</option>
                    <option  <?php if(($data["user_type"]) == "7"): ?>selected<?php endif; ?>  value="7">客服主管</option>
                    <option  <?php if(($data["user_type"]) == "8"): ?>selected<?php endif; ?>  value="8">客服</option>
                </select>
            </div>
        </div>
        <div class="form-group has-info" id="objective" >
            <label class="control-label col-sm-2" for="inputInfo">客服：</label>
            <div class="col-sm-6">
                <input type="text" id="cusName" name="cus_name" value="<?php echo ($data["cus_name"]); ?>" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="inputInfo">备注：</label>
            <div class="col-sm-6">
                <input type="text" id="remark" name="remark" value="<?php echo ($data["remark"]); ?>" class="form-control">          
            </div>
        </div>
    </form>
</div>
<div class="modal-footer">
    <button type="button" id="butCancel" class="btn btn-default" data-dismiss="modal">关闭</button>
    <button type="button" id="butSaveuser" class="btn btn-primary" >保存</button>
</div>  
<script type="text/javascript" >
    $(function($) {
        if ($("#userType").val() != "8") {
            $("#objective").hide();
        }
        uservalid();
        $("#butSaveuser").click(function() {
            var isOK = $("#form-User").valid();
            if (isOK) {
                saveDic();
            }
        });
    });
    $("#userType").change(function() {
        if ($(this).val() == "8") {
            $("#objective").show();
        } else {
            $("#objective").hide();
        }

    });
    function saveDic() {
        var data = $Util.getData($("#form-User"));
        $.ajax({
            type: "post",
            url: "/Backend/Backend/saveUser",
            data: {'par': data},
            dataType: "json",
            beforeSend: function(XMLHttpRequest) {
                $(".sub-content").showLoading();
            },
            success: function(data, textStatus) {
                $(".sub-content").hideLoading();
                if (data.status == 1) {
                     $("#userID").val(data.data);
                    $("#hidAction").val("edit");
                    $win.confirm(data.msg + ",是否关闭窗口？").on(function() {
                        $('#butCancel').trigger("click");
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
    function  uservalid() {
        $("#form-User").validate({
            rules: {
                userName: {required: true},
                realName: {required: true},
                phone: {required: true},
                userType: {required: true},
                cusName: {required: true}
            },
            messages: {
                userName: {required: "用户名不能为空!"},
                realName: {required: "用户姓名不能为空!"},
                phone: {required: "电话不能为空!"},
                userType: {required: "用户类型不能为空!"},
                cusName: {required: "客服昵称不能为空!"}
            }
        });
    }
</script>
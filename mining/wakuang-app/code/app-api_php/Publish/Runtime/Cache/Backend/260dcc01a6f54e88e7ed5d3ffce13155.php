<?php if (!defined('THINK_PATH')) exit();?><div id="sub-borrow-edit">
    <script type="text/javascript" src="<?php echo (BPATH); ?>js/validate.extend.js?version=<?php echo (BNO); ?>" ></script>     
    <h4 class="title">APP版本</h4>
    <form class="form-horizontal" id="form-appver">      
        <input  type="hidden" name="hid_action" id="hidAction" value="view" >
        <input  type="hidden" name="ver_id" id="verID" value="<?php echo ($data["ver_id"]); ?>" >
        <input  type="hidden" name="hid_terminal" id="hidterminal" value="<?php echo ($data["terminal"]); ?>" >
        <ul id="myTab" class="nav nav-tabs">
            <li class="active"><a href="#home" data-toggle="tab">基本信息</a></li>
        </ul>
        <div id="myTabContent" class="tab-content form-item">
            <div class="tab-pane fade in active" id="home">
                <div class="row">
                    <div class="span4 has-info">  
                        <label class="col-sm-2 control-label" for="inputInfo">版本名称：</label>
                        <div class="col-sm-8">
                            <input type="text" id="versionName"  maxlength="30" name="version_name" value="<?php echo ($data["version_name"]); ?>" class="form-control" >
                        </div>
                    </div>
                    <div class="span4 has-info">
                        <label class="col-sm-2 control-label" for="inputInfo">版本号：</label>
                        <div class="col-sm-8">
                            <input type="text" id="versionCode"  maxlength="30" name="version_code" value="<?php echo ($data["version_code"]); ?>" class="form-control" >
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="span4 has-info">  
                        <label class="col-sm-2 control-label" for="inputInfo">终端类型：</label>
                        <div class="col-sm-8">
                            <select  name="terminal" id="terminal" class="form-control">
                                <option  value ="">--请选择--</option>
                                <option  <?php if(($data["terminal"]) == "2"): ?>selected<?php endif; ?>  value ="2">安卓</option>
                                <option  <?php if(($data["terminal"]) == "3"): ?>selected<?php endif; ?>   value ="3">IOS</option>
                            </select>    
                        </div>
                    </div>
                    <div class="span4 has-info">
                        <label class="col-sm-2 control-label" for="inputInfo">APP大小：</label>
                        <div class="col-sm-8 ">
                            <div class="input-group">
                                <input type="text" id="appSize" name="app_size" maxlength="30"  value="<?php echo ($data["app_size"]); ?>" class="form-control" >
                                <div class="input-group-addon">兆</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" id="hidUserDiv">              
                    <div class="span4 has-info">
                        <label class="col-sm-2 control-label" for="inputInfo">是否强制更新：</label>
                        <div class="col-sm-8">
                            <select  name="is_forced" id="isForced" class="form-control">
                                <option  value ="">--请选择--</option>
                                <option   <?php if(($data["is_forced"]) == "0"): ?>selected<?php endif; ?>  value ="0">不强制更新</option>
                                <option  <?php if(($data["is_forced"]) == "1"): ?>selected<?php endif; ?>   value ="1">强制更新</option>
                            </select> 
                        </div>
                    </div>

                    <div class="span4 has-info checkCode">  
                        <label class="col-sm-2 control-label" for="inputInfo">校验码：</label>
                        <div class="col-sm-8">
                            <input type="text" id="checkCode"  name="check_code" value="<?php echo ($data["check_code"]); ?>" class="form-control" >
                        </div>                 
                    </div>

                </div>
                <div class="row updateUrl">
                    <div class="span4 has-info">  
                        <label class="col-sm-2 control-label" for="inputInfo">更新地址：</label>
                        <div class="col-sm-8">
                            <input type="text" id="updateUrl"  name="update_url" value="<?php echo ($data["update_url"]); ?>" class="form-control" >
                        </div>
                    </div>
                </div>         

                <div class="row">
                    <div class="span4 has-info">  
                        <label class="col-sm-2 control-label" for="inputInfo">更新说明：</label>
                        <div class="col-sm-8">
                            <textarea name="update_desc" clos="40" style="margin-left: 0px; margin-right: 0px; width: 302px;"   rows="8"><?php echo ($data["update_desc"]); ?></textarea>
                        </div>
                    </div>
                </div>     
            </div>  
            <div class="row">
                <div class="col-sm-2"> </div> 
                <div class="col-sm-8">    
                    <a class="btn btn-primary mt_10 butSavever" href="javascript:;">保存</a>  
                    <a class="btn btn-info mt_10 butReturn" href="javascript:;">返回</a> 
                </div>
            </div>     
        </div>
    </form>
    <script type="text/javascript" >
        $(function() {
            $(".butReturn").click(function() {
                $Util.returnRefresh();
            });
            $(".updateUrl").hide();
            $(".checkCode").hide();
            var hidterminal = $("#hidterminal").val();
            if (hidterminal == 2) {
                $(".updateUrl").show();
                $(".checkCode").show();
            }
            appverValid();
            $(".butSavever").click(function() {
                var isOK = $("#form-appver").valid();
                if (isOK) {
                    savever();
                }
            });
            $("#terminal").change(function() {
                var terVal = $(this).val();
                if (terVal == 2) {
                    $(".updateUrl").show();
                    $(".checkCode").show();
                }
                if (terVal == 3) {
                    $(".updateUrl").hide();
                    $(".checkCode").hide();
                }
            });
        });
        function savever() {
            var data = $Util.getData($("#form-appver"));
            $.ajax({
                type: "post",
                url: "/Backend/Spread/saveAppVer",
                data: {'par': data},
                dataType: "json",
                beforeSend: function() {
                    $(".sub-content").showLoading();
                },
                success: function(data) {
                    $(".sub-content").hideLoading();
                    if (data.status == 1) {
                        $("#shareID").val(data.data);
                        $("#hidAction").val("edit");
                        $win.confirm(data.msg + ",是否关闭窗口？").on(function() {
                            $('.butReturn').trigger("click");
                        });
                    } else {
                        $win.warn(data.msg);
                    }
                },
                complete: function() {
                    $(".sub-content").hideLoading();
                },
                error: function() {
                    $(".sub-content").hideLoading();
                }
            });
        }
        function appverValid() {
            $("#form-appver").validate({
                rules: {
                    versionName: {required: true, maxlength: 30},
                    versionCode: {required: true, maxlength: 30},
                    terminal: {required: true},
                    appSize: {required: true, maxlength: 30},
                    isForced: {required: true},
                    checkCode: {required: true},
                    updateUrl: {required: true},
                    updateDesc: {required: true}
                },
                messages: {
                    versionName: {required: "版本名称不能为空!", maxlength: "最大长度30个字符"},
                    versionCode: {required: "版本号不能为空!", maxlength: "最大长度30个字符"},
                    terminal: {required: "终端类型不能为空!"},
                    appSize: {required: "APP大小不能为空!", maxlength: "最大长度30个字符"},
                    isForced: {required: "是否强制更新不能为空!"},
                    checkCode: {required: "校验码不能为空!"},
                    updateUrl: {required: "更新地址不能为空!"},
                    updateDesc: {required: "更新说明不能为空!"}
                }
            });
        }
    </script>
</div>
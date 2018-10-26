<?php if (!defined('THINK_PATH')) exit();?><div id="sub-banner-edit">
    <script type="text/javascript" src="<?php echo (BPATH); ?>js/datejs/WdatePicker.js"></script> 
    <script type="text/javascript" src="<?php echo (BPATH); ?>js/article/ueditor.config.js?version=<?php echo (BNO); ?>" ></script>    
    <script type="text/javascript" charset="utf-8" src="/Public/plugins/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<?php echo (BPATH); ?>js/uploader/webuploader.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<?php echo (BPATH); ?>js/uploader/uploader.js"></script>
    <h4 class="title">优惠券-编辑</h4>
    <input  type="hidden" name="hid_action" id="hidAction" value="view" >
    <form class="form-horizontal" id="form-banner">
        <ul id="myTab" class="nav nav-tabs">
            <li class="active"><a href="#home" data-toggle="tab">基本信息</a></li>
        </ul>
        <input type="hidden" id="bannerID" name="cou_id" value="<?php echo ($data["cou_id"]); ?>">
        <input type="hidden" id="hidUpURL" value="<?php echo (UPLOAD); ?>">
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="home" style="position: relative;">
                <div style="position: absolute;top:5px;left:45%;margin-left: 10px;">
                    <div class="uppic">
                        <?php if(strlen($data['coupon_icon']) > 0): ?><img src='<?php echo (UPLOAD); echo ($data["coupon_icon"]); ?>' id='bannerUrl1'  style="width: 240px;height: 180px;display: block;margin-bottom: 15px;" />
                            <?php else: ?>
                            <img src='/Public/backend/images/graybox.jpg' id='bannerUrl1' style="width: 240px;height: 180px;display: block;margin-bottom: 15px;" /><?php endif; ?>
                        <div class="upfilebox">
                            <label class="upload-box upload-img">上传图片</label>  
                        </div>
                    </div>
                </div>
                <div class="form-group  has-info pt-10">
                    <label class="col-sm-1 control-label" >优惠券名称：</label>
                    <div class="col-sm-4">
                        <input class="form-control" type="text" id="bannerName" placeholder="广告图名称" name="coupon_name" value="<?php echo ($data["coupon_name"]); ?>">
                    </div>
                </div>
                <div class="form-group has-info">
                    <!--<label class="col-sm-1 control-label" >广告类型：</label>-->
                    <div class="col-sm-4">
                        <!--<select  name="dic_type" id="dicType" class="span12 form-control">-->
                            <!--<?php echo ($htmlType); ?>-->
                        <!--</select>-->
                        <input type="hidden" name="dic_type" value="167"/>
                    </div>
                </div>
                <div class="form-group has-info">
                    <label class="col-sm-1 control-label" for="inputInfo">logo路径：</label>
                    <div class="col-sm-4">
                        <input type="text" id="bannerUrl" name="coupon_icon" value="<?php echo ($data["coupon_icon"]); ?>" class="form-control" disabled="disabled"><span style="color: red;">必填选项</span>
                    </div>
                </div>

                <div class="form-group  has-info pt-10">
                    <label class="col-sm-1 control-label" >满：</label>
                    <div class="col-sm-4">
                        <input class="form-control" type="text" id="quota_max" placeholder="输入整数" name="quota_max" value="<?php echo ($data["quota_max"]); ?>">
                    </div>
                </div>
                <div class="form-group  has-info pt-10">
                    <label class="col-sm-1 control-label" >减：</label>
                    <div class="col-sm-4">
                        <input class="form-control" type="text" id="quota_discount" placeholder="输入整数" name="quota_discount" value="<?php echo ($data["quota_discount"]); ?>">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label" for="inputInfo">截止时间：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" style="width: 180px; float: left;" name="start_datetime" id="start_datetime" value="<?php echo (formatshortDate($data["start_datetime"])); ?>" onclick="WdatePicker({isShowClear: true, readOnly: true})"  placeholder="请输入开始时间">
                        <span style="float: left;">~</span>
                        <input type="text" name="end_datetime" id="end_datetime" style="width: 180px; float: left;"  value="<?php echo (formatshortDate($data["end_datetime"])); ?>" class="form-control" onclick="WdatePicker({isShowClear: true, readOnly: true})"   placeholder="请输入结束时间">
                        <span style="float: left; color: red;position:relative;top: -2px;left: 15px;">不选择代表<br/>不限制时间</span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label">排序：</label>
                    <div class="col-sm-4">
                        <input type="text" id="sort" name="sort" value="<?php echo ((isset($data["sort"]) && ($data["sort"] !== ""))?($data["sort"]):0); ?>" class="form-control">
                    </div>
                </div>
                <div class="form-group remark">
                    <label class="col-sm-1 control-label" for="inputInfo">文章内容：</label>
                    <div class="col-sm-1">
                        <textarea  class="area_edit span12" cols="50" rows="4"  name="remark" id="remark"><?php echo ($data["remark"]); ?></textarea>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label" ></label>
            <div class="col-sm-4">      
                <a class="btn btn-primary mt_10 butSaveBanner" href="javascript:;">保存</a>  
                <a class="btn btn-info mt_10 butReturn" href="javascript:;">返回</a> 
            </div>
        </div>   
    </form>
    <script type="text/javascript" >
        $(function() {
             var ue = UE.getEditor('remark');//初始化编辑器
            $(".upload-img").InitUploader({
                filesize: 3,
                sendurl: "/Backend/Spread/saveBannerImg",
                inputID: "bannerUrl",
                browseID: "bannerUrl1",
                sef: "<?php echo ($think["BPATH"]); ?>js/uploader/uploader.swf",
                filetypes: "jpg,jpge,png,gif",
            });
            $(".butReturn").click(function() {
                $Util.returnRefresh();
            });
            bannervalid();
            $(".butSaveBanner").click(function() {
                var isOK = $("#form-banner").valid();
                if (isOK) {
                    saveBanner();
                }
            });
            $("select[name='dic_type']").change(function() {
                if ($(this).val() * 1 == 181) {
                    $(".remarkBanner").show();
                } else {
                    $(".remarkBanner").hide();
                }
            });
            if ($("select[name='dic_type']").val() * 1 == 181) {
                $(".remarkBanner").show();
            }
        });
        function saveBanner() {
            var data = $Util.getData($("#form-banner"));
            data.dic_name = $("#dicType").find("option:selected").text();
            $.ajax({
                type: "post",
                url: "/Backend/Index/saveCoupon",
                data: {'par': data},
                dataType: "json",
                beforeSend: function(XMLHttpRequest) {
                    $(".sub-content").showLoading();
                },
                success: function(data, textStatus) {
                    $(".sub-content").hideLoading();
                    if (data.status == 1) {
                        $("#bannerID").val(data.data);
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
        function bannervalid() {
            $("#form-banner").validate({
                rules: {
                    bannerName: {required: true, maxlength: 30},
                    dicType: {required: true},
                    bannerSort: {digits: true},
                    bannerUrl: {required: true},
                    linkUrl: {
                        funCheck: {fun: function(value, element) {
                                if ($("#inLink").val() == 0 && value) {
                                    var url = /^http:\/\//;
                                    return url.test(value);
                                }
                                return true;
                            }}
                    }
                },
                messages: {
                    bannerName: {required: "名称不能为空!", maxlength: "最大长度30个字符"},
                    dicType: {required: "请选择类型"},
                    bannerSort: {digits: "排序为0~999的正整数!"},
                    bannerUrl: {required: "请上传图片!"},
                    linkUrl: {funCheck: "非内链类型的url必须以http://开头"},
                }
            });
        }
    </script>
</div>
<?php if (!defined('THINK_PATH')) exit();?><div id="sub-User-list">
    <script type="text/javascript" src="<?php echo (BPATH); ?>js/datejs/WdatePicker.js"></script> 
    <input type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>">
    <h4 class="title">状态切换</h4>
    <form class="form-inline" id="query_log"> 

        <div class="form-group">
            <input type="button"   onclick="btn(<?php echo ($value); ?>)"  class="btn btn-info" value='<?php if($value == 1): ?>关闭审核<?php else: ?>开启审核<?php endif; ?>' />
        </div>
        </form>
    <script>
        function btn(value) {
            if (value == 1) {  //代表开启
                value = 0;
            } else {
                value = 1;
            }
            $.ajax({
                type: "post",
                url: "/Backend/Index/changeStatus",
                data: {'value': value},
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
                            var va = $('.butReturn').trigger("click");
                            if(va) {
                                window.location.reload();
                            }
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
    </script>
</div>
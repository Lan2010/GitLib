<?php if (!defined('THINK_PATH')) exit();?><div id="sub-User-list">
    <script type="text/javascript" src="<?php echo (BPATH); ?>js/datejs/WdatePicker.js"></script> 
    <input type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>">
    <h4 class="title">认证录音和图像信息</h4>
    <form class="form-inline" id="query_log"> 
        <div class="form-group">
            <label class="control-label">上传人电话:</label>
            <input type="text" class="form-control mr-10" value="<?php echo ($_POST['txtphone']); ?>" name="txtphone" placeholder="电话">
        </div>
        <div class="form-group">
            <label class="control-label">上传类型:</label>
            <select name="type" style="width: 100px;">
                <option value=""  >--</option>
                <option value="Face" <?php if($_POST['type']== 'Face'): ?>selected<?php endif; ?> >图像</option>
                <option value="Voice" <?php if($_POST['type']== 'Voice'): ?>selected<?php endif; ?> >录音</option>
            </select>
        </div>
        <div class="form-group mr-10">
            <label class="control-label">上传时间:</label>
            <input type="text" class="form-control mr-5" onclick="WdatePicker({isShowClear: true, readOnly: true, maxDate: '%y-%M-%d'})" value="<?php echo ($_POST['txtDateStart']); ?>" id="txtDateStart" name="txtDateStart" placeholder="开始日期">     
            <label class="control-label">～</label>
            <input type="text" class="form-control mr-5" onclick="WdatePicker({isShowClear: true, readOnly: true, maxDate: '%y-%M-%d'})" value="<?php echo ($_POST['txtDateEnd']); ?>" id="txtDateEnd" name="txtDateEnd" placeholder="结束日期">
        </div>
        <div class="form-group">
            <a href="<?php echo U(ACTION_NAME, $_GET);?>"  onclick="$Util.openQuery(this, event, 'query_log')"  class="btn btn-info"><i class="icon-search"></i>查询</a>
        </div>
    </form>
    <table class="table table-striped table-bordered">
        <thead>
            <tr> 

                <th>上传人电话</th>
                <th>路径</th>
                <th>类型</th>
                <th>认证时间</th>
                <th>操作</th>
            </tr>           
        </thead>
        <tbody>
        <?php if(is_array($list)): $i = 0; $__LIST__ = $list;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$vo): $mod = ($i % 2 );++$i;?><tr>
                <td><?php echo ($vo["phone"]); ?></td>
                <td><?php echo ($vo["path"]); ?></td>
                <td>
                    <?php switch($vo["type"]): case "Face": ?>人脸图像<?php break;?>
                        <?php case "Voice": ?>音频<?php break;?>
                        <?php default: ?>
                            未知<?php endswitch;?>
                </td>
                <td><?php echo (formatlongDate($vo["add_detatime"])); ?></td>
                <td>
                    <a href="<?php echo ($vo["path"]); ?>" target="_blank">浏览</a>
                </td>
            </tr><?php endforeach; endif; else: echo "" ;endif; ?>
        </tbody>
    </table>
    <nav class=" pagination-centered">
        <ul class="pagination">  
            <?php echo ($page); ?>
        </ul>
    </nav>  
    <script>
        function getUserInfo() {
            $win.confirm("确定要导出么？").on(function () {
                var data = $Util.getData($("#query_log"));
                $.ajax({
                    type: "post",
                    url: "/Backend/User/expInvite",
                    data: {"data": data},
                    dataType: "json",
                    beforeSend: function () {
                        $(".sub-content").showLoading();
                    },
                    success: function (data) {
                        $(".sub-content").hideLoading();
                        if (data.status == 1) {
                            window.location.href = "/" + data.data;
                        } else {
                            $win.warn(data.msg);
                        }
                    },
                    complete: function () {
                        $(".sub-content").hideLoading();
                    },
                    error: function () {
                        $(".sub-content").hideLoading();
                    }
                });
            });
        }
    </script>
</div>
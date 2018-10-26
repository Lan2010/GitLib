<?php if (!defined('THINK_PATH')) exit();?><div id="sub-User-list">
    <script type="text/javascript" src="<?php echo (BPATH); ?>js/datejs/WdatePicker.js"></script> 
    <input type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>">
    <h4 class="title">用户邀请信息</h4>
    <form class="form-inline" id="query_log"> 
        <div class="form-group">
            <label class="control-label">被邀请人电话:</label>
            <input type="text" class="form-control mr-10" value="<?php echo ($_POST['txtphone']); ?>" name="txtphone" placeholder="被邀请人">
            <label class="control-label">邀请人电话:</label>
            <input type="text" class="form-control mr-10" value="<?php echo ($_POST['yqphone']); ?>" name="yqphone" placeholder="邀请人">
        </div>
        <div class="form-group mr-10">
            <label class="control-label">邀请时间:</label>
            <input type="text" class="form-control mr-5" onclick="WdatePicker({isShowClear: true, readOnly: true, maxDate: '%y-%M-%d'})" value="<?php echo ($_POST['txtDateStart']); ?>" id="txtDateStart" name="txtDateStart" placeholder="开始日期">     
            <label class="control-label">～</label>
            <input type="text" class="form-control mr-5" onclick="WdatePicker({isShowClear: true, readOnly: true, maxDate: '%y-%M-%d'})" value="<?php echo ($_POST['txtDateEnd']); ?>" id="txtDateEnd" name="txtDateEnd" placeholder="结束日期">
        </div>
        <div class="form-group">
            <a href="<?php echo U(ACTION_NAME, $_GET);?>"  onclick="$Util.openQuery(this, event, 'query_log')"  class="btn btn-info"><i class="icon-search"></i>查询</a>
            <input type="button" onclick="getUserInfo()" id="missile" class="btn btn-primary" value="导出">
            <?php if(($showadd) == "1"): ?><a href="/Backend/User/addInvitelView/" title="添加邀请记录" onclick="$win.dialog(this, event)" class="btn btn-success"><i class="icon-plus"></i>添加邀请记录</a><?php endif; ?>
        </div>
    </form>
    <table class="table table-striped table-bordered">
        <thead>
            <tr> 

                <th>被邀请人电话</th>
                <th>邀请人电话</th>
                <th>邀请时间</th>
                <th>是否处理</th>
            </tr>           
        </thead>
        <tbody>
        <?php if(is_array($result)): $i = 0; $__LIST__ = $result;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$vo): $mod = ($i % 2 );++$i;?><tr>
                <td><?php echo ($vo["phone"]); ?></td>
                <td><?php echo ($vo["invite_user_phone"]); ?></td>
                <td><?php echo (formatlongDate($vo["add_datetime"])); ?></td>
                <td>
                    <?php switch($vo["rewardStutas"]): case "0": ?><span style="color: red;">未发放</span><?php break;?>
            <?php case "1": ?><span style="color: green;">已发放</span><?php break;?>
            <?php default: ?>未知<?php endswitch;?>
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
<?php if (!defined('THINK_PATH')) exit();?><div id="sub-User-list">
    <script type="text/javascript" src="<?php echo (BPATH); ?>js/datejs/WdatePicker.js"></script> 
    <input type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>">
    <h4 class="title">用户登录信息<span class="titleqit">默认查询一周内记录</span></h4>
    <form class="form-inline" id="query_log"> 
        <div class="form-group">
            <label class="control-label">手机号:</label>
            <input type="text" class="form-control mr-10" value="<?php echo ($_POST['txtphone']); ?>" name="txtphone" placeholder="请输入手机号"> 
        </div>
        <div class="form-group mr-10">
            <label class="control-label">登录时间:</label>
            <input type="text" class="form-control mr-5" onclick="WdatePicker({isShowClear: true, readOnly: true, maxDate: '%y-%M-%d'})" value="<?php echo ($_POST['txt_date_start']); ?>" id="txtDateStart" name="txtDateStart" placeholder="开始日期">
            <label class="control-label">～</label>
            <input type="text" class="form-control mr-5" onclick="WdatePicker({isShowClear: true, readOnly: true, maxDate: '%y-%M-%d'})" value="<?php echo ($_POST['txt_date_end']); ?>" id="txtDateEnd" name="txtDateEnd" placeholder="结束日期">
            <label class="control-label">用户登录次数:</label>
            <select name='second' id="second" class="form-control">
                <option value="">--请选择--</option>
                <option value='1' <?php if(($_POST['second']) == "1"): ?>selected="selected"<?php endif; ?>>1-3</option>
                <option value='3' <?php if(($_POST['second']) == "3"): ?>selected="selected"<?php endif; ?>>3-5</option>
                <option value='5' <?php if(($_POST['second']) == "5"): ?>selected="selected"<?php endif; ?>>5-8</option>
                <option value='8' <?php if(($_POST['second']) == "8"): ?>selected="selected"<?php endif; ?>>>=8</option>
            </select>
        </div> 
        <div class="form-group">
            <a href="<?php echo U(ACTION_NAME, $_GET);?>"  onclick="$Util.openQuery(this, event, 'query_log')"  class="btn btn-info"><i class="icon-search"></i>查询</a>
            <input type="button" onclick="exportLogin()" id="missile" class="btn btn-primary" value="导出">
            <a href="" id="dowonload"></a>
        </div>
    </form>

    <table class="table table-striped table-bordered">
        <thead>
            <tr> 
                <th>手机号</th>
                <th>登录次数</th>
                <th>手机次数</th>
                <th>微信次数</th>
                <th>PC次数</th>
                <th>最后IP</th>
                <th>最后时间</th>
            </tr>           
        </thead>
        <tbody>
        <?php if(is_array($result)): $i = 0; $__LIST__ = $result;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$vo): $mod = ($i % 2 );++$i;?><tr>  
                <td><?php echo ($vo["phone"]); ?></td>
                <td><?php echo ($vo["login_times"]); ?></td>
                <td><?php echo ($vo["mobile_times"]); ?></td>
                <td><?php echo ($vo["wechat_times"]); ?></td>
                <td><?php echo ($vo["pc_times"]); ?></td>
                <td><?php echo ($vo["last_login_ip"]); ?></td>
                <td><?php echo (formatlongDate($vo["last_login_datetime"])); ?></td>
            </tr><?php endforeach; endif; else: echo "" ;endif; ?>
        </tbody>
    </table>
    <nav class=" pagination-centered">
        <ul class="pagination">  
            <?php echo ($page); ?>
        </ul>
    </nav>  
    <script type="text/javascript">
        //        导出数据
        function exportLogin() {
            $win.confirm("确定要导出么？").on(function() {
                var data = $Util.getData($("#query_log"));
                $.ajax({
                    type: "post",
                    url: "/Backend/User/exportLogin",
                    data: {"data": data},
                    dataType: "json",
                    beforeSend: function(XMLHttpRequest) {
                        $(".sub-content").showLoading();
                    },
                    success: function(data) {
                        $(".sub-content").hideLoading();
                        if (data.status == 1) {
                            $("#dowonload").text("Dowonload");
                            $("#dowonload").attr("href", "/" + data.data);
                            $win.message(data.msg);
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
            });
        }

    </script>
</div>
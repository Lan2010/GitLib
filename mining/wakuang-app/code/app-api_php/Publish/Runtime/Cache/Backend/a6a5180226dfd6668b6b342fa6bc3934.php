<?php if (!defined('THINK_PATH')) exit();?><div id="sub-Link-list">
    <input type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>">
    <h4 class="title">意见反馈</h4>
    <form class="form-inline" id="query_feed">      
        <label class="control-label">电话号码: </label>
        <input type="text" class="form-control" value="<?php echo ($_POST['phone']); ?>" name="phone" placeholder="请输入">     
        <a href="<?php echo U(ACTION_NAME, $_GET);?>"  onclick="$Util.openQuery(this, event, 'query_feed')"  class="btn btn-info"><i class="icon-search"></i>查询</a>
        <input type="button" onclick="expProp()" id="missile" class="btn btn-primary" value="导出">
    </form>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>          
                <th>电话</th>
                <th>用户反馈的信息</th>  
                <th>终端</th>
                <th>APP版本</th>
                <th>反馈时间</th> 
                <th>已处理</th> 
                <th>处理人</th> 
                <th>处理时间</th> 
                <th>操作</th> 
            </tr>
        </thead>
        <tbody>
        <?php if(is_array($result)): $i = 0; $__LIST__ = $result;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$item): $mod = ($i % 2 );++$i;?><tr>         
                <td ><?php echo ($item["phone"]); ?></td>
                <td style="text-align: left"><a href="#" data-toggle="tooltip" data-placement="top" title="<?php echo ($item["feback_info"]); ?>" title="" ><?php echo (sub_str($item["feback_info"],0,20,'...')); ?></a></td>
                <td ><?php echo (formatterminal($item["terminal"])); ?></td>
                <td ><?php echo ($item["versions"]); ?></td>
                <td ><?php echo (formatlongDate($item["add_datetime"])); ?></td>
                <td >
            <?php if($item["status"] == 0): ?><span style="color: red;">否</span>
                <?php else: ?>
                <span style="color: green;">是</span><?php endif; ?>
            </td>
            <td><?php echo ($item["edit_user_name"]); ?></td>
            <td><?php echo (formatlongDate($item["edit_datetime"])); ?></td>
            <td >
                <a class="btn btn-xs btn-info pr-10" href="/Backend/Service/editProp/key/<?php echo ($item["feback_id"]); ?>" title="意见-反馈"  onclick="$win.dialog(this, event)"><span class="icon-edit"></span>编辑</a>
            </td>
            </tr><?php endforeach; endif; else: echo "" ;endif; ?>
        </tbody>
    </table>
    <nav class=" pagination-centered">
        <ul class="pagination">  
            <?php echo ($pageHtml); ?>
        </ul>
    </nav>
    <script>
        $(function() {
            $('[data-toggle="tooltip"]').tooltip();
        });

        function expProp() {
            $win.confirm("确定要导出么？").on(function() {
                var data = $Util.getData($("#query_feed"));
                $.ajax({
                    type: "post",
                    url: "/Backend/Service/expProp",
                    data: {"data": data},
                    dataType: "json",
                    beforeSend: function() {
                        $(".sub-content").showLoading();
                    },
                    success: function(data) {
                        $(".sub-content").hideLoading();
                        if (data.status == 1) {
                            window.location.href = "/" + data.data;
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
            });
        }
    </script>
</div>
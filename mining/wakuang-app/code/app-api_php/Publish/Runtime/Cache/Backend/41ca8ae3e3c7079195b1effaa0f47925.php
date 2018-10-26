<?php if (!defined('THINK_PATH')) exit();?><div id="sub-par-list">
    <input  type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>" >
    <h4 class="title">系统参数</h4>
    <form class="form-inline" id="query_Syspar">      
        <div class="form-group">
            <label class="control-label">参数描述：</label>
            <input type="text" class="form-control" value="<?php echo ($_POST['parName']); ?>" name="parName" placeholder="请输入">     
            <a href="<?php echo U(ACTION_NAME, $_GET);?>"  onclick="$Util.openQuery(this, event, 'query_Syspar')"  class="btn btn-info"><i class="icon-search"></i>查询</a>
            <a href="/Backend/System/getXinParameter/" title="增加-参数" onclick="$win.dialog(this, event)" class="btn btn-primary"><i class="icon-plus"></i>新增</a>
        </div>    
    </form>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>          
                <th>参数描述</th>
                <th>Key</th>
                <th>参数值</th>               
                <th>创建人</th>
                <th>修改人</th>
                <th>备注</th>  
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <?php if(is_array($result)): $i = 0; $__LIST__ = $result;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$item): $mod = ($i % 2 );++$i;?><tr>         
                <td><?php echo ($item["par_name"]); ?></td>
                <td><?php echo ($item["par_key"]); ?></td>
                <td ><?php echo ($item["par_value"]); ?></td>
                <td ><?php echo ($item["add_user_name"]); ?></td>
                <td ><?php echo ($item["edit_user_name"]); ?></td>
                <td ><?php echo ($item["remark"]); ?></td>
                <td>
                    <a class="btn btn-xs btn-primary pr-10" href="/Backend/System/getXinParameter/key/<?php echo ($item["par_id"]); ?>"  onclick="$win.dialog(this, event)" title="修改-参数"><span class="icon-edit"></span>修改  </a>
                    <a class="btn btn-xs btn-warning pr-10 delete" href="/Backend/System/delParameter" data="<?php echo ($item["par_id"]); ?>"><span class="icon-remove"></span>删除</a>
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
            $(".delete").click(function(e) {
                var key = $(this).attr("data");
                var url = $(this).attr("href");
                $win.confirm("确定要删除吗").on(function() {
                    $.ajax({
                        type: "post",
                        url: url,
                        data: {"key": key},
                        dataType: "json",
                        beforeSend: function(XMLHttpRequest) {
                            $(".sub-content").showLoading();
                        },
                        success: function(data, textStatus) {
                            $(".sub-content").hideLoading();
                            if (data.status == 1) {
                                $win.message(data.msg).on(function() {
                                    $Util.openQuery();
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

                    })
                });
                e.preventDefault();
            });
        });

    </script>
</div>
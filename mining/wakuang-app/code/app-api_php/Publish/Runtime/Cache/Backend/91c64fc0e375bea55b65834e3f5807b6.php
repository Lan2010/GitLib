<?php if (!defined('THINK_PATH')) exit();?><div id="sub-Link-list">
    <input type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>">
    <h4 class="title">Redis缓存管理</h4>    
    <form class="form-inline" id="query_Redis">      
        <label class="control-label">KEY: </label>
        <input type="text" class="form-control" value="<?php echo ($key); ?>" name="txtkey" placeholder="请输入">          
        <a href="<?php echo U(ACTION_NAME, $_GET);?>"  onclick="$Util.openQuery(this, event, 'query_Redis')"  class="btn btn-info"><i class="icon-search"></i>查询</a>
    </form>  
    <table class="table table-striped table-bordered">
        <thead>
            <tr>          
                <th>NO</th>
                <th>Key</th>
                <th>类型</th>
                <th>过期时间</th>              
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <?php if(is_array($redlist)): $i = 0; $__LIST__ = $redlist;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$item): $mod = ($i % 2 );++$i;?><tr>         
                <td><?php echo ($i); ?></td>
                <td style="text-align: left"><?php echo ($item["key"]); ?></td>
                <td><?php echo ($item["typeName"]); ?></td>
                <td><?php echo ($item["time"]); ?></td>                
                <td><a class="btn btn-xs btn-primary pr-10" href="/Backend/System/getView/key/<?php echo ($item["key"]); ?>" title="查看-Redis详情"  onclick="$win.dialog(this, event)"><span class="icon-search"></span>查看</a>
                    <a class="btn btn-xs btn-warning pr-10 delete" href="/Backend/System/delRedis" data-toggle="modal" data="<?php echo ($item["key"]); ?>" ><span class="icon-remove"></span>删除</a>
                </td>
            </tr><?php endforeach; endif; else: echo "" ;endif; ?>
        </tbody>
    </table>
    <script type="text/javascript" >
        $(function($) {
            $(".delete").click(function(e) {
                var url = $(this).attr("href");
                var key = $(this).attr("data");
                $win.confirm("确定要删除吗").on(function() {
                    $.ajax({
                        type: "post",
                        url: url,
                        data: {'key': key},
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
                        complete: function(XMLHttpRequest, textStatus) {
                            $(".sub-content").hideLoading();
                        },
                        error: function() {
                            $(".sub-content").hideLoading();
                        }
                    });
                });
                e.preventDefault();
            });
        });
    </script>
</div>
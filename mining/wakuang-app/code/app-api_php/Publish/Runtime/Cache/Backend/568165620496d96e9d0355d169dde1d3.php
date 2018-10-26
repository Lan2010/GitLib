<?php if (!defined('THINK_PATH')) exit();?><div id="sub-role-list">
    <input type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>">
    <h4 class="title">角色-列表</h4>
    <form class="form-inline" id="query_role">      
        <label class="control-label pl_20 pr_10">角色名称: </label>
        <input type="text" value="<?php echo ($_POST['txtroleName']); ?>" name="txtroleName" placeholder="请输入"  class="form-control">   
        <a href="<?php echo U(ACTION_NAME, $_GET);?>"  onclick="$Util.openQuery(this, event, 'query_role')"  class="btn btn-info"><i class="icon-zoom-in"></i>查询</a>
        <a href="/Backend/Backend/editRole" title="新增-角色"  onclick="$win.dialog(this, event)" class="btn btn-primary"><i class="icon-adn"></i>新增</a> 
    </form>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>          
                <th>角色名</th>
                <th>备注</th> 
                <th>创建人</th>
                <th>创建时间</th>              
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <?php if(is_array($result)): $i = 0; $__LIST__ = $result;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$vo): $mod = ($i % 2 );++$i;?><tr>         
                <td><?php echo ($vo["role_name"]); ?></td>
                <td><?php echo ($vo["role_remark"]); ?></td>
                <td><?php echo ($vo["add_user_name"]); ?></td>
                <td><?php echo (formatlongDate($vo["add_date_time"])); ?></td>
                <td><a class="btn btn-xs btn-primary pr-10" href="/Backend/Backend/editRole/key/<?php echo ($vo["role_id"]); ?>" title="编辑-后台角色"  onclick="$win.dialog(this, event)"><span class="icon-edit"></span>修改</a>
                    <a class="btn btn-xs btn-info pr-10" href="/Backend/Backend/siteRole/key/<?php echo ($vo["role_id"]); ?>" title="配置权限-(<?php echo ($vo["role_name"]); ?>)"  onclick="$win.dialog(this, event)"><span class="icon-edit"></span>配置权限</a>
                    <a class="btn btn-xs btn-warning pr-10 delete" href="/Backend/Backend/delRole" data="<?php echo ($vo["role_id"]); ?>"><span class="icon-remove"></span>删除</a>
                </td>
            </tr><?php endforeach; endif; else: echo "" ;endif; ?>
        </tbody>
    </table>
    <nav class=" pagination-centered">
        <ul class="pagination">  
            <?php echo ($page); ?>
        </ul>
    </nav>
    <script type="text/javascript" >
        $(function ($) {
            $(".delete").click(function (e) {
                var url = $(this).attr("href");
                var key = $(this).attr("data");
                $win.confirm("确定要删除吗").on(function () {
                    $.ajax({
                        type: "post",
                        url: url,
                        data: {'key': key},
                        dataType: "json",
                        beforeSend: function (XMLHttpRequest) {
                            $(".sub-content").showLoading();
                        },
                        success: function (data, textStatus) {
                            $(".sub-content").hideLoading();
                            if (data.status == 1) {
                                $win.message(data.msg).on(function () {
                                    $Util.openQuery();
                                });
                            } else {
                                $win.warn(data.msg);
                            }
                        },
                        complete: function (XMLHttpRequest, textStatus) {
                            $(".sub-content").hideLoading();
                        },
                        error: function () {
                            $(".sub-content").hideLoading();
                        }
                    });
                });
                e.preventDefault();
            });
        });
    </script>
</div>
<?php if (!defined('THINK_PATH')) exit();?><div id="sub-buser-list">
    <input type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>">
    <h4 class="title">后台-用户</h4>
    <form class="form-inline" id="query_buser">      
        <label class="control-label pl_20 pr_10">用户名称: </label>
        <input type="text" class="form-control" value="<?php echo ($_POST['txtuserName']); ?>" name="txtuserName" placeholder="请输入"> 
        <label class="control-label pl_20 pr_10">姓名: </label>
        <input type="text" class="form-control" value="<?php echo ($_POST['txtrealName']); ?>" name="txtrealName" placeholder="请输入"> 
        <a href="<?php echo U(ACTION_NAME, $_GET);?>"  onclick="$Util.openQuery(this, event, 'query_buser')"  class="btn btn-info"><i class="icon-zoom-in"></i>查询</a>
        <a href="/Backend/Backend/editUser" title="编辑-后台用户"  onclick="$win.dialog(this, event)" class="btn btn-success"><i class="icon-plus"></i>新增</a> 
    </form>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>          
                <th>用户名</th>
                <th>姓名</th>     
                <th>电话</th>  
                <th>客服昵称</th>  
                <th>类型</th>  
                <th>角色名</th>
                <th>备注</th>  
                <th>创建人</th>
                <th>创建时间</th>              
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <?php if(is_array($user)): $i = 0; $__LIST__ = $user;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$item): $mod = ($i % 2 );++$i;?><tr>         
                <td><?php echo ($item["user_name"]); ?></td>
                <td><?php echo (aes($item["real_name"],'DECODE')); ?></td>
                <td><?php echo ($item["phone"]); ?></td>
                <td><?php echo ($item["cus_name"]); ?></td>
                <td> <?php switch($item["userType"]): case "1": ?><span class="text-info">后台用户</span><?php break;?>
            <?php case "2": ?><span class="text-warning">管理员</span><?php break;?>
            <?php case "7": ?><span class="text-success">客服主管</span><?php break;?>
            <?php case "8": ?><span class="text-error">客服</span><?php break;?>      
            <?php default: ?>未知<?php endswitch;?></td>
            <td><?php echo ($item["user_role"]); ?></td>
            <td><?php echo ($item["remark"]); ?></td>
            <td><?php echo ($item["add_user_name"]); ?></td>
            <td><?php echo (formatlongDate($item["add_datetime"])); ?></td>
            <td><a class="btn btn-xs btn-primary pr-10" href="/Backend/Backend/editUser/key/<?php echo ($item["user_id"]); ?>" title="编辑-后台用户"  onclick="$win.dialog(this, event)"><span class="icon-edit"></span>修改</a>
                <a class="btn btn-xs btn-info pr-10" href="/Backend/Backend/siteRoleUser/key/<?php echo ($item["user_id"]); ?>" title="分配角色-(<?php echo ($item["user_name"]); ?>)"  onclick="$win.dialog(this, event)"><span class="icon-edit"></span>分配角色</a>
                <a class="btn btn-xs btn-warning pr-10 delete" href="/Backend/Backend/delUser" data="<?php echo ($item["user_id"]); ?>"><span class="icon-remove"></span>删除</a>
            </td>
            </tr><?php endforeach; endif; else: echo "" ;endif; ?>
        </tbody>
    </table>
    <nav class=" pagination-centered">
        <ul class="pagination">  
            <?php echo ($pageHtml); ?>
        </ul>
    </nav>
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
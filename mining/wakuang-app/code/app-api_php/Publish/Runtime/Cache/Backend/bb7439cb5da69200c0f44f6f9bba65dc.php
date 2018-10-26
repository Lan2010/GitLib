<?php if (!defined('THINK_PATH')) exit();?><div id="sub-sen-list">
    <input type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>">
    <h4 class="title">APP版本</h4>
    <form class="form-inline" id="query_sen">      
        <label class="control-label pl_20 pr_10">版本名称: </label>
        <input type="text" value="<?php echo ($_POST['versionName']); ?>" name="versionName" placeholder="请输入" class="form-control"> 
        <label class="control-label pl_20 pr_10">系统: </label>
        <select  name="terminal" id="terminal" class="form-control">
            <option  value ="">--请选择--</option>
            <option  value ="2" <?php if(($_POST['terminal']) == "2"): ?>selected<?php endif; ?>>安卓</option>
            <option  value ="3" <?php if(($_POST['terminal']) == "3"): ?>selected<?php endif; ?>>IOS</option>
        </select> 


        <a href="<?php echo U(ACTION_NAME, $_GET);?>"  onclick="$Util.openQuery(this, event, 'query_sen')"  class="btn btn-info"><i class="icon-search"></i>查询</a>
        <a href="/Backend/Spread/getAllAPP" title="增加-APP版本"  onclick="$Util.openWin(this, event)"  class="btn btn-success"><i class="icon-plus"></i>新增</a> 
    </form>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>          
                <th>版本名称</th>
                <th>版本号</th>
                <th>终端类型</th>
                <th>校验码</th>
                <th>APP大小</th>
                <th>更新地址</th>
                <th>更新说明</th>
                <th>是否强制更新</th>
                <th>状态</th>
                <th>创建人</th>        
                <th>创建时间</th>        
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <?php if(is_array($result)): $i = 0; $__LIST__ = $result;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$vo): $mod = ($i % 2 );++$i;?><tr>
                <td><?php echo ($vo["version_name"]); ?></td>
                <td><?php echo ($vo["version_code"]); ?></td>
                <td>
                    <?php if(($vo["terminal"]) == "2"): ?>安卓
            <?php else: ?>
            IOS<?php endif; ?>
            </td>
            <td><?php echo ($vo["check_code"]); ?></td>
            <td><?php echo ($vo["app_size"]); ?></td>
            <td  style="text-align: left"><a href="#"   data-toggle="popover" data-placement="bottom" data-content="<?php echo ($vo["update_url"]); ?>" title="" data-original-title="提示"><?php echo (sub_str($vo["update_url"],0,15,'...')); ?></td>
            <td style="text-align: left"><a href="#" data-toggle="tooltip" data-placement="top" title="<?php echo ($vo["update_desc"]); ?>" title="" ><?php echo (sub_str($vo["update_desc"],0,15,'...')); ?></a></td>
            <td>
                <?php if(($vo["is_forced"]) == "0"): ?>不强制
            <?php else: ?>
            强制<?php endif; ?>
            </td>
            <td>
            <?php if(($vo["status"] == 1)): ?>有效
                <?php elseif(($vo["status"] == 2)): ?>
                过往版本<?php endif; ?>
            </td>
            <td><?php echo ($vo["add_user_name"]); ?></td>
            <td><?php echo (formatlongDate($vo["add_datetime"])); ?></td>
            <td>
                <a class="btn btn-xs btn-primary pr-10" href="/Backend/Spread/getAllAPP/key/<?php echo ($vo["ver_id"]); ?>"  onclick="$Util.openWin(this, event)" title="修改-APP版本"><span class="icon-edit"></span>修改  </a>
                <a class="btn btn-xs btn-warning pr-10 delete" href="/Backend/Spread/delAppVer" data="<?php echo ($vo["ver_id"]); ?>"><span class="icon-remove"></span>删除</a>
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
        $(function($) {

            $("[data-toggle='popover']").popover().on("mouseenter", function() {
                var _this = this;
                $(this).popover("show");
                $(this).siblings(".popover").on("mouseleave", function() {
                    $(_this).popover('hide');
                });
            }).on("mouseleave", function() {
                var _this = this;
                setTimeout(function() {
                    if (!$(".popover:hover").length) {
                        $(_this).popover("hide");
                    }
                }, 100);
            });
            $(".delete").click(function(e) {
                var url = $(this).attr("href");
                var key = $(this).attr("data");
                $win.confirm("你确定要删除吗").on(function() {
                    $.ajax({
                        type: "post",
                        url: url,
                        data: {'key': key},
                        datatype: "json",
                        beforeSend: function() {
                            $(".sub-content").showLoading();
                        },
                        success: function(data) {
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
                    });
                });
                e.preventDefault();
            });
        });
    </script>
</div>
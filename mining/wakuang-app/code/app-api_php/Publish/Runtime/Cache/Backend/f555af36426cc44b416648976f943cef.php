<?php if (!defined('THINK_PATH')) exit();?><style>
    .table th, .table td {
        text-align: left;
        vertical-align: middle;
        padding: 10px 20px;
    }
    .table th:last-of-type, .table td:last-of-type{
        text-align: center;

    }
</style>
<div id="sub-bmenu-list">
    <input type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>">
    <h4 class="title">后台功能-列表</h4>
    <form class="form-inline" id="query_bmenu">      
        <label class="control-label pl_20 pr_10">功能名称: </label>
        <input type="text" value="<?php echo ($_POST['txtmenuName']); ?>" name="txtmenuName" placeholder="请输入" class="form-control">   
        <a href="<?php echo U(ACTION_NAME, $_GET);?>"  onclick="$Util.openQuery(this, event, 'query_bmenu')"  class="btn btn-info"><i class="icon-zoom-in"></i>查询</a>
        <a href="/Backend/Backend/editMenu/" title="新增-后台功能"  onclick="$win.dialog(this, event)" class="btn btn-primary"><i class="icon-plus"></i>新增</a> 
    </form>
    <table class="table table-striped table-bordered" id="example-basic">
        <thead>
            <tr>          
                <th>功能名称</th>
                <th>功能代码</th>
                <th>URL链接</th> 
                <th>是否菜单</th>
                <th>是否显示</th>
                <th>功能类型</th>
                <th>排序</th>              
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <?php if(is_array($result)): $kyid = 0; $__LIST__ = $result;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$vo): $mod = ($kyid % 2 );++$kyid;?><tr data-tt-id="<?php echo ($kyid); ?>">
                <td><?php echo ($vo["menu_name"]); ?></td>
                <td><?php echo ($vo["menu_code"]); ?></td>
                <td><?php if(($vo["menu_url"]) == ""): ?>/<?php else: echo ($vo["menu_url"]); endif; ?>
            </td>
            <td><?php if(($vo["if_right"]) == "1"): ?><i class="spanok"></i><?php else: ?><i class="spanno"></i><?php endif; ?></td>
            <td><?php if(($vo["if_display"]) == "1"): ?><i class="spanok"></i><?php else: ?><i class="spanno"></i><?php endif; ?></td>
            <td><?php echo ($levelType[$vo['level_type']]); ?></td>
            <td><?php echo ($vo["menu_sort"]); ?></td>
            <td>
                <a class="btn btn-xs btn-primary pr-10" href="/Backend/Backend/editMenu/key/<?php echo ($vo["menu_id"]); ?>" title="编辑-后台菜单" onclick="$win.dialog(this, event)"><span class="icon-edit"></span>修改  </a>
                <a class="btn btn-xs btn-warning pr-10 delete" href="/Backend/Backend/delMenu/" data-toggle="modal" data="<?php echo ($vo["menu_id"]); ?>"><span class="icon-plus"></span>删除</a>
            </td>
            </tr>
            <?php if(is_array($vo["children"])): $chlkeyid = 0; $__LIST__ = $vo["children"];if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$item): $mod = ($chlkeyid % 2 );++$chlkeyid;?><tr data-tt-id="<?php echo ($kyid); ?>.<?php echo ($chlkeyid); ?>" data-tt-parent-id="<?php echo ($kyid); ?>">
                    <td><?php echo ($item["menu_name"]); ?></td>
                    <td><?php echo ($item["menu_code"]); ?></td>
                    <td> <?php if(($item["menu_url"]) == ""): ?>/
                <?php else: ?>
                <?php echo ($item["menu_url"]); endif; ?></td>
                <td><?php if(($item["if_right"]) == "1"): ?><i class="spanok"></i><?php else: ?><i class="spanno"></i><?php endif; ?></td>
                <td><?php if(($item["if_display"]) == "1"): ?><i class="spanok"></i><?php else: ?><i class="spanno"></i><?php endif; ?></td>
                <td><?php echo ($levelType[$item['level_type']]); ?></td>
                <td><?php echo ($item["menu_sort"]); ?></td>
                <td>
                    <a class="btn btn-xs btn-primary pr-10" href="/Backend/Backend/editMenu/key/<?php echo ($item["menu_id"]); ?>"  title="编辑-后台菜单" onclick="$win.dialog(this, event)"><span class="icon-edit"></span>修改  </a>
                    <a class="btn btn-xs btn-warning pr-10 delete" href="/Backend/Backend/delMenu/" data-toggle="modal" data="<?php echo ($item["menu_id"]); ?>"><span class="icon-plus"></span>删除</a>
                </td>
                </tr>
                <?php if(is_array($item["children"])): $i = 0; $__LIST__ = $item["children"];if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$thritem): $mod = ($i % 2 );++$i;?><tr data-tt-id="<?php echo ($kyid); ?>.<?php echo ($chlkeyid); ?>.1" data-tt-parent-id="<?php echo ($kyid); ?>.1">
                        <td><?php echo ($thritem["menu_name"]); ?></td>
                        <td><?php echo ($thritem["menu_code"]); ?></td>
                        <td> <?php if(($thritem["menu_url"]) == ""): ?>/
                    <?php else: ?>
                    <?php echo ($thritem["menu_url"]); endif; ?></td>
                    <td><?php if(($thritem["if_right"]) == "1"): ?><i class="spanok"></i><?php else: ?><i class="spanno"></i><?php endif; ?></td>
                    <td><?php if(($thritem["if_display"]) == "1"): ?><i class="spanok"></i><?php else: ?><i class="spanno"></i><?php endif; ?></td>
                    <td><?php echo ($levelType[$thritem['level_type']]); ?></td>
                    <td><?php echo ($thritem["menu_sort"]); ?></td>
                    <td>
                        <a class="btn btn-xs btn-primary pr-10" href="/Backend/Backend/editMenu/key/<?php echo ($thritem["menu_id"]); ?>"  title="编辑-后台功能" onclick="$win.dialog(this, event)"><span class="icon-edit"></span>修改  </a>
                        <a class="btn btn-xs btn-warning pr-10 delete" href="/Backend/Backend/delMenu/" data-toggle="modal" data="<?php echo ($thritem["menu_id"]); ?>"><span class="icon-plus"></span>删除</a>
                    </td>
                    </tr><?php endforeach; endif; else: echo "" ;endif; endforeach; endif; else: echo "" ;endif; endforeach; endif; else: echo "" ;endif; ?>
        </tbody>
    </table>
    <div class="pagination pagination-centered">  
        <?php echo ($pageHtml); ?>
    </div>
    <script src="<?php echo (BPATH); ?>js/jquery.treetable.js?version=<?php echo (BNO); ?>"></script>
    <script type="text/javascript" >
                            $(function ($) {
                                $(".delete").click(function (e) {
                                    var href = $(this).attr("href");
                                    var key = $(this).attr("data");
                                    $win.confirm("确定要删除吗").on(function () {
                                        $.ajax({
                                            type: "post",
                                            url: href,
                                            data: {"key": key},
                                            dataType: "json",
                                            beforesend: function (XMLHttpRequest) {
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
                                $("#example-basic").treetable({expandable: true});
                            });
    </script>
</div>
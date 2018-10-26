<?php if (!defined('THINK_PATH')) exit();?><link rel="stylesheet" href="<?php echo (BPATH); ?>style/jquery.treetable.css?version=<?php echo (BNO); ?>" />  
<style>
    .table th, .table td {
        text-align: left;
        vertical-align: middle;
        padding: 10px 20px;
    }
    .table th:last-of-type, .table td:last-of-type{
        text-align: center;
    }
</style>
<div id="sub-dic-list">
    <input type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>">
    <h4 class="title">后台字典-列表</h4>
    <form class="form-inline" id="query_dic">      
        <label class="control-label pl_20 pr_10">字典名称: </label>
        <input type="text" value="<?php echo ($_POST['txtdicName']); ?>" name="txtdicName" placeholder="请输入" class="form-control">   
        <a href="<?php echo U(ACTION_NAME, $_GET);?>"  onclick="$Util.openQuery(this, event, 'query_dic')"  class="btn btn-info"><i class="icon-zoom-in"></i>查询</a>
        <a href="/Backend/System/editDic/" title="增加-后台字典"  onclick="$win.dialog(this, event)" class="btn btn-primary"><i class="icon-plus"></i>新增</a> 
    </form>
    <table class="table table-striped table-bordered" id="example-basic">
        <thead>
            <tr>          
                <th>字典名称</th>
                <th>字典ID</th> 
                <th>字典key</th>
                <th>备注</th>     
                <th>创建人</th>
                <th>创建时间</th> 
                <th>修改人</th>
                <th>修改时间</th>   
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <?php if(is_array($result)): $kyid = 0; $__LIST__ = $result;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$vo): $mod = ($kyid % 2 );++$kyid;?><tr data-tt-id="<?php echo ($kyid); ?>">
                <td><?php echo ($vo["dic_name"]); ?></td>
                <td> <?php echo ($vo["dic_id"]); ?></td>
                <td><?php echo ($vo["dic_key"]); ?></td>
                <td><?php echo ($vo["dic_remark"]); ?></td>
                <td><?php echo ($vo["add_user_name"]); ?></td>
                <td><?php echo (formatlongDate($vo["add_datetime"])); ?></td>
                <td><?php echo ($vo["edit_user_name"]); ?></td>
                <td><?php echo (formatlongDate($vo["edit_datetime"])); ?></td>
                <td>
                    <a class="btn btn-xs btn-primary pr-10" href="/Backend/System/editDic/key/<?php echo ($vo["dic_id"]); ?>" title="修改-字典管理" onclick="$win.dialog(this, event)" class="btn btn-primary"><span class="icon-edit"></span>修改  </a>
                    <a class="btn btn-xs btn-warning pr-10 delete" href="/Backend/System/delDic" data-toggle="modal" data="<?php echo ($vo["dicID"]); ?>" class="btn btn-primary"><span class="icon-plus"></span>删除  </a>
                </td>
            </tr>
            <?php if(is_array($vo["children"])): $i = 0; $__LIST__ = $vo["children"];if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$item): $mod = ($i % 2 );++$i;?><tr data-tt-id="<?php echo ($kyid); ?>.<?php echo ++$dicId;?>" data-tt-parent-id="<?php echo ($kyid); ?>">
                    <td><?php echo ($item["dic_name"]); ?></td>
                    <td> <?php echo ($item["dic_id"]); ?></td>
                    <td><?php echo ($item["dic_key"]); ?></td>
                    <td><?php echo ($item["dic_remark"]); ?></td>
                    <td><?php echo ($item["add_user_name"]); ?></td>
                    <td><?php echo (formatlongDate($item["add_datetime"])); ?></td>
                    <td><?php echo ($item["edit_user_name"]); ?></td>
                    <td><?php echo (formatlongDate($item["edit_datetime"])); ?></td>
                    <td>
                        <a class="btn btn-xs btn-primary pr-10" href="/Backend/System/editDic/key/<?php echo ($item["dic_id"]); ?>" title="修改-字典管理" onclick="$win.dialog(this, event)" class="btn btn-primary"><span class="icon-edit"></span>修改  </a>
                        <a class="btn btn-xs btn-warning pr-10 delete" href="/Backend/System/delDic"  data-toggle="modal" data="<?php echo ($item["dicID"]); ?>" class="btn btn-primary"><span class="icon-plus"></span>删除  </a>
                    </td>
                </tr>
                <?php if(is_array($item["children"])): $i = 0; $__LIST__ = $item["children"];if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$dic): $mod = ($i % 2 );++$i;?><tr data-tt-id="<?php echo ($kyid); ?>.1.<?php echo ($dicId); ?>" data-tt-parent-id="<?php echo ($kyid); ?>.<?php echo ($dicId); ?>">
                        <td><?php echo ($dic["dic_name"]); ?></td>
                        <td> <?php echo ($dic["dic_id"]); ?></td>
                        <td><?php echo ($dic["dic_key"]); ?></td>
                        <td><?php echo ($dic["dic_remark"]); ?></td>
                        <td><?php echo ($dic["add_user_name"]); ?></td>
                        <td><?php echo (formatlongDate($dic["add_datetime"])); ?></td>
                        <td><?php echo ($dic["edit_user_name"]); ?></td>
                        <td><?php echo (formatlongDate($dic["edit_datetime"])); ?></td>
                        <td>
                            <a class="btn btn-xs btn-primary pr-10" href="/Backend/System/editDic/key/<?php echo ($dic["dic_id"]); ?>" title="修改-字典管理" onclick="$win.dialog(this, event)" class="btn btn-primary"><span class="icon-edit"></span>修改  </a>
                            <a class="btn btn-xs btn-warning pr-10 delete" href="/Backend/System/delDic"  data-toggle="modal" data="<?php echo ($dic["dicID"]); ?>" class="btn btn-primary"><span class="icon-plus"></span>删除  </a>
                        </td>
                    </tr><?php endforeach; endif; else: echo "" ;endif; endforeach; endif; else: echo "" ;endif; endforeach; endif; else: echo "" ;endif; ?>
        </tbody>
    </table>
    <script src="<?php echo (BPATH); ?>js/jquery.treetable.js?version=<?php echo (BNO); ?>"></script>
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
                                    $("#example-basic").treetable({expandable: true});
                                });
    </script>
</div>
<?php if (!defined('THINK_PATH')) exit();?><div id="sub-banner-list">
    <input type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>">
    <h4 class="title">广告-维护</h4>
    <form class="form-inline" id="query_share">      
        <label class="control-label ">名称: </label>
        <input type="text" value="<?php echo ($_POST['txtName']); ?>" name="txtName" placeholder="请输入" class="form-control mr-10"> 
        <label class="control-label ">类型: </label>
        <!--<select class="form-control" name="txtType" value="<?php echo ($_POST['txtType']); ?>">-->
            <!--<?php echo ($htmlType); ?>-->
        <!--</select> -->
        <a href="<?php echo U(ACTION_NAME, $_GET);?>"  onclick="$Util.openQuery(this, event, 'query_share')"  class="btn btn-info"><i class="icon-search"></i>查询</a>
        <a href="/Backend/Spread/editBanner" title="增加-广告图"  onclick="$Util.openWin(this, event)"  class="btn btn-success"><i class="icon-plus"></i>新增</a> 
    </form>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>          
                <th>广告图名称</th>
                <th>广告类型</th>
                <th>广告封面</th>  
                <th>广告标题</th>
                <th>排序</th>  
                <th>内链</th>
                <th>开始时间</th>
                <th>结束时间</th>
                <th>创建人</th>
                <th>创建时间</th>              
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <?php if(is_array($result)): $i = 0; $__LIST__ = $result;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$vo): $mod = ($i % 2 );++$i;?><tr>
                <td  style="text-align: left"><?php echo ($vo["banner_name"]); ?></td>
                <td>首页广告图</td>
                <td> <?php echo (strlen($vo['banner_url']) > 0 ? "<a class='image_gall' title='浏览' href='" . UPLOAD . $vo['banner_url'] . "'> [浏览]</a>" : ""); ?></td>
            <td  style="text-align: left"><a href="#"   data-toggle="popover" data-placement="bottom" data-content="<?php echo ($vo["banner_title"]); ?>" title="" data-original-title="提示"><?php echo (sub_str($vo["banner_title"],0,15,'...')); ?></td>
            <td><?php echo ($vo["banner_sort"]); ?></td>
            <?php if(($vo["in_link"]) == "1"): ?><td>内链</td>
            <?php else: ?>
            <td>外链</td><?php endif; ?>
            <td><?php if($vo["banner_starttime"] * 1 > 0 ): echo (formatshortDate($vo["banner_starttime"])); else: echo ($vo["banner_starttime"]); endif; ?> </td>
            <td><?php if($vo["banner_endtime"] * 1 > 0 ): echo (formatshortDate($vo["banner_endtime"])); else: echo ($vo["banner_endtime"]); endif; ?></td>
            <td><?php echo ($vo["add_user_name"]); ?></td>
            <td><?php echo (formatlongDate($vo["add_datetime"])); ?></td>
            <td>
                <a class="btn btn-xs btn-primary pr-10" href="/Backend/Spread/editBanner/key/<?php echo ($vo["banner_id"]); ?>"  onclick="$Util.openWin(this, event)"><span class="icon-edit"></span>修改  </a>
                <a class="btn btn-xs btn-warning pr-10 delete" href="/Backend/Spread/delBanner" data="<?php echo ($vo["banner_id"]); ?>"><span class="icon-remove"></span>删除</a>
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
        $(function($) {
            $(".image_gall").popImage();
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
                        $(_this).popover("hide")
                    }
                }, 100);
            });
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
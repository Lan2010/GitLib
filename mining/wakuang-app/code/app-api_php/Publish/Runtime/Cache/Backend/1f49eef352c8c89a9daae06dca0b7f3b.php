<?php if (!defined('THINK_PATH')) exit();?><div id="sub-sen-list">
    <input type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>">
    <h4 class="title">审核优惠券</h4>
    <form class="form-inline" id="query_sen">      
        <label class="control-label pl_20 pr_10">优惠券名字: </label>
        <input type="text" value="<?php echo ($_POST['coupon_name']); ?>" name="coupon_name" placeholder="请输入" class="form-control">
        <a href="<?php echo U(ACTION_NAME, $_GET);?>"  onclick="$Util.openQuery(this, event, 'query_sen')"  class="btn btn-info"><i class="icon-search"></i>查询</a>
        <a href="/Backend/Index/editCoupon" title="增加-优惠券"  onclick="$Util.openWin(this, event)"  class="btn btn-success"><i class="icon-plus"></i>新增</a>
    </form>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>          
                <th>优惠券名称</th>
                <th>截止日期</th>
                <th>满</th>
                <th>减</th>
                <th>添加时间</th>
                <th>图标浏览</th>
                <th>排序</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <?php if(is_array($result)): $i = 0; $__LIST__ = $result;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$vo): $mod = ($i % 2 );++$i;?><tr>
                <td><?php echo ($vo["coupon_name"]); ?></td>
                <td><?php echo (formatlongDate($vo["end_datetime"])); ?></td>
                <td><?php echo ($vo["quota_max"]); ?></td>
                <td><?php echo ($vo["quota_discount"]); ?></td>
                <td><?php echo (formatlongDate($vo["add_datetime"])); ?></td>
                <td><a href="<?php echo ($vo["coupon_icon"]); ?>">浏览</a></td>
                <td><?php echo ($vo["sort"]); ?></td>
            <td>
                <a class="btn btn-xs btn-primary pr-10" href="/Backend/Index/editCoupon/key/<?php echo ($vo["cou_id"]); ?>"  onclick="$Util.openWin(this, event)" title="修改-APP版本"><span class="icon-edit"></span>修改  </a>
                <a class="btn btn-xs btn-warning pr-10 delete" href="/Backend/Index/delCoupon" data="<?php echo ($vo["cou_id"]); ?>"><span class="icon-remove"></span>删除</a>
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
<?php if (!defined('THINK_PATH')) exit();?><div id="sub-User-list">
    <script type="text/javascript" src="<?php echo (BPATH); ?>js/datejs/WdatePicker.js"></script> 
    <input type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>">
    <h4 class="title">基因信息</h4>
    <form class="form-inline" id="query_log"> 
        <div class="form-group">
            <label class="control-label">用户电话:</label>
            <input type="text" class="form-control mr-10" value="<?php echo ($_POST['txtphone']); ?>" name="txtphone" placeholder="电话">
        </div>
        <div class="form-group">
            <label class="control-label">性别:</label>
            <select name="sex" style="width: 100px;">
                <option value="">--</option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>
        </div>
        <div class="form-group mr-10">
            <label class="control-label">生日时间:</label>
            <input type="text" class="form-control mr-5" onclick="WdatePicker({isShowClear: true, readOnly: true, maxDate: '%y-%M-%d'})" value="<?php echo ($_POST['txtDateStart']); ?>" id="txtDateStart" name="txtDateStart" placeholder="生日时间">
        </div>
        <div class="form-group">
            <a href="<?php echo U(ACTION_NAME, $_GET);?>"  onclick="$Util.openQuery(this, event, 'query_log')"  class="btn btn-info"><i class="icon-search"></i>查询</a>
            <input type="button" onclick="getUserInfo()" id="missile" class="btn btn-primary" value="导出">
        </div>
    </form>
    <table class="table table-striped table-bordered">
        <thead>
            <tr> 

                <th>电话</th>
                <th>姓名</th>
                <th>遗传病史</th>
                <th>性别</th>
                <th>身高</th>
                <th>体重</th>
                <th width="width:200px;">籍贯</th>
                <th style="width: 200px;">生日</th>
                <th style="width: 200px;">运动时长</th>
                <th>认证时间</th>
            </tr>           
        </thead>
        <tbody>
        <?php if(is_array($list)): $i = 0; $__LIST__ = $list;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$vo): $mod = ($i % 2 );++$i;?><tr>
                <td><?php echo ($vo["phone"]); ?></td>
                <td><?php echo ($vo["name"]); ?></td>
                <td><?php echo ($vo["ill"]); ?> </td>
                <td><?php echo ($vo["height"]); ?></td>
                <td><?php echo ($vo["weight"]); ?></td>
                <td><?php if($vo['sex'] == 1): ?>男 <?php else: ?> 女<?php endif; ?></td>
                <td style="width: 200px;"><?php echo ($vo["native_place"]); ?></td>
                <td style="width: 200px;"><?php echo (formatlongDate($vo["birth_date"])); ?></td>
                <td style="width: 200px;"><?php echo ($vo["exercise_time"]); ?>/秒</td>
                <td><?php echo (formatlongDate($vo["add_datetime"])); ?></td>
            </tr><?php endforeach; endif; else: echo "" ;endif; ?>
        </tbody>
    </table>
    <nav class=" pagination-centered">
        <ul class="pagination">  
            <?php echo ($page); ?>
        </ul>
    </nav>  
    <script>
        function getUserInfo() {
            $win.confirm("确定要导出么？").on(function () {
                var data = $Util.getData($("#query_log"));
                $.ajax({
                    type: "post",
                    url: "/Backend/User/exgene",
                    data: {"data": data},
                    dataType: "json",
                    beforeSend: function () {
                        $(".sub-content").showLoading();
                    },
                    success: function (data) {
                        $(".sub-content").hideLoading();
                        if (data.status == 1) {
                            window.location.href = "/" + data.data;
                        } else {
                            $win.warn(data.msg);
                        }
                    },
                    complete: function () {
                        $(".sub-content").hideLoading();
                    },
                    error: function () {
                        $(".sub-content").hideLoading();
                    }
                });
            });
        }
    </script>
</div>
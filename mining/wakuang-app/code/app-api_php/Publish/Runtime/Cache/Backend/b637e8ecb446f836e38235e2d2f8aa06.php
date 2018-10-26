<?php if (!defined('THINK_PATH')) exit();?><div id="sub-Bank-list">
    <script type="text/javascript" src="<?php echo (BPATH); ?>js/datejs/WdatePicker.js"></script> 
    <input type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>">
    <h4 class="title">银行卡绑定 </h4>
    <form class="form-inline" id="query_Bank"> 
        <div class="form-group ">
            <label class="control-label">基本信息:</label>
            <input type="text" class="form-control ipt mr-10" value="<?php echo ($_POST['name']); ?>" name="name" placeholder="请输入姓名"> 
            <input type="text" class="form-control ipt mr-10" value="<?php echo ($_POST['phone']); ?>" name="phone" placeholder="请输入手机号"> 
        </div>    
        <div class="form-group ">
            <label class="control-label">绑卡时间:</label>
            <input type="text" class="form-control mr-5" onclick="WdatePicker({isShowClear: true, readOnly: true, maxDate: '%y-%M-%d'})" value="<?php echo ($_POST['txtDateStart']); ?>" id="txtDateStart" name="txtDateStart" placeholder="请输入开始日期">     
            <label class="control-label">～</label>
            <input type="text" class="form-control mr-5" onclick="WdatePicker({isShowClear: true, readOnly: true, maxDate: '%y-%M-%d'})" value="<?php echo ($_POST['txtDateEnd']); ?>" id="txtDateEnd" name="txtDateEnd" placeholder="请输入结束日期">   
        </div> 

        <div class="form-group">
            <a href="<?php echo U(ACTION_NAME, $_GET);?>"  onclick="$Util.openQuery(this, event, 'query_Bank')"  class="btn btn-info"><i class="icon-search"></i>查询</a> 
            <a href="/Backend/Audit/expBank"    onclick="$Util.openExport(this, event, 'query_Bank')" class="btn btn-primary"><i class="icon-circle-arrow-down"></i>导出</a>
        </div>
    </form>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>     
                <th>手机号</th>
                <th>卡号</th>
                <th>银行</th>
                <th>终端</th>
                <th>绑卡时间</th>
            </tr>
        </thead>
        <tbody>
        <?php if(is_array($result)): $i = 0; $__LIST__ = $result;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$vo): $mod = ($i % 2 );++$i;?><tr>
                <td><?php echo ($vo["phone"]); ?></td>
                <td><?php echo ($vo["bank_card_no"]); ?></td>
                <td><?php echo ($vo["branch"]); ?></td>
                <td><?php echo (formatterminal($vo["terminal"])); ?></td>
                <td><?php echo (formatlongDate($vo["add_datetime"])); ?></td>
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
            $("#txtTypeID").val("<?php echo ($_POST['txtTypeID']); ?>");
            $("#txtStatus").val("<?php echo ($_POST['txtStatus']); ?>");
        });
    </script>
</div>
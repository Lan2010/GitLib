<?php if (!defined('THINK_PATH')) exit();?><div id="sub-realName-list"> 
    <script type="text/javascript" src="<?php echo (BPATH); ?>js/datejs/WdatePicker.js"></script> 
    <input type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>">
    <h4 class="title">实名认证记录  <span class="titleqit">默认查询最近三天数据</span></h4>   
    <form class="form-inline" id="query_RealName">      
        <div class="form-group ">
            <label class="control-label">手机号: </label>
            <input type="text" class="form-control mr-10" value="<?php echo ($_POST['txtuserName']); ?>" name="txtuserName" id="txtuserName" placeholder="请输入">   
            <label class="control-label">姓名: </label>
            <input type="text" class="form-control mr-10" value="<?php echo ($_POST['txtrealName']); ?>" name="txtrealName" id="txtrealName"   placeholder="请输入"> 
        </div>  
        <div class="form-group mr-10">
            <label class="control-label">日期: </label>
            <input type="text" class="form-control mr-5" onclick="WdatePicker({isShowClear: true, readOnly: true, maxDate: '%y-%M-%d'})" value="<?php echo ($_POST['txtstart']); ?>" id="txtstart" name="txtstart" placeholder="请输入">     
            <label class="control-label">- </label>
            <input type="text" class="form-control mr-5" onclick="WdatePicker({isShowClear: true, readOnly: true, maxDate: '%y-%M-%d'})" value="<?php echo ($_POST['txtend']); ?>" id="txtend" name="txtend" placeholder="请输入">   
        </div>   

        <a href="<?php echo U(ACTION_NAME, $_GET);?>"  onclick="$Util.openQuery(this, event, 'query_RealName')"  class="btn btn-info"><i class="icon-search"></i>查询</a>  
        <a href="/Backend/Audit/expRealName"  onclick="$Util.openExport(this, event, 'query_RealName')"  class="btn btn-info"><i class="icon-search"></i>导出</a>
    </form>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>       
                <th>手机号</th>
                <th>姓名</th> 
                <th>单号</th>
                <th>证件号</th>
                <th>年龄</th>
                <th>性别</th>
                <th>证件地址</th>
                <th>申请时间</th>
                <th>终端</th>
            </tr>
        </thead>
        <tbody>
        <?php if(is_array($realuser)): $i = 0; $__LIST__ = $realuser;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$item): $mod = ($i % 2 );++$i;?><tr> 
                <td><?php echo ($item["phone"]); ?></td>
                <td><?php echo (aes($item["real_name"],'DECODE')); ?></td>
                <td><?php echo ($item["order_no"]); ?></td>
                <td><?php echo (hidCard8(aes($item["card_id"],'DECODE'))); ?></td>
                <td><?php echo (getCardAge(aes($item["card_id"],'DECODE'))); ?></td>
                <td><?php echo (formatSex($item["user_sex"])); ?></td>
                <td style="text-align: left"><?php echo ($item["card_address"]); ?></td>

            <td><?php echo (formatlongDate($item["add_datetime"])); ?></td>
            <td><?php echo (formatterminal($item["terminal"])); ?></td>
            </tr><?php endforeach; endif; else: echo "" ;endif; ?>
        </tbody>
    </table>
    <nav class=" pagination-centered">
        <ul class="pagination">  
            <?php echo ($pageHtml); ?>
        </ul>
    </nav> 
</div>
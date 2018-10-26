<?php if (!defined('THINK_PATH')) exit();?><div id="sub-goods-list">
    <script type="text/javascript" src="<?php echo (BPATH); ?>js/datejs/WdatePicker.js"></script> 
    <input  type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>" >
    <h4 class="title">系统日志</h4>
    <form class="form-inline" id="query_Syslog">      
        <div class="form-group">
            <label class="control-label">关键字：</label>
            <input type="text" class="form-control" value="<?php echo ($_POST['log_Key']); ?>" name="logKey" placeholder="请输入">
        </div>
        <div class="form-group  mr-10" style="width:800px;">
            <label class="control-label">日志生成时间：</label>
            <input type="text" class="form-control" name="txtDateStart" id="txtDateStart" value="<?php echo ($_POST['txtDateStart']); ?>" onclick="WdatePicker({isShowClear: true, readOnly: true})" placeholder="请输入开始时间">
            <label class="control-label">～</label>
            <input type="text" name="txtDateEnd" id="txtDateEnd"  value="<?php echo ($_POST['txtDateEnd']); ?>" class="form-control" onclick="WdatePicker({isShowClear: true, readOnly: true})" placeholder="请输入结束时间">
        </div>
        <div class="form-group" >
            <a href="<?php echo U(ACTION_NAME, $_GET);?>"  onclick="$Util.openQuery(this, event, 'query_Syslog')"  class="btn btn-info"><i class="icon-search"></i>查询</a>
        </div>
    </form>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>          
                <th>关键字</th>
                <th>URL</th>
                <th>标记</th>
                <th>日志生成时间</th>               
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <?php if(is_array($result)): $i = 0; $__LIST__ = $result;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$item): $mod = ($i % 2 );++$i;?><tr>         
                <td style="text-align:left;"><?php echo ($item["log_key"]); ?></td>
                <td style="text-align:left;"><?php echo ($item["url"]); ?></td>
                <td style="text-align:left;"><?php echo ($item["log_tag"]); ?></td>
                <td><?php echo (formatlongDate($item["add_datetime"])); ?></td>
                <td>
                    <a class="btn btn-xs btn-primary pr-10" href="/Backend/System/getLogView/key/<?php echo ($item["log_id"]); ?>" title="查看-日志详情" onclick="$win.dialog(this, event)"><span class="icon-search"></span>查看</a>
                </td>
            </tr><?php endforeach; endif; else: echo "" ;endif; ?>
        </tbody>
    </table>
    <nav class=" pagination-centered">
        <ul class="pagination">  
            <?php echo ($pageHtml); ?>
        </ul>
    </nav>
</div>
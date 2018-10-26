<?php if (!defined('THINK_PATH')) exit();?><div id="sub-User-list">
    <script type="text/javascript" src="<?php echo (BPATH); ?>js/datejs/WdatePicker.js"></script> 
    <input type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>">
    <h4 class="title">设备信息</h4>
    <form class="form-inline" id="query_log"> 
        <div class="form-group">
            <label class="control-label">用户电话:</label>
            <input type="text" class="form-control mr-10" value="<?php echo ($_POST['txtphone']); ?>" name="txtphone" placeholder="电话">
        </div>
        <div class="form-group">
            <label class="control-label">是否绑定:</label>
            <select name="state" style="width: 100px;">
                <option value="">--</option>
                <option value="2" <?php if($_POST['state']== 2): ?>selected<?php endif; ?> >已绑定</option>
                <option value="1" <?php if($_POST['state']== 1): ?>selected<?php endif; ?> >未绑定</option>
            </select>
        </div>
        <div class="form-group">
            <label class="control-label">MAC:</label>
            <input type="text" class="form-control mr-10"  size="8" value="<?php echo ($_POST['MAC']); ?>" name="MAC" placeholder="MAC">
            <label class="control-label">设备ID:</label>
            <input type="text" class="form-control mr-10" size="8" value="<?php echo ($_POST['deviceID']); ?>" name="deviceID" placeholder="设备ID">
        </div>
        <div class="form-group mr-10">
            <label class="control-label">绑定时间:</label>
            <input type="text" class="form-control mr-5" onclick="WdatePicker({isShowClear: true, readOnly: true, maxDate: '%y-%M-%d'})" value="<?php echo ($_POST['txtDateStart']); ?>" id="txtDateStart" name="txtDateStart" placeholder="开始日期">     
            <label class="control-label">～</label>
            <input type="text" class="form-control mr-5" onclick="WdatePicker({isShowClear: true, readOnly: true, maxDate: '%y-%M-%d'})" value="<?php echo ($_POST['txtDateEnd']); ?>" id="txtDateEnd" name="txtDateEnd" placeholder="结束日期">
        </div>
        <div class="form-group">
            <a href="<?php echo U(ACTION_NAME, $_GET);?>"  onclick="$Util.openQuery(this, event, 'query_log')"  class="btn btn-info"><i class="icon-search"></i>查询</a>
            <input type="button" onclick="getUserInfo()" id="missile" class="btn btn-primary" value="导出">
        </div>
        </form>
        <br/>
        <div style="border: 1px solid #dddddd; height: 100px; margin-bottom: 20px;">
            <div style="width: 60%;height: 100%;  float: left;">
                <ul style="font-size: 12px; padding-top: 10px;">
                    <li>1 请务必保证导入的格式为csv,xls,xlsx格式的文件,且导入的文件只有第一页有效</li>
                    <li>2 导入格式为csv的时候，不支持中文字符，请注意，另外请数据量过多时，请分批次导入</li>
                    <li>3 导入的内容格式为，首行为标头，为每一列的解释，导入的数据从第二行开始导入</li>
                    <li>4 导入的内容格式内容顺序为:A列为设备的ID标识，B列为设备的类型,C列为设备的模式,D列为设备的MAC地址,E列为备注(只需5列数据)</li>
                </ul>
            </div>
            <div style=" width: 20%;height: 100%;float: left; text-align: center; padding-top:30px;  ">
                <form method="post" enctype="multipart/form-data" action="/Backend/Operate/importF" >
                    <input type="file" name="file" style="display: inline-block; width: 150px;" />
                    <input type="submit"  class="btn btn-primary" value="导入">
                </form>
            </div>
        </div>

    <table class="table table-striped table-bordered">
        <thead>
            <tr> 

                <th>设备ID</th>
                <th>设备类型</th>
                <th>设备模式</th>
                <th>MAC</th>
                <th width="width:200px;">绑定状态</th>
                <th style="width: 200px;">绑定时间</th>
                <th style="width: 200px;">备注</th>
                <th>用户电话</th>
                <th>操作</th>
            </tr>           
        </thead>
        <tbody>
        <?php if(is_array($list)): $i = 0; $__LIST__ = $list;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$vo): $mod = ($i % 2 );++$i;?><tr>
                <td><?php echo ($vo["device_id"]); ?></td>
                <td><?php echo ($vo["device_type"]); ?></td>
                <td><?php echo ($vo["device_model"]); ?></td>
                <td><?php echo ($vo["device_mac"]); ?></td>
                <td >
                    <?php if($vo['state'] == 1): ?>未绑定
                        <?php else: ?>
                        绑定<?php endif; ?>
                </td>
                <td ><?php echo (formatlongDate($vo["bind_datetime"])); ?></td>
                <td ><?php echo ($vo["remark"]); ?></td>
                <td>
                    <?php if(empty($vo['phone'])): ?>无
                        <?php else: ?>
                        <?php echo ($vo["phone"]); endif; ?>
                </td>
                <td>
                    <a class="btn btn-xs btn-primary pr-10" href="/Backend/Operate/edit/key/<?php echo ($vo["charge_id"]); ?>"  onclick="$Util.openWin(this, event)" title="修改-APP版本"><span class="icon-edit"></span>修改  </a>
                </td>
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
                    url: "/Backend/Operate/exCharge",
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
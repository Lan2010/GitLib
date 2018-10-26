<?php if (!defined('THINK_PATH')) exit();?><div id="sub-User-list">
    <script type="text/javascript" src="<?php echo (BPATH); ?>js/datejs/WdatePicker.js"></script> 
    <input type="hidden" name="hidrefurl" id="hidrefurl" value="<?php echo U(ACTION_NAME, $_GET);?>">
    <h4 class="title">用户信息查询 <span class="titleqit">默认查询一周内记录</span></h4>
    <form class="form-inline" id="query_User"> 
        <div class="form-group ">
            <label class="control-label">基本信息:</label>
            <input type="text" class="form-control ipt mr-10" value="<?php echo ($_POST['txtphone']); ?>" name="txtphone" id="txtphone" placeholder="手机号"> 
            <input type="text" class="form-control ipt mr-10" value="<?php echo ($_POST['txtrealName']); ?>" name="txtrealName" id="txtrealName" placeholder="姓名">       
        </div>
        <div class="form-group mr-10">
            <label class="control-label">注册时间:</label>
            <input type="text" class="form-control mr-5" onclick="WdatePicker({isShowClear: true, readOnly: true, maxDate: '%y-%M-%d'})" value="<?php echo ($_POST['txtDateStart']); ?>" id="txtDateStart" name="txtDateStart" placeholder="开始日期">     
            <label class="control-label">～</label>
            <input type="text" class="form-control mr-5" onclick="WdatePicker({isShowClear: true, readOnly: true, maxDate: '%y-%M-%d'})" value="<?php echo ($_POST['txtDateEnd']); ?>" id="txtDateEnd" name="txtDateEnd" placeholder="结束日期">   
        </div>
        <div class="form-group">
            <a href="<?php echo U(ACTION_NAME, $_GET);?>"  onclick="$Util.openQuery(this, event, 'query_User')"  class="btn btn-info"><i class="icon-search"></i>查询</a>
            <input type="button" onclick="getUserInfo()" id="missile" class="btn btn-primary" value="导出">
            <a href="" id="dowonload"></a>
        </div>
    </form>
    <table class="table table-striped table-bordered">
        <thead>
            <tr> 
                <th>ID</th>
                <th>手机</th>
                <th>注销</th>

                <th>实名状态</th>
                <th>性别</th>
                <th>注册时间</th>
                <th>终端</th>
            </tr>
        </thead>
        <tbody>
        <?php if(is_array($result)): $i = 0; $__LIST__ = $result;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$vo): $mod = ($i % 2 );++$i;?><tr>  
                <td ><?php echo ($vo["user_id"]); ?></td>
                <td><?php echo ($vo["phone"]); ?></td>
                <td><?php if(($vo["user_status"]) == "1"): ?><span class="spanno"></span><?php else: ?><span class="spanok"><?php endif; ?></td>
                <td><?php if(($vo["real_status"]) == "1"): ?><span class="spanok"></span><?php else: ?><span class="spanno"><?php endif; ?></td>
                <td><?php echo (formatSex($vo["user_sex"])); ?></td>
                <td><?php echo (formatlongDate($vo["reg_datetime"])); ?></td>
                <td><?php echo (formatterminal($vo["reg_terminal"])); ?> </td>
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
                                $("#txtuserType").val("<?php echo ($_POST['txtuserType']); ?>");
                                $("#txtrealStatus").val("<?php echo ($_POST['txtrealStatus']); ?>");
                                $("#txtuserSex").val("<?php echo ($_POST['txtuserSex']); ?>");
                            });
                            function getUserInfo() {
                                if (!confirm("是否确认导出数据？")) {
                                    return;
                                }
                                var url = "/Backend/User/exportUserInfo/";
                                getInfo(url, 1);
                            }
                            function getInfo(url, page) {
                                var emp = getPar();
                                //   $("#missile").attr("disabled", true);
                                $.ajax({
                                    type: "post",
                                    url: url,
                                    data: {"par": emp, "page": page},
                                    dataType: "json",
                                    beforeSend: function() {
                                        $(".sub-content").showLoading();
                                    },
                                    success: function(data) {
                                        $(".sub-content").hideLoading();
                                        if (data.status == 1) {
                                            $("#dowonload").text("Dowonload");
                                            $("#dowonload").attr("href", "/" + data.data);
                                            $win.message(data.msg);
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
                            }
                            function getPar() {
                                var emp = new Object();
                                emp.txtphone = $("#txtphone").val();
                                emp.txtrealName = $("#txtrealName").val();
                                emp.txtrealStatus = $("#txtrealStatus").val();
                                emp.txtuserSex = $("#txtuserSex").val();
                                emp.txtuserType = $("#txtuserType").val();
                                emp.txtDateStart = $("#txtDateStart").val();
                                emp.txtDateEnd = $("#txtDateEnd").val();
                                emp.birthdayStart = $("#birthdayStart").val();
                                emp.birthdayEnd = $("#birthdayEnd").val();
                                emp.vipLerver = $("#vipLerver").val();
                                emp.serviceID = $("#serviceID").val();
                                return emp;
                            }
                        </script>
                        </div>
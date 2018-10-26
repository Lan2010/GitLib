<?php if (!defined('THINK_PATH')) exit();?><!DOCTYPE html>
<html>
    <head>
        <title>后台管理</title>
        <meta name="description" content="网站后台" />
        <meta name="author" content="えう">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />      
        <link type="text/css" rel="stylesheet" href="<?php echo (BPATH); ?>style/bootstrap.css" />
        <link type="text/css" rel="stylesheet" href="<?php echo (BPATH); ?>style/bootstrap-responsive.css" />
        <link type="text/css" rel="stylesheet" href="<?php echo (BPATH); ?>fonts/css/font-awesome.min.css?version=<?php echo (BNO); ?>" />
        <!--[if lte IE 7]>
            <link rel="stylesheet" href="<?php echo (BPATH); ?>fonts/css/font-awesome-ie7.min.css?version=<?php echo (BNO); ?>" />
        <![endif]--> 
        <link type="text/css" rel="stylesheet" href="<?php echo (BPATH); ?>style/toastr.css?version=<?php echo (BNO); ?>" />
        <link type="text/css" rel="stylesheet" href="<?php echo (BPATH); ?>style/gallery.css?version=<?php echo (BNO); ?>" />   
        <link type="text/css" rel="stylesheet" href="<?php echo (BPATH); ?>style/jquery.treetable.css?version=<?php echo (BNO); ?>" />           
        <link type="text/css" rel="stylesheet" href="<?php echo (BPATH); ?>style/qianbox.css?version=<?php echo (BNO); ?>" />   
        <link type="text/css" rel="stylesheet" href="<?php echo (BPATH); ?>js/loading/css/showLoading.css?version=<?php echo (BNO); ?>" />   

    </head>
    <body class="ht">      
        <!--头部导航开始-->
        <div id="in-nav">
            <div class="clearfix">
                <ul class="pull-right" style="margin-right: 45px;">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:;"><?php echo ($realName); ?><b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/Backend/Index/loginOut">退出</a></li>
                        </ul>
                    </li>
                    <li><a  href="#mdlpassword" data-toggle="modal" >修改密码</a></li>                

                </ul>
                <div class="fl">
                    <ul>
                        <li>
                            <a id="logo" href="http://www.qianhezi.cn/" class="logo"></a>
                        </li>
                        <li><a href="javascript:;">管理控制台</a></li>                     
                    </ul>
                </div>
            </div>
        </div>
        <!--头部导航结束-->
        <div class="row-fluid  sidebarfull viewbody">    	
            <!--左侧菜单开始-->
            <div class="sidebar">
                <div class="sidebaricon">
                    <i class="icon-reorder"></i>
                </div>
                <ul class="col-nav">  
                    <?php if(is_array($menu)): $i = 0; $__LIST__ = $menu;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$main): $mod = ($i % 2 );++$i; if($main["if_right"] == 0 ): ?><li>
                                <a href="#pane<?php echo ($main["menu_id"]); ?>" data-toggle="collapse" class="accordion-toggle">
                                    <i class="pull-left icon-caret-down"></i><?php echo ($main["menu_name"]); ?></a>
                            </li>  
                            <li id="pane<?php echo ($main["menu_id"]); ?>" class="collapse in">
                                <ul>
                                    <li>
                                    <?php if(is_array($main["children"])): $i = 0; $__LIST__ = $main["children"];if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$children): $mod = ($i % 2 );++$i;?><a href="<?php echo ($children["menu_url"]); ?>" class="children-menu"> <i class="pull-left icon-plane"></i><?php echo ($children["menu_name"]); ?></a><?php endforeach; endif; else: echo "" ;endif; ?>
                            </li>
                            </ul>
                            </li>
                            <?php else: ?>
                            <li>
                                <a href="javascript:;" data-toggle="collapse" id="<?php echo ($main["menu_id"]); ?>" name="<?php echo ($main["menu_name"]); ?>" class="mainmenuclick accordion-toggle">
                                    <i class="pull-left icon-align-right"></i><?php echo ($main["menu_name"]); ?></a>
                            </li><?php endif; endforeach; endif; else: echo "" ;endif; ?>                   
                </ul>
            </div>
            
    <div class="reght-content">   
        <div class="sub-content">          
            <div class="row-fluid">

                <h4 class="title fl">今天注册用户</h4>

                <h4 class="title fl">昨天注册用户</h4>
                <h4 class="title fl">今天提现用户</h4>

                <h4 class="title fl">昨天提现用户</h4>
            </div>
        </div> 
    </div>

        </div>      
        <script type="text/javascript"  src="<?php echo (BPATH); ?>js/jquery.min.js"></script>
        <link type="text/css" rel="stylesheet" href="<?php echo (BPATH); ?>js/loading/css/showLoading.css?version=<?php echo (BNO); ?>" />
        <script type="text/javascript"  src="<?php echo (BPATH); ?>js/loading/jquery.showLoading.min.js"></script>
        <link type="text/css" rel="stylesheet" href="<?php echo (BPATH); ?>js/qtip/css/jquery.qtip.min.css?version=<?php echo (BNO); ?>" />
        <script type="text/javascript"  src="<?php echo (BPATH); ?>js/jquery.validate.min.js"></script>
        <script type="text/javascript"  src="<?php echo (BPATH); ?>js/qtip/jquery.qtip.js"></script>
        <script type="text/javascript"  src="<?php echo (BPATH); ?>js/bootstrap.min.js"></script>   
        <script type="text/javascript"  src="<?php echo (BPATH); ?>js/back.js?version=<?php echo (BNO); ?>"></script>
        <script type="text/javascript" src="<?php echo (BPATH); ?>js/echarts-all.js"></script>
    
    <div id="mdlpassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  class="modal fade" >
        <div class="modal-dialog" role="document" >
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close"  aria-hidden="true">×</button>
                    <h4 class="title modal-title" id="dialogdModalLabel">修改密码</h4></div>
                <div class="modalcontent"><div class="modal-body">                      
                        <form class="form-horizontal" id="form-word" >
                            <div class="form-group has-info">
                                <label class="control-label col-sm-2" for="inputInfo">姓名：</label>
                                <div class=" col-sm-5">
                                    <label class="control-label" for="inputInfo"> <?php echo ($realName); ?> </label>
                                </div>
                            </div>
                            <div class="form-group has-info">
                                <label class="control-label col-sm-2" for="inputInfo">原密码：</label>
                                <div class=" col-sm-6">
                                    <input type="password" id="oldPass" name="oldPass" maxlength="30" value="" class="form-control">         
                                </div>
                            </div>
                            <div class="form-group has-info">
                                <label class="control-label col-sm-2" for="inputInfo">新密码：</label>
                                <div class="col-sm-6">
                                    <input type="password" id="newPass" name="newPass" maxlength="30" value="" class="form-control">          
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="butCancelPass" class="btn btn-default"  >关闭</button>
                        <button type="button" id="butSavePass" class="btn btn-primary">保存</button>
                    </div>
                    <script type="text/javascript" >
                        $(function ($) {
                            PassValid();
                            $("#butSavePass").click(function () {
                                var isOK = $("#form-word").valid();
                                if (isOK) {
                                    savePass();
                                }
                            });
                        });
                        function savePass() {
                            var data = {};
                            data.oldPass = $("#oldPass").val();
                            data.newPass = $("#newPass").val();
                            $.ajax({
                                type: "post",
                                url: "/Backend/Index/savePass",
                                data: {'par': data},
                                dataType: "json",
                                beforeSend: function (XMLHttpRequest) {
                                    $(".sub-content").showLoading();
                                },
                                success: function (data, textStatus) {
                                    $(".sub-content").hideLoading();
                                    if (data.status == 1) {
                                        $win.message(data.msg).on(function () {
                                            $('#butCancelPass').trigger("click");
                                            window.location.href = "/Backend/Index/loginOut";

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
                        }
                        function PassValid() {
                            $("#form-word").validate({
                                rules: {
                                    oldPass: {required: true, maxlength: 30},
                                    newPass: {required: true, maxlength: 30}
                                },
                                messages: {
                                    oldPass: {required: "请输入!", maxlength: "最大长度30个字符"},
                                    newPass: {required: "请输入!", maxlength: "最大长度30个字符"}
                                }
                            });
                        }
                        //侧边栏收缩
                        $('.sidebaricon').click(function () {
                            $(".sidebar").toggleClass("sidebar-close");
                            $(".reght-content").toggleClass("sidebar-close");
                        })
                    </script>
                </div>
            </div>
        </div>
    </div>   

</body>
</html>
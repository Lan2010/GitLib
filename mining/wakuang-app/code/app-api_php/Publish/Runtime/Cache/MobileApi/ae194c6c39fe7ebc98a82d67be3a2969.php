<?php if (!defined('THINK_PATH')) exit();?><!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title><?php echo ($service); ?> - 在线接口文档</title>

        <link rel="stylesheet" href="<?php echo (PUBLIC_DIR); ?>mobileapi/style/semantic.min.css">
        <link rel="stylesheet" href="<?php echo (PUBLIC_DIR); ?>mobileapi/style/table.min.css">
        <link rel="stylesheet" href="<?php echo (PUBLIC_DIR); ?>mobileapi/style/container.min.css">
        <link rel="stylesheet" href="<?php echo (PUBLIC_DIR); ?>mobileapi/style/message.min.css">
        <link rel="stylesheet" href="<?php echo (PUBLIC_DIR); ?>mobileapi/style/label.min.css">

    </head>

    <body>
        <div class="ui text container" style="max-width: none !important;">
            <div class="ui floating message">
                <h2 class='ui header'>接口：<?php echo ($service); ?></h2><br/> <span class='ui teal tag label'><?php echo ($description); ?></span>
                <div class="ui raised segment">
                    <span class="ui red ribbon label">接口说明</span>
                    <div class="ui message">
                        <p><?php echo ($descComment); ?></p>
                    </div>
                </div>
                <h3>接口参数</h3>
                <table class="ui red celled striped table" >
                    <thead>
                        <tr><th>参数名字</th><th>类型</th><th>是否必须</th><th>默认值</th><th>其他</th><th>说明</th></tr>
                    </thead>
                    <tbody>
                    <?php if(is_array($rules)): foreach($rules as $k=>$vo): ?><tr><td><?php echo ($vo["name"]); ?></td><td><?php echo ($vo["type"]); ?></td><td><?php echo ($vo["require"]); ?></td><td><?php echo ($vo["default"]); ?></td><td><?php echo ($vo["other"]); ?></td><td><?php echo ($vo["desc"]); ?></td></tr><?php endforeach; endif; ?>
                    </tbody>
                </table>
                <h3>返回结果</h3>
                <table class="ui green celled striped table" >
                    <thead>
                        <tr><th>返回字段</th><th>类型</th><th>说明</th></tr>
                    </thead>
                    <tbody>
                    <?php if(is_array($returns)): foreach($returns as $k=>$vo): ?><tr><td><?php echo ($vo["name"]); ?></td><td><?php echo ($vo["type"]); ?></td><td><?php echo ($vo["detail"]); ?></td></tr><?php endforeach; endif; ?>
                    </tbody>
                </table>
                <div class="ui blue message">
                    <strong>温馨提示：</strong> 此接口参数列表根据后台代码自动生成，可将 ?service= 改成您需要查询的接口/服务
                </div>
            </div>
        </div>
    </body>
</html>
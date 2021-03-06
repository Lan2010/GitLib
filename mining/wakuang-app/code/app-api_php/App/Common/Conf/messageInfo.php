<?php

C('phone_Message', array(
    /*     * *****************************验证码 start 如果添加要变更接口文档 ************************************ */
    'Register' => '尊敬的用户：您的注册验证码:%s',
    'Find' => '尊敬的用户: 您正在找回密码，验证码:%s',
    'RealName' => '尊敬的用户:您正在进行实名认证，验证码:%s',
    'SmsCode' => '尊敬的用户：您的验证码:%s',
    'LoginSign' => "您的登录验证码为：%s，不要告诉任何人，以免造成帐号被盗风险！",
    /*     * *****************************验证码 end ************************************ */

    /*     * *****************************业务短信 start  ************************************ */
    'Loan' => '尊敬的 %s :您的标的 %s 已满标,放款资金已入账.',
    'Repay' => '尊敬的 %s :您的标的 %s 第 %s 期已还款，还款金额为￥%.2f.',
    'RealNamePass' => '尊敬的%s :您的实名认证已经通过，感谢您的合作.',
    'RealNameNoPass' => '尊敬的 %s :您的实名认证没有通过，原因：%s',
    'WithdrawVerify' => '尊敬的 %s :您的提现申请已经审核通过,财务即将打款,请注意查收.',
    'Tender' => '尊敬的 %s: 您的出借 %s ;出借金额为￥%.2f ; 已出借成功.',
    'RepayCollect' => '尊敬的 %s: 您的出借 %s ; 第 %s 期已还款 ;还款金额为￥%.2f',
    'Reg_Success' => '恭喜您注册成功!68元红包已到账，还有600元红包等着您！下载APP( t.cn/RITGwWJ )轻松查看',
    'AwaitRepay' => '您的账户将于%s扣款%.2f元。请于扣款日上午10点前存入足够资金。您可登录钱盒子帐号，随时查询借款信息',
    /*     * *****************************业务短信 end ************************************ */

    /*     * *****************************生日短信 start  ************************************ */
    'Birthday' => '世界那么大，遇见即是缘分！今天是您的生日，衷心祝您生日快乐！100元红包已送达您的账户，钱盒子金融感恩一路有您相伴',
    /*     * *****************************生日短信 end ************************************ */
    /*     * *****************************加息券过期短信 start  ************************************ */
    'XJTicketRemind' => '亲爱的小主，%s元红包将于3天后到期，请小主尽快使用，详情登录官网 www.qianhezi.cn 或APP( t.cn/RITGwWJ )',
    /*     * *****************************加息券过期短信 end ************************************ */
    /*     * *****************************现金券过期短信 start  ************************************ */
    'JXTicketRemind' => '亲爱的小主，%d张加息券将于3天后到期，请小主尽快使用，详情登录官网 www.qianhezi.cn 或APP( t.cn/RITGwWJ )',
        /*         * *****************************现金券过期短信 end ************************************ */
));
C('site_Message', array(
    'Register' => array('title' => '注册成功',
        'body' => '恭喜您注册成功，获得68元红包，还有600元大礼包等着您哦！',
    ),
    'OpenedEAccount' => array('title' => '开通存管账户成功',
        'body' => '恭喜您成功开通存管账户，获得120元红包，还有480元大礼包等着您哦！',
    ),
    'Recharge' => array('title' => '充值成功',
        'body' => '恭喜您成功充值%.2f元，祝您出借愉快！',
    ),
    'FirstRecharge' => array('title' => '首次充值成功',
        'body' => '您首次充值%.2f元已成功，获得130元红包，还有350元大礼包等着您哦！',
    ),
    'Tender' => array('title' => '出借成功',
        'body' => '您出借的项目【%s】已成功，出借金额为%.2f元，感谢您对钱盒子的支持！',
    ),
    'FirstTender' => array('title' => '首次出借成功',
        'body' => '您首次出借%.2f元已成功，获得350元红包，还有一份神秘大礼等着您哦！',
    ),
    'RepayCollect' => array('title' => '回款成功',
        'body' => '您出借的项目【%s】第%s期已回款，回款金额为%.2f元。',
    ),
    'CashSuccess' => array('title' => '提现成功',
        'body' => '您提现%.2f元已成功。温馨提示：提现至存管账户为实时到帐，提现至银行卡以实际到帐时间为准。',
    ),
    'InviteFriend' => array('title' => '邀请活动现金券奖励',
        'body' => '您邀请的好友%s已完成首次出借，%d元红包已发放至您的钱盒子账户，请留意查收！',
    ),
    'accountMoney' => array('title' => '可用余额提醒',
        'body' => '您的可用余额超过5000了，赶紧去出借，站岗会有很大的损失喔！',
    )
));
C('email_Message', array(
    'EmailCode' => '您好！您正在进行邮箱绑定，邮箱验证码为：%s（30分钟内有效，请及时完成绑定）',
));

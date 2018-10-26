<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\News\Logic;

use Common\Common\Redis;

class SendEmailLogic {

    /**
     *  发送邮件公共方法
     * @param type $to 接收邮件者邮箱
     * @param type $name 用户名
     * @param type $subject
     * @param type $content
     * @return boolean
     */
    public function SendSystemEmail($to, $subject, $htmlcontent, $txtcontent) {
        $toemail = $this->send_Mail($to, $subject, $htmlcontent, $txtcontent);
        if ($toemail === true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 邮件发送函数
     */
    private function send_Mail($to, $subject, $htmlcontent, $txtcontent) {
        Vendor('PHPMailer.PHPMailerAutoload');
        $mail = new \PHPMailer(); //实例化
        $mail->IsSMTP(); // 启用SMTP
        $mail->Host = C('MAIL_HOST'); //smtp服务器的名称（这里以QQ邮箱为例）
        $mail->SMTPAuth = C('MAIL_SMTPAUTH'); //启用smtp认证
        $mail->Username = C('MAIL_USERNAME'); //你的邮箱名
        $mail->Password = C('MAIL_PASSWORD'); //邮箱密码
        $mail->From = C('MAIL_FROM'); //发件人地址（也就是你的邮箱地址）
        $mail->FromName = C('MAIL_FROMNAME'); //发件人姓名
        $mail->AddAddress($to, "尊敬的客户");
        $mail->WordWrap = 50; //设置每行字符长度
        $mail->IsHTML(C('MAIL_ISHTML')); // 是否HTML格式邮件
        $mail->CharSet = C('MAIL_CHARSET'); //设置邮件编码
        $mail->Subject = $subject; //邮件主题
        $mail->Body = $htmlcontent; //邮件内容
        $mail->AltBody = $txtcontent;
        return($mail->Send());
    }

    /**
     * 发送邮箱验证码
     * @param type $userID
     * @param type $phone
     */
    public function sendEmailCode($userID, $email, $htmlcontent) {
        $arr = array("status" => 1, "data" => "");
        $key = "Send_2_EmailCode_" . $userID;
        $talKey = "Send_60_EmailCode_User_" . $userID;
        $countKey = "Send_60_EmailCode_Email_" . base64_encode($email);
        $validKey = "Valid_EmailCode_" . $userID . "_" . base64_encode($email);
        $redis = Redis::GetInstance();
        $over = $redis->get($key);
        $varlast = 120 - (time() - $over) - 1;
        if ($varlast * 1 > 1) {
            return "请 " . $varlast . " 秒后再重试获取！";
        }
        $mailcount = $redis->get($countKey);
        if (!empty($mailcount) && $mailcount * 1 == 5) {
            return "该邮箱获取验证码过多！";
        }
        $count = $redis->get($talKey);
        if (!empty($count) && $count * 1 == 5) {
            return "1小时内最多只能获取5次邮箱验证码！";
        }
        $code = rand_code();
        $redis->set($key, time(), 120);
        $redis->set($validKey, $code, 1800);
        $redis->set($countKey, $mailcount * 1 + 1, 3600); //设置一小时只能发送5封邮件
        $redis->set($talKey, $count * 1 + 1, 3600); //设置一小时只能发送5封邮件
        $emailtArr = C('email_Message');
        $txtcontent = $emailtArr["EmailCode"];
        $txtcontent = sprintf($txtcontent, $code);
        if (!empty($htmlcontent)) {
            $htmlcontent = sprintf($htmlcontent, $code, date("Y年m月d日"));
        } else {
            $htmlcontent = $txtcontent;
        }
        $result = $this->SendSystemEmail($email, '钱盒子用户邮箱验证邮件', $htmlcontent, $txtcontent);
        if ($result === true) {
            return true;
        } else {
            return "邮箱验证码发送失败，请稍后再试！";
        }
    }

    /**
     * 验证邮箱验证码
     * @param type $userID
     * @param type $email
     * @param type $code
     * @return boolean
     */
    public function checkEmailCode($userID, $email, $code) {
        $validKey = "Valid_EmailCode_" . $userID . "_" . base64_encode($email);
        $key = "Send_2_EmailCode_" . $userID;
        $talKey = "Send_60_EmailCode_User_" . $userID;
        $countKey = "Send_60_EmailCode_Email_" . base64_encode($email);
        $redis = Redis::GetInstance();
        $validcode = $redis->get($validKey);
        if (!empty($validcode) && $validcode == $code && strlen($validcode) > 3) {
            $redis->del($validKey);
            $redis->del($key);
            $redis->del($talKey);
            $redis->del($countKey);
            return true;
        }
        return false;
    }

}

?>

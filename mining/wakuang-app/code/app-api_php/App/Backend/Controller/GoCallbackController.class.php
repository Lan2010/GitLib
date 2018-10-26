<?php
/**
 * Created by PhpStorm.
 * User: RLY
 * Date: 2018/6/15
 * Time: 11:14
 */

namespace Backend\Controller;

use Think\Controller;

/**
 * Description of GoCallbackController
 *
 * @author DREAM
 */
class GoCallbackController extends Controller
{

    private $data; //参数
    private $sign; //签名

    public function __construct()
    {
        parent::__construct();
        $responseInfo = file_get_contents('php://input');
        $inputParam = stripcslashes($responseInfo);
        $this->data = json_decode($inputParam, TRUE);
        $this->sign = sha1(md5($this->data["returnurl"]) . "starchain");
        if ($this->sign != $this->data["sign"]) {
            logger("ERROR-------Golang业务回调签名错误：", $this->data["returnurl"]);
            echo 'ok';
            exit();
        }
        logger("Start-------Golang业务回调参数：", $inputParam);
    }

    /**
     * 定时器固定每天执行一次（大概在凌晨3分执行）
     */
    public function rabTimer()
    {
        echo 'ok';
        logger("定期器消息：rabTimer", $this->data["returnurl"]);
    }

    /**
     * 定时器固定每5分钟执行一次
     */
    public function rab5Minute()
    {
        echo 'ok';
        logger("定期器消息：rab5Minute", $this->data["returnurl"]);
    }
}
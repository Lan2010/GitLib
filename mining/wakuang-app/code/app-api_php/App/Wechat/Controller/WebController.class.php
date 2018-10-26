<?php

namespace Wechat\Controller;
use Think\Controller;

class WebController extends Controller{

    public function getServiceAgreement() {
        $this->display("index");
    }


    public function web() {
        $this->display("web");
    }


    public function gengc() {
        echo "<h1>正在开发中</h1>";
    }
}
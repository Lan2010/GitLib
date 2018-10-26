<?php

namespace Common\Common;

/**
 * 用户邀请码
 *
 * Class InvalidCode
 * @package Common\Common
 */
class InvalidCode
{

    /**
     * 验证码长度
     */
    const LEN = 6;

    /**
     * 邀请码元素集
     */
    const STUFFS = array('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', '1', '2', '3', '4', '5', '6', '7', '8');

    //$this->factorial(self::LEN);
    const PERMUTATION = 720;

    /**
     * 最大组合
     */
    //$this->getCombination(count(self::STUFFS), self::LEN);
    const MAX_COMBINATION = 906192;

    private static $_instance;


    /**
     * 单列
     *
     * @return InvalidCode
     */
    public static function getInstance()
    {
        if (!(self::$_instance instanceof self)) {
            self::$_instance = new self();
        }
        return self::$_instance;
    }

    /**
     * 生成邀请码
     *
     * @param $userId
     * @return string
     */
    public function generate($userId)
    {
        $com = $userId / self::PERMUTATION;
        if ($com >= self::MAX_COMBINATION) {
            return "";
        }
        $per = $userId % self::PERMUTATION;
        $chars = $this->combination($com);
        $chars = $this->permutation($chars, $per);
        return implode("", $chars);
    }

    /**
     * 阶乘函数
     *
     * @param $n
     * @return int
     */
    private function factorial($n)
    {
        $per = 1;
        for ($i = 2; $i <= $n; ++$i) {
            $per *= $i;
        }
        return $per;
    }

    private function getCombination($n, $m)
    {
        $com = 1;
        for ($i = $n - $m + 1; $i <= $n; ++$i) {
            $com *= $i;
        }
        for ($i = 2; $i <= $m; ++$i) {
            $com /= $i;
        }
        return $com;
    }

    /**
     *生成邀请码组合
     *
     * @param $com
     * @return array
     */
    private function combination($com)
    {
        $chars = array();
        $start = 0;
        $index = 0;
        $length = count(self::STUFFS);
        while ($index < self::LEN) {
            for ($i = $start; $i < $length; ++$i) {
                $c = $this->getCombination($length - $i - 1, self::LEN - $index - 1);
                if ($com >= $c) {
                    $com -= $c;
                    continue;
                }
                $chars[$index++] = self::STUFFS[$i];
                $start = $i + 1;
                break;
            }
        }
        return $chars;
    }


    /**
     * 排列邀请码
     *
     * @param $chars
     * @param $per
     * @return mixed
     */
    private function permutation($chars, $per)
    {
        $tmpchars = $chars;
        $offset = array();
        $charscount = $step = count($chars);
        for ($i = $charscount - 1; $i >= 0; --$i) {
            $offset[$i] = $per % $step;
            $per /= $step;
            $step--;
        }

        for ($i = 0; $i < $charscount; ++$i) {
            if ($offset[$i] == 0)
                continue;
            $tmp = $tmpchars[$i];
            $tmpchars[$i] = $tmpchars[$i - $offset[$i]];
            $tmpchars[$i - $offset[$i]] = $tmp;
        }

        return $tmpchars;
    }

}
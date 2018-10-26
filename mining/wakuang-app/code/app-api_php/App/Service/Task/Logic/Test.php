<?php
/**
 * Created by PhpStorm.
 * User: RLY
 * Date: 2018/8/11
 * Time: 10:29
 */
$age=array("Peter"=>"35","Ben"=>"\"Ben\"=>\"37\"","Ben01"=>"37");
echo  'daw';
foreach ($age as $key=>$value) {
    echo $value;
    if($value == "37") {
        echo $value;
        $starPoint = $value;
    }
}
echo  "ad".$starPoint;


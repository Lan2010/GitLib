<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Service\System\Model;

use Common\Model\SlaveModel;

class SysRegionModel extends SlaveModel {
    
    /**
     * 查出所有的列表
     * 
     */
    public function getRegionList(){
        
        $list = $this->select();
        return $list;
    }
    
}

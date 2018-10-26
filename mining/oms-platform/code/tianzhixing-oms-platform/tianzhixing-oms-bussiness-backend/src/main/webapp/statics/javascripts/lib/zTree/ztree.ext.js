/**
 * @auhor jhyu
 * note [扩展封装ztree，适用于组织机构管理模块、权限管理模块、所属组织机构架构]
 */
(function() {
    var multiTree = {},
        zNodeData = [],
        targetTree,
        targetTreeId,
        btnTree,
        inputTree,
        inputTreeId,
        divTree,
        inputTreeOffset,
        orgInstitutionIds = [], 
        orgId,
        cachefunc;
    /**
     * [createZtree 创建树方法]
     * @param  Object {  
     *         target: 初始化树对应元素 $('#....') [必填]
     *         url: 请求地址 'string' [可选]
     *         type：请求类型 'post' or 'get' [可选]
     *         data: 请求数据 [Array] [可选]
     *         dataField: 请求返回的最终数组 [必填] 默认'list'
     *         zNodes：自传入数据 [Array] [可选]
     *         setting： 配置 [必填] 详情请查阅：http://www.ztree.me/v3/api.php
     *         async： 是否异步 true or false [可选] 
     *     } 
     */
    multiTree.initZtree = function(param) {
        param = param || {};

        if (typeof(param) === 'object') {

            /**
             * 选择 ajax方式创建数据
             */
            if (param.target && param.url) {
                param.data = param.data || {};

                ajaxUtil.ajax(param.url, param.data, param.type, function(rep) {
                    if (rep.code !== 0 || rep.data[param.dataField].length === 0) {
                        zNodeData = [{
                            id: 1,
                            pId: 0,
                            name: "暂无相关数据",
                            open: true
                        }];
                    } else {
                        zNodeData = rep.data[param.dataField === '' ? 'list' : param.dataField];
                    }

                    $.fn.zTree.init($(param.target), param.setting, zNodeData);

                }, null, "JSON", null, null, param.async);
            }

            /**
             * 选择 自组装数据传入
             */
            if (param.target && (param.zNodes && param.zNodes.length > 0) && param.setting) {
                zNodeData = param.zNodes;

                $.fn.zTree.init($(param.target), param.setting, zNodeData);
            }

        } else {
            return layer.alert('请传入对象！');
        }
    }

    /**
     * setList 得到返回数据 array
     */
    multiTree.setList = function(institutions) {
        var list = [];

        if (institutions.length > 0) {
            for (var i in institutions) {
                list.push($.extend(institutions[i], {
                    open: true
                }));
            }
        }

        return list;
    }

    /**
     * setId 得到返回数据 id
     */
     multiTree.setId = function (id) {
        return orgId = id;
    }

    /**
     * setOrg
     * @param array
     * return array
     */
    multiTree.setOrg = function (list) {
        if (list.length > 0) {
            orgInstitutionIds = list;
        } else {
            orgInstitutionIds = "";
        }
        return orgInstitutionIds;
    }

    /**
     * getOrg return array
     */
    multiTree.getOrg = function () {
        if (orgId) {
            return orgId;
        } else {
            return orgInstitutionIds;
        }
    }

    /**
     * 显示树方法
     */
    multiTree.showMenu = function() {
        var btnTree = $(arguments[0]),
            inputTree = $(btnTree).siblings('input'),
            inputTreeId = $(inputTree).attr('id'),
            divTree = $(btnTree).siblings('.organizationTreeWrap'),
            targetTree = $(divTree).children('ul'),
            targetTreeId = $(targetTree).attr('id'),
            inputTreeOffset = $(inputTree).offset();


        $(divTree).fadeIn("fast");
        $(targetTree).focus();
        $(targetTree).css({
            left: inputTreeOffset.left + "px",
            top: inputTreeOffset.top + btnTree.outerHeight() + "px"
        }).slideDown("fast");
        $("body").bind("mousedown", onBodyDown);
    }

    /**
     * 隐藏树方法
     */
    multiTree.hideMenu = function () {
        $('.organizationTreeWrap').fadeOut("fast");
        $('.organizationTreeWrap').children('ul').fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);

        multiTree.callback();
    }

    multiTree.callback = function (func) {
        if (multiTree.callback && typeof multiTree.callback === 'function' && func) {
            cachefunc = func;
        }

        if (cachefunc) {
            cachefunc();
        }
    }

    /**
     * 关闭树
     */
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "organizationInstitutionInput" || event.target.id == "organizationInstitutionTree" || $(
                event.target).parents("#organizationInstitutionTree").length > 0)) {
            multiTree.hideMenu();
        }
    }

    window.multiTree = multiTree;
}());

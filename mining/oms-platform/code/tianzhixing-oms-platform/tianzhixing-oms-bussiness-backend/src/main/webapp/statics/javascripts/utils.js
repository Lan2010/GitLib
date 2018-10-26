/**
 *
 * utils note : 回调函数参数，当该值为单个元素时直接使用该值，如果为多个元素请使用对象
 * @author Sunset
 *
 */
var utils = (function () {
	
	/**
	 * 显示错误信息
	 */
	var showError = function(data) {
		data && layer.msg(data.msg || "系统异常!", {icon: 5});
	};
	
    /**
     * 生成多个select的option（用在多个select中的optionValue,optionText一样）
     * @param selectObj {} 选项对象 {"name" : "url"} 键值对 key : select元素名称，url : select请求的路径
     * @param selectedValue 选中的值===需要回显的值
     * @param optionValue 请求返回JSON数据中的key名，如product.value(暂支持.分割)，获取JSON中指定名称为optionValue的数据作为option的Value
     * @param optionText 请求返回JSON数据中的key名，如product.name(暂支持.分割)，获取JSON中指定名称为optionText的数据作为option的Text
     *
     */
    var generateOptions = function (selectObj, selectedValue, optionValue, optionText) {
        for (var k in selectObj) {
            generateOption(selectObj[k], $("select[name='" + k + "']"), selectedValue, optionValue, optionText);
        }
    };

    /**
     * 生产单个select的option
     * @param url 请求地址（必填）
     * @param ele select对象（必填）
     * @param val 回显的值
     * @param optionVal option对于的value值
     * @param optionText option对于的text值
     * @param key {"code":0,"msg":"查询成功","data":{"list":[{ 对应list
     * @param data
     * @param func 回调函数
     * @param funcParamsObj 回调函数参数对象
     * @param disable 是否禁用请选择选项
     * @param store 用于缓存数据 {"key" : data(object)}
     * @param mkey 用于获取store中的值
     * @param firstText 第一个选项显示的文本（只有当disable不为true时有效）
     * @param firstVal 第一个选项的value（只有当disable不为true时有效）
     * @param hideKey 隐藏的key
     */
    var generateOption = function (url, ele, val, optionVal, optionText, key, data, func, funcParamsObj, disable, store, mkey, firstText, firstVal, hideKey) {
        //优先判断缓存是否存在
        if (mkey && store[mkey]) {
            handleOption(ele, val, optionVal, optionText, key, store[mkey], func, funcParamsObj, disable, store, mkey, firstText, firstVal, hideKey)
        } else {
            if (url && ele) {
                ajaxUtil.ajax(url, data, "get", function (resp) {
                    handleOption(ele, val, optionVal, optionText, key, resp, func, funcParamsObj, disable, store, mkey, firstText, firstVal, hideKey);
                });
            } else {
                callback(func, funcParamsObj);
                console.log("The warning ocurred while generating options and the message is : parameters were missed");
            }
        }
    };

    /**
     * 处理生成下拉选项
     * @param data
     *
     */
    var handleOption = function (ele, val, optionVal, optionText, key, data, func, funcParamsObj, disable, store, mkey, firstText, firstVal, hideKey) {
        if (data.code === 0 && data.data) {
            key = key || "list";
            var _list = data.data[key];
            var _options, firstText = firstText || "--请选择--", firstVal = firstVal || "";
            _options = !disable ? "<option value='" + firstVal + "'>" + firstText + "</option>" : "";
            ele.empty();// 如果元素下已经包含option选项
            if (_list && _list.length) {
                store && mkey && (store[mkey] = data);
                optionVal = optionVal || "id";
                optionText = optionText || "name";
                $.each(_list, function (index, option) {
                    if (option) {
                        var _text = handleOptionText(optionText, option);
                        var _value = getJSONvalue(option, optionVal);
                        var _selected = "";
                        if (val && val == _value) {// 处理回显选中
                            _selected = " selected='selected' ";
                            ele.val(_value);
                        }
                        var hideVal = handleHideAttr(hideKey, option);
                        _options += "<option value='" + _value + "'" + _selected + hideVal + ">" + _text + "</option>";
                    }
                });
            }
            ele.append(_options);
            callback(func, funcParamsObj);
        } else {
        	showError(data);
        }
    };

    /**
     * 生成多个checkbox（用在多个元素中的optionValue,optionText一样）
     * @param selectObj {} 选项对象 {"name" : "url"} 键值对 key : select元素名称，url : select请求的路径
     * @param selectedValue 选中的值===需要回显的值
     * @param optionValue 请求返回JSON数据中的key名，如product.value(暂支持.分割)，获取JSON中指定名称为optionValue的数据作为option的Value
     * @param optionText 请求返回JSON数据中的key名，如product.name(暂支持.分割)，获取JSON中指定名称为optionText的数据作为option的Text
     * @param optionName input元素的name
     *
     */
    var generateCheckboxs = function (selectObj, selectedValue, optionValue, optionText, optionName) {
        for (var k in selectObj) {
            generateCheckbox(selectObj[k], $("select[name='" + k + "']"), selectedValue, optionValue, optionText);
        }
    };

    /**
     * 处理生成复选框业务
     * @param ele
     * @param val
     * @param optionVal
     * @param optionText
     * @param optionName
     * @param key
     * @param data
     * @param func
     * @param funcParamsObj
     * @param store
     * @param mkey
     * @param enable
     */
    var handleCheckBox = function (ele, val, optionVal, optionText, optionName, key, data, func, funcParamsObj, store, mkey, enable) {
        if (data.code === 0 && data.data) {
            key = key || "list";
            var _list = data.data[key], _options = ""; 
            ele.empty();// 如果元素下已经包含option选项
            if (_list && _list.length) {
                store && mkey && (store[mkey] = data);
                optionVal = optionVal || "id";
                optionText = optionText || "name";
                var _idAll = optionName + "_All", _checked;
                if (enable !== false) {
                    val && _list.length == val.length && (_checked = "checked");
                    _options += '<div class="checkbox checkbox-success checkbox-inline">' +
                        '<input type="checkbox" value="" id="' + _idAll + '" name="' + _idAll + '" ' + _checked + '>' +
                        '<label for="' + _idAll + '">全选/反选</label></div>';
                }
                $.each(_list, function (index, option) {
                    if (option) {
                        var _text = handleOptionText(optionText, option);
                        var _value = getJSONvalue(option, optionVal);
                        var _checked = "", _id = optionName + "_" + _value;
                        if (val && val.constructor === Array) {// 处理回显选中
                            if (val && val.indexOf(_value) !== -1) {
                                _checked = " checked='checked' ";
                            }
                        } else {
                            if (val && val == _value) {// 处理回显选中
                                _checked = " checked='checked' ";
                            }
                        }
                        _options += '<div class="checkbox checkbox-success checkbox-inline">' +
                            '<input type="checkbox" id="' + _id + '" name="' + optionName + '" value="' + _value + '"' + _checked + '>' +
                            '<label for="' + _id + '">' + _text + '</label></div>';
                    }
                });
                ele.append(_options);
                //生成验证html
                ele.append('<label for="' + optionName + '" class="error">请勾选</label>');
                $("#" + _idAll).on("click", function () {
                    $(this).is(":checked") && $("[name='" + optionName + "']").prop("checked", true);
                    !$(this).is(":checked") && $("[name='" + optionName + "']").prop("checked", false);
                });
            } else {
            	ele.append(_options);
            }
            callback(func, funcParamsObj);
        } else {
        	showError(data);
        }
    }

    /**
     * 生产单个checkbox
     * @param url 请求地址（必填）
     * @param ele select对象（必填）
     * @param val 回显的值
     * @param optionVal option对于的value值
     * @param optionText option对于的text值
     * @param optionName input元素的name
     * @param key {"code":0,"msg":"查询成功","data":{"list":[{ 对应list
     * @param data
     * @param func 回调函数
     * @param funcParamsObj 回调函数参数对象
     * @param store
     * @param mkey
     * @param enable 是否禁用生成全选/反选按钮，default ： 生成全选/反选
     *
     */
    var generateCheckbox = function (url, ele, val, optionVal, optionText, optionName, key, data, func, funcParamsObj, store, mkey, enable) {
        //优先判断缓存是否存在
        if (mkey && store[mkey]) {
            handleCheckBox(ele, val, optionVal, optionText, optionName, key, store[mkey], func, funcParamsObj, store, mkey, enable);
        } else {
            if (url && ele) {
                ajaxUtil.ajax(url, data, "get", function (resp) {
                    handleCheckBox(ele, val, optionVal, optionText, optionName, key, resp, func, funcParamsObj, store, mkey, enable);
                });
            } else {
                callback(func, funcParamsObj);
                console.log("The warning ocurred while generating options and the message is : parameters were missed");
            }
        }
    };

    /**
     * 生成多个radio（用在多个元素中的optionValue,optionText一样）
     * @param selectObj {} 选项对象 {"name" : "url"} 键值对 key : select元素名称，url : select请求的路径
     * @param selectedValue 选中的值===需要回显的值
     * @param optionValue 请求返回JSON数据中的key名，如product.value(暂支持.分割)，获取JSON中指定名称为optionValue的数据作为option的Value
     * @param optionText 请求返回JSON数据中的key名，如product.name(暂支持.分割)，获取JSON中指定名称为optionText的数据作为option的Text
     * @param optionName input元素的name
     *
     */
    var generateRadios = function (selectObj, selectedValue, optionValue, optionText, optionName) {
        for (var k in selectObj) {
            generateCheckbox(selectObj[k], $("select[name='" + k + "']"), selectedValue, optionValue, optionText);
        }
    };

    /**
     * 处理生成单选按钮
     * @param ele
     * @param val
     * @param optionVal
     * @param optionText
     * @param optionName
     * @param key
     * @param data
     * @param func
     * @param funcParamsObj
     * @param store
     * @param mkey
     */
    var handleRadios = function (ele, val, optionVal, optionText, optionName, key, data, func, funcParamsObj, store, mkey) {
        if (data.code === 0 && data.data) {
            key = key || "list";
            var _list = data.data[key], _options = "";
            ele.empty();// 如果元素下已经包含option选项
            if (_list && _list.length) {
                store && mkey && (store[mkey] = data);
                optionVal = optionVal || "id";
                optionText = optionText || "name";
                $.each(_list, function (index, option) {
                    if (option) {
                        var _text = handleOptionText(optionText, option);
                        var _value = getJSONvalue(option, optionVal);
                        var _checked = "", _id = optionName + "_" + _value;
                        // 处理默认选中第一个
                        if ((val === null || val === '' || val === undefined) && index === 0) {
                            _checked = " checked='checked' ";
                        }
                        if (val && val == _value) {// 处理回显选中
                            _checked = " checked='checked' ";
                        }
                        _options += '<div class="radio radio-success radio-inline">' +
                            '<input type="radio" id="' + _id + '" name="' + optionName + '" value="' + _value + '"' + _checked + '>' +
                            '<label for="' + _id + '">' + _text + '</label></div>';
                    }
                });
                ele.append(_options);
                // 生成验证html
                ele.append('<label for="' + optionName + '" class="error">请勾选</label>');
            } else {
            	ele.append(_options);
            }
            callback(func, funcParamsObj);
        } else {
        	showError(data);
        }
    };

    /**
     * 处理隐藏属性
     * @param key
     * @param data
     * @returns {string}
     */
    var handleHideAttr = function (key, data) {
        var _str = "";
        if (key && data) {
            if (key.constructor === Array) {
                for (var i in key) {
                    var k = key[i];
                    _str += " " + k + "='" + data[k] + "'";
                }
            } else {
                _str = " " + key + "='" + data[key] + "'";
            }
        }
        return _str;
    };

    /**
     * 处理option文本
     * @param optionText
     * @param option
     * @returns {*}
     */
    var handleOptionText = function (optionText, option) {
        var _text;
        if (optionText.constructor === Array) {
            for (var k in optionText) {
                _text && _text.length ? _text += "(" + getJSONvalue(option, optionText[k]) + ")" : _text = getJSONvalue(option, optionText[k]);
            }
        } else {//其他情况当做字符串处理
            _text = getJSONvalue(option, optionText);
        }
        return _text;
    };

    /**
     * 生产单个radio
     * @param url 请求地址（必填）
     * @param ele select对象（必填）
     * @param val 回显的值
     * @param optionVal option对于的value值
     * @param optionText option对于的text值
     * @param optionName input元素的name
     * @param key {"code":0,"msg":"查询成功","data":{"list":[{ 对应list
     * @param data
     * @param func 回调函数
     * @param funcParamsObj 回调函数参数对象
     * @param store
     * @param mkey
     *
     */
    var generateRadio = function (url, ele, val, optionVal, optionText, optionName, key, data, func, funcParamsObj, store, mkey) {
        if (mkey && store[mkey]) {// 存在则获取第一个
            handleRadios(ele, val, optionVal, optionText, optionName, key, store[mkey], func, funcParamsObj, store, mkey)
        } else {
            if (url && ele) {
                ajaxUtil.ajax(url, data, "get", function (resp) {
                    handleRadios(ele, val, optionVal, optionText, optionName, key, resp, func, funcParamsObj, store, mkey);
                });
            }
        }
    };

    /**
     * select 联动查询,将ele的值作为参数请求服务器生成targetEle的option选项,支持N级
     * @param obj {}（必填）
     * @param obj.ele 当前select对象,父级select对象（必填）
     * @param obj.targetEle 目标select对象，子级select对象（必填）
     * @param obj.targetUrl 目标请求地址（必填）
     * @param obj.param obj.data对于的key, 即父级select的值在目标请求URL中对应的参数名,不传时默认用ele名称作为参数名
     * @param obj.selectVal 目标回显值
     * @param obj.optionVal 生成目标select下option value的key
     * @param obj.optionText 生成目标select下option text的key
     * @param obj.data 目标请求URL的参数对象
     * @param obj.key 解析响应数据列表中对于的Key名称
     * @param obj.callback 回调函数
     * @param obj.callbackParamsObj 回调函数参数对象
     * @param obj.next 下一个元素对象(构造为当前obj结构一样的对象)
     */
    var selectChange = function (obj) {
        if (obj && typeof obj === "object") {
            if (obj.ele && obj.targetEle && obj.targetUrl) {
                obj["param"] = obj["param"] || obj.ele.attr("name");
                obj.data = obj.data || {};
                obj.ele.off("change");
                obj.ele.on("change", function (e) {
                    var v = $(this).val();
                    if (v) {
                        // 处理参数
                        obj["data"][obj.param] = v;
                        generateOption(obj.targetUrl, obj.targetEle, obj.selectVal, obj.optionVal, obj.optionText, obj.key, obj.data);
                    } else {
                        obj.targetEle.empty().append("<option value=''>--请选择--</option>");
                    }
                });
                // 回显值
                if (obj.selectVal) {
                    generateOption(obj.targetUrl, obj.targetEle, obj.selectVal, obj.optionVal, obj.optionText, obj.key, obj.data);
                }
            }
        }
        callback(obj.callback, obj.callbackParamsObj);
        // 递归处理
        if (obj.next && typeof obj.next === "object") {
            arguments.callee(obj.next);
        }
    };
    /**
     * select zTree关闭时联动请求服务端生成下一级select的option
     * @param obj [] || {}（必填）{}对象为单个，[]数组时为多个
     * @param obj.zTreeId zTreeId（必填）
     * @param obj.targetEle 目标select对象，子级select对象（必填）
     * @param obj.targetUrl 目标请求地址（必填）
     * @param obj.selectVal 目标回显值
     * @param obj.param obj.data对于的key, 即父级Ztree的值在目标请求URL中对应的参数名,不传时默认用ZTree名称作为参数名
     * @param obj.optionVal 生成目标select下option value的key
     * @param obj.optionText 生成目标select下option text的key
     * @param obj.data 目标请求URL的参数对象
     * @param obj.key 解析响应数据列表中对于的Key名称
     * @param obj.callback 回调函数
     * @param obj.callbackParamsObj
     *
     */
    var zTreeClose = function (obj) {
        if (obj) {
            if (obj.constructor === Array) {
                $.each(obj, function (i, o) {
                    _handleOption(o);
                });
            } else if (typeof obj === "object") {
                _handleOption(obj);
            }
        }
    };

    /**
     *
     * @param obj
     */
    function _handleOption(obj) {
        if (obj && obj.zTreeId && obj.targetUrl && obj.targetEle) {
            //depend on zTree
            var treeObj = $.fn.zTree.getZTreeObj(obj.zTreeId);
            // 获取zTree 选中的值
            var nodes = treeObj.getCheckedNodes(true);
            if (nodes && nodes.length > 0) {
                if (obj.param) {
                    var data = [];
                    $.each(nodes, function (i, n) {
                        data.push(n.id);
                    });
                    //obj.param 如果和obj.data里面重复会覆盖掉原始参数
                    obj["data"][obj.param] = data;
                }
                generateOption(obj.targetUrl, obj.targetEle, obj.selectVal, obj.optionVal, obj.optionText, obj.key, obj.data);
            } else {
                obj.targetEle.empty().append("<option value=''>--请选择--</option>");
            }
        }
        callback(obj.callback, obj.callbackParamsObj);
    }


    /**
     * 下载 jQuery.download 可以扩展为JQuery的函数
     * @param url
     * @param data "param1=2&param2=2" || {"ids" : ["1", "2"] , "name" : "测试"}
     * @param type POST || GET
     */
    var download = function (url, data, type) {
        if (url) {
            url = config.api + url;
            type = type || "POST";
            var iframe = $("#download_frame");
            iframe = iframe.length > 0 ? iframe : $("<iframe name='download_frame' style='display:none;' id='download_frame' />");
            $('body').append(iframe);
            var form = $("<form/>");
            form.attr('target', 'download_frame');// 指向iframe
            form.attr('method', type);
            form.attr('action', url);
            iframe.append(form);
            if (data) {
                data = typeof data == 'string' ? data : decodeURIComponent($.param(data));// 把参数组装成 form的  input
                $.each(data.split('&'), function (i, d) {
                    var splits = d.split('=');
                    form.append('<input type="hidden" name="' + splits[0] + '" value="' + splits[1] + '" />');
                });
            }
            form.submit();
            form.remove();
            iframe.get(0).onload = function () {
                var response, responseStr = iframe.contents().text();
                try {
                    response = JSON.parse(responseStr);
                } catch (e) {
                    response = responseStr;
                }
                if (response) {
                    layer.msg(response.msg || "导出数据错误！", {icon : 5});
                }
            };
        }
    };

    /**
     * 获取JSON对象的属性值
     * @param json
     * @param key key的命名方式 eg : product.name
     */
    var getJSONvalue = function (json, key) {
        if (json && key) {
            var _ks = key.split(".");
            if (_ks && _ks.length > 0) {
                var _k = _ks[0], _co = json[_k], begin = _k.indexOf("["), _length = _k.length;
                // check '_k' is really Array
                if (begin != -1 && _k.charAt(_length - 1) === "]") {
                    var i = _k.slice(begin + 1, _length - 1);//get index eg : 'contacts[1]' we got 1
                    _k = _k.slice(0, begin);//get key 'eg:contacts[1]' we got 'contacts'
                    if (json[_k]) {// check json[_k] is already exist
                        _co = json[_k][i];
                        // 4 = .[0] 4 '.[0]'
                        var index = key.indexOf(_k) + _k.length + 4;
                        return getJSONvalue(_co, key.slice(index));
                    }
                    return "";
                } else {
                    if (_co !== null && _co !== '' && _co !== undefined) {
                        if (typeof _co === "object") {// Array || Object
                            // get next property's name if json[_k] is object
                            // get next property'name eg : contacts.name
                            var index = key.indexOf(_k) + _k.length + 1;// there got name's index
                            return getJSONvalue(_co, key.slice(index));// there send object '_co' and 'index' to function
                        }
                        return _co;
                    }
                    return "";
                }
            }
            return json[key];
        } else {
            console.log("The warning ocurred while getting getJSONvalue and the message is : parameters were missed");
        }
    };

    /**
     * 获取URL或者当前window的URL的对应的值
     * @param key (必填)
     * @param url
     *
     */
    var getValueFromUrl = function (key, url) {
        if (key) {
            // ?A=B
            if (url) {
                url = url.slice(url.indexOf("?"));
            } else {
                url = location.search;
            }
            // ?A= || ?A=B
            if (url && url.length >= 3) {
                url = url.slice(1);
                var paramters = url.split("&");
                var paramObj = {};
                for (var i in paramters) {
                    var param = paramters[i];
                    var ar = param.split("=");
                    var k = ar[0];
                    var v = ar[1];
                    // 判断参数是否是数组
                    if (k.indexOf("[]") != -1) {
                        // 判断是否存在key
                        k = k.replace("[]", "");
                        if (paramObj[k]) {
                            paramObj[k].push(v);
                        } else {
                            paramObj[k] = [v];
                        }
                    } else {
                        paramObj[k] = v;
                    }
                }
                return paramObj[key];
            } else {
                return "";
            }
        }
    };

    /**
     * 禁用指定区域下的form元素
     * @param ele
     */
    var disabledElements = function (ele) {
        ele && ele.length && (function () {
            $("textarea, input, select, button:not([undisabled])", ele).prop("disabled", true);
        })();
    };

    /**
     * 启用元素
     * @param ele
     */
    var enableElements = function (ele) {
        ele && ele.length && (function () {
            $("textare, input, select, button:not([undisabled])", ele).prop("disabled", false);
        })();
    };

    /**
     * 表单回填数据
     * @param form表单对象
     * @data 回填JSON数据对象
     * @param func回调函数
     * @param funcParamsObj 回调函数参数对象
     */
    var setFormValue = function (form, data, func, funcParamsObj) {
        if (form && data && typeof data === "object") {
            $("input,select,textarea", form).each(function (i, ele) {
                // input 分为三种 普通input, radio, checkbox
                var name = ele.name;
                ele = $(ele);
                if (ele.is("input[type='text']") || ele.is("input[type='hidden']") || ele.is("textarea")) {
                    ele.val(data[name]);
                } else if (ele.is("input[type='radio']")) {
                    $("input[name='" + name + "'][value='" + data[name] + "']").prop("checked", true);
                } else if (ele.is("input[type='checkbox']")) {
                    // 返回来[a,b,c];
                    if (data[name] && data[name].constructor === Array) {
                        for (var n in data[name]) {
                            $("input[name='" + name + "'][value='" + data[name][n] + "']").prop("checked", true);
                        }
                    } else {
                        $("input[name='" + name + "'][value='" + data[name] + "']").prop("checked", true);
                    }
                } else if (ele.is("select")) {
                    ele.val(data[name]);
                    $("select[name='" + name + "'] > option[value='" + data[name] + "']").prop("selected", true);
                }
            });
        } else {
            console.log("The error ocurred while setting form value and the message is : parameters were missed");
        }
        callback(func, funcParamsObj);
    };

    /**
     * 获取表单的JSON对象字符串
     * @param form
     *
     */
    var getFormJsonString = function (form) {
        var formJson = {};
        if (form) {
            $("input,select,textarea", form).each(function (i, ele) {
                var name = ele.name || ele.id;
                ele = $(ele);
                if (ele.is("input[type='text']") || ele.is("input[type='hidden']") || ele.is("textarea") || ele.is("input[type='password']")) {
                    formJson[name] = ele.val();
                } else if (ele.is("input[type='radio']")) {
                    if (ele.is(":checked")) {
                        formJson[name] = ele.val();
                    }
                } else if (ele.is("input[type='checkbox']")) {
                    if (ele.is(":checked")) {
                        if (formJson[name]) {
                            formJson[name].push(ele.val());
                        } else {
                            formJson[name] = [ele.val()];
                        }
                    }
                } else if (ele.is("select")) {
                    formJson[name] = $("select[name='" + name + "']", form).val();
                }
            });
        } else {
            console.log("The error occured while getFromJsonString and the message is : parameter form was empty!");
        }
        return formJson;
    };

    /**
     * 绑定某个开关元素单击事件
     * @param ele
     */
    var onoffClick = function (ele) {
        if (ele) {
            if (ele.is(":checked")) {
                ele.removeAttr("checked");
                ele.val("false");
            } else {
                ele.attr("checked", true);
                ele.val("true");
            }
        }
    };

    /**
     * 开关元素单击事件
     */
    var onoffswitchlabelBind = function () {
        $(".onoffswitch-label").click(function (e) {
            var id = $(this).attr("for");
            if (id) {
                var ele = $("#" + id);
                if (ele.is(":checked")) {
                    ele.removeAttr("checked");
                    ele.val("false");
                } else {
                    ele.attr("checked", true);
                    ele.val("true");
                }
            }
        });
    };

    /**
     * 回调函数
     */
    var callback = function (func, funcParamsObj) {
        func && typeof func === "function" && func(funcParamsObj);
    };

    /**
     * @param id
     *
     */
    var getInfo = function(arg) {
        //id, url, form, $btn
        arg.id && ajaxUtil.ajax(arg.url, {id : arg.id}, "get", function(resp) {
            if (resp.code === 200) {
                resp.data && resp.data.object && utils.setFormValue($('#'+arg.form), resp.data.object);
            } else {
                layer.msg(resp.msg || "操作失败！", {icon: 5});
            }
        }, null, null, {btn : $('#'+arg.btn)});
    };

    return {
        generateOptions: generateOptions,
        generateOption: generateOption,
        handleOption: handleOption,
        generateCheckboxs: generateCheckboxs,
        generateCheckbox: generateCheckbox,
        generateRadios: generateRadios,
        generateRadio: generateRadio,
        selectChange: selectChange,
        zTreeClose: zTreeClose,
        download: download,
        getJSONvalue: getJSONvalue,
        getValueFromUrl: getValueFromUrl,
        setFormValue: setFormValue,
        getFormJsonString: getFormJsonString,
        callback: callback,
        onoffClick: onoffClick,
        onoffswitchlabelBind: onoffswitchlabelBind,
        disabledElements: disabledElements,
        enableElements : enableElements,
        getInfo : getInfo
    }
}());




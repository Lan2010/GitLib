/**
 * ajaxUtil note : 可扩展
 * @author Sunset
 * 
 */
var ajaxUtil = (function () {
	/*$.support.cors = true;*/
	/**
	 * @param url 请求地址
	 * @param data 请求参数
	 * @param type 请求类型 post,delete,put,get and the default value is "post"
	 * @param dataType 返回数据类型 xml,html,script,json,jsonp,text and the default value is "json"
	 * @param success 成功回调函数
	 * @param error 错误回调函数
	 * @param dataType 
	 * @param setting 操作对象 {
	 * 		disBtn : true, default true
	 * 		btn : $("#btn"),
	 * 		mask : true, default true
	 * 		maskIndex : 遮罩层索引
	 * 		}
	 * @param contentType default: 'application/x-www-form-urlencoded; charset=UTF-8'
	 * @param async default : true
	 */
	var ajax = function(url, data, type, success, error, dataType, setting, contentType, async) {
		if (url && success) {
			$.ajax({
				url : getUrl(url),
				type : getType(type),
//				traditional : true,
				dataType : getDataType(dataType),
				data : getData(data),
				contentType : getContentType(contentType),
				timeout : getTimeout(),
				async: getAsync(async),
				beforeSend : function(settings ) {
					handleBtn(setting, true);
					handleMask(setting, true);
				},
				success : function(data, textStatus) {
					handleSuccess(success, data, textStatus, dataType || getDataType(dataType), setting);
				},
				error : function(textStatus, errorThrown) {
					handleError(error, textStatus, errorThrown, setting);
				}
			});
		} else {
			layer.alert("参数错误！");
		}
	};

	/**
	 * @param url 请求地址
	 * @param data 请求参数
	 * @param form 请求表单
	 * @param type 请求类型 post,delete,put,get and the default value is "post" 
	 * @param dataType 返回数据类型 xml,html,script,json,jsonp,text and the default value is "json"
	 * @param success 成功回调函数
	 * @param error 错误回调函数
	 * @param setting {
	 *      disBtn : true, default true
	 * 		btn : $("#btn"),
	 * 		mask : true, default true
	 * 		maskIndex : 遮罩层索引 
	 * }
	 */
	var ajaxSubmit = function(url, form, data, type, success, error, setting) {
		if (url && form && success) {
			form.ajaxSubmit({
				url : getUrl(url),
				type : getType(type),
				dataType : getDataType(dataType),
				data : getData(data),
				timeout : getTimeout(),
				beforeSend : function( settings ) {
					handleBtn(setting, true);
					handleMask(setting, true);
				},
				success : function(data, textStatus) {
					handleSuccess(success, data, textStatus, "JSON", setting);
				},
				error : function(textStatus, errorThrown) {
					handleError(error, textStatus, errorThrown, setting);
				}
			});
		} else {
			layer.alert("参数错误！");
		}
	};
	
	/**
	 * 获取静态资源 eg : HTML,TXT,JSON,JS,CSS....
	 * @param 请求
	 * @param target 获取后渲染到指定的地方
	 * @param data 
	 * @param type
	 * @param success
	 */
	var loadSource = function(url, target, data, type, success) {
		success = success || function (_data) {
			console.log("load source success.");
		};
		if (url && target) {
			var options = {
				url : url,
				target : target,
				type : getType(type),
				data : getData(data),
				timeout : getTimeout(),
//				async: false,
				success : function(data, textStatus) {
					handleSuccess(success, data, textStatus, "source");
				}
			};
			$(this).ajaxSubmit(options);
		}
	};

	/**
	 * 
	 * @param form
	 * @param dataType
	 * @param success
	 * @param error
	 * @param setting {
	 *      disBtn : true, default true
	 * 		btn : $("#btn"),
	 * 		mask : true, default true
	 * 		maskIndex : 遮罩层索引 
	 * }
	 */
	var ajaxForm = function(form, dataType, success, error, setting) {
		if (form && success) {
			form.ajaxForm({
				dataType : getDataType(dataType),
				timeout : getTimeout(),
				beforeSubmit : function(formData, jqForm, options) {
					handleBtn(setting, true);
					handleMask(setting, true);
				},
				success : function(data, textStatus) {
					handleSuccess(success, data, textStatus, "JSON", setting);
				},
				error : function(textStatus, errorThrown) {
					handleError(error, textStatus, errorThrown, setting);
				}
			});
		}
	};

	/**
	 * 处理url
	 * @param url
	 * 
	 */
	var getUrl = function (url) {
		return url ? config.api.default + url : url;
	};
	
	/**
	 * 处理dataType
	 * @param dataType
	 * 
	 */
	var getDataType = function (dataType) {
		return dataType || "json";
	};

	/**
	 * 处理data
	 * @param data
	 */
	var getData = function (data) {
		return data || {};
	};

	/**
	 * 处理type
	 * @param type
	 */
	var getType = function (type) {
		return type || "POST";
	};

	/**
	 * 处理contentType
	 * @param contentType
	 * @returns {String}
	 */
	var getContentType = function (contentType) {
		return contentType || "application/x-www-form-urlencoded; charset=UTF-8";
	};

	/**
	 * 处理async
	 * @param async
	 * @returns {Boolean}
	 */
	var getAsync = function (async) {
		return async === false ? async : true;
	};
	
	/**
	 * 设置会话时间
	 * @param timeout
	 * @returns {Number}
	 */
	var getTimeout = function (timeout) {
		return timeout || 180000;
	};

	/**
	 * 处理按钮业务
	 * @param setting {
	 *      disBtn : true, default true
	 * 		btn : $("#btn"),
	 * 		mask : true, default true
	 * 		maskIndex : 遮罩层索引 
	 * }
	 * @param disabled 是否禁用 
	 */
	var handleBtn = function (setting, disabled) {
		if (setting && setting["enable"] !== false) {
			if (disabled) {
				setting.btn.prop("disabled", true);
//				setting.btn.addClass("disabled");
			} else {
				setting.btn.prop("disabled", false);
//				setting.btn.removeClass("disabled");
			}
		}
	};

	/**
	 * 处理遮罩层业务
	 * @param setting {
	 *      disBtn : true, default true
	 * 		btn : $("#btn"),
	 * 		mask : true, default true
	 * 		maskIndex : 遮罩层索引 
	 * }
	 * @param show 是否展示
	 * 
	 */
	var handleMask = function (setting, show) {
		if (setting) {
			if (show) {
				setting.maskIndex = layer.load(); 
			} else {
				layer.close(setting.maskIndex);
			}
		}
	};

	/**
	 * 错误处理函数
	 * @param error 错误回调函数
	 * @param textStatus
	 * @param errorThrown
	 * @param setting {
	 *      disBtn : true, default true
	 * 		btn : $("#btn"),
	 * 		mask : true, default true
	 * 		maskIndex : 遮罩层索引 
	 * }
	 */
	var handleError = function (error, textStatus, errorThrown, setting) {
		if (error && typeof error === "function") {
			error(textStatus, errorThrown);
		} else {
			layer.alert("系统请求错误,请与管理员联系！");
		}
		handleBtn(setting, false);
		handleMask(setting, false);
	};

	/**
	 * 成功处理函数
	 * @param success 回调函数
	 * @param data 数据
	 * @param textStatus
	 * @param dataType
	 * @param setting {
	 *      enable : true, 设置是否启用按钮 default : null
	 * 		btn : $("#btn"),
	 * 		mask : true, default true
	 * 		maskIndex : 遮罩层索引 
	 * }
	 */
	var handleSuccess = function (success, data, textStatus, dataType, setting) {
		success(data);
		handleBtn(setting, false);
		handleMask(setting, false);
	};

	return {
		ajax : ajax,
		ajaxSubmit : ajaxSubmit,
		ajaxForm : ajaxForm,
		loadSource : loadSource
	};
})();
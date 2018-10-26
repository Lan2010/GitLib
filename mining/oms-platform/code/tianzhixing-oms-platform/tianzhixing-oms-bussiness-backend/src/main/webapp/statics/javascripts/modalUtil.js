/**
 * modalUtil note : 可扩展
 * @author Sunset
 *
 */
var modalUtil = (function () {
    /**
     * 打开弹出层
     * @param ele弹出层对象
     * @param title 弹出层的标题
     * @param form 表单对象
     * @param clearHidden 情况隐藏域值
     * @param func 回调函数
     * @param funcParamsObj 匿名函数参数对象
     *
     */
    var openModal = function (ele, title, form, clearHidden, func, funcParamsObj) {
        title = title || "";
        if (ele) {
            // 禁用 BootStrap Modal 点击空白时自动关闭
            ele.modal({
                backdrop: 'static',
                keyboard: false
            });
            if (form) {
                form[0].reset();// reset form
                clearHidden && $("input[type='hidden']", form).val('');// reset input[type='hidden']
            }
            $(".modal-title", ele).text(title);// set modal's title
        }
        callback(func, funcParamsObj);
    };

    /**
     * 关闭弹出层
     * @param ele弹出层对象
     * @param form 表单对象
     * @param func 回调函数
     * @param clearHidden 清空隐藏域属性
     * @param funcParamsObj
     *
     */
    var closeModal = function (ele, form, func, clearHidden, funcParamsObj) {
        if (ele) {
            ele.modal('hide');
            if (form) {
                form[0].reset();// reset form
                clearHidden && $("input[type='hidden']", form).val('');// reset input[type='hidden']
            }
            $(".modal-title", ele).text("");// set modal's title
        }
        callback(func, funcParamsObj);
    };
    
    /**
     * 设置modal的宽度和高度
     * 
     */
    var resizeModalBody = function() {
    	var modalHeader = 98, modalFooter = 65, 
    		$modalBody = $(".modal-content .modal-body"),
    		$contentMain = $("#content-main", window.top.document),
    		contentMainHeight = $contentMain.height(), 
    		contentMainWidth = $contentMain.width();
		var maxHeight = contentMainHeight - modalHeader - modalFooter - 30,
			maxHeight = maxHeight < 100 ? 100 : maxHeight;
		$modalBody.css({maxHeight : maxHeight, overflow : "auto"});
    };

    /**
     * 回调函数
     * @param func 回调函数
     * @param funcParamsObj 回调函数对象
     *
     */
    function callback(func, funcParamsObj) {
        func && typeof func === "function" && func(funcParamsObj);
    };

    return {
        open: openModal,
        close: closeModal,
        resizeModalBody : resizeModalBody
    };
})();   


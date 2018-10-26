var uploadUtil = (function () {

    var init = function(arg, dataParam, func, funcParamsObj){
        var oFileInput = new FileInput();
        oFileInput.Init(arg.ctrlName, arg.uploadUrl, arg.fileType, arg.maxFileCount, arg.drop, arg.autoUpload, dataParam, func, funcParamsObj);
    };

    //初始化fileinput
    var FileInput = function () {
        var oFile = new Object();
        //初始化fileinput控件（第一次初始化）
        oFile.Init = function (ctrlName, uploadUrl, fileType, maxFileCount, drop, autoUpload, dataParam, func, funcParamsObj) {
            var control = $('#' + ctrlName);
            //初始化上传控件的样式
            control.fileinput({
                language: 'zh', //设置语言
                uploadUrl: uploadUrl, //上传的地址
                uploadExtraData: dataParam, //上传额外的参数
                allowedFileExtensions: fileType,//接收的文件后缀
                showUpload: true, //是否显示上传按钮
                showCaption: false,//是否显示标题
                browseClass: "btn btn-primary", //按钮样式
                dropZoneEnabled: drop,//是否显示拖拽区域
                //minImageWidth: 50, //图片的最小宽度
                //minImageHeight: 50,//图片的最小高度
                //maxImageWidth: 1000,//图片的最大宽度
                //maxImageHeight: 1000,//图片的最大高度
                maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
                //minFileCount: 0,
                maxFileCount: maxFileCount, //表示允许同时上传的最大文件个数
                enctype: 'multipart/form-data',
                validateInitialCount: true,
                previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
                msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            });
            //导入文件上传完成之后的事件
            control.on("fileuploaded", function (event, data, previewId, index) {
                callback(func, funcParamsObj, event, data.response, previewId, index, "fileuploaded");
            });
            //选择文件后立即执行（fileselect：单个文件，filebatchselected：多个文件）
            control.on('filebatchselected', function(event, files) {
                if(autoUpload){
                    //ajaxFileUpload(uploadUrl, {}, ctrlName, func, funcParamsObj)
                    control.fileinput("upload");
                }

            });
            control.on('filesuccessremove', function(event, id) {//点击删除后立即执行
                control.fileinput('refresh');//文件框刷新操作
                callback(func, funcParamsObj, event, null, null, null, 'filesuccessremove');
            });
        }
        return oFile;
    };

    function ajaxFileUpload(url, data, file , func, funcParamsObj) {
        $.ajaxFileUpload
        (
            {
                url: url, //用于文件上传的服务器端请求地址
                type: 'post',
                data: data,
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: file, //文件上传域的ID
                dataType: 'text', //返回值类型 一般设置为json
                success: function (data, status)  //服务器成功响应处理函数
                {
                    callback(func, funcParamsObj, null, data, null, null, true);
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    callback(func, funcParamsObj, null, data, null, null, false);
                }
            }
        )
        return false;
    }

    function callback(func, funcParamsObj, event, data, previewId, index, opertaion) {
        func && typeof func === "function" && func(funcParamsObj, {event: event, data:data, previewId:previewId, index:index, status: opertaion});
    };


    return {
        init: init
    };
})();
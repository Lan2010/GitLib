/**
 * note : 上传流程 1、客户端发起上传请求（页面增加上传中图标）
 *                 2、服务端上传成功返回数据ID
 *                 3、根据服务端返回的ID客户端需要到指定的API循环请求已验证当前上传后端是否上传到文件服务器
 *                    A : 成功===上传图标消失(设置ID到对应的form(元素)input域中)
 *                    B : 失败===提示上传失败
 * @author Sunset
 * @date 2016/02/24
 *
 */
var webuploaderExt = (function () {

    /**
     * 用于存储定时器id
     * @type {Array}
     * @maskIndex 遮罩层Id
     */
    var timerIds = [], maskIndex;

    /**
     * @param _ {}
     *            not null
     * @param _.fileNumLimit
     *            文件限制数量 default : 1
     * @param _.fileSizeLimit
     *            文件大小 default : 200MB
     * @param _.method
     *            请求方式 default : POST
     * @param _.server
     *            服务器地址 default : config.attachment.uploader
     * @param _.pick
     *            选取文件的元素 not null
     * @param _.resize
     *            压缩 default : false
     * @param _.accept
     *            accept extensions default : jpg,bmp,png,pdf,zip
     * @param _.formData
     *            form data default : null
     * @param _.success
     *            upload success callback function not null
     * @param _.fileInput
     *              附件隐藏域元素选择器 not null
     * @param _.remark {//备注对象
     *      modalEle   : modalEle, //modal弹出框对象
     *      btn        : btn, // 备注按钮选择器
     *      btnSave    : btnSave,// 备注保存按钮
     *      fileInput  : fileInput, // 备注表单附件元素选择器
     *      form       : form // 备注表单对象
     *    }
     *	@param _.showMask 是否显示遮罩层
     */
    var init = function (_) {
        var fileInput = _.fileInput, $fileInput = $(fileInput),
            modalEle = _.remark && _.remark.modalEle ? _.remark.modalEle : null,
            btnRemark = _.remark && _.remark.btn ? _.remark.btn : null,
            $btnRemark = btnRemark ? $(btnRemark) : null,
            btnRemarkSave = _.remark && _.remark.btnSave ? _.remark.btnSave : null,
            remarkFileInput = _.remark && _.remark.fileInput ? _.remark.fileInput : null,
            $remarkFileInput = remarkFileInput ? $(remarkFileInput) : null,
            remarkForm = _.remark && _.remark.form ? _.remark.form : null;
        var uploader = WebUploader.create({
            // 自动上传
            auto: true,
        	
            // swf文件路径
            swf: 'Uploader.swf',

            // 准备下一个文件
            prepareNextFile : true,
            
            // 文件总数
//            fileNumLimit: _["fileNumLimit"] || 20,
            
            threads : 50,

            // 200M 单个文件大小
            fileSingleSizeLimit: _["fileSizeLimit"] || 200 * 1024 * 1024,

            // 文件上传方式
            method: _["method"] || "POST",

            // 文件接收服务端。
            server: _["server"] || config.attachment.uploader,

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: _["pick"],

            // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
            resize: _["resize"] || false,
            
            //是否去重
            duplicate : _["duplicate"] || false,

            headers: _["headers"] || {},

            // 上传文件类型
            accept: _["accept"] || {
                title: 'Applications',
                extensions: 'gif,jpg,jpeg,bmp,png,pdf,zip,xlsx,xls,doc,docx,pptx,ppt',
                mimeTypes: 'image/*,application/pdf,application/zip,application/doc,application/docx,application/xls,application/xlsx,application/pptx,application/ppt'
            }
        });

        /**
         * 选择文件
         */
        uploader.on('fileQueued', function (file) {
            $('#thelist, .uploader-list').show().children('div').remove();

            $('#thelist, .uploader-list').append('<div id="' + file.id + '" class="item">' +
                '<h4 class="info"> 已选择文件 《' + file.name + '》</h4>' +
                '</div>');
            // 设置参数
            uploader.options.formData = _["formData"] || uploader.options.formData;
            // 设置上传按钮不可见
            if (_["showMask"]) {
            	togglePicker(uploader.options.pick, false);
                maskIndex = layer.load();
            }
        });

        // 文件上传过程中创建进度条实时显示。
        uploader.on('uploadProgress', function (file, percentage) {
            var $li = $('.uploader-list div'), //$('#' + file.id),
                $percent = $li.find('.progress .progress-bar');

            // 避免重复创建
            if (!$percent.length) {
                $percent = $('<div class="progress progress-striped active">' +
                    '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                    '</div>' +
                    '</div>').appendTo($li).find('.progress-bar');
            }

            $li.find('p.state').text('上传中');

            $percent.css('width', percentage * 100 + '%');

            _["progress"]  && _["progress"](file, percentage);
        });
        

        uploader.on('uploadSuccess', function (file, resp) {
            if (resp.code === 0) {
                // 获取附件树对象和选中节点数据
                var params = uploader["params"];
                // 上传成功之后需要处理根据返回的ID发起请求验证后端上传是否完成
                handleFileInput(null, fileInput, btnRemark, remarkFileInput, _["urlCallBack"] || config.attachment.fileStatus, calculateDelay(file.size), true, uploader, file, resp, _["success"], params, _["api"], _);

            } else {
                layer.msg("文件《" + file.name + "》上传失败," + resp.msg, {icon: 5});
                if (_["showMask"]) {
                    // 设置按钮可见
                    togglePicker(uploader.options.pick, true);
                    layer.close(maskIndex);
                }
            }
        });

        uploader.on('uploadError', function (file, reason) {
            layer.msg("文件《" + file.name + "》上传失败！", {icon: 5});

            _["error"]  && _["error"](file, reason);
        });

        uploader.on('error', function (type) {
        	var msg = "上传错误!";
            if ("Q_EXCEED_NUM_LIMIT" === type) {
                msg = "文件总数不能超过:" + uploader.options.fileNumLimit + "个!";
            } else if ("Q_EXCEED_SIZE_LIMIT" === type || "F_EXCEED_SIZE" === type) {
            	msg = "单个文件不能超过:" + WebUploader.Base.formatSize( uploader.options.fileSingleSizeLimit ) + "!";
            } else if ("Q_TYPE_DENIED" === type) {
            	msg = "上传文件类型不被允许!";
            } else if ("F_DUPLICATE" === type) {
            	msg = "上传文件重复!";
            }
            layer.msg(msg, {icon : 5});
        });

        uploader.on('uploadComplete', function (file) {
            $('#thelist, .uploader-list').hide();
            $('.uploader-list div').find('.progress').fadeOut();

            _["complete"]  && _["complete"](file);
        });


        uploader.on("uploadFinished", function() {
        	// 如果去重开启后不能重置
            // !_["duplicate"] &&
            uploader.reset();
        });
        
        // 备注按钮存在则绑定单击事件
        $btnRemark && $btnRemark.length && $btnRemark.on("click", function (e) {
            var _fileId = $fileInput.val();
            // 弹出备注层,获取备注信息
            modalUtil.open(modalEle, "附件备注", remarkForm, false, getRemarks, [_fileId, remarkForm]);
        });

        // 备注保存按钮存在则绑定单击事件
        btnRemarkSave && btnRemarkSave.length && btnRemarkSave.on("click", function (e) {
            var _remark = $("#remark", remarkForm).val();
            var _fileId = $remarkFileInput.val();
            if (_remark && _fileId) {
                ajaxUtil.ajax("attachment/addAttachmentRemark.do", {attachId : _fileId, remark : _remark}, "post", function (data) {
                    if (data.code === 0) {
                        layer.msg(data.msg, function () {
                            modalUtil.close(modalEle);
                        });
                    }
                }, function (request, textStatus, errorThrown) {
                    console.error("附件上传失败，出现如下错误信息 : %s ", errorThrown);
                }, "JSON", {btn : btnRemarkSave});
            } else {
                layer.alert("请填写备注信息或等待附件上传完成！");
            }
        });

        // 备注删除按钮存在则绑定单击事件
        remarkForm && remarkForm.on("click", "button[name='btnRemarkDel']", function (e) {
        	var _dataId = $(this).attr("data-id"), $this = $(this);
            _dataId && layer.confirm("您确定要删除当前备注信息吗？", function (index) {
                ajaxUtil.ajax("attachment/delAttachmentRemark.do", {id : _dataId}, "get", function (data) {
                    if (data.code === 0) {
                        // 动态删除备注信息
                    	$this.parent().parent().remove();
                    	layer.msg(data.msg);
                    }
                }, function (request, textStatus, errorThrown) {
                    console.error("删除附件备注失败，出现如下错误信息 : %s ", errorThrown);
                });
            });
        });

    	//初始化之后重新计算可点区域样式
        setTimeout(function(){
            $(_["pick"] + " input").parent().css({
            	top: "0px",
            	left: "0px",
            	width: "100%",
            	height: "100%"
            });
        }, 1800);

        return uploader;
    };

    /**
     * 备注活动div #actionRow
     * 根据附件Id获取备注信息
     * @param fileId
     */
    var getRemarks = function (params) {
    	if (params && params.constructor === Array && params.length) {
    		$("#actionRow").siblings("div").remove();
    		ajaxUtil.ajax("attachment/findAttachRemarkByAttachId.do", {attachId : params[0]}, "get", function (data) {
                if (data.code === 0) {
                	var attachmentRemarks = data.data.attachmentRemarks, contents = [];;
                	if (attachmentRemarks && attachmentRemarks.length) {
                		for (var i = 0, ll = attachmentRemarks.length; i < ll; i++) {
							var remark = attachmentRemarks[i];
							var str = '<div class="form-group"><label class="col-sm-2 control-label">备注：</label><div class="col-sm-8"><textarea class="form-control" disabled="disabled">' +
								remark.remark +
								'</textarea></div><div class="col-sm-2"><button type="button" data-id="' + remark.id + '" class="btn btn-danger" name="btnRemarkDel"><i class="fa fa-times"></i> 删除</button></div></div>'
							contents.push(str);
						}
                	} else {
                		contents.push('<div class="form-group"><div class="col-sm-8"><h2>暂无备注数据...</h2></div></div>');
                	}
                	params[1].prepend(contents.join(""));
                }
            }, function (request, textStatus, errorThrown) {
                console.error("获取附件备注失败，出现如下错误信息 : %s ", errorThrown);
            });
    	} else {
    		console.warn("参数错误！");
    	}
    };

    /**
     *  上传成功之后需要处理根据返回的ID发起请求验证后端上传是否完成
     *  @param ele 上传后显示的图标元素
     *  @param fileInput 附件隐藏域元素
     *  @param btnRemark 备注按钮对象
     *  @param remarkFileInput 备注表单附件隐藏域元素
     *  @param url 需要发起请求的API URL
     *  @param delay 间隔时间
     *  @param first 是否为第一次执行
     *  @param uploader 附件初始化对象
     *  @param file 上传的文件对象
     *  @param resp 服务器响应的数据
     *  @param _success 用于客户自己扩展的成功回调函数
     *  @param params 选中节点和选中树参数对象
     *  @param api
     *  @param option
     */
    var handleFileInput = function (ele, fileInput, btnRemark, remarkFileInput, url, delay, first, uploader, file, resp, _success, params, api, option) {
        // 附件Id
        var fileId = resp.data.attachment.id;
    	if (first) {
            var timerId = setTimeout(function () {
                handleFileInput(ele, fileInput, btnRemark, remarkFileInput, url, delay, false, uploader, file, resp, _success, params, api, option);
            }, delay);
            timerIds.push(timerId);
    	} else {
    		var success = function (data) {
                if (data && data.data && data.code === 0) {
                	var $ele = $(ele), $fileInput = $(fileInput), $btnRemark = $(btnRemark), $remarkFileInput = $(remarkFileInput);
                	var status = data.data.attachmentStatus && data.data.attachmentStatus.name ? data.data.attachmentStatus.name : "";
                    if (status === "UPLOADED") {
                        if (option["showMask"]) {
                            // 设置按钮可见
                            togglePicker(uploader.options.pick, true);
                            layer.close(maskIndex);
                        }
                        // 设置图标为成功的样式
                    	$fileInput && $fileInput.length && $fileInput.val(fileId);
                        // 设置附件备注按钮显示且可用（由于权限和附件同时控制了附件备注按钮所以此处需要做特殊处理，权限控制class hide，附件控制display:none && disable = true）
                    	$btnRemark && $btnRemark.length && $btnRemark.removeClass("hide").show() && $btnRemark.prop("disable", false);
                    	$remarkFileInput && $remarkFileInput.length && $remarkFileInput.val(fileId);
                        //回调
                        _success && typeof _success === "function" &&  _success(file, resp, params, uploader, data);
                    } else if (status === "UPLOAD_FAILED") {
                        if (option["showMask"]) {
                            // 设置按钮可见
                            togglePicker(uploader.options.pick, true);
                            layer.close(maskIndex);
                        }
                        // 设置图标为失败的样式
                    	$fileInput && $fileInput.length && $fileInput.val("");
                        $btnRemark && $btnRemark.length && $btnRemark.hide() && $btnRemark.prop("disable", true);
                        $remarkFileInput && $remarkFileInput.length && $remarkFileInput.val("");
//                        var node = params.node;
                        layer.msg("[" + file.name + "]上传失败，原因如下：" + data.data.reason || "上传错误！", {icon: 5});
                    } else {// 上传中
                        // 时间递减
                        delay = delay <= 1000 ? 1000 : delay -= 1000;
                        var timerId = setTimeout(function () {
                            handleFileInput(ele, fileInput, btnRemark, remarkFileInput, url, delay, false, uploader, file, resp, _success, params, api, option);
                        }, delay);
                        timerIds.push(timerId);
                    }
                }
            };
            ajaxUtil.ajax(url, {id: fileId}, "GET", success, null, null, null, null, null, api);
    	}
    };

    /**
     * 文件大小和时间的对应关系
     * MB : SS
     */
    var delays = {
        50: 2500,
        100: 5000,
        150: 7500,
        200: 10000
    };

    /**
     * 根据附件大小计算第一次发起请求的时间
     * @param fileSize
     * @return {Number}
     */
    var calculateDelay = function (fileSize) {
        if (fileSize > 0) {
            // 单个附件<=200MB == 200 * 1024 * 1024 字节
            for (var m in delays) {
                var tm = m * 1024 * 1024;
                if (fileSize <= tm) {
                    return delays[m];
                }
            }
        }
        return 10000;
    };

    /**
     * 上传附件后设置上传按钮不可见防止多次点击
     * 上传成功||失败之后显示
     * 设置picker是否可以见
     */
    var togglePicker = function (picker, show) {
    	picker = $(picker);
        picker.length && show && picker.removeClass("hide");
        picker.length && !show && picker.addClass("hide");
    };

    /**
     * 获取队列中的数量
     * 根据队列的数量判断是否存在文件上传(服务端)
     */
    var getQueues = function () {
        timerIds.forEach(function (element, index, _array) {
            element._called && timerIds.splice(index, 1);
        });
        return timerIds.length;
    };


    return {
        init: init,
        getQueues : getQueues
    };
}());



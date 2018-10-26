;
!function(win, undefined) {
    var weui = {
        loading: false,
        isScroll: false,
        isDebenLoad: true,
        load: function() {
            $.showLoading("加载中...");
        },
        hideload: function() {
            $.hideLoading();
            $(".weui-toast").remove();
        },
        scrollLoad: function(obj, distance, callback) {
            //weui.loading = false; //状态标记
            distance = distance ? distance : 50; //距离底部50px，开始加载
            obj = obj ? obj : document;
            var lastHeight = $(obj.body).scrollTop();
            var nextHeight = lastHeight;
            weui.bindScroll(distance, obj, lastHeight, nextHeight, callback);
        },
        bindScroll: function(distance, obj, lastHeight, nextHeight, callback) {
            $(obj.body).infinite(distance).on("infinite", function() {
                if (weui.isScroll) {
                    return false;
                }
                weui.isScroll = true;
                lastHeight = $(obj.body).scrollTop();
                setTimeout(function() {
                    if (typeof callback == "function") {
                        nextHeight = $(obj.body).scrollTop();
                        callback.call(null, nextHeight - lastHeight);
                    }
                }, 1000); //模拟延迟
            });
        },
        /**
         * 封装请求的数据
         * */
        getAjaxData: function(postUrl, data, nodeId, pageID) {
            pageID = !pageID ? "pageIndex" : pageID;
            if (weui.loading || (weui.isDebenLoad == false && pageID != "pageIndex")) {
                weui.isScroll = false;
                return true;
            }
            $.ajax({
                async: true,
                url: postUrl,
                type: 'post',
                dataType: 'json',
                data: data,
                beforeSend: function(XMLHttpRequest) {
                    weui.loading = true;
                    weui.load();
                },
                success: function(json) {
                    weui.hideload();
                    weui.isScroll = false;
                    if (json.status * 1 > 0) {
                        weui.addData(json.data, nodeId, pageID);
                    } else {
                        if (json.msg) {
                            $.alert(json.msg);
                            return false;
                        }
                        $("#wxMore").hide();
                        $("#wxLoad").hide();
                        if (json.data.pageIndex == 1) {
                            $("#nodata").show();
                        } else {
                            $("#wxNo").show();
                        }
                    }
                },
                complete: function(XMLHttpRequest, textStatus) {
                    weui.hideload();
                },
                error: function(xhr) {
                    weui.loading = false;
                    $.alert("请检查网络连接！");
                    weui.hideload();
                }
            });

        },
        addData: function(jsonData, nodeId, pageID) {
            if (!jsonData.data || jsonData.data == null) {
                $("#wxNo").show();
                $("#wxMore").hide();
                $("#wxLoad").hide();
            } else {
                if (jsonData.dataSize < jsonData.pageSize) {
                    $("#wxMore").hide();
                    $("#wxLoad").hide();
                    $("#wxNo").show();
                } else {
                    $("#wxMore").show();
                    $("#wxNo").hide();
                    $("#wxLoad").hide();
                }
                if (jsonData.pageIndex > 2) {
                    $("#" + nodeId).append(jsonData.data);
                } else {
                    $("#" + nodeId).html(jsonData.data);
                }
                $("#" + pageID).val(jsonData.pageIndex * 1);
                if ((jsonData.dataSize < jsonData.pageSize)) {
                    weui.loading = true;
                    weui.isDebenLoad = false;
                } else {
                    weui.loading = false;
                }

            }
        },
        ajax: function(postUrl, jsonData, callBack) {
            $.ajax({
                url: postUrl,
                type: 'post',
                data: {"par": jsonData},
                dataType: "json",
                beforeSend: function() {
                    weui.load();
                },
                success: function(data) {
                    weui.hideload();
                    if (typeof callBack === "function") {
                        callBack.call(null, data);
                    }
                },
                complete: function() {
                    weui.hideload();
                },
                error: function() {
                    weui.hideload();
                }

            });
        },
        message: function(message) {  //直接提示没有按钮确定
            var time = 2000;
            $.toast(message);
            setTimeout(function() {
                $(".weui-toast").hide();
            }, time);
        },
        showTime: function(time_distance, obj, isShowClass) {
            var timer = obj;
            var sytime = time_distance - 1000;
            if (isNaN(time_distance)) {
                time_distance = timer.attr("distance");
            }
            distance = time_distance || 0;
            var str_time;
            var int_day, int_hour, int_minute, int_second;
            var distance = distance;
            distance = distance - 1000;
            if (sytime > 0) {
                int_day = Math.floor(distance / 86400000);
                distance -= int_day * 86400000;
                int_hour = Math.floor(distance / 3600000);
                distance -= int_hour * 3600000;
                int_minute = Math.floor(distance / 60000);
                distance -= int_minute * 60000;
                int_second = Math.floor(distance / 1000);
                if (int_hour < 10)
                    int_hour = "0" + int_hour;
                if (int_minute < 10)
                    int_minute = "0" + int_minute;
                if (int_second < 10)
                    int_second = "0" + int_second;
                isShowClass = isShowClass == 1 ? true : false;
                if (isShowClass) {
                    str_time = '<span class="color_orange">' + int_day + "</span>" + "天" + "<span class='color_orange'>" + int_hour + '</span>' + "小时" 
                            + "<span class='color_orange'>" + int_minute + '</span>'  + "分钟" +
                            "<span class='color_orange'>" + int_second + '</span>' + "秒";
                } else {
                    str_time = int_day + "天" + int_hour + "小时" + int_minute + "分钟" + int_second + "秒";
                }
                timer.html(str_time);
                setTimeout(function() {
                    weui.showTime(sytime, obj, isShowClass);
                }, 1000);
            } else if (distance == -1000) {
                timer.text("项目未开始");
                return;
            } else {
                timer.text("项目已结束");
                return;
            }
        },
    };
    window.weui = weui;
}(window);

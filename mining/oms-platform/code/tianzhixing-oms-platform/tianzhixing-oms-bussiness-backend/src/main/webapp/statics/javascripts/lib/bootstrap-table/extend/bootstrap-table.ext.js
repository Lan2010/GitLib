/**
 * @author Sunset
 * @date 2016/03/10
 */
var tableService = (function () {

    /**
     * 初始化
     * @param _
     * @returns {bootstrapTable}
     */
    var init = function (_) {
        if (!_ || typeof _ !== "object" || !_["columns"]) return null;
        _["table"] = _["table"] || $("#table");
        _["showRefresh"] = _["showRefresh"] === false ? false : true;
        _["pagination"] = _["pagination"] === false ? false : true;
        _["striped"] = _["striped"] === false ? false : true;
        _["clickToSelect"] = _["clickToSelect"] === false ? false : true
        _["columns"] = _["columns"] || [];
        _["api"] = _["api"] || config.api.default;
        _["url"] = _["url"] ? _["api"] + _["url"] : "";
        return _["table"].bootstrapTable({
            url: _["url"],
            method: _["method"] || "get",
            contentType: _["contentType"] || 'application/x-www-form-urlencoded',
            dataField: _["dataField"] || "list",
            uniqueId: _["uniqueId"] || "id",
            showRefresh: _["showRefresh"],// 存在Bug如果传入的是false判断无效
            search: _["search"] || false,
            sidePagination: _["sidePagination"] || "server",
            toolbar: _["toolbar"] || "#toolbar",
            clickToSelect: _["clickToSelect"],
            singleSelect: _["singleSelect"] || false,
            pagination: _["pagination"],
            striped: _["striped"],
            pageList: _["pageList"] || [500, 200, 100, 50, 25, 10],
            pageSize: _["pageSize"] || 10,
            pageNumber: _["pageNumber"] || 1,
            queryParams: _["queryParams"],
//			smartDisplay    : _[ "smartDisplay" ] || true,
            responseHandler: _["responseHandler"],
            columns: (function () {
                _["columns"].forEach(function (column) { // TODO 此处只能处理一维数组多维数组待完成
                    if (!Array.isArray(column) && column.field !== "" && !column.formatter && undefined === column.sortable) { // 默认将所有的数据列都开打排序功能
                        column.sortable = true;
                    }
                    /*else if (!column.formatter && !column.filter) { // filter 处理器
                     column.filter = {
                     type: "select",
                     data: []
                     }
                     }*/
                    return column;
                });
                return _["columns"];
            })(),
            onLoadSuccess: function (data, status, jqXHR) {
                _["onLoadSuccess"] && _["onLoadSuccess"](data);
                // _fnAddFilterData(_, data.list || []);
            },
            onLoadError: function (status, jqXHR) {
                if (status === -1 || status == 400 || status === 401 || jqXHR.status === 0) {
                    config.sessionTimeout();
                }
                _["onLoadError"] && _["onLoadError"](status, jqXHR);
            },
            onExpandRow: _["onExpandRow"] || function (index, row, $detail) {
                console.log('expandRow');
            },
            filter: _["filter"] || false,
            height: _["height"] || 560
        });

        /*$(window).resize(function () {
         _[ "table" ].bootstrapTable("resetWidth");
         console.log('resize')
         //			_[ "table" ].bootstrapTable('resetView', {
         //                height: getHeight()
         //            });
         });*/
    };

    /**
     往过滤器添加数据
     liuhao 2016年8月17日11:15:31

     conf: 配置对象
     data: 列表的json数据
     */
    var _fnAddFilterData = function (conf, data) {
        var hasFilter = conf['filter'],
            cols = conf['columns'],
            tab = conf["table"];

        if (!hasFilter) {
            return;
        }
        $.each(cols, function (i, n) {
            if ($.isPlainObject(n) && !n.checkbox && !n.formatter) {
                var fieldName = n.field;

                var colData = $.map(data, function (d) {
                    if (fieldName.indexOf(".") !== -1) {
                        return utils.getJSONvalue(d, fieldName);
                    }
                    return d[fieldName];
                });
                colData = _.uniq(colData);
                tab.bootstrapTable("setFilterData", fieldName, colData);
            }
        });


    };

    /**
     * 获取表格选中数据
     * @param action 动作
     * @param table bootstrapTable 对象
     * @param isMultiSelect 是否允许多选
     */
    var getSelections = function (action, table, isMultiSelect) {
        if (action && table) {
            var rows = table.bootstrapTable('getSelections');
            if (isMultiSelect) {
                if (!rows || rows.length <= 0) {
                    layer.alert("请选择需要" + action + "的数据！");
                } else {
                    return rows;
                }
            } else {
                if (!rows || rows.length <= 0) {
                    layer.alert("请选择需要" + action + "的数据！");
                    $("#reply").hide();
                } else if (rows.length > 1) {
                    layer.alert("一次只能" + action + "一条数据！");
                } else {
                    return rows[0];
                }
            }
        }
        return null;
    };

    /**
     * 获取全部选中数据
     * @param table
     */
    var getAllSelections = function (table) {
        if (table) {
            return table.bootstrapTable('getAllSelections');
        }
    };

    /**
     * 获取全部数据
     * @param table
     */
    var getData = function (table) {
        if (table) {
            return table.bootstrapTable("getData");
        }
    };

    /**
     * 删除表格选中的数据
     * @param table bootstrapTable对象
     * @param ids 选中行的id数组 Array
     */
    var remove = function (table, ids) {
        table && ids && ids.length && table.bootstrapTable('remove', {
            field: 'id',
            values: ids
        });
    };

    /**
     * 增加一行
     * @param table bootstrapTable
     * @param row
     */
    var insert = function (table, row) {
        table && row && table.bootstrapTable("insertRow", row);
    };

    /**
     * 增加多行
     * @param table bootstrapTable
     * @param rows
     */
    var insertRows = function (table, rows) {
        table && rows && rows.length && (function () {
            for (var i in rows) {
                insert(table, rows[i]);
            }
        }());
    };

    /**
     * 根据唯一Id删除行
     * @param table
     * @param id
     */
    var removeByUniqueId = function (table, id) {
        table && id && table.bootstrapTable("removeByUniqueId", id);
    };

    /**
     * 根据唯一Id更改行数据
     * @param table
     * @param row {id: 1, row: row}
     */
    var updateByUniqueId = function (table, row) {
        table && row && table.bootstrapTable("updateByUniqueId", row);
    };

    /**
     * 根据Id获取行数据
     * @param table
     * @param id
     */
    var getRowByUniqueId = function (table, id) {
        var row;
        table && id && (function () {
            row = table.bootstrapTable('getRowByUniqueId', id)
        }());
        return row;
    };

    /**
     * 重新加载数据，始数据将会被删除
     * 注：调用此方法时如果采用服务端分页则必须满足服务端数据结构，如果采用客户端分页则data必须为数组
     * @param table
     * @param data
     */
    var load = function (table, data) {
        table && data && table.bootstrapTable('load', data);
    };

    /**
     * 刷新表格
     * @param table
     * @param params
     * Refresh the remote server data, you can set {silent: true} to refresh the data silently, and set {url: newUrl, pageNumber: pageNumber, pageSize: pageSize} to change the url (optional), page number (optional) and page size (optional). To supply query params specific to this request, set {query: {foo: 'bar'}}.
     */
    var refresh = function (table, params) {
        table && table.bootstrapTable('refresh', params);
    };

    /**
     * 销毁
     * @param table
     *
     */
    var destroy = function (table) {
        table && table.bootstrapTable("destroy");
    };

    return {
        init: init,
        getSelections: getSelections,
        insert: insert,
        insertRows: insertRows,
        remove: remove,
        updateByUniqueId: updateByUniqueId,
        getRowByUniqueId: getRowByUniqueId,
        removeByUniqueId: removeByUniqueId,
        refresh: refresh,
        load: load,
        destroy: destroy,
        getAllSelections: getAllSelections,
        getData: getData
    };
})();

/*
 * bootstrap-table - v1.9.0 - 2015-09-30
 * https://github.com/wenzhixin/bootstrap-table
 * Copyright (c) 2015 zhixin wen
 * Licensed MIT License
 */
!function (a) {
    "use strict";
    a.fn.bootstrapTable.locales["zh-CN"] = {
        formatLoadingMessage: function () {
            return "加载中..."
        },
        formatRecordsPerPage: function (a) {
            return "每页显示 " + a + " 条记录"
        },
        formatShowingRows: function (a, b, c) {
            return "显示第 " + a + " 到第 " + b + " 条记录，总共 " + c + " 条记录"
        },
        formatSearch: function () {
            return "搜索"
        },
        formatNoMatches: function () {
            return "没有找到匹配的记录"
        },
        formatPaginationSwitch: function () {
            return "隐藏/显示分页"
        },
        formatRefresh: function () {
            return "刷新"
        },
        formatToggle: function () {
            return "切换"
        },
        formatColumns: function () {
            return "列"
        }
    }, a.extend(a.fn.bootstrapTable.defaults, a.fn.bootstrapTable.locales["zh-CN"])
}(jQuery);
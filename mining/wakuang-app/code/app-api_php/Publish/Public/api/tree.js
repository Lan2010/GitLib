
$.fn.skTree = function (options, data, data1, data2) {
    if ( !options){
        return null;
    }
    var $this = $(this);
    var $tree = $this.data("sk-tree");
    if ($tree && typeof options == "string") {
        if ( typeof $tree.actions[options] != "undefined"){
            var action = $tree.actions[options];
            return action(data, data1, data2);
        }
    }
    if (!$tree) {
        var defaultOptions = {
            label: "label",
            type: "type",
            items: "items",
            active : null,
            data: [],
            dataFormat : null,
            types: [],
            itemClass: "",
            multi: false,
            canShrink : true,
            expand : false,
            checkbox : false,
            checkOnclick : true,
            selectParents : false,
            unSelect :[]
        }
        $.extend(defaultOptions, options);

        $tree = new SKTree($this, defaultOptions);
        $this.data("sk-tree", $tree);
        if(options["data"]){
            $tree.setDataSource(options["data"]);
        }
    }
    $this.data("dataSource", function(data){
        return $tree.actions["dataSource"](data);
    });
    return $tree;
}
function SKTree($div, options) {
    var $this = this;
    this.options = options;
    this.$div = $div;
    this.items = [];
    this.labelName = options["label"];
    this.typeName = options["type"];
    this.activeName = options["active"];
    this.itemsName = options["items"];
    this.data = [];
    this.dataFormat = {"type":"tree","options":{items:this.itemsName}};
    if(options["dataFormat"]){this.dataFormat = options["dataFormat"]};

    // 树形控件行为
    $div.addClass("sk-tree")
    this.activeItem = function(item){
        var $item = item["node"];
        if (!$this.options["multi"]) {
            $div.find(".tree-item.active").removeClass("active");
            $item.addClass("active");
        } else {
            $item.toggleClass("active");
        }
        if ( $this.options["selectParents"]){
            selectParents(item);
        }
    }
    this.select = function(items, unActive){
        if (arguments.length >= 0 && typeof items == "object"){
            $.each(items, function(idx, value){
                if(unActive ==="toggle"){
                    $this.activeItem(value);
                }
                else if( unActive === true){
                    value.node.removeClass("active");
                }else {
                    value.node.addClass("active");
                }
            });
        } else {
            var $items = $div.find(".tree-item.active");
            var result = [];
            $items.each(function(i, v){
                if($(this).closest(".sk-tree")[0] == $div[0]){
                    result.push($(this).data("data"));
                }
            });
            return result;
        }
    }
    this.unselect = function(items){
        $.each(items, function(i,v){
            v["unSelect"] = true;
            v.node.addClass("unSelect");
        })
    }
    $div.on("click", ".tree-item-title, .check", function (e) {
        if($(this).closest(".sk-tree")[0] != $div[0]){
            return;
        }
        var $item = $(this).closest(".tree-item");
        var item = $item.data("data");
        var canActive = $(this).hasClass("check") || $this.options["checkOnclick"];
        canActive = canActive || item["items"].length == 0;
        canActive = !item["unSelect"] && canActive;
        if( canActive ){
            $this.activeItem(item);
        }
        if (options["canShrink"] && $(this).hasClass("tree-item-title")){
            $item.toggleClass("closeTree");
        }
        if (options["click"]) {
            e["check"]= $(this).hasClass("check");
            options["click"]($item, item, e);
        }
        e.preventDefault();
        return false;
    });
    this.delete = function(item){
        item.node.remove();
        if(item["items"] && item["items"].length >0){
            $.each(item["items"], function(ii, vv){
                $this.delete(vv);
            })
        }
        // 注意，grep的function,第一个参数是value, 第二个参数才是索引值
        $this.items = $.grep($this.items, function(v,i){
            return v != item;
        })
        // 注意，grep的function,第一个参数是value, 第二个参数才是索引值
        $this.data = $.grep($this.data, function(v,i){
            return v != item["data"];
        })
    }

    // 创建控件
    var createTree = function(){
        $this.$div.empty();
        $.each($this.items, function (idx, value) {
            var $item = $this.createTreeItem(value);
            $this.$div.append($item);
        });
        if ( options["expand"]){
            $div.find(".tree-item").addClass("closeTree");
        }
    }
    this.createTreeItem = function (item) {
        var $item = $('<div class="tree-item" f-type="' + item["type"] + '"></div>');
        var $itemItem = $("<div class='tree-item-item'></div>");
        var $itemTitle = $('<div class="tree-item-title"><i class="img"></i><span class="text">' + item["label"] + '<span></div>');
        $itemItem.appendTo($item);
        $itemTitle.appendTo($itemItem);
        if ( options["checkbox"]){
            $itemItem.prepend("<i class='check'></i>")
        }

        //$itemItem.append("<div style='clear: both;'");
        var $items = $('<div class="tree-item-sub"></div>');


        $items.appendTo($item);
        $item.data("data", item);
        item["node"] = $item;

        if (options["initItem"]) {
            options["initItem"]($itemItem, item);
        }
        if(item["active"]){
            $item.addClass("active");
        }
        if(item["itemClass"]){
            $item.addClass(item["itemClass"]);
        }

        if (item["items"]) {
            $.each(item["items"], function (idx, value) {
                var $itemItem = $this.createTreeItem(value)
                $items.append($itemItem);
            });
        }
        return $item;
    }


    // 生成数据
    var genItems = function(){
        $this.items = [];
        var type =$this.dataFormat["type"];
        var ops = $this.dataFormat["options"];
        if ( type == "tree"){
            $this.items = getItemsTree($this.data, ops);
        } else if (type == "list"){
            $this.items = getItemsList($this.data, ops);
        }
        $this.attachItemInfo($this.items);
    }
    this.attachItemInfo = function (items) {
        $.each(items, function(index, item){
            var itemData = item["data"]
            var level = item["level"];
            var label = "";
            var active = false;
            label = $.getNameValue($this.labelName, itemData, level);
            if ($this.activeName){
                active = $.getNameValue($this.activeName, itemData, level);
            }

            var type = "";
            // types 已过时，不建议用,可直接用typ。此处为兼容参数types,
            if (options["types"] && options["types"].length > 0) {
                type = options["types"][level];
            } else {
                type = $.getNameValue($this.typeName, itemData, level)
            }
            item["label"] = label;
            item["type"] = type;
            item["active"] = active;

            if(options["itemClass"]){
                var itemClass = $.getNameValue(options["itemClass"], null, level)
                item["itemClass"] = itemClass;
            }
            if ( options["unSelect"] && options["unSelect"].length > 0){
                if($.inArray(type, options["unSelect"])>=0){
                    item["unSelect"] = true;
                }
            }
            if(item["items"] && item["items"].length > 0){
                $this.attachItemInfo(item["items"]);
            }
        });
    }

    var getItemsList = function(data, options){
        var defaultOptions = {
            self : "self",
            parent : "parent",
            parentValue : 0,
            type : "type",
            create : function(treeItem, itemData){return ;}
        }
        $.extend(defaultOptions, options ||{});
        var self = defaultOptions["self"];
        var parentName = defaultOptions["parent"];
        var parentValue = defaultOptions["parentValue"];
        var itemsName = defaultOptions["parentValue"];
        var createFun = defaultOptions["create"];
        var getItems = function(pValue, level){
            var newItems = [];
            // 注意，grep的function,第一个参数是value, 第二个参数才是索引值
            var finds = $.grep(data, function(v,i){
                return v[parentName] == pValue;
            });
            if ( finds.length > 0){
                $.each(finds, function(kk,vv){
                    var item = {
                        "data" : vv,
                        "level" : level,
                        "items" : getItems(vv[self], level + 1)
                    }
                    $.each(item["items"], function(kkk, vvv){
                        vvv["parent"] = item;
                    });
                    if ( createFun){createFun(item, vv);}
                    newItems.push(item);
                });
            }
            return newItems;
        }
        var result = getItems(parentValue, 0);
        return result;
    }
    var getItemsTree = function(data, options){
        var itemName = options["items"];

        var getItem = function(itemData, level){
            var item = {
                "items": [],
                "level" : level,
                "data": itemData,
                "parent" : null
            }
            if (itemData[itemName]){
                $.each(itemData[itemName], function(i,v){
                    var newItem  =getItem(v, level+1);
                    newItem["parent"] = item;
                    item["items"].push(newItem);
                })
            }
            return item;
        }
        var result = [];
        $.each(data, function (idx, value) {
            result.push(getItem(value, 0))
        });
        return result;
    }

    this.setDataSource = function (data) {
        $this.data = data;
        genItems(data);
        createTree();
    }
    this.getDataSource = function(){
        return $this.data
    }

    var selectParents = function(item){
        var parentItem = item["parent"];
        if(parentItem){
            $this.select([parentItem]);
            selectParents(parentItem);
        }
    }
    this.actions = {
        "data": function(data){
            if ( data){
                $this.setDataSource(data);
            } else {
                return $this.getDataSource();
            }
        },
        "dataSource" : function(data){
            if ( data){
                $this.setDataSource(data);
            } else {
                return $this.getDataSource();
            }
        },
        "clear": function () {
            $this.$div.find(".tree-item").removeClass("active");
        },
        "items": function () {
            return $this.items;
        },
        "select" : $this.select,
        "unselect" : $this.unselect,
        "delete" : $this.delete,
        "options" : function(options){
            $this.options = $.extend($this.options, options);
        }
    }
}

/**
 * wuj 20180723
 */
var app={};

window.app = app;




/**
 * bootstrap-table
 * http://bootstrap-table.wenzhixin.net.cn/
 * @param id
 * @param options
 * {
 * url: "后端json接口url",
 * toolbar: '工具栏id',
 * permission: true|false true 开启功能权限过滤
 * params:function(){
 *     //返回额外参数
 *     return {params:'params1'};
 * },
 * columns:[{
 *    field: '字段',
 *    title: '标题',
 *    width: '宽度',
 *    formatter:function (value,row,index) {
 *          //字段格式化
 *          return '';
 *    }
 * }]
 * }
 */
app.table = function (id, options) {
    var defaultOptions = {
		method: 'post',
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 15, 20, 100],        //可供选择的每页的行数（*）
        classes:"table table-no-bordered",
        // search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列
        showRefresh: false,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        // height:window.innerHeight-160,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        // showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        undefinedText:'-',                   //当数据为 undefined 时显示的字符
        exportTypes:[ 'csv', 'excel'], //导出文件类型 ，支持多种类型文件导出
        //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
		// queryParamsType = 'limit' ,返回参数必须包含
		// limit, offset, search, sort, order 否则, 需要包含:
		// pageSize, pageNumber, searchText, sortName,
		// sortOrder.
		// 返回false将会终止请求
        queryParams: function (params) {
            if (params.limit){
                var pageParams = {
                    //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                    pageNo: params.offset/params.limit+1,  //页码
                    pageSize: params.limit   //页面大小
                };
                $.extend(params,pageParams);
            }
            if (options.params){
                $.extend(params,options.params());
            }
            return params;
        },//传递参数（*）
        responseHandler:function (result) {
           // bs.respHandle(result);
            return result.data;
        },
        onLoadSuccess:function () {
            
        },
        onColumnSwitch: function (field, checked) {
           
        }
    };
    options.url = app.apiRoot()+options.url;
    $.extend(defaultOptions,options);
    var $table = $(id);
    $table.bootstrapTable(defaultOptions);
};


/**
 * 刷新表格
 * @param id
 */
app.tableRefresh = function (id) {
    $(id).bootstrapTable('refresh');
};

/**
 * query查询
 * @param id
 * @param params
 * {
 *    params1:'params1',
 *    params2:'params2'
 * }
 */
app.tableQuery = function (id,params) {
    $(id).bootstrapTable('refresh', {
        pageNumber: 1,
        query:params
    });
};


/**
 * query查询(传入新url)
 * @param id
 * @param newUrl
 */
app.tableQueryUrl = function (id,newUrl) {
    $(id).bootstrapTable('refresh', {
        pageNumber: 1,
        url:app.apiRoot()+ newUrl
    });
};

/**
 * 获取表格行数据
 * @param id
 * @param index
 * @return {*}
 */
app.tableRow = function (id,index) {
    return $(id).bootstrapTable('getData')[index];
};
/**
 * api根url地址
 * @return {string|*}
 */
app.apiRoot = function () {
    if(app.rootUrl){
        return app.rootUrl;
    }
    var js=document.scripts;
    var jsroot='';
    for(var i=js.length;i>0;i--){
        jsroot=js[i-1].src;
        if(jsroot.indexOf("/js/common")>-1){
            jsroot=jsroot.substring(0,jsroot.indexOf("/js/common"));
            break;
        }
    }
    app.rootUrl = jsroot;
    return app.rootUrl;
};

$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}
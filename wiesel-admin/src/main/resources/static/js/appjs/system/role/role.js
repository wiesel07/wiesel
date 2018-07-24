var prefix = "/sys/role";
$(function() {
	load();
});

function load() {
	 app.table('#table',{
		    url : prefix + "/list", // 服务器数据的加载地址
			dataType : "json", // 服务器返回的数据类型
	        toolbar: '#toolbar',
	        method: 'get',
	        columns: [   { // 列配置项
								// 数据类型，详细参数配置参见文档http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation/
								checkbox : true
						// 列表中显示复选框
						},
						{
							field : 'roleId', // 列字段名
							title : '序号' // 列标题
						},
						{
							field : 'roleName',
							title : '角色名'
						},
						{
							field : 'remark',
							title : '备注'
						},
						{
							field : '',
							title : '权限'
						},
						{
							title : '操作',
							field : 'roleId',
							align : 'center',
							formatter : function(value, row, index) {
								var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
										+ row.roleId
										+ '\')"><i class="fa fa-edit"></i></a> ';
								var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
										+ row.roleId
										+ '\')"><i class="fa fa-remove"></i></a> ';
								return e + d;
							}
						} 
	               ],
	        params:function(){
	           return $("#queryForm").serializeObject();
	        }
	    });
}
function reLoad() {
	$('#table').bootstrapTable('refresh');
}
function add() {
	// iframe层
	layer.open({
		type : 2,
		title : '添加角色',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code === 0) {
					layer.msg("删除成功");
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})

}
function edit(id) {
	layer.open({
		type : 2,
		title : '角色修改',
		maxmin : true,
		shadeClose : true, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function batchRemove() {
	
	var rows = $('#table').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	}, function() {
		var ids = new Array();
		$.each(rows, function(i, row) {
			ids[i] = row['roleId'];
		});
		console.log(ids);
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {});
}
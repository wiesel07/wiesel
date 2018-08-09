var prefix = "/sys/user"
$(function() {

	loadTree();
	load();
});

function load() {
	 app.table('#table',{
				url : prefix + "/list", // 服务器数据的加载地址
				iconSize : 'outline',
				toolbar : '#toolbar',
				dataType : "json", // 服务器返回的数据类型
				method : 'get', 
				columns : [
					{
						checkbox : true
					},
					{
						field : 'userId', // 列字段名
						title : '序号' // 列标题
					},
					{
						field : 'name',
						title : '姓名'
					},
					{
						field : 'username',
						title : '用户名'
					},
					{
						field : 'email',
						title : '邮箱'
					},
					{
						field : 'status',
						title : '状态',
						align : 'center',
						formatter : function(value, row, index) {
							if (value == '0') {
								return '<span class="label label-danger">禁用</span>';
							} else if (value == '1') {
								return '<span class="label label-primary">正常</span>';
							}
						}
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a  class="btn btn-success btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
								+ row.userId
								+ '\')"><i class="fa fa-edit ">编辑</i></a> ';
							var d = '<a class="btn btn-danger btn-sm ' + s_delete_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
								+ row.userId
								+ '\')"><i class="fa fa-remove">删除</i></a> ';
							var f = '<a class="btn btn-info btn-sm ' + s_resetPwd_h + '" href="#" title="重置密码"  mce_href="#" onclick="resetPwd(\''
								+ row.userId
								+ '\')"><i class="fa fa-key">重置密码</i></a> ';
							return e + d + f;
						}
					} ],
				params:function(){
			           return $("#queryForm").serializeObject();
			     }
			});
}
function reLoad() {
	$('#table').bootstrapTable('refresh');
}
function add() {
	var url=prefix + '/add';
	app.layer_show({title:'添加用户',content : url});
}
function del(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : "/sys/user/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}
function edit(id) {
	var url=prefix + '/edit/'+id;
	app.layer_show({title:'用户修改',content : url});
}
function resetPwd(id) {
	layer.open({
		type : 2,
		title : '重置密码',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '400px', '260px' ],
		content : prefix + '/resetPwd/' + id // iframe的url
	});
}
function batchDelete() {
	var rows = $('#table').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据",{time:600});
		return;
	}
	
	app.modalConfirm('确定要删除选中的记录？',
			function() {
						var ids = new Array();
						$.each(rows, function(i, row) {
							ids[i] = row['userId'];
						});
						var data ={"ids":ids};
						app._ajax(	{url : prefix + "/batchDelete",
								     data :data
								})
	                    }			
	 );
}


function loadTree() {
	var setting = {
		view : {
			selectedMulti : false
		},
		data : {
			key : {
				title : "title"
			},
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pid"
			}
		},
		callback : {
			onClick : function(event, treeId, treeNode) {
				tree.expandNode(treeNode);
				$("#deptId").val(treeNode.id);
				$("#parentId").val(treeNode.pId);
				//$.table.search();
			}
		}
	}
	$.ajax({
		type : "GET",
		url : "/sys/dept/tree",
		success : function(result) {
			tree = $.fn.zTree.init($("#deptTree"), setting, result); //.expandAll(true);
			 // 展开第一级节点
		    var nodes = tree.getNodesByParam("level", 0);
		    for (var i = 0; i < nodes.length; i++) {
		        tree.expandNode(nodes[i], true, false, false);
		    }
		    // 展开第二级节点
		    nodes = tree.getNodesByParam("level", 1);
		    for (var i = 0; i < nodes.length; i++) {
		        tree.expandNode(nodes[i], true, false, false);
		    } 
		}
	});
};

$('#jstree').on("changed.jstree", function(e, data) {
	if (data.selected == -1) {
		var opt = {
			query : {
				deptId : '',
			}
		}
		$('#exampleTable').bootstrapTable('refresh', opt);
	} else {
		var opt = {
			query : {
				deptId : data.selected[0],
			}
		}
		$('#exampleTable').bootstrapTable('refresh',opt);
	}

});
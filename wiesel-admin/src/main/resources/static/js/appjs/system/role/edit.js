var menuTrees;
$(function() {
	loadTree();
	validateRule();
});

function loadTree(){
	var setting = {
			check:{enable:true,nocheckInherit:true,chkboxType:{"Y":"ps","N":"ps"}},
			view:{selectedMulti:false,nameIsHTML: true},
			data:{simpleData:{enable:true, idKey: "id",
		        pIdKey: "pid"},key:{title:"title"}},
			callback:{
				beforeClick: function (treeId, treeNode, clickFlag) {
					var menuTrees = $.fn.zTree.getZTreeObj(treeId);
					menuTrees.checkNode(treeNode, !treeNode.checked, true, true);
					return false;
				}
			}
		};
	var roleId = $('#roleId').val();
	$.ajax({
		type : "GET",
		url : "/sys/menu/tree/"+roleId,
		success : function(result) {
			menuTrees = $.fn.zTree.init($("#menuTree"), setting, result); //.expandAll(true);
		}
	});
};


$.validator.setDefaults({
	submitHandler : function() {
		getAllSelectNodes();
		update();
	}
});

//获取选中的菜单
function getAllSelectNodes() {
    var menuIds = "";
    var treeNodes = menuTrees.getCheckedNodes(true);
    for (var i = 0; i < treeNodes.length; i++) {
        if (0 == i) {
        	menuIds = treeNodes[i].id;
        } else {
        	menuIds += ("," + treeNodes[i].id);
        }
    }
	$('#menuIds').val(menuIds);
//    return menuIds;
}


function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url  : "/sys/role/update",
		data : $('#editForm').serialize(),
		async : false,
		error : function(request) {
			app.modalAlert("系统错误", modal_status.FAIL);
		},
		success : function(result) {
			if (result.code == web_status.SUCCESS) {
				parent.layer.msg("修改成功,正在刷新数据请稍后……",{icon:1,time: 800,shade: [0.1,'#fff']},function(){
					app.parentReload();
				});
			} else {
				app.modalAlert(result.msg, modal_status.FAIL);
			}

		}
   });
}


function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#editForm").validate({
		rules : {
			roleName : {
				required : true
			},
			roleSign : {
				required : true
			}
		},
		messages : {
			roleName : {
				required : icon + "请输入角色名"
			},
			roleSign : {
				required : icon + "请输入角色标识"
			}
		}
	});
}
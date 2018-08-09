var prefix = "/sys/menu"
$(function() {
	validateRule();
});

function selectMenuTree(){
	var parentId = $("#parentId").val();
	var url=prefix + '/treeView/'+parentId;
	app.layer_show({title:'选择菜单',content : url,area:["360px","360px"]});
	
}
//打开图标列表
function selectIcon(){
	var url = "/FontIcoList.html";
	app.layer_show({title:'图标列表',content : url, area: ['600px', '90%']});
}

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : prefix+"/save",
		data : $('#addForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}
		}
	});
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#addForm").validate({
		rules : {
			name : {
				required : true
			},
			type : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入菜单名"
			},
			type : {
				required : icon + "请选择菜单类型"
			}
		}
	})
}
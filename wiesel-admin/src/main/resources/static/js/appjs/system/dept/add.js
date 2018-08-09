var prefix = "/sys/dept"
$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function selectDeptTree(){
	var parentId = $("#parentId").val();
	var url=prefix + '/treeView/'+parentId;
	app.layer_show({title:'选择部门',content : url,area:["360px","360px"]});
	
}

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
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}
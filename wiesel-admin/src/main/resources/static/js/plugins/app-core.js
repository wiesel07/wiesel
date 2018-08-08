/**
 * 通用方法封装处理
 * Copyright (c) 2018 wuj
 */
(function ($) {
	 $.extend({
		 // 表格树封装处理
	     treeTable: {
	        _option: {},
	        _treeTable: {},
	        // 初始化表格
	        init: function(options) {
	            $.treeTable._option = options;
	            var treeTable = $('#bootstrap-treeTable').bootstrapTreeTable(options);
	            $.treeTable._treeTable = treeTable;
	        },
	        // 条件查询
	        search: function(formId) {
	        	var currentId = app.isEmpty(formId) ? $('form').attr('id') : formId;
	        	var params = {};
	        	$.each($("#" + currentId).serializeArray(), function(i, field) {
	        		params[field.name] = field.value;
		        });
	        	$.treeTable._treeTable.bootstrapTreeTable('refresh', params);
	        },
	        // 刷新
	        refresh: function() {
	        	$.treeTable._treeTable.bootstrapTreeTable('refresh');
	        }
	    }
	 });
})(jQuery);

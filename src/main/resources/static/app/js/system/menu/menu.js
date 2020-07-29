
dgID = 'dg_list';
dlgPreviewID = 'dlgPreview';
dlgModID = 'dlgMod';
dlgAddID = 'dlgAdd';
gridType = 2;

$(function () {
	$('#addType').combobox({
		onChange: function(newValue,oldValue){
			if (newValue == '0') {
				$('#addUrl').textbox({disabled: true});
			} else {
				$('#addUrl').textbox({disabled: false});
			}
		}
	});
	
	$('#modType').combobox({
		onChange: function(newValue,oldValue){
			if (newValue == '0') {
				$('#modUrl').textbox({disabled: true});
			} else {
				$('#modUrl').textbox({disabled: false});
			}
		}
	});
	
});


/**
* 增加每行的操作按钮，注意formatter里面的field不能是之前已经出现过的field，否则将会直接使用之前的field进行渲染，formatter将无效
* @param {String} id id
* @param {String} row 当前行的数据
* @param {String} index 当前行的序号
*/
function formatCZ(id, row, index){
	var cz = "";
	if(comm_shiro_str.indexOf("system:menu:get") >= 0) {
		cz += "<a href=\"javascript:void(0)\" onclick=\"addNextDataByDialog('dlgAdd','" + row.menuId + "')\" style=\"color: #3d76b9; text-decoration:underline;margin-right: 10px;\">增加下級</a>";
	}
	if(comm_shiro_str.indexOf("system:menu:edit") >= 0) {
		cz += "<a href=\"javascript:void(0)\" onclick=\"myDataByDialog('dlgMod','dlgModForm','system/menu/get/" + row.menuId + "')\" style=\"color: #3d76b9; text-decoration:underline;margin-right: 10px;\">编辑</a>";
	}
	if(comm_shiro_str.indexOf("system:menu:remove") >= 0) {
		cz += "<a href=\"javascript:void(0)\" onclick=\"delDataOne('system/menu/remove','id=" + row.menuId + "')\" style=\"color: #3d76b9; text-decoration:underline;margin-right: 10px;\">删除</a>";
	}
	return cz;
}

/**
* 修改用户状态为中文显示
* @param {String} id id
* @param {String} row 当前行的数据
* @param {String} index 当前行的序号
*/
function formatType(id, row, index){
	// 0：目录 1：菜单 2：按钮
	if (row.type == '0') {
		return '目录';
	} else if (row.type == '1') {
		return '菜单';
	} else if (row.type == '2') {
		return '按钮';
	}
}

/**
* 添加数据
* @param {String} dialogID 弹框ID
*/
function myAddDataByDialog(dialogID){
	$('#' +　dialogID).dialog('open');
	$('#' +　dialogID).form('clear');
	
	$('#addUrl').textbox({disabled: true});
	$('#modUrl').textbox({disabled: true});
	
	$('#parentIdAdd').combotree({
		url:"system/menu/tree",
		method:"get",
		editable:false
	});
}

/**
* 添加下级数据
* @param {String} dialogID 弹框ID
* @param {String} parentId 上级ID
*/
function addNextDataByDialog(dialogID, parentId){
	$('#' +　dialogID).dialog('open');
	$('#' +　dialogID).form('clear');
	$('#addUrl').textbox({disabled: true});
	$('#modUrl').textbox({disabled: true});
	
	$('#parentIdAdd').combotree({
		url:"system/menu/tree",
		method:"get",
		editable:false
	});
	
	$('#parentIdAdd').combotree('setValue', {
		id: parentId
	});
}

/**
* 预览和修改
* @param {String} dialogID 弹框ID
* @param {String} dlgPreviewFormID 待提交表单的ID
* @param {String} url 远程地址
*/
function myDataByDialog(dialogID, dlgPreviewFormID, url){
	$('#' + dialogID).dialog('open');
	$('#' + dlgPreviewFormID).form('clear');
	
	$('#parentIdMod').combotree({
		url:"system/menu/tree",
		method:"get",
		editable:false
	});
	
	$.ajax({
		cache : true,
		type : "GET",
		url : url,
		beforeSend :function(request) {
			//在请求加载数据之前触发。 返回false以取消此操作。
			$.messager.progress();
		},
		error : function(request) {
			$.messager.progress('close');
		},
		success : function(data) {
			//顶级节点
			if(data.parentId == 0){
				data.parentId = -1
			}
			//填充表格
			$('#' + dlgPreviewFormID).form('load',data);
			//如果为目录禁用输入框
			if(data.type == 0){
				$('#modUrl').textbox({disabled: true});
			} else {
				$('#modUrl').textbox({disabled: false});
			}
			
			$.messager.progress('close');
		}
	});
}

/**
* 全部收缩
*/
function collapseAll(){
    $('#dg_list').treegrid('collapseAll');
}
/**
* 全部展开
*/
function expandAll(){
    $('#dg_list').treegrid('expandAll');
}
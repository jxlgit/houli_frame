
dgID = 'dg_list';
dlgPreviewID = 'dlgPreview';
dlgModID = 'dlgMod';
dlgAddID = 'dlgAdd';

/**
* 增加每行的操作按钮，注意formatter里面的field不能是之前已经出现过的field，否则将会直接使用之前的field进行渲染，formatter将无效
* @param {String} id id
* @param {String} row 当前行的数据
* @param {String} index 当前行的序号
*/
function formatCZ(id, row, index){
	var cz = "";
	if(comm_shiro_str.indexOf("system:role:get") >= 0) {
		cz += "<a href=\"javascript:void(0)\" onclick=\"myDataByDialog('dlgPreview','dlgPreviewForm','system/role/get/" + row.roleId + "','previewTree','" + row.roleId + "')\" style=\"color: #3d76b9; text-decoration:underline;margin-right: 10px;\">预览</a>";
	}
	if(comm_shiro_str.indexOf("system:role:edit") >= 0) {
		cz += "<a href=\"javascript:void(0)\" onclick=\"myDataByDialog('dlgMod','dlgModForm','system/role/get/" + row.roleId + "','modTree','" + row.roleId + "')\" style=\"color: #3d76b9; text-decoration:underline;margin-right: 10px;\">修改</a>";
	}
	if(comm_shiro_str.indexOf("system:role:remove") >= 0) {
		cz += "<a href=\"javascript:void(0)\" onclick=\"delDataOne('system/role/remove','id=" + row.roleId + "')\" style=\"color: #3d76b9; text-decoration:underline;margin-right: 10px;\">删除</a>";
	}
	return cz;
}

/**
* 预览和修改角色
* @param {String} dialogID 弹框ID
* @param {String} dlgPreviewFormID 待提交表单的ID
* @param {String} url 远程地址
* @param {String} treeID 树ID
* @param {String} roleId 角色ID
*/
function myDataByDialog(dialogID, dlgPreviewFormID, url, treeID, roleId){
	$('#' + dialogID).dialog('open');
	$('#' + dlgPreviewFormID).form('clear');
	
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
			//填充表格
			$('#' + dlgPreviewFormID).form('load',data);
			//填充树
			$('#' + treeID).tree({
			    url: "system/menu/tree/" + roleId,
			    method:"get",
			    loadFilter: function(dataTree){
			         return dataTree;
			    }
			});
			
			$.messager.progress('close');
		}
	});
}


/**
* 提交表单(EasyUI插件方式)
* @param {String} formID 待提交表单的ID
* @param {String} treeID 树ID
*/
function mySubmitForm(formID, treeID){
	var nodes = $('#' + treeID).tree('getChecked');
	var menuIds = new Array();
	for(var i=0; i<nodes.length; i++){
        menuIds.push(nodes[i].id);
    }
	$('#' + treeID + 'MenuIds').val(menuIds);
	submitForm(formID);
}
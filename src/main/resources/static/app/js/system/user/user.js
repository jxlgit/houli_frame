
dgID = 'dg_list';
dlgPreviewID = 'dlgPreview';
dlgModID = 'dlgMod';
dlgAddID = 'dlgAdd';

$(function () {
	/**
	*点击树加载用户数据
	**/
	$('#userTree').tree({
		onClick: function(node){
			var id = node.id;
			if(id > 0){
				$('#searchDeptId').val(node.id);
			} else {
				$('#searchDeptId').val("");
				id = "";
			}
			$('#dg_list').datagrid('load', {deptId:id});
			
		},
		onLoadSuccess:function(node, data){
			//加载成功默认选中跟节点
			var node = $(this).tree('find', -1);
			$(this).tree('select', node.target);
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
	if(comm_shiro_str.indexOf("system:user:get") >= 0) {
		cz += "<a href=\"javascript:void(0)\" onclick=\"loadUrlByDialog('dlgPreview','system/user/get/" + row.userId + "')\" style=\"color: #3d76b9; text-decoration:underline;margin-right: 10px;\">预览</a>";
	}
	if(comm_shiro_str.indexOf("system:user:edit") >= 0) {
		cz += "<a href=\"javascript:void(0)\" onclick=\"loadUrlByDialog('dlgMod','system/user/edit/" + row.userId + "')\" style=\"color: #3d76b9; text-decoration:underline;margin-right: 10px;\">修改</a>";
	}
	if(comm_shiro_str.indexOf("system:user:remove") >= 0) {
		cz += "<a href=\"javascript:void(0)\" onclick=\"delDataOne('system/user/remove','id=" + row.userId + "')\" style=\"color: #3d76b9; text-decoration:underline;margin-right: 10px;\">删除</a>";
	}
	if(comm_shiro_str.indexOf("system:user:resetPwd") >= 0) {
		cz += "<a href=\"javascript:void(0)\" onclick=\"loadUrlByDialog('dlgReset','system/user/resetPwd/" + row.userId + "')\" style=\"color: #3d76b9; text-decoration:underline;margin-right: 10px;\">重置密码</a>";
	}
	return cz;
}

/**
* 修改用户状态为中文显示
* @param {String} id id
* @param {String} row 当前行的数据
* @param {String} index 当前行的序号
*/
function formatST(id, row, index){
	if (row.status == '0') {
		return '禁用';
	} else if (row.status == '1') {
		return '正常';
	}
}
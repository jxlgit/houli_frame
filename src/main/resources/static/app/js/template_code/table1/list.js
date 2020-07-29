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
	if(comm_shiro_str.indexOf("template_code:table1:get") >= 0) {
		cz += "<a href=\"javascript:void(0)\" onclick=\"previewDataByDialog('dlgPreview','dlgPreviewForm','template_code/table1/get/" + row.id + "')\" style=\"color: #3d76b9; text-decoration:underline;margin-right: 10px;\">预览</a>";
	}
	if(comm_shiro_str.indexOf("template_code:table1:edit") >= 0) {
		cz += "<a href=\"javascript:void(0)\" onclick=\"previewDataByDialog('dlgMod','dlgModForm','template_code/table1/get/" + row.id + "')\" style=\"color: #3d76b9; text-decoration:underline;margin-right: 10px;\">修改</a>";
	}
	if(comm_shiro_str.indexOf("template_code:table1:remove") >= 0) {
		cz += "<a href=\"javascript:void(0)\" onclick=\"delDataOne('template_code/table1/remove','id=" + row.id + "')\" style=\"color: #3d76b9; text-decoration:underline;margin-right: 10px;\">删除</a>";
	}
	return cz;
}
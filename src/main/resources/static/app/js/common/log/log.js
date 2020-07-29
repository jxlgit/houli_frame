
dgID = 'dg_list';
dlgPreviewID = 'dlgPreview';
dlgModID = 'dlgMod';
dlgAddID = 'dlgAdd';
isShowGridTip = true;

function formatCZ(id, row, index){
	var cz = "";
	if(comm_shiro_str.indexOf("common:log:get") >= 0) {
		cz += "<a href=\"javascript:void(0)\" onclick=\"previewDataByDialog('dlgPreview','dlgPreviewForm','common/log/get/" + row.id + "')\" style=\"color: #3d76b9; text-decoration:underline;margin-right: 10px;\">预览</a>";
	}
	if(comm_shiro_str.indexOf("common:log:edit") >= 0) {
		cz += "<a href=\"javascript:void(0)\" onclick=\"previewDataByDialog('dlgMod','dlgModForm','common/log/get/" + row.id + "')\" style=\"color: #3d76b9; text-decoration:underline;margin-right: 10px;\">修改</a>";
	}
	if(comm_shiro_str.indexOf("common:log:remove") >= 0) {
		cz += "<a href=\"javascript:void(0)\" onclick=\"delDataOne('common/log/remove','id=" + row.id + "')\" style=\"color: #3d76b9; text-decoration:underline;margin-right: 10px;\">删除</a>";
	}
	return cz;
}





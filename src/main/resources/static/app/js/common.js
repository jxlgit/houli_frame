
// 定义用于js内权限控制的字符串
var comm_shiro_str = '';
// 是否展示全局datagridtip，默认为false
var isShowGridTip = false;



//扩展ajax方法,用来统一处理后端抛出的异常
(function($){
    //备份jquery的ajax方法
    var _ajax=$.ajax;
    //重写jquery的ajax方法
    $.ajax=function(opt){
        //备份opt中error和success方法
        var fn = {
            error:function(XMLHttpRequest, textStatus, errorThrown){},
            success:function(data, textStatus){},
            complete:function(XMLHttpRequest, textStatus){},
            beforeSend : function(XMLHttpRequest){}
        };
        if(opt.error){
            fn.error=opt.error;
        }
        if(opt.success){
            fn.success=opt.success;
        }
        if(opt.beforeSend){
            fn.beforeSend=opt.beforeSend;
        }
        if(opt.complete){
            fn.complete=opt.complete;
        }
        // //扩展增强处理
        var _opt = $.extend(opt, {
            error: function (xhr,textStatus,errorThrown) {
            	var code = xhr.status;
            	if (code == 504) {
    				//超时
                    $.messager.alert({
                    	title: '提示',
                    	msg: "登录超时，请重新登录！",
                    	icon: "error",
                    	fn: function(){
                    		window.top.location.href='login';
                    	}
                    });
                } else if (code == 403) {
                	//无权限
            		$.messager.alert('提示', "你无权访问！", 'error');
                } else {
                	//其他错误
                	$.messager.alert('提示', "服务器错误！", 'error');
                }
            	
                fn.error(xhr, textStatus, errorThrown);
            },
            success: function (data, textStatus) {
            	/**
            	if(typeof data == 'string') {
            		var json = JSON.parse(data);
            		if(json.hasOwnProperty("code")) {
            			if (json.code == 999) {
            				//超时
                            $.messager.alert({
                            	title: '提示',
                            	msg: json.msg,
                            	fn: function(){
                            		window.top.location.href='login';
                            	}
                            });
                        } else if (json.code == 403) {
                        	//无权限
                    		$.messager.alert('提示', json.msg, 'error');
                        } else if (json.code != 0 && json.code != 200){
                        	//其他错误
                        	$.messager.alert('提示', json.msg, 'error');
                        }
            			
            		}
            	}
               	**/
                fn.success(data, textStatus);
            },
            beforeSend: function (XHR,obj) {
                //提交前回调方法
                fn.beforeSend(XHR,obj);
            },
            complete: function (XHR, TS) {
                //请求完成后回调函数 (请求成功或失败之后均调用)。
                fn.complete(XHR, TS);
            }
        });
        return _ajax(_opt);
    };
})(jQuery);



$(function(){
	
	//设置全局Tip
	$('#dg_list').datagrid({
		onLoadSuccess:function(data){
			
			if (isShowGridTip) {
				showGridTip();
			}
			
		} 
	});
    
});

/**
 * 判断字符是否为空
 * @param obj
 * @returns 为空返回true，否则返回false
 */
function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
}

/**
 * 展示全局grid提示信息
 * @returns
 */
function showGridTip(){
	$('.datagrid-btable').find('div').tooltip({
        content: function(){
        	var val = $(this).text();
        	if (val != null && val !='') {
        		var tip = '<span style="color:#fff">' + val + '</span>';
                return tip;
			}
        },
        onShow: function(){
            $(this).tooltip('tip').css({
                backgroundColor: '#666',
                borderColor: '#666'
            });
        }
    });
}

/**
* 展示单个列的提示信息（自定义单元格的方式）
* @param {String} value 单元格值
*/
function showCellTooltip(value){  
	if(value != null){
		return "<span title='" + value + "' class='easyui-tooltip'>" + value + "</span>";  
	}
}  

/**
 * 将表单数据转为json
 * @param {String} formID 表单ID
 */
function form2Json(formID) {
	var arr = $("#" + formID).serializeArray()
	var jsonStr = "";

	if(arr.length > 0) {
		jsonStr += '{';
		for(var i = 0; i < arr.length; i++) {
			jsonStr += '"' + arr[i].name + '":"' + arr[i].value + '",'
		}
		jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
		jsonStr += '}';

		var json = JSON.parse(jsonStr);
		return json;
	} else {
		return "";
	}
}

/**
 * 页面框架相关， 增加Tab，默认可以关闭
 * @param {String} title 标题
 * @param {String} url 链接地址
 */
function addTabDefault(title, url) {
	addTab(title, url, true);
}

/**
 * 页面框架相关， 增加Tab
 * @param {String} title 标题
 * @param {String} url 链接地址
 * @param {Boolean} canClose 是否可以关闭
 */
function addTab(title, url, canClose) {
	var isHave = parent.$('#centerPanel').tabs('exists', title);
	if(isHave) {
		parent.$('#centerPanel').tabs('select', title);
	} else {
		var strCont = '<iframe scrolling="auto" frameborder="0" src="' + url + '" style="width:100%; height:100%; margin: -2px;"></iframe>';
		parent.$('#centerPanel').tabs('add', {
			title: title,
			content: strCont,
			closable: canClose
		});
	}
}

/**
 * 页面框架相关， 删除当前打开的Tab
 */
function removeCurrentTab(){
    var tab = parent.$('#centerPanel').tabs('getSelected');
    if (tab){
        var index = parent.$('#centerPanel').tabs('getTabIndex', tab);
        parent.$('#centerPanel').tabs('close', index);
    }
}

/**
 * 页面框架相关， 删除所有可以关闭的Tab
 */
function removeAllTabs(){
	var tabs = $('#centerPanel').tabs('tabs');
	for(var i = tabs.length - 1; i >= 0; i--){
		var tab = tabs[i];
		if(tab != undefined) {
			var closable = tab.panel('options').closable;
			if(closable){
                $('#centerPanel').tabs('close', i);
			}
		}
	}
}

/**
 * 页面框架相关， 删除其他可以关闭的Tab
 */
function removeOtherTabs(){
	var tabs = $('#centerPanel').tabs('tabs');
	var tabSelected=$('#centerPanel').tabs('getSelected');
	for(var i = tabs.length - 1; i >= 0; i--){
		var tab = tabs[i];
		if(tab != undefined) {
			var closable = tab.panel('options').closable;
			if(closable && tabSelected != tab){
				$('#centerPanel').tabs('close',i);
			}
		}
	}
}

/**
 * 页面框架相关，展开收缩菜单项
 */
function toggleMenu() {
	var opts = $('#sm').sidemenu('options');
	$('#sm').sidemenu(opts.collapsed ? 'expand' : 'collapse');
	opts = $('#sm').sidemenu('options');
	
	if(opts.collapsed){
		$('#sm').sidemenu('resize', {width:60});
		$(".logo-div").css("width","60px");
		$('#logo').attr("src", "app/img/logo-d.png");
		$('#mainLayout').layout('panel', 'west').panel('resize',{width:60});
	} else {
		$('#sm').sidemenu('resize', {width:180});
		$(".logo-div").css("width","180px");
		$('#logo').attr("src", "app/img/logo.png");
		$('#mainLayout').layout('panel', 'west').panel('resize',{width:180});
	}
	$('#mainLayout').layout('resize');
	
}



// easyui的datagrid表格ID
var dgID;
// 预览数据 弹框ID
var dlgPreviewID;
// 修改数据 弹框ID
var dlgModID;
// 新增数据 弹框ID
var dlgAddID;
// datagrid还是treegrid，默认为datagrid，datagrid为1，treegrid为2
var gridType = 1;

/**
* 加载页面至Dialog
* @param {String} dialogID 弹框ID
* @param {String} url 远程地址
*/
function loadUrlByDialog(dialogID, url){
	$('#' + dialogID).dialog('open');
	$('#' + dialogID).dialog('center');
	$('#' + dialogID).dialog('refresh', url);
}
/**
* 表格条件搜索（一个文本框搜索一个字段，所有搜索文本框需要在Form表单内）
* @param {String} dgID easyui的datagrid表格ID
* @param {String} dgSearchFormID 搜索表单ID
*/
function submitSearch(dgID, dgSearchFormID){
	if (gridType == 1) {
		$('#' + dgID).datagrid('load', form2Json(dgSearchFormID));
	} else if (gridType == 2) {
		$('#' + dgID).treegrid('load', form2Json(dgSearchFormID));
	} else {
		$('#' + dgID).datagrid('load', form2Json(dgSearchFormID));
	}
}
/**
* 表格条件搜索（一个文本框搜索多个字段，不需要在Form表单内）
* 注意：默认搜索的KEY都是and关系，如果需要or关系， 需要在Mapper.xml文件中将list下的where条件中and改为or
* @param {String} dgID easyui的datagrid表格ID
* @param {String} searchKeys 搜索的key,逗号分隔，如：'username,password,email'
*/
function moreSearch(dgID, searchKeys){
	var searchName = $('#searchName').val();
	var searchKeysArray = searchKeys.split(",");
	
	var jsonSearch = '{';
	$.each(searchKeysArray, function(i, searchKey) {
		if (i == searchKeysArray.length - 1) {
			jsonSearch += '"' + searchKey + '":"' + searchName + '"';
		} else {
			jsonSearch += '"' + searchKey + '":"' + searchName + '",';
		}
	});
	jsonSearch += "}";
	
	if (gridType == 1) {
		$('#' + dgID).datagrid('load', JSON.parse(jsonSearch));
	} else if (gridType == 2) {
		$('#' + dgID).treegrid('load', JSON.parse(jsonSearch));
	} else {
		$('#' + dgID).datagrid('load', JSON.parse(jsonSearch));
	}
}
/**
* 表格条件搜索（用于easyui下拉条件搜索，只需要调用doSearch，key和value会自动填充）
* @param {String} key 搜索对应的key
* @param {String} value 搜索对应的值
*/
function doSearch(value, key){
    var jsonSearch = '{';
	jsonSearch += '"' + key + '":"' + value + '"';
	jsonSearch += "}";
	
    if (gridType == 1) {
		$('#' + dgID).datagrid('load', JSON.parse(jsonSearch));
	} else if (gridType == 2) {
		$('#' + dgID).treegrid('load', JSON.parse(jsonSearch));
	} else {
		$('#' + dgID).datagrid('load', JSON.parse(jsonSearch));
	}
}
/**
* 添加数据
* @param {String} dialogID 弹框ID
*/
function addDataByDialog(dialogID){
	$('#' +　dialogID).dialog('open');
	$('#' +　dialogID).form('clear');
}
/**
* 预览数据
* @param {String} dialogID 弹框ID
* @param {String} dlgPreviewForm 需要填充弹框内内容的表单ID
* @param {String} url 远程地址
*/
function previewDataByDialog(dialogID, dlgPreviewFormID, url){
	$('#' + dialogID).dialog('open');
	getData(dlgPreviewFormID ,url);
}
/**
* 删除数据（单条）
* @param {String} url 远程地址
* @param {String} data 发送的数据，如id=1
*/
function delDataOne(url, data){
	$.messager.confirm('删除', '你确定要删除该条数据吗？', function(r){
        if (r){
       	 postData(url,data);
        }
   });
}
/**
* 删除数据（批量）
* @param {String} dgID easyui的datagrid表格ID
* @param {String} url 远程地址
* @param {String} idField 用于删除数据的字段，默认为id
*/
function delDataBatch(dgID, url, idField){
	// 返回所有选择的行，当没有选择的记录时，返回一个空数组
	var rows = $('#' + dgID).datagrid('getChecked');
	
	if (gridType == 1) {
		rows = $('#' + dgID).datagrid('getChecked');
	} else if (gridType == 2) {
		rows = $('#' + dgID).treegrid('getCheckedNodes');
	} else {
		rows = $('#' + dgID).datagrid('getChecked');
	}
	
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择要删除的数据。', 'info');
		return;
	}
	$.messager.confirm('删除', '确认要删除选中的'+ rows.length +'条数据吗?', function(r){
        if (r){
        	var ids = new Array();
        	if (idField == undefined || idField == null) {
        		idField = "id";
			}
			// 遍历所有选择的行数据，取每条数据对应的ID
			$.each(rows, function(i, row) {
				ids[i] = row[idField];
			});
			$.ajax({
				type : 'POST',
				data : {
					"ids" : ids
				},
				url : url,
				success : function(data) {
					if (data.code == 0) {
						reLoad();
						$.messager.alert('提示', data.msg, 'info');
					} else {
						$.messager.alert('提示', data.msg, 'error');
					}
				}
			});
        }
   });
}
/**
* 提交表单(EasyUI插件方式)
* @param {String} formID 待提交表单的ID
*/
function submitForm(formID){
	$('#' + formID).form('submit', {
       onSubmit: function(param){
	       	// param.p1 = 'value1';可以添加额外的参数
	        // 在提交之前触发，返回false以阻止提交操作,可以做一些检查。                    
	       	var isValid = $(this).form('validate');
	   		if (!isValid){
	   			return isValid;     
	   		} else {
	   			$.messager.progress();
	   		}
       },
       success:function(data){
       	// change the JSON string to javascript object
       	var data = eval('(' + data + ')'); 
       	if (data.code == 0){
	       		reLoad();
	       		$.messager.alert('提示', data.msg, 'info');
            } else {
				$.messager.alert('提示', data.msg, 'error');
			}
       		$.messager.progress('close');
       },
       onLoadError:function(){
       		$.messager.progress('close');
       }
	});
}
/**
* 发送数据(JQuery的POST方式)
* @param {String} url 远程地址
* @param {String} data 发送的数据，如id=1
*/
function postData(url,data) {
	$.ajax({
		cache : true,
		type : "POST",
		url : url,
		data : data,
		beforeSend :function(request) {
			//在请求加载数据之前触发。 返回false以取消此操作。
			$.messager.progress();
		},
		error : function(request) {
			$.messager.progress('close');
		},
		success : function(data) {
			if (data.code == 0){
				reLoad();
				$.messager.alert('提示', data.msg, 'info');
			} else {
				$.messager.alert('提示', data.msg, 'error');
			}
			$.messager.progress('close');
		}
	});
}
/**
* 获取数据并填充至表单中(JQuery的GET方式)
* @param {String} formID 待提交表单的ID
* @param {String} url 远程地址
*/
function getData(formID, url){
	$('#' + formID).form('clear');
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
			$('#' + formID).form('load',data);
			$.messager.progress('close');
		}
	});
}

/**
* get请求,无返回值(JQuery的GET方式)
* @param {String} url 远程地址
*/
function getDataNoReturn(url){
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
			$.messager.progress('close');
		}
	});
}

/**
* 重新加载表格数据 和 初始化弹窗数据
*/
function reLoad() {
	if (gridType == 1) {
		$('#' + dgID).datagrid('reload');
	} else if (gridType == 2) {
		$('#' + dgID).treegrid('reload');
	} else {
		$('#' + dgID).datagrid('reload');
	}
	
	$('#' + dlgPreviewID).dialog('close');
	$('#' + dlgModID).dialog('close');
	$('#' + dlgAddID).dialog('close');
}




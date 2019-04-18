/***
 * 
 * 右侧操作区域，使用easyui时所用到的方法
 **/
///////////////////////////////////////////////////////////////////以下是为easyui增删改查操作提供的基础方法/////////////////////////////////////////////////////////////////
/**
 * 初始化右侧页面方法
 */
;
function initRightPage(dg, searchform) {
	initBreadcrumb();
	initEasyUI();
	initSearchState(searchform);
	initBindEvent();
	initMyBindEvent();
	initDataGridState(dg);//初始化datagrid
}
/**
 * 面包屑初始化，应用于所有管理后台右侧的操作区域
 */
function initBreadcrumb() {
	var topbody;
	if (window.parent.frames.topbar) {
		topbody = window.parent.frames.topbar.document.body;
	}
	try {
		$("[data-crumb]")
				.breadcrumb(
						{
							target : $(".breadcrumbs", topbody),
							crumbdivider : '<div class="breadcrumb_divider"></div>',
							crumbhtml : '<a href="javascript:void(0)" data-scrumb="{index:{index}}">{title}</a>'
						});
	} catch (e) {
	}
}
function initEasyUI() {
	// $('.easyui-tooltip').tooltip({
	// onShow: function(){
	// $(this).tooltip('tip').css({backgroundColor:'#ccc', borderColor:'#999'});
	// }
	// });
}
/**
 * 初始化搜索状态
 */
function initSearchState(searchform) {
	if (getSearchForm(searchform).length == 0) {
		getSearchIcon().hide();
	}
}
/**
 * 初始绑定事件
 */
function initBindEvent() {
	$("html").bind("datagrid-onselect-event", dgOnSelectHandler);
	$("html").bind("datagrid-onunselect-event", dgOnUnSelectHandler);
	$("html").bind("datagrid-onselectall-event", dgOnSelectAlltHandler);
	$("html").bind("datagrid-onunselectall-event", dgOnUnSelectAlltHandler);
	$("html").bind("datagrid-oncheck-event", dgOnCheckHandler);
	$("html").bind("datagrid-onuncheck-event", dgOnUnCheckHandler);
	$("html").bind("datagrid-oncheckall-event", dgOnCheckAllHandler);
	$("html").bind("datagrid-onuncheckall-event", dgOnUnCheckAllHandler);
	$("html").bind("datagrid-onloadsuccess-event", dgOnLoadSuccessHandler);
	$("html").bind("form-reset-event", formResetHandler);
	$("html").bind("form-clear-event", formClearHandler);
}
/**
 * 初始自定义绑定事件
 */
function initMyBindEvent() {
	$("html").bind("datagrid-onselect-event", mydgOnSelectHandler);
	$("html").bind("datagrid-onunselect-event", mydgOnUnSelectHandler);
	$("html").bind("datagrid-onselectall-event", mydgOnSelectAlltHandler);
	$("html").bind("datagrid-onunselectall-event", mydgOnUnSelectAlltHandler);
	$("html").bind("datagrid-oncheck-event", mydgOnCheckHandler);
	$("html").bind("datagrid-onuncheck-event", mydgOnUnCheckHandler);
	$("html").bind("datagrid-oncheckall-event", mydgOnCheckAllHandler);
	$("html").bind("datagrid-onuncheckall-event", mydgOnUnCheckAllHandler);
	$("html").bind("datagrid-onloadsuccess-event", mydgOnLoadSuccessHandler);
	$("html").bind("form-reset-event", myformResetHandler);
	$("html").bind("form-clear-event", myformClearHandler);
	$("html").bind("status-after-event", myStatusAfterHandler);
}

/**
 * 初始化datagrid的状态
 * 
 * @param dg
 */
function initDataGridState(dg) {
	getDataGrid(dg).datagrid({
		loadFilter : datagrid_loadFilter,
		onSelect : datagrid_onSelect,
		onUnselect : datagrid_onUnselect,
		onSelectAll : datagrid_onSelectAll,
		onUnSelectAll : datagrid_onUnSelectAll,
		onCheck : datagrid_onCheck,
		onUncheck : datagrid_onUncheck,
		onCheckAll : datagrid_onCheckAll,
		onUncheckAll : datagrid_onUnCheckAll,
		onLoadSuccess : datagrid_onLoadSuccess
	});
}
/**
 * 直接提交表单
 * 
 * @param obj
 *            当前点击按钮，在form中
 */
function submit(obj) {
	if ($(obj).parents("form").form("validate")) {
		$(obj).parents("form").submit();
	}
}
/**
 * 清除表单
 * 
 * @param obj
 *            当前点击按钮，在form中
 */
function clearForm(form) {
//	var args = {
//			form : form
//		};
	$("html").trigger("form-clear-event", form);
	//form.form("clear");
	//initFormData(form, {});
	$(form).form("reset");//使用重置代替清空所有字段
}
/**
 * 重置表单所有数据
 * 
 * @param obj
 *            当前点击按钮，在form中
 */
function resetForm(obj) {
	if(confirm("确定重置所有数据吗？")){
		var args = {
			obj : obj
		};
		$("html").trigger("form-reset-event", args);
	}
}
/**
 * 重新初始化checkbox/radio等的初始状态
 * @param form
 */
function reinitFormDefaultData(form) {
	$("input[checked='checked']", form).click();
}
/**
 * datagrid右上角的查询图标，点击显示查询表单
 */
function searchShowHandler(searchform) {
	getSearchForm(searchform).slideToggle();
	reinitFormDefaultData(searchform);
}
/**
 * 提交查询处理方法
 * 
 * @param obj
 *            当前点击对象
 * @param dg
 * @param searchform
 */
function searchHandler(obj, dg, searchform) {
	getDataGrid(dg).datagrid("load", getSearchForm(searchform).serializeObj());// TODO
	// 错误处理
}
/**
 * 点击添加按钮处理方法
 * 
 * @param obj
 *            点击按钮，this
 * @param addform
 *            需要显示添加的form
 */
function addHandler(obj, addform) {
	crumbShow(obj);
	clearForm(getAddForm(addform));
	reinitFormDefaultData(getAddForm(addform));
}
/**
 * 点击编辑按钮处理方法
 * 
 * @param obj
 *            this
 * @param dg
 * @param editform
 */
function editHandler(obj, dg, editform) {
	var urls = getFreshRowUrls(dg);
//	console.log(editform);
//	console.log(urls);
	if (editBefore(obj, dg, editform, urls, 0)) {
//		console.log("1");
		editAction(obj, dg, editform);
		// editAfter(obj, dg, editform);
	}
}
/**
 * 编辑前的对选中的row做封装，使用递归方法，在请求完ajax之后才调用editAction方法
 * 
 * @param obj
 * @param dg
 * @param editform
 * @param urls
 * @param i
 * @returns {Boolean}
 */
function editBefore(obj, dg, editform, urls, i) {
	if (urls != null && urls.length > 0 && i < urls.length) {
		var url = urls[i];
		$.get(url, function(data) {
			freshRowHandler(obj, dg, editform, data, i);
			i++;
			if (i < urls.length) {
				editBefore(obj, dg, editform, urls, i);
			} else {
				editAction(obj, dg, editform);
			}
		});
		// 调用ajax时不返回值，在方法内部调用action
	} else {
		freshRowHandler(obj, dg, editform, null, i);//没有调用ajax获取新数据时调用刷新row数据
		return true;
	}
}
/**
 * 开始编辑操作
 * 
 * @param obj
 * @param dg
 * @param editform
 */
function editAction(obj, dg, editform) {
	var row = getSelected(dg);
	if (row == null) {
		warn('请先选择要编辑的项！');
	} else {
		crumbShow(obj);
		initFormData(getEditForm(editform), row);
		// getEditForm(editform).attr("action",
		// getEditForm(editform).attr("action").format(row));// 格式化并赋值
	}
}
// function editAfter(obj, dg, editform) {
//	
// }
/**
 * 点击删除处理方法
 * 
 * @param uri 要提交的uri
 * @param dg 对应的datagrid的选择器
 */
function deleteHandler(uri, dg) {
	var list = getSelections(dg);
	if (list.length == 0) {
		warn('请先选择要删除的项！');
	} else {
		$.messager.confirm('确认删除', '你确定要删除选中的项吗？', function(flag) {
			if (flag) {
				$.get(uri, getJsonForDelete(list), function(result) {
					if (result.success) {
						getDataGrid(dg).datagrid("reload"); // 重新加载数据表格
						deleteSuccessHandler(result);
					} else {
						deleteErrHandler(result);
					}
				});
			}
		});
	}
}
/**
 * 改变状态处理方法
 * 
 * @param obj
 * @param action
 * @param dg
 */
function statusHandler(obj, action, dg, dlg, statusform) {
	var list = getSelections(dg);
	if (list.length == 0) {
		warn('请先选择要操作的项！');
	} else {
		$("html").trigger("status-reset-event");
		getStatusForm(statusform).attr("action", action);
		initStatusForm(dg, statusform);
		getStatusDLG(dlg).dialog('open');
		var title = getStatusDLG(dlg).dialog("options").title.format($(obj).text());
		getStatusDLG(dlg).dialog('setTitle', title);
		initFormData(getStatusForm(statusform), getSelected(dg));
		$("html").trigger("status-after-event", list[0]);
	}
}
/**
 * 初始化statusform的参数
 * 
 * @param dg
 * @param statusform
 */
function initStatusForm(dg, statusform) {
	// var ids = getSelectedIDs(null, dg);
	// $("[name='ids']", getStatusForm(statusform)).val(ids);
}
/**
 * 状态提交的处理方法
 * 
 * @param obj
 * @param dg
 * @param dlg
 * @param statusform
 */
function status_submitHandler(obj, dg, dlg, statusform) {
	getStatusForm(statusform).form('submit', {
		success : function(str) {
			var result = $.parseJSON(str);
			if (result.success) {
				getDataGrid(dg).datagrid("reload"); // 重新加载数据表格
				getStatusDLG(dlg).dialog('close');
				$("html").trigger("status-reset-event");
			}
			resultHandler(result);
		}
	});
}
/**
 * 状态弹出框取消操作处理方法
 * 
 * @param obj
 */
function statusdlg_cancelHandler(obj, dlg) {
	getStatusDLG(dlg).dialog('close');
}
/**
 * 查看详情处理方法
 * 
 * @param obj
 * @param dg
 * @param viewform
 */
function viewHandler(obj, dg, viewform) {
	var urls = getFreshRowUrls(dg);
	if (viewBefore(obj, dg, viewform, urls, 0)) {
		viewAction(obj, dg, viewform);
		// viewAfter(obj, dg, viewform);
	}
}
/**
 * 查看详情前的对选中的row做封装，可重写，若不返回true则可在方法里直接调用viewAction(obj, dg, editform);
 * 
 * @param obj
 * @param dg
 * @param viewform
 */
function viewBefore(obj, dg, viewform, urls, i) {
	// console.log(urls);
	// console.log(i);
	// console.log(urls != null && urls.length > 0 && i < urls.length);
	if (urls != null && urls.length > 0 && i < urls.length) {
		var url = urls[i];
		$.get(url, function(data) {
			freshRowHandler(obj, dg, viewform, data, i);
			i++;
			if (i < urls.length) {
				return viewBefore(obj, dg, viewform, urls, i);
			} else {
				viewAction(obj, dg, viewform);
			}
		});
		// 调用ajax时不返回值，在方法内部调用action
	} else {
		freshRowHandler(obj, dg, viewform, null, i);//没有调用ajax获取新数据时调用刷新row数据
		return true;
	}
}
/**
 * 查看操作
 * 
 * @param obj
 * @param dg
 * @param viewform
 */
function viewAction(obj, dg, viewform) {
	var row = getSelected(dg);
	if (row == null) {
		warn('请先选择要查看的项！');
	} else {
		crumbShow(obj);
		initFormData(getViewForm(viewform), row);
	}
}
// function viewAfter(obj, dg, viewform) {
//	
// }
/**
 * 更新选中一行内的row数据
 * 
 * @param obj
 * @param dg
 * @param editform
 * @param data
 * @param i 第几个url
 */
function freshRowHandler(obj, dg, form, data, i) {
	var row = getSelected(dg);
	var index = getDataGrid(dg).datagrid('getRowIndex', row);
	freshRow(row, data, i);
	// getDataGrid(dg).datagrid('updateRow', {index:index, row:row});
}
/**
 * 添加或编辑的表单提交处理方法，使用easyui的form提交
 * 
 * @param obj
 *            当前点击按钮
 * @param dg
 *            对应的datagrid的选择器
 */
function submitHandler(obj, dg) {
	$(obj).parents("form").form('submit', {
		success : function(str) {
			var result = $.parseJSON(str);
			if (result.success || result.addSuccess || result.editSuccess) {
				getDataGrid(dg).datagrid("reload"); // 重新加载数据表格
				clearForm($(obj).parents("form"));
				submitSuccessHandler(result);
			} else {
				submitErrHandler(result);
			}
		}
	});
}
/**
 * doaction处理方法
 * 
 * @param obj 点击的按钮
 * @param uri 要提交的uri
 * @param dg 对应的datagrid的选择器
 */
function doactionHandler(obj, uri, confirm, needselection, dg) {
	var name = $(obj).text();
	var list = getSelections(dg);
	if (list.length == 0 && needselection) {
		warn('请先选择要{0}的项！'.format(name));
	} else {
		if(confirm) {
			$.messager.confirm('确认{0}'.format(name), '你确定要{0}选中的项吗？'.format(name), trueHandler);
		} else {
			trueHandler(true);
		}
		function trueHandler(flag) {//确认操作
			if (flag) {
				$.get(uri.format(list[0]), getJsonForDoaction(obj, list), function(result) {
					if (result.success) {
						getDataGrid(dg).datagrid("reload"); // 重新加载数据表格
						doactionSuccessHandler(obj, result);
					} else {
						doactionErrHandler(obj, result);
					}
				});
			}
		}
	}
}
// /////////////////////////////////////////////////////////////////以下是可重写的事件/////////////////////////////////////////////////////////////////
/**
 * datagrid加载数据成功后重新初始化数据
 */
function datagrid_loadFilter(data) {
	initDGData(data.rows);
	return data;
}
/**
 * datagrid的选择事件，若需要增加额外的处理方法，可以$("html").bind("datagrid-onselect-event",
 * function(){})增加一个绑定
 */
function datagrid_onSelect(index, data) {
	var args = {
		'dg' : "#{0}".format($(this).datagrid("options").id),
		'index' : index,
		'data' : data
	};
	$("html").trigger("datagrid-onselect-event", args);
}
/**
 * datagrid取消选择事件
 * 
 * @param index
 * @param data
 */
function datagrid_onUnselect(index, data) {
	var args = {
		'dg' : "#{0}".format($(this).datagrid("options").id),
		'index' : index,
		'data' : data
	};
	$("html").trigger("datagrid-onunselect-event", args);
}
/**
 * datagrid选择所以行时的事件
 * @param rows
 */
function datagrid_onSelectAll(rows) {
	var args = {
			'dg' : "#{0}".format($(this).datagrid("options").id),
			'rows' : rows,
		};
	$("html").trigger("datagrid-onselectall-event", args);
}
/**
 * datagrid取消选择所以行时的事件
 * @param rows
 */
function datagrid_onUnSelectAll(rows) {
	var args = {
			'dg' : "#{0}".format($(this).datagrid("options").id),
			'rows' : rows,
		};
	$("html").trigger("datagrid-onunselectall-event", args);
}
/**
 * datagrid勾选复选框时的事件
 * @param index
 * @param data
 */
function datagrid_onCheck(index, data) {
	var args = {
		'dg' : "#{0}".format($(this).datagrid("options").id),
		'index' : index,
		'data' : data
	};
	$("html").trigger("datagrid-oncheck-event", args);
}
/**
 * datagrid取消勾选复选框时的事件
 * @param index
 * @param data
 */
function datagrid_onUncheck(index, data) {
	var args = {
		'dg' : "#{0}".format($(this).datagrid("options").id),
		'index' : index,
		'data' : data
	};
	$("html").trigger("datagrid-onuncheck-event", args);
}
/**
 * datagrid勾选所有复选框时的事件
 * @param rows
 */
function datagrid_onCheckAll(rows) {
	var args = {
			'dg' : "#{0}".format($(this).datagrid("options").id),
			'rows' : rows,
		};
	$("html").trigger("datagrid-oncheckall-event", args);
}
/**
 * datagrid取消勾选所有复选框时的事件
 * @param rows
 */
function datagrid_onUnCheckAll(rows) {
	var args = {
			'dg' : "#{0}".format($(this).datagrid("options").id),
			'rows' : rows,
		};
	$("html").trigger("datagrid-onuncheckall-event", args);
}
/**
 * datagrid加载成功事件
 * 
 * @param data
 */
function datagrid_onLoadSuccess(data) {
	var args = {
			'dg' : "#{0}".format($(this).datagrid("options").id),
			'index' : 0,
			'data' : data
	};
	$("html").trigger("datagrid-onloadsuccess-event", args);
}
/**
 * datagrid选择一条记录时对应状态按钮的操作
 * 
 * @param j
 */
function dgOnSelectHandler(event, j) {
	toolbarStateHandler(event, j);
}
/**
 * datagrid取消选择一条记录时对应状态按钮的操作
 * 
 * @param j
 */
function dgOnUnSelectHandler(event, j) {
	toolbarStateHandler(event, j);
}
/**
 * datagrid选择所有行时处理逻辑
 * @param event
 * @param j
 */
function dgOnSelectAlltHandler(event, j) {
	//nothing
}
/**
 * datagrid取消选择所有行时处理逻辑
 * @param event
 * @param j
 */
function dgOnUnSelectAlltHandler(event, j) {
	//nothing
}
/**
 * datagrid勾选复选框时处理逻辑
 */
function dgOnCheckHandler() {
	//nothing
}
/**
 * datagrid取消勾选复选框时处理逻辑
 */
function dgOnUnCheckHandler() {
	//nothing
}
/**
 * datagrid勾选所有行时处理逻辑
 */
function dgOnCheckAllHandler() {
	//nothing
}
/**
 * datagrid取消勾选所有行时处理逻辑
 */
function dgOnUnCheckAllHandler() {
	//nothing
}
/**
 * 重新初始化datagrid的row数据
 * @param data
 */
function initDGData(row) {
	
}
/**
 * datagrid加载数据成功时对应状态按钮的操作
 * 
 * @param j event事件触发时传递的参数
 */
function dgOnLoadSuccessHandler(event, j) {
	toolbarStateHandler(event, j);
}
/**
 * toolbar工具栏按钮状态的处理方法
 * @param event
 * @param j
 */
function toolbarStateHandler(event, j) {
	var list = getSelections(j.dg);
	if (list.length == 0) {
		getBtnDisable().linkbutton("disable");
		getBtnEnable().linkbutton("disable");
		getBtnEdit().linkbutton("disable");
		getBtnDel().linkbutton("disable");
		getBtnView().linkbutton("disable");
	} else {
		var row = list[0];
		getBtnEdit().linkbutton("enable");
		getBtnDel().linkbutton("enable");
		getBtnView().linkbutton("enable");
		if (row.status == 1) {// 正常
			getBtnDisable().linkbutton("enable");
			getBtnEnable().linkbutton("disable");
		} else if (row.status == 2) {// 已删除
			getBtnDisable().linkbutton("disable");
			getBtnEnable().linkbutton("disable");
			getBtnEdit().linkbutton("disable");
		} else if (row.status == 0) {// 冻结
			getBtnDisable().linkbutton("disable");
			getBtnEnable().linkbutton("enable");
		} else if (row.status == 10) {// 初始
			getBtnDisable().linkbutton("disable");
			getBtnEnable().linkbutton("enable");
		} else {// 其他
			getBtnDisable().linkbutton("disable");
			getBtnEnable().linkbutton("enable");
		}
	}
	if (list.length > 1) {
		getBtnView().linkbutton("disable");
	}
	mytoolbarStateHandler(event, j);
}

/**
 * 重置表单数据处理方法
 * @param event
 * @param j
 */
function formResetHandler(event, j) {
	$(j.obj).parents("form").form("reset");
}
/**
 * 清空表单数据处理方法
 * @param event
 * @param j
 */
function formClearHandler(event, j) {
	
}
// /////////////////////////////////////////////////////////////////以下是可重写的get方法/////////////////////////////////////////////////////////////////
/**
 * 获取datagrid对象，若datagrid的id不是dg，则可以重写此方法
 */
function getDataGrid(dg) {
	if (dg) {
	} else {
		dg = "#dg"
	}
	return $(dg);
}
/**
 * 
 * 获取searchform对象，若searchform的class不是searchform，则可以重写此方法
 * 
 * @param searchform
 * @returns
 */
function getSearchForm(searchform) {
	if (searchform) {
	} else {
		searchform = ".searchform"
	}
	return $(searchform);
}
/**
 * 获取显示查询的图标对象
 * 
 * @returns
 */
function getSearchIcon() {
	return $("#searchicon");
}
/**
 * 获取addform对象，若addform的id不是addform，则可以重写此方法
 * 
 * @param section
 * @returns
 */
function getAddForm(section) {
	return $("form", section);
}
/**
 * 获取editform对象，若editform的id不是editform，则可以重写此方法
 * 
 * @param section
 */
function getEditForm(section) {
	return $("form", section);
}
/**
 * 获取viewform对象，若viewform的id不是viewform，则可以重写此方法
 * 
 * @param section
 * @returns
 */
function getViewForm(section) {
	return $("form", section);
}
/**
 * 获取修改状态的表单
 * 
 * @param statusform
 */
function getStatusForm(statusform) {
	if (statusform) {
	} else {
		statusform = "#statusform"
	}
	return $(statusform);
}
/**
 * 获取修改状态的对话框
 * 
 * @param dlg
 * @returns
 */
function getStatusDLG(dlg) {
	if (dlg) {
	} else {
		dlg = "#dlg-status";
	}
	return $(dlg);
}
/**
 * 删除成功处理方法
 */
function deleteSuccessHandler(result) {
	ok('删除成功！');
}
/**
 * 删除失败处理方法
 */
function deleteErrHandler(result) {
	warn('删除失败！');
}
/**
 * 提交成功处理方法
 */
function submitSuccessHandler(result) {
	crumbReturn();
	crumbGo(0);
	resultHandler(result);
}
/**
 * 提交失败处理方法
 */
function submitErrHandler(result) {
	resultHandler(result);
}
/**
 * doaction成功处理方法
 */
function doactionSuccessHandler(obj, result) {
	var name= $(obj).text();
	ok('{0}成功！'.format(name));
}
/**
 * doaction失败处理方法
 */
function doactionErrHandler(obj, result) {
	var name= $(obj).text();
	warn('{0}失败！'.format(name));
}
/**
 * 获取选择的一条记录，可重写封装
 * 
 * @param dg
 * @returns
 */
function getSelected(dg) {
	return getDataGrid(dg).datagrid('getSelected');
}
/**
 * 获取所有选择的记录，可重写封装
 * 
 * @param dg
 * @returns
 */
function getSelections(dg) {
	return getDataGrid(dg).datagrid('getSelections');
}
/**
 * 获取所有记录，可重写封装
 * 
 * @param dg
 * @returns
 */
function getRows(dg) {
	return getDataGrid(dg).datagrid('getRows');
}
/**
 * 在删除时处理返回一个Json对象
 * 
 * @param list
 * @returns {___anonymous4986_4987}
 */
function getJsonForDelete(list) {
	var json = {};
	json.ids = getSelectedIDs(list).join(",");
	return json;
}
/**
 * 在doaction时处理返回一个Json对象
 * 
 * @param list
 * @returns {___anonymous4986_4987}
 */
function getJsonForDoaction(obj, list) {
	var json = {};
	json.ids = getSelectedIDs(list).join(",");
	return json;
}
/**
 * 获取已选中的所有项的ID，以数组返回，若item的ID不是id/uid/oid则可重写此方法
 * 
 * @param list
 * @returns {Array}
 */
function getSelectedIDs(list, dg) {
	if (list) {
	} else {
		list = getSelections(dg)
	}
	var ids = new Array();
	for ( var i in list) {
		var item = list[i];
		ids.push(getId(item));
	}
	return ids;
}
/**
 * 获取本行中的id
 * 
 * @param row
 * @returns {String}
 */
function getId(row) {
	if (row.uid) {
		return row.uid;
	} else if (row.oid) {
		return row.oid;
	} else if (row.id) {
		return row.id;
	}
	return "";
}

/**
 * 获取添加按钮
 * 
 * @returns
 */
function getBtnAdd() {
	return $("#btnadd");
}
/**
 * 获取编辑按钮
 * 
 * @returns
 */
function getBtnEdit() {
	return $("#btnedit");
}
/**
 * 获取删除按钮
 * 
 * @returns
 */
function getBtnDel() {
	return $("#btndel");
}
/**
 * 获取查看按钮
 * 
 * @returns
 */
function getBtnView() {
	return $("#btnview");
}
/**
 * 获取解冻按钮
 * 
 * @returns
 */
function getBtnEnable() {
	return $("#btnenable");
}
/**
 * 获取冻结按钮
 * 
 * @returns
 */
function getBtnDisable() {
	return $("#btndisable");
}
/**
 * 获取导出按钮
 * @returns
 */
function getBtnExport() {
	return $("#btexport");
}

/**
 * 用data来初始化某个form的表单数据及set-key值
 * 
 * @param form
 * @param data
 */
function initFormData(form, data) {
	form.form('load', data);
	form.set(data);
}
/**
 * 返回在编辑、查看时需要调用ajax的多个urls，若返回含有多项的数组，则会多次调用ajax请求
 * 
 * @param dg
 * @returns
 */
function getFreshRowUrls(dg) {
	var urls = new Array();
	var u = getFreshRowUrl(dg);
	if (u != null && u.length > 0) {
		urls.push(u);
	}
	return urls;
}
/**
 * 返回在编辑、查看时需要调用ajax的一个url，调用一次ajax请求
 * 
 * @param dg
 * @returns
 */
function getFreshRowUrl(dg) {
	return null;
}
/**
 * 将获取到的data数据，进行处理赋值给row
 * 
 * @param row
 * @param data
 * @param i
 */
function freshRow(row, data, i) {

}
/**
 * 结果处理方法
 * 
 * @param result
 */
function resultHandler(result) {
	var flag = myResultHandler(result);
	if(flag || typeof(flag) == "undefined") {
		//do nothing
	} else if (result.addSuccess) {
		ok('添加成功！');
	} else if (result.editSuccess) {
		ok('修改成功！');
	} else if (result.success) {
		ok('操作成功！');
	} else if (result.dopwdIsWrong) {
		err('操作密码错误！');
	} else if (result.err) {
		err("系统出现错误：{0}".format(result.errmsg));
	} else {
		err('操作失败！');
	}
}
/**
 * 是否是第一条数据
 * 
 * @param dg
 * @param row
 * @returns {Boolean}
 */
function isFirstRow(dg, row) {
	var index = getDataGrid(dg).datagrid("getRowIndex", row);
	if (index == 0) {
		return true;
	}
	return false;
}
/**
 * 是否是最后一条数据
 * 
 * @param dg
 * @param row
 * @returns {Boolean}
 */
function isLastRow(dg, row) {
	var rowNum = getDataGrid(dg).datagrid("getRows").length;
	var index = getDataGrid(dg).datagrid("getRowIndex", row);
	if (index == (rowNum - 1)) {
		return true;
	}
	return false;
}
/////////////////////////////////////////////////////////////////以下是可自定义可重写的方法/////////////////////////////////////////////////////////////////

/**
 * 自定义toolbar工具栏按钮状态的处理方法
 */
function mytoolbarStateHandler(event, j) {
	
}

/**
 * 自定义处理方法，针对功能重写此方法
 * 触发事件时的参数：
 * var args = {
		'dg' : "#{0}".format($(this).datagrid("options").id),
		'index' : index,
		'data' : data
	};
	$("html").trigger("datagrid-onunselect-event", args);
	重写举例：
	function mydgOnSelectHandler(event, j) {
		var list = getSelections(j.dg);
	}
 */
function mydgOnSelectHandler(event, j){}
function mydgOnUnSelectHandler(event, j){}
function mydgOnSelectAlltHandler(event, j){}
function mydgOnUnSelectAlltHandler(event, j){}
function mydgOnCheckHandler(event, j){}
function mydgOnUnCheckHandler(event, j){}
function mydgOnCheckAllHandler(event, j){}
function mydgOnUnCheckAllHandler(event, j){}
function mydgOnLoadSuccessHandler(event, j){}
function myformResetHandler(event, j){}
function myformClearHandler(event, j){}
function myStatusAfterHandler(event, j){}
function myResultHandler(result){ return false;} //若重载则返回return true

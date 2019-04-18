var map = {};

/**
 * 返回map的长度
 * @returns {Number}
 */
function length(){
	var length = 0;
	for (var sn in map) {
		if (map[sn]) {
			length++;
		}
	}
	return length;
}
/**
 * 启用禁用Export按钮
 */
function enAndDisBtnExport(){
	if (length() > 0) {
		getBtnExport().linkbutton("enable");
	} else {
		getBtnExport().linkbutton("disable");
	}
}
/**
 * 自定义勾选复选框时的事件
 * @param event
 * @param j
 */
function mydgOnCheckHandler(event, j) {
	map[j.data.accountsn] = true;
	enAndDisBtnExport();
}

/**
 * 自定义取消勾选复选框时的事件
 * @param event
 * @param j
 */
function mydgOnUnCheckHandler(event, j) {
	delete map[j.data.accountsn];
	enAndDisBtnExport();
}

/**
 * 自定义勾选所有复选框时的事件
 * @param event
 * @param j
 */
function mydgOnCheckAllHandler(event, j) {
	for(var i = 0; i < j.rows.length; i++){
		if (map[j.rows[i].accountsn] == undefined){
			map[j.rows[i].accountsn] = true;
		}
	}
	enAndDisBtnExport();
}

/**
 * 自定义取消勾选所有复选框时的事件
 * @param event
 * @param j
 */
function mydgOnUnCheckAllHandler(event, j) {
	for(var i = 0; i < j.rows.length; i++){
		delete map[j.rows[i].accountsn];
	}
	enAndDisBtnExport();
}

/**
 * 自定义toolbar工具栏按钮状态的处理方法
 * @param event
 * @param j
 */
function mytoolbarStateHandler(event, j) {
	var list = getSelections(j.dg);
	if (list.length != 1) {
		getBtnView().linkbutton("disable");
	} else {
		getBtnView().linkbutton("enable");
	}
}

/**
 * datagrid加载数据成功时对应状态按钮的操作
 * @param event
 * @param j
 */
function mydgOnLoadSuccessHandler(event, j) {
	var rows = j.data.rows;
	for(var i = 0; i < rows.length; i++) {
		if(map[rows[i].accountsn]) {
			$('#dg').datagrid('checkRow', i);
		}
	}
	enAndDisBtnExport();
}

/**
 * 导出EXCEL
 */
function exportexcel() {
	var sns = "";
	for (var sn in map) {
		if (map[sn]) {
			sns = sns + "," + sn;
		}
	}
	$("#export input").val(sns);
	$("#export").submit();
}

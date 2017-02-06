/**
 * @fileOverview {@link ThreadQueryResultForm}クラスを記述したファイルです。
 */

/**
 * @class ThreadQueryResultForm
 *
 * @extends QueryResultForm
 */
ThreadQueryResultForm = createSubclass("ThreadQueryResultForm", {}, "QueryResultForm");


/**
 * HTMLエレメントとの対応付けを行います。
 */
ThreadQueryResultForm.prototype.attach = function() {
	QueryResultForm.prototype.attach.call(this);
	var thisForm = this;
	this.find("#backButton").click(function() {
		thisForm.back();
	});
};

ThreadQueryResultForm.prototype.back = function() {
	this.get().hide();
	var ed = this.parent.getComponent("editForm");
	if (ed != null) {
		ed.newThread();
		ed.get().show();
	}
	this.parent.find("#queryForm").show();
	this.parent.find("#queryResultForm").show();
};

ThreadQueryResultForm.prototype.setQueryResult = function(queryResult) {
	QueryResultForm.prototype.setQueryResult.call(this, queryResult);
	var thisForm = this;
	this.find("[id$='\.viewFileButton']").click(function() {
		thisForm.viewFile($(this));
	});
	for (var i = 0; i < this.formData.queryResult.length; i++) {
		var finfo = this.formData.queryResult[i];
		var btnid = "queryResult[" + i + "].viewFileButton";
		var lblid = "queryResult[" + i + "].fileCount";
		logger.log("finfo.fileCount=" + finfo.fileCount);
		if (finfo.fileCount == null || finfo.fileCount == 0) {
			this.find("#" + this.selectorEscape(btnid)).hide();
			this.find("[for='" + this.selectorEscape(lblid) + "']").hide();
		} else {
			this.find("#" + this.selectorEscape(btnid)).show();
			this.find("[for='" + this.selectorEscape(lblid) + "']").show();
		}
	}
};

ThreadQueryResultForm.prototype.viewFile = function(btn) {
	var tbl = this.getComponent("queryResult");
	var articleId = tbl.getSameRowField(btn, "articleId").val();
	logger.log("articleId=" + articleId);
	var dlg = currentPage.getComponent("fileDialog");
	var fileListForm = dlg.getComponent("fileListForm");
//	fileListForm.clearFileList();
	dlg.showModal({width:540, height:400});
	fileListForm.getFileList(articleId);
};

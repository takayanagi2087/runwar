/**
 * @fileOverview {@link FileListForm}クラスを記述したファイルです。
 */

/**
 * @class FileListForm
 *
 * @extends Form
 */
FileListForm = createSubclass("FileListForm", {}, "Form");


/**
 * HTMLエレメントとの対応付けを行います。
 */
FileListForm.prototype.attach = function() {
	Form.prototype.attach.call(this);
};

FileListForm.prototype.clearFileList = function() {
	this.find("#queryResult tbody").html("");
};

FileListForm.prototype.getFileList = function(articleId) {
	this.find("#articleId").val(articleId);
	var thisForm = this;
	this.submit("getFileList", function(r) {
		if (r.status == ServerMethod.SUCCESS) {
			//thisForm.getComponent("attachFileTable").setTableData(r.result);
			thisForm.setFormData(r.result);
		}
	});
};
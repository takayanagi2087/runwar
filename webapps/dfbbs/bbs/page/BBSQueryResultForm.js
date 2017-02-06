/**
 * @fileOverview {@link BBSQueryResultForm}クラスを記述したファイルです。
 */

/**
 * @class BBSQueryResultForm
 *
 * @extends QueryResultForm
 */
BBSQueryResultForm = createSubclass("BBSQueryResultForm", {}, "QueryResultForm");


/**
 * HTMLエレメントとの対応付けを行います。
 */
BBSQueryResultForm.prototype.attach = function() {
	QueryResultForm.prototype.attach.call(this);
};

/**
 * 選択データを更新します。
 */
BBSQueryResultForm.prototype.updateData = function() {
	var threadForm = this.parent.getComponent("threadQueryResultForm");
	if (threadForm != null) {
		currentPage.pushThreadModeStatus();
		this.parent.find("#queryForm").hide();
		this.parent.find("#queryResultForm").hide();

		var aid = this.find("#articleId").val();
		threadForm.find("#articleId").val(aid);
		threadForm.find("#threadId").val(aid);
		threadForm.get().show();
		threadForm.changePage();

		var editForm = this.parent.getComponent("editForm");
		if (editForm != null) {
			logger.log("aid=" + aid);
			editForm.newThreadArticel(aid);
		}
	}
};

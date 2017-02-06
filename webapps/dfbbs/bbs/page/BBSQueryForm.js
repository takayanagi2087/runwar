/**
 * @fileOverview {@link BBSQueryForm}クラスを記述したファイルです。
 */

/**
 * @class BBSQueryForm
 *
 * @extends QueryForm
 */
BBSQueryForm = createSubclass("BBSQueryForm", {}, "QueryForm");


/**
 * HTMLエレメントとの対応付けを行います。
 */
BBSQueryForm.prototype.attach = function() {
	QueryForm.prototype.attach.call(this);
};

BBSQueryForm.prototype.newData = function() {
	var editForm = this.parent.getComponent("editForm");
	editForm.articleMode = "newThread";
	QueryForm.prototype.newData.call(this);
};
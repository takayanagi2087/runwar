/**
 * @fileOverview {@link ArticlePage}クラスを記述したファイルです。
 */

/**
 * @class ArticlePage
 *
 * @extends AdminPage
 */
ArticlePage = createSubclass("ArticlePage", {}, "AdminPage");


/**
 * HTMLエレメントとの対応付けを行います。
 */
ArticlePage.prototype.attach = function() {
	AdminPage.prototype.attach.call(this);
};

/**
 * 各フォームにあるスレッドリストを取得します。
 *
 */
ArticlePage.prototype.getThreadList = function() {
	var qf = this.getComponent("queryForm");
	var sel = qf.getComponent("threadId");
	sel.getOptionList();
	var ol = sel.optionList;
	var qrf = this.getComponent("queryResultForm");
	var qr = qrf.getComponent("queryResult");
	qr.getComponent("threadId").setOptionList(ol);
	var ef = this.getComponent("editForm");
	ef.getComponent("threadId").setOptionList(ol);
};
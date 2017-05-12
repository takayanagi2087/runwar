/**
 * @fileOverview {@link ArticleEditForm}クラスを記述したファイルです。
 */

/**
 * @class ArticleEditForm
 *
 * @extends EditForm
 */
ArticleEditForm = createSubclass("ArticleEditForm", {}, "EditForm");


/**
 * HTMLエレメントとの対応付けを行います。
 */
ArticleEditForm.prototype.attach = function() {
	EditForm.prototype.attach.call(this);
};

/**
 * ArticlePageかどうかを返します。
 * @returns {Boolean} ArticlePageの場合true。
 */
ArticleEditForm.prototype.isArticlePage = function() {
	return (location.href.indexOf("ArticlePage") > 0);
};

/**
 * 新規データ作成。
 */
ArticleEditForm.prototype.newData = function() {
	EditForm.prototype.newData.call(this);
	this.find("#editFormTitle").html("新規スレッドまたは記事の作成");
	this.find("#threadId").parents("tr:first").show();
};

/**
 * BBSPage用の新規作成処理です。
 * <pre>
 * 常にスレッド作成になるので、スレッドIDを隠します。
 * </pre>
 */
ArticleEditForm.prototype.newThread = function() {
	this.reset();
	this.saveMode = "new";
	this.find("#editFormTitle").html("新規スレッド作成");
	this.find("#threadId").parents("tr:first").hide();
	this.toEditMode();
	this.find("#backButton").hide();
};

/**
 * 記事の更新処理です。
 * <pre>
 * 記事の更新はArticlePageでしか呼ばれません。
 * </pre>
 */
ArticleEditForm.prototype.updateData = function() {
	this.find("#editFormTitle").html("記事の更新");
	this.find("#threadId").parents("tr:first").show();
	EditForm.prototype.updateData.call(this);
};

/**
 * BBSPageのスレッドの記事追加フォーム用の初期化処理です。
 * @param {String} tid スレッドID。
 */
ArticleEditForm.prototype.newThreadArticel = function(tid) {
	this.parent.find("#editForm").show();
	this.reset();
	this.saveMode = "new";
	this.find("#editFormTitle").html("スレッドへの新規投稿");
	var tidField = this.getComponent("threadId");
	tidField.setValue(tid);
	this.find("#threadId").parents("tr:first").show();
	this.toEditMode();
	this.find("#backButton").hide();
};

/**
 * 編集モードにします。
 * <pre>
 * 各フィールドを編集可能状態にします。
 * threadIdはArticlePageの新規の時のみ編集可能に設定します。
 * </pre>
 */
ArticleEditForm.prototype.toEditMode = function() {
	EditForm.prototype.toEditMode.call(this);
	var tid = this.getComponent("threadId");
	tid.lock(true);
	if (this.isArticlePage()) {
		if (this.saveMode == "new") {
			tid.lock(false);
		}
	}
};

/**
 * 保存や削除後の画面状態遷移を行います。
 */
EditForm.prototype.changeStateForAfterUpdate = function() {
	currentPage.getThreadList();
	var form = this;
	var qf = form.parent.getComponent("queryForm");
	var rf = form.parent.getComponent("queryResultForm");
	var trf = form.parent.getComponent("threadQueryResultForm");
	if (this.isArticlePage()) {
		form.clearData();
		form.toEditMode();
		form.parent.toQueryMode();
		if (rf != null) {
			rf.changePage();
		}
	} else {
		form.clearData();
		form.toEditMode();
		if (rf.get().is(":visible")) {
			rf.changePage();
		}
		if (trf.get().is(":visible")) {
			form.getComponent("threadId").lock(false);
			var threadId = trf.find("#threadId").val();
			form.find("#threadId").val(threadId);
			form.getComponent("threadId").lock(true);
			trf.changePage();
		}
	}
};

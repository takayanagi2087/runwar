/**
 * @fileOverview {@link BBSPage}クラスを記述したファイルです。
 */

/**
 * @class BBSPage
 *
 * @extends BasePage
 */
BBSPage = createSubclass("BBSPage", {}, "BasePage");


/**
 * HTMLエレメントとの対応付けを行います。
 */
BBSPage.prototype.attach = function() {
	BasePage.prototype.attach.call(this);
	var qr = this.getComponent("queryResultForm");
	if (qr != null) {
		logger.debug("changePage");
		qr.changePage();
		qr.get().show();
	}
	var tqr = this.getComponent("threadQueryResultForm");
	if (tqr != null) {
		tqr.get().hide();
	}
	var ed = this.getComponent("editForm");
	if (ed != null) {
		ed.newThread();
		ed.get().show();
	}
};

/**
 * 確認モードへの画面遷移履歴を登録します。
 */
BBSPage.prototype.pushThreadModeStatus = function() {
	this.pushState("threadMode", "threadMode", location.href);
};

/**
 * 戻るボタンが押された場合の処理を行います。
 * @param {Object} event イベント。
 */
BBSPage.prototype.onBackButton = function(event) {
	if (event.originalEvent.state) {
		if (this.find("#threadQueryResultForm").is(":visible")) {
			var f = this.getComponent("threadQueryResultForm");
			f.back();
		} else {
			Page.prototype.onBackButton.call(this, event);
		}
	}
};

/**
 * 各フォームにあるスレッドリストを取得します。
 *
 */
BBSPage.prototype.getThreadList = function() {
	var ef = this.getComponent("editForm");
	ef.getComponent("threadId").getOptionList();
};


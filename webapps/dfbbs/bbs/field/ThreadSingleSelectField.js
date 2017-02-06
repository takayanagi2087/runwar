/**
 * @fileOverview {@link ThreadSingleSelectField}クラスを記述したファイルです。
 */

/**
 * @class ThreadSingleSelectField
 *
 * @extends SingleSelectField
 */
ThreadSingleSelectField = createSubclass("ThreadSingleSelectField", {}, "SingleSelectField");


/**
 * HTMLエレメントとの対応付けを行います。
 */
ThreadSingleSelectField.prototype.attach = function() {
	SingleSelectField.prototype.attach.call(this);
	var thisField = this;
};

/**
 * サーバからスレッドの一覧を取得します。
 */
ThreadSingleSelectField.prototype.getOptionList = function() {
	var m = this.getSyncServerMethod("queryOption");
	var r = m.execute("");
	if (r.status == ServerMethod.SUCCESS) {
		this.setOptionList(r.result);
	}
};


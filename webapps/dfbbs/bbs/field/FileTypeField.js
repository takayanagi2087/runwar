/**
 * @fileOverview {@link FileTypeField}クラスを記述したファイルです。
 */

/**
 * @class FileTypeField
 *
 * @extends EnumOptionSingleSelectField
 */
FileTypeField = createSubclass("FileTypeField", {}, "EnumOptionSingleSelectField");


/**
 * HTMLエレメントとの対応付けを行います。
 */
FileTypeField.prototype.attach = function() {
	EnumOptionSingleSelectField.prototype.attach.call(this);
	var thisField = this;
	this.get().change(function() {
		thisField.id = $(this).attr("id");
		thisField.showField($(this));
	});
	this.showField(this.get());
};

/**
 * ファイルタイプに応じたフィールドを表示する。
 * @param {jQuery} sel イベントが発生した要素。
 */
FileTypeField.prototype.showField = function(sel) {
	var type = sel.val();
	var tr = sel.parents("tr:first");
	tr.find(".fileDiv").hide();
	tr.find("." + sel.val()).show();
};

/**
 * 設定されたファイルタイプに応じたフィールドを表示するように改造。
 * @param {String} v 設定値。
 */
FileTypeField.prototype.setValue = function(v) {
	EnumOptionSingleSelectField.prototype.setValue.call(this, v);
	this.showField(this.get());
};
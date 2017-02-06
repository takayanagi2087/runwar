/**
 * @fileOverview {@link ArticleQueryResultForm}クラスを記述したファイルです。
 */

/**
 * @class ArticleQueryResultForm
 *
 * @extends QueryResultForm
 */
ArticleQueryResultForm = createSubclass("ArticleQueryResultForm", {}, "QueryResultForm");


/**
 * HTMLエレメントとの対応付けを行います。
 */
ArticleQueryResultForm.prototype.attach = function() {
	QueryResultForm.prototype.attach.call(this);
};


package bbs.field;

import dataforms.field.common.RecordIdField;


/**
 * ArticleIdFieldフィールドクラス。
 *
 */
public class ArticleIdField extends RecordIdField {

	/**
	 * フィールドコメント。
	 */
	private static final String COMMENT = "記事ID";
	/**
	 * コンストラクタ。
	 */
	public ArticleIdField() {
		super(null);
		this.setComment(COMMENT);
	}
	/**
	 * コンストラクタ。
	 * @param id フィールドID。
	 */
	public ArticleIdField(final String id) {
		super(id);
		this.setComment(COMMENT);
	}

	@Override
	protected void onBind() {
		super.onBind();

	}
}

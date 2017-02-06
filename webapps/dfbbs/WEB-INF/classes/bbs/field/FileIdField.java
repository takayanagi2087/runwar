package bbs.field;

import dataforms.field.sqltype.SmallintField;


/**
 * FileIdFieldフィールドクラス。
 *
 */
public class FileIdField extends SmallintField {

	/**
	 * フィールドコメント。
	 */
	private static final String COMMENT = "ファイルID";
	/**
	 * コンストラクタ。
	 */
	public FileIdField() {
		super(null);
		this.setComment(COMMENT);
		this.setHidden(true);
	}
	/**
	 * コンストラクタ。
	 * @param id フィールドID。
	 */
	public FileIdField(final String id) {
		super(id);
		this.setComment(COMMENT);
		this.setHidden(true);
	}

	@Override
	protected void onBind() {
		super.onBind();

	}
}

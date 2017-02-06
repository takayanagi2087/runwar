package bbs.field;

import dataforms.field.sqltype.VarcharField;
import dataforms.validator.MaxLengthValidator;


/**
 * FileCommentFieldフィールドクラス。
 *
 */
public class FileCommentField extends VarcharField {
	/**
	 * フィールド長。
	 */
	private static final int LENGTH = 254;

	/**
	 * フィールドコメント。
	 */
	private static final String COMMENT = "ファイルコメント";
	/**
	 * コンストラクタ。
	 */
	public FileCommentField() {
		super(null, LENGTH);
		this.setComment(COMMENT);
	}
	/**
	 * コンストラクタ。
	 * @param id フィールドID。
	 */
	public FileCommentField(final String id) {
		super(id, LENGTH);
		this.setComment(COMMENT);
	}

	@Override
	protected void onBind() {
		super.onBind();
		this.addValidator(new MaxLengthValidator(this.getLength()));

	}
}

package bbs.field;

import dataforms.field.sqltype.VarcharField;
import dataforms.validator.MaxLengthValidator;


/**
 * TitleFieldフィールドクラス。
 *
 */
public class TitleField extends VarcharField {
	/**
	 * フィールド長。
	 */
	private static final int LENGTH = 256;

	/**
	 * フィールドコメント。
	 */
	private static final String COMMENT = "記事タイトル";
	/**
	 * コンストラクタ。
	 */
	public TitleField() {
		super(null, LENGTH);
		this.setComment(COMMENT);
	}
	/**
	 * コンストラクタ。
	 * @param id フィールドID。
	 */
	public TitleField(final String id) {
		super(id, LENGTH);
		this.setComment(COMMENT);
	}

	@Override
	protected void onBind() {
		super.onBind();
		this.addValidator(new MaxLengthValidator(this.getLength()));

	}
}

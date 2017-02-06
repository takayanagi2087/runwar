package bbs.field;

import dataforms.field.sqltype.VarcharField;
import dataforms.validator.MaxLengthValidator;


/**
 * AutherFieldフィールドクラス。
 *
 */
public class AutherField extends VarcharField {
	/**
	 * フィールド長。
	 */
	private static final int LENGTH = 64;

	/**
	 * フィールドコメント。
	 */
	private static final String COMMENT = "著者";
	/**
	 * コンストラクタ。
	 */
	public AutherField() {
		super(null, LENGTH);
		this.setComment(COMMENT);
	}
	/**
	 * コンストラクタ。
	 * @param id フィールドID。
	 */
	public AutherField(final String id) {
		super(id, LENGTH);
		this.setComment(COMMENT);
	}

	@Override
	protected void onBind() {
		super.onBind();
		this.addValidator(new MaxLengthValidator(this.getLength()));

	}
}

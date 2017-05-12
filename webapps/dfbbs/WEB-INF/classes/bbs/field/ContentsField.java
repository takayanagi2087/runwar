package bbs.field;

import dataforms.field.sqltype.VarcharField;
import dataforms.validator.MaxLengthValidator;


/**
 * ContentsFieldフィールドクラス。
 *
 */
public class ContentsField extends VarcharField {
	/**
	 * フィールド長。
	 */
	private static final int LENGTH = 2000;

	/**
	 * フィールドコメント。
	 */
	private static final String COMMENT = "本文";
	/**
	 * コンストラクタ。
	 */
	public ContentsField() {
		super(null, LENGTH);
		this.setComment(COMMENT);
	}
	/**
	 * コンストラクタ。
	 * @param id フィールドID。
	 */
	public ContentsField(final String id) {
		super(id, LENGTH);
		this.setComment(COMMENT);
	}

	@Override
	protected void onBind() {
		super.onBind();
		this.addValidator(new MaxLengthValidator(this.getLength()));

	}
}

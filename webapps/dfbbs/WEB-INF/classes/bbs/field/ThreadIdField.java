package bbs.field;

import dataforms.field.common.RecordIdField;


/**
 * ThreadIdFieldフィールドクラス。
 *
 */
public class ThreadIdField extends RecordIdField {

	/**
	 * フィールドコメント。
	 */
	private static final String COMMENT = "スレッドID";
	/**
	 * コンストラクタ。
	 */
	public ThreadIdField() {
		super(null);
		this.setComment(COMMENT);
	}
	/**
	 * コンストラクタ。
	 * @param id フィールドID。
	 */
	public ThreadIdField(final String id) {
		super(id);
		this.setComment(COMMENT);
	}

	@Override
	protected void onBind() {
		super.onBind();

	}
}

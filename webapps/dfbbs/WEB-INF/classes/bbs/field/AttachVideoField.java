package bbs.field;

import dataforms.field.common.BlobStoreVideoField;


/**
 * AttachVideoFieldフィールドクラス。
 *
 */
public class AttachVideoField extends BlobStoreVideoField {

	/**
	 * フィールドコメント。
	 */
	private static final String COMMENT = "動画";
	/**
	 * コンストラクタ。
	 */
	public AttachVideoField() {
		super(null);
		this.setComment(COMMENT);
	}
	/**
	 * コンストラクタ。
	 * @param id フィールドID。
	 */
	public AttachVideoField(final String id) {
		super(id);
		this.setComment(COMMENT);
	}

	@Override
	protected void onBind() {
		super.onBind();

	}
}

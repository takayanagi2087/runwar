package bbs.field;

import dataforms.field.common.BlobStoreImageField;


/**
 * AttachImageFieldフィールドクラス。
 *
 */
public class AttachImageField extends BlobStoreImageField {

	/**
	 * フィールドコメント。
	 */
	private static final String COMMENT = "画像";
	/**
	 * コンストラクタ。
	 */
	public AttachImageField() {
		super(null);
		this.setComment(COMMENT);
	}
	/**
	 * コンストラクタ。
	 * @param id フィールドID。
	 */
	public AttachImageField(final String id) {
		super(id);
		this.setComment(COMMENT);
	}

	@Override
	protected void onBind() {
		super.onBind();
		this.setThumbnailWidth(420);
		this.setThumbnailHeight(315);
	}
}

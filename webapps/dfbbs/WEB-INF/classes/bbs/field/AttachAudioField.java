package bbs.field;

import dataforms.field.common.BlobStoreAudioField;


/**
 * AttachAudioFieldフィールドクラス。
 *
 */
public class AttachAudioField extends BlobStoreAudioField {

	/**
	 * フィールドコメント。
	 */
	private static final String COMMENT = "音声";
	/**
	 * コンストラクタ。
	 */
	public AttachAudioField() {
		super(null);
		this.setComment(COMMENT);
	}
	/**
	 * コンストラクタ。
	 * @param id フィールドID。
	 */
	public AttachAudioField(final String id) {
		super(id);
		this.setComment(COMMENT);
	}

	@Override
	protected void onBind() {
		super.onBind();

	}
}

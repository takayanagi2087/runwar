package bbs.field;

import dataforms.field.common.EnumOptionSingleSelectField;


/**
 * FileTypeFieldフィールドクラス。
 *
 */
public class FileTypeField extends EnumOptionSingleSelectField {
	/**
	 * 列挙名称。
	 */
	private static final String ENUM_TYPE = "fileType"; // 適切に修正して使用してください。
	/**
	 * フィールドコメント。
	 */
	private static final String COMMENT = "ファイルタイプ";
	/**
	 * コンストラクタ。
	 */
	public FileTypeField() {
		super(null, ENUM_TYPE);
		this.setComment(COMMENT);
	}
	/**
	 * コンストラクタ。
	 * @param id フィールドID。
	 */
	public FileTypeField(final String id) {
		super(id, ENUM_TYPE);
		this.setComment(COMMENT);
	}

	@Override
	protected void onBind() {
		super.onBind();

	}
}

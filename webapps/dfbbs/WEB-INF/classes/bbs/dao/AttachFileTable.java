package bbs.dao;

import java.util.Map;
import dataforms.dao.Table;
import bbs.field.ArticleIdField;
import bbs.field.FileIdField;
import dataforms.field.common.SortOrderField;
import bbs.field.FileCommentField;
import bbs.field.FileTypeField;
import bbs.field.AttachFileField;
import bbs.field.AttachImageField;
import bbs.field.AttachVideoField;
import bbs.field.AttachAudioField;


/**
 * 添付ファイルテーブルクラス。
 *
 */
public class AttachFileTable extends Table {
	/**
	 * コンストラクタ。
	 */
	public AttachFileTable() {
		this.setComment("添付ファイルテーブル");
		this.addPkField(new ArticleIdField()); //記事ID
		this.addPkField(new FileIdField()); //ファイルID
		this.addField(new SortOrderField()); //ソート順
		this.addField(new FileCommentField()); //ファイルコメント
		this.addField(new FileTypeField()); //ファイルタイプ
		this.addField(new AttachFileField()); //ファイル
		this.addField(new AttachImageField()); //画像
		this.addField(new AttachVideoField()); //動画
		this.addField(new AttachAudioField()); //音声

		this.addUpdateInfoFields();
	}
	
	@Override
	public String getJoinCondition(final Table joinTable, final String alias) {
		AttachFileTableRelation r = new AttachFileTableRelation(this);
		return r.getJoinCondition(joinTable, alias);
	}
	
	/**
	 * Entity操作クラスです。
	 */
	public static class Entity extends dataforms.dao.Entity {
		/** 記事IDのフィールドID。 */
		public static final String ID_ARTICLE_ID = "articleId";
		/** ファイルIDのフィールドID。 */
		public static final String ID_FILE_ID = "fileId";
		/** ソート順のフィールドID。 */
		public static final String ID_SORT_ORDER = "sortOrder";
		/** ファイルコメントのフィールドID。 */
		public static final String ID_FILE_COMMENT = "fileComment";
		/** ファイルタイプのフィールドID。 */
		public static final String ID_FILE_TYPE = "fileType";
		/** ファイルのフィールドID。 */
		public static final String ID_ATTACH_FILE = "attachFile";
		/** 画像のフィールドID。 */
		public static final String ID_ATTACH_IMAGE = "attachImage";
		/** 動画のフィールドID。 */
		public static final String ID_ATTACH_VIDEO = "attachVideo";
		/** 音声のフィールドID。 */
		public static final String ID_ATTACH_AUDIO = "attachAudio";

		/**
		 * コンストラクタ。
		 */
		public Entity() {
			
		}
		/**
		 * コンストラクタ。
		 * @param map 操作対象マップ。
		 */
		public Entity(final Map<String, Object> map) {
			super(map);
		}
		/**
		 * 記事IDを取得します。
		 * @return 記事ID。
		 */
		public java.lang.Long getArticleId() {
			return (java.lang.Long) this.getMap().get(Entity.ID_ARTICLE_ID);
		}

		/**
		 * 記事IDを設定します。
		 * @param articleId 記事ID。
		 */
		public void setArticleId(final java.lang.Long articleId) {
			this.getMap().put(Entity.ID_ARTICLE_ID, articleId);
		}

		/**
		 * ファイルIDを取得します。
		 * @return ファイルID。
		 */
		public java.lang.Short getFileId() {
			return (java.lang.Short) this.getMap().get(Entity.ID_FILE_ID);
		}

		/**
		 * ファイルIDを設定します。
		 * @param fileId ファイルID。
		 */
		public void setFileId(final java.lang.Short fileId) {
			this.getMap().put(Entity.ID_FILE_ID, fileId);
		}

		/**
		 * ソート順を取得します。
		 * @return ソート順。
		 */
		public java.lang.Short getSortOrder() {
			return (java.lang.Short) this.getMap().get(Entity.ID_SORT_ORDER);
		}

		/**
		 * ソート順を設定します。
		 * @param sortOrder ソート順。
		 */
		public void setSortOrder(final java.lang.Short sortOrder) {
			this.getMap().put(Entity.ID_SORT_ORDER, sortOrder);
		}

		/**
		 * ファイルコメントを取得します。
		 * @return ファイルコメント。
		 */
		public java.lang.String getFileComment() {
			return (java.lang.String) this.getMap().get(Entity.ID_FILE_COMMENT);
		}

		/**
		 * ファイルコメントを設定します。
		 * @param fileComment ファイルコメント。
		 */
		public void setFileComment(final java.lang.String fileComment) {
			this.getMap().put(Entity.ID_FILE_COMMENT, fileComment);
		}

		/**
		 * ファイルタイプを取得します。
		 * @return ファイルタイプ。
		 */
		public java.lang.String getFileType() {
			return (java.lang.String) this.getMap().get(Entity.ID_FILE_TYPE);
		}

		/**
		 * ファイルタイプを設定します。
		 * @param fileType ファイルタイプ。
		 */
		public void setFileType(final java.lang.String fileType) {
			this.getMap().put(Entity.ID_FILE_TYPE, fileType);
		}

		/**
		 * ファイルを取得します。
		 * @return ファイル。
		 */
		public dataforms.dao.file.FileObject getAttachFile() {
			return (dataforms.dao.file.FileObject) this.getMap().get(Entity.ID_ATTACH_FILE);
		}

		/**
		 * ファイルを設定します。
		 * @param attachFile ファイル。
		 */
		public void setAttachFile(final dataforms.dao.file.FileObject attachFile) {
			this.getMap().put(Entity.ID_ATTACH_FILE, attachFile);
		}

		/**
		 * 画像を取得します。
		 * @return 画像。
		 */
		public dataforms.dao.file.ImageData getAttachImage() {
			return (dataforms.dao.file.ImageData) this.getMap().get(Entity.ID_ATTACH_IMAGE);
		}

		/**
		 * 画像を設定します。
		 * @param attachImage 画像。
		 */
		public void setAttachImage(final dataforms.dao.file.ImageData attachImage) {
			this.getMap().put(Entity.ID_ATTACH_IMAGE, attachImage);
		}

		/**
		 * 動画を取得します。
		 * @return 動画。
		 */
		public dataforms.dao.file.VideoData getAttachVideo() {
			return (dataforms.dao.file.VideoData) this.getMap().get(Entity.ID_ATTACH_VIDEO);
		}

		/**
		 * 動画を設定します。
		 * @param attachVideo 動画。
		 */
		public void setAttachVideo(final dataforms.dao.file.VideoData attachVideo) {
			this.getMap().put(Entity.ID_ATTACH_VIDEO, attachVideo);
		}

		/**
		 * 音声を取得します。
		 * @return 音声。
		 */
		public java.lang.Object getAttachAudio() {
			return (java.lang.Object) this.getMap().get(Entity.ID_ATTACH_AUDIO);
		}

		/**
		 * 音声を設定します。
		 * @param attachAudio 音声。
		 */
		public void setAttachAudio(final java.lang.Object attachAudio) {
			this.getMap().put(Entity.ID_ATTACH_AUDIO, attachAudio);
		}


	}
	/**
	 * 記事IDフィールドを取得します。
	 * @return 記事IDフィールド。
	 */
	public ArticleIdField getArticleIdField() {
		return (ArticleIdField) this.getField(Entity.ID_ARTICLE_ID);
	}

	/**
	 * ファイルIDフィールドを取得します。
	 * @return ファイルIDフィールド。
	 */
	public FileIdField getFileIdField() {
		return (FileIdField) this.getField(Entity.ID_FILE_ID);
	}

	/**
	 * ソート順フィールドを取得します。
	 * @return ソート順フィールド。
	 */
	public SortOrderField getSortOrderField() {
		return (SortOrderField) this.getField(Entity.ID_SORT_ORDER);
	}

	/**
	 * ファイルコメントフィールドを取得します。
	 * @return ファイルコメントフィールド。
	 */
	public FileCommentField getFileCommentField() {
		return (FileCommentField) this.getField(Entity.ID_FILE_COMMENT);
	}

	/**
	 * ファイルタイプフィールドを取得します。
	 * @return ファイルタイプフィールド。
	 */
	public FileTypeField getFileTypeField() {
		return (FileTypeField) this.getField(Entity.ID_FILE_TYPE);
	}

	/**
	 * ファイルフィールドを取得します。
	 * @return ファイルフィールド。
	 */
	public AttachFileField getAttachFileField() {
		return (AttachFileField) this.getField(Entity.ID_ATTACH_FILE);
	}

	/**
	 * 画像フィールドを取得します。
	 * @return 画像フィールド。
	 */
	public AttachImageField getAttachImageField() {
		return (AttachImageField) this.getField(Entity.ID_ATTACH_IMAGE);
	}

	/**
	 * 動画フィールドを取得します。
	 * @return 動画フィールド。
	 */
	public AttachVideoField getAttachVideoField() {
		return (AttachVideoField) this.getField(Entity.ID_ATTACH_VIDEO);
	}

	/**
	 * 音声フィールドを取得します。
	 * @return 音声フィールド。
	 */
	public AttachAudioField getAttachAudioField() {
		return (AttachAudioField) this.getField(Entity.ID_ATTACH_AUDIO);
	}


}

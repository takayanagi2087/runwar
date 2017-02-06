package bbs.dao;

import java.util.Map;

import dataforms.dao.Query;
import dataforms.dao.SubQuery;
import dataforms.field.base.Field;
import dataforms.field.base.FieldList;
import dataforms.field.sqlfunc.CountField;

/**
 * 記事ごとの添付ファイルの数を求める副問合せクラスです。
 *
 */
public class FileCountSubQuery extends SubQuery {
	/**
	 * 記事ごとの添付ファイルの数を求める問合せクラスです。
	 *
	 */
	public static class FileCountQuery extends Query {
		/**
		 * コンストラクタ。
		 */
		public FileCountQuery() {
			AttachFileTable t = new AttachFileTable();
			FieldList flist = new FieldList();
			flist.add(t.getArticleIdField());
			flist.add(new CountField(Entity.ID_FILE_COUNT, t.getFileIdField()));
			this.setFieldList(flist);
			this.setMainTable(t);
		}
	}

	/**
	 * コンストラクタ。
	 */
	public FileCountSubQuery() {
		super(new FileCountQuery());
	}

	/**
	 * Entity操作クラスです。
	 */
	public static class Entity extends dataforms.dao.Entity {
		/** 記事IDのフィールドID。 */
		public static final String ID_ARTICLE_ID = "articleId";
		/** スレッドIDのフィールドID。 */
		public static final String ID_FILE_COUNT = "fileCount";


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
		 * 記事IDを取得します。
		 * @return 記事ID。
		 */
		public java.lang.Long getFileCount() {
			return (java.lang.Long) this.getMap().get(Entity.ID_FILE_COUNT);
		}

		/**
		 * 記事IDを設定します。
		 * @param articleId 記事ID。
		 */
		public void setFileCount(final java.lang.Long fileCount) {
			this.getMap().put(Entity.ID_FILE_COUNT, fileCount);
		}


	};

	/**
	 * ファイル数フィールドを取得します。
	 * @return ファイル数フィールド。
	 */
	public Field<?> getFileCountField() {
		return this.getFieldList().get(Entity.ID_FILE_COUNT);
	}
}

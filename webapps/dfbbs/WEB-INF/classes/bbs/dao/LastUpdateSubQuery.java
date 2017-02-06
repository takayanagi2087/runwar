package bbs.dao;

import java.util.Map;

import bbs.field.ThreadIdField;
import dataforms.dao.Query;
import dataforms.dao.SubQuery;
import dataforms.field.base.FieldList;
import dataforms.field.sqlfunc.MaxField;
import dataforms.field.sqltype.TimestampField;

/**
 * 最終投稿日を求める副問合せクラスです。
 *
 */
public class LastUpdateSubQuery extends SubQuery {
	/**
	 * 最終投稿日を求める問合せクラスです。
	 *
	 */
	public static class LastUpdateQuery extends Query {
		/**
		 * コンストラクタ。
		 */
		public LastUpdateQuery() {
			ArticleTable t = new ArticleTable();
			FieldList flist = new FieldList();
			flist.add(t.getThreadIdField());
			flist.add(new MaxField(Entity.ID_LAST_UPDATE_TIMESTAMP, t.getUpdateTimestampField()));
			this.setFieldList(flist);
			this.setMainTable(t);
		}
	}

	/**
	 * コンストラクタ。
	 */
	public LastUpdateSubQuery() {
		super(new LastUpdateQuery());
	}

	/**
	 * Entity操作クラスです。
	 */
	public static class Entity extends dataforms.dao.Entity {
		/** スレッドIDのフィールドID。 */
		public static final String ID_THREAD_ID = "threadId";
		/** 記事件数のフィールドID。 */
		public static final String ID_LAST_UPDATE_TIMESTAMP = "lastUpdateTimestamp";
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
		 * スレッドIDを取得します。
		 * @return スレッドID。
		 */
		public java.lang.Long getThreadId() {
			return (java.lang.Long) this.getMap().get(Entity.ID_THREAD_ID);
		}

		/**
		 * スレッドIDを設定します。
		 * @param threadId スレッドID。
		 */
		public void setThreadId(final java.lang.Long threadId) {
			this.getMap().put(Entity.ID_THREAD_ID, threadId);
		}

		/**
		 * 最終更新日時を取得します。
		 * @return 最終更新日時。
		 */
		public java.sql.Timestamp getLastUpdateTimestamp() {
			return (java.sql.Timestamp) this.getMap().get(Entity.ID_LAST_UPDATE_TIMESTAMP);
		}

		/**
		 * 最終更新日時を設定します。
		 * @param deleteFlag 最終更新日時。
		 */
		public void setLastUpdateTimestamp(final java.sql.Timestamp articleCount) {
			this.getMap().put(Entity.ID_LAST_UPDATE_TIMESTAMP, articleCount);
		}


	}
	/**
	 * スレッドIDフィールドを取得します。
	 * @return スレッドIDフィールド。
	 */
	public ThreadIdField getThreadIdField() {
		return (ThreadIdField) this.getField(Entity.ID_THREAD_ID);
	}

	/**
	 * 最終更新日時フィールドを取得します。
	 * @return 最終更新日時フィールド。
	 */
	public TimestampField getLastUpdateTimestampField() {
		return (TimestampField) this.getField(Entity.ID_LAST_UPDATE_TIMESTAMP);
	}

}

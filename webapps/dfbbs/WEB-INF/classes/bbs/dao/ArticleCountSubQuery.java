package bbs.dao;

import java.util.Map;

import bbs.field.ThreadIdField;
import dataforms.dao.Query;
import dataforms.dao.SubQuery;
import dataforms.field.base.FieldList;
import dataforms.field.sqlfunc.CountField;
import dataforms.field.sqltype.BigintField;

/**
 * スレッドIDとそのスレッド内の記事数を求める副問合せクラスです。
 *
 */
public class ArticleCountSubQuery extends SubQuery {
	/**
	 * 記事数を求める問合せクラスです。
	 *
	 */
	public static class ArticleCountCountQuery extends Query {
		/**
		 * コンストラクタ。
		 */
		public ArticleCountCountQuery() {
			ArticleTable t = new ArticleTable();
			FieldList flist = new FieldList();
			flist.add(t.getThreadIdField());
			flist.add(new CountField(Entity.ID_ARTICLE_COUNT, t.getArticleIdField()));
			this.setFieldList(flist);
			this.setMainTable(t);
		}
	}

	/**
	 * コンストラクタ。
	 */
	public ArticleCountSubQuery() {
		super(new ArticleCountCountQuery());
	}

	/**
	 * Entity操作クラスです。
	 */
	public static class Entity extends dataforms.dao.Entity {
		/** スレッドIDのフィールドID。 */
		public static final String ID_THREAD_ID = "threadId";
		/** 記事件数のフィールドID。 */
		public static final String ID_ARTICLE_COUNT = "articleCount";
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
		 * 記事件数を取得します。
		 * @return 記事件数。
		 */
		public java.lang.Long getArticleCount() {
			return (java.lang.Long) this.getMap().get(Entity.ID_ARTICLE_COUNT);
		}

		/**
		 * 記事件数を設定します。
		 * @param deleteFlag 削除フラグ。
		 */
		public void setArticleCount(final java.lang.Long articleCount) {
			this.getMap().put(Entity.ID_ARTICLE_COUNT, articleCount);
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
	 * 記事件数ルフィールドを取得します。
	 * @return 記事件数フィールド。
	 */
	public BigintField getArticleCountField() {
		return (BigintField) this.getField(Entity.ID_ARTICLE_COUNT);
	}

}

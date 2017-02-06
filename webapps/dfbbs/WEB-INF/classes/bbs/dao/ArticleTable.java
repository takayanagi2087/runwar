package bbs.dao;

import java.util.Map;
import dataforms.dao.Table;
import bbs.field.ArticleIdField;
import bbs.field.ThreadIdField;
import bbs.field.TitleField;
import bbs.field.AutherField;
import bbs.field.ContentsField;
import dataforms.field.common.DeleteFlagField;


/**
 * 記事テーブルクラス。
 *
 */
public class ArticleTable extends Table {
	/**
	 * コンストラクタ。
	 */
	public ArticleTable() {
		this.setAutoIncrementId(true);
		this.setComment("記事テーブル");
		this.addPkField(new ArticleIdField()); //記事ID
		this.addField(new ThreadIdField()); //スレッドID
		this.addField(new TitleField()); //記事タイトル
		this.addField(new AutherField()); //著者
		this.addField(new ContentsField()); //本文
		this.addField(new DeleteFlagField()); //削除フラグ

		this.addUpdateInfoFields();
	}
	
	@Override
	public String getJoinCondition(final Table joinTable, final String alias) {
		ArticleTableRelation r = new ArticleTableRelation(this);
		return r.getJoinCondition(joinTable, alias);
	}
	
	/**
	 * Entity操作クラスです。
	 */
	public static class Entity extends dataforms.dao.Entity {
		/** 記事IDのフィールドID。 */
		public static final String ID_ARTICLE_ID = "articleId";
		/** スレッドIDのフィールドID。 */
		public static final String ID_THREAD_ID = "threadId";
		/** 記事タイトルのフィールドID。 */
		public static final String ID_TITLE = "title";
		/** 著者のフィールドID。 */
		public static final String ID_AUTHER = "auther";
		/** 本文のフィールドID。 */
		public static final String ID_CONTENTS = "contents";
		/** 削除フラグのフィールドID。 */
		public static final String ID_DELETE_FLAG = "deleteFlag";

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
		 * 記事タイトルを取得します。
		 * @return 記事タイトル。
		 */
		public java.lang.String getTitle() {
			return (java.lang.String) this.getMap().get(Entity.ID_TITLE);
		}

		/**
		 * 記事タイトルを設定します。
		 * @param title 記事タイトル。
		 */
		public void setTitle(final java.lang.String title) {
			this.getMap().put(Entity.ID_TITLE, title);
		}

		/**
		 * 著者を取得します。
		 * @return 著者。
		 */
		public java.lang.String getAuther() {
			return (java.lang.String) this.getMap().get(Entity.ID_AUTHER);
		}

		/**
		 * 著者を設定します。
		 * @param auther 著者。
		 */
		public void setAuther(final java.lang.String auther) {
			this.getMap().put(Entity.ID_AUTHER, auther);
		}

		/**
		 * 本文を取得します。
		 * @return 本文。
		 */
		public java.lang.String getContents() {
			return (java.lang.String) this.getMap().get(Entity.ID_CONTENTS);
		}

		/**
		 * 本文を設定します。
		 * @param contents 本文。
		 */
		public void setContents(final java.lang.String contents) {
			this.getMap().put(Entity.ID_CONTENTS, contents);
		}

		/**
		 * 削除フラグを取得します。
		 * @return 削除フラグ。
		 */
		public java.lang.String getDeleteFlag() {
			return (java.lang.String) this.getMap().get(Entity.ID_DELETE_FLAG);
		}

		/**
		 * 削除フラグを設定します。
		 * @param deleteFlag 削除フラグ。
		 */
		public void setDeleteFlag(final java.lang.String deleteFlag) {
			this.getMap().put(Entity.ID_DELETE_FLAG, deleteFlag);
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
	 * スレッドIDフィールドを取得します。
	 * @return スレッドIDフィールド。
	 */
	public ThreadIdField getThreadIdField() {
		return (ThreadIdField) this.getField(Entity.ID_THREAD_ID);
	}

	/**
	 * 記事タイトルフィールドを取得します。
	 * @return 記事タイトルフィールド。
	 */
	public TitleField getTitleField() {
		return (TitleField) this.getField(Entity.ID_TITLE);
	}

	/**
	 * 著者フィールドを取得します。
	 * @return 著者フィールド。
	 */
	public AutherField getAutherField() {
		return (AutherField) this.getField(Entity.ID_AUTHER);
	}

	/**
	 * 本文フィールドを取得します。
	 * @return 本文フィールド。
	 */
	public ContentsField getContentsField() {
		return (ContentsField) this.getField(Entity.ID_CONTENTS);
	}

	/**
	 * 削除フラグフィールドを取得します。
	 * @return 削除フラグフィールド。
	 */
	public DeleteFlagField getDeleteFlagField() {
		return (DeleteFlagField) this.getField(Entity.ID_DELETE_FLAG);
	}


}

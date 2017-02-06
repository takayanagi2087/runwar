package bbs.dao;

import dataforms.dao.Table;
import dataforms.dao.TableRelation;

/**
 * ArticleTableの関係を定義するクラスです。
 *
 */
public class ArticleTableRelation extends TableRelation {
	/**
	 * コンストラクタ。
	 * @param table 対象テーブル。
	 */
	public ArticleTableRelation(final Table table) {
		super(table);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getJoinCondition(final Table joinTable, final String alias) {
		if (joinTable instanceof FileCountSubQuery) {
			return this.getTable().getLinkFieldCondition(
					ArticleTable.Entity.ID_ARTICLE_ID,
					joinTable,
					alias,
					AttachFileTable.Entity.ID_ARTICLE_ID);
		} else if (joinTable instanceof ArticleCountSubQuery) {
			return this.getTable().getLinkFieldCondition(
					ArticleTable.Entity.ID_THREAD_ID,
					joinTable,
					alias,
					 ArticleCountSubQuery.Entity.ID_THREAD_ID);
		} else if (joinTable instanceof LastUpdateSubQuery) {
			return this.getTable().getLinkFieldCondition(
					ArticleTable.Entity.ID_THREAD_ID,
					joinTable,
					alias,
					LastUpdateSubQuery.Entity.ID_THREAD_ID);
		}
		return null;
	}
}

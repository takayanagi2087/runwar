package bbs.dao;

import dataforms.dao.Table;
import dataforms.dao.TableRelation;

/**
 * AttachFileTableの関係を定義するクラスです。
 *
 */
public class AttachFileTableRelation extends TableRelation {
	/**
	 * コンストラクタ。
	 * @param table 対象テーブル。
	 */
	public AttachFileTableRelation(final Table table) {
		super(table);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getJoinCondition(final Table joinTable, final String alias) {
		return null;
	}
}

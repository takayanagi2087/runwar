package bbs.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import bbs.field.ThreadSingleSelectField;
import dataforms.controller.ApplicationException;
import dataforms.dao.Dao;
import dataforms.dao.JDBCConnectableObject;
import dataforms.dao.Query;
import dataforms.dao.Table;
import dataforms.dao.TableList;
import dataforms.field.base.FieldList;
import dataforms.field.common.RowNoField;
import dataforms.field.sqlfunc.CountField;
import dataforms.field.sqltype.TimestampField;
import net.arnx.jsonic.JSON;

/**
 * 記事のDaoクラスです。
 *
 */
public class ArticleDao extends Dao {
	/**
	 * Logger.
	 */
	private static Logger logger = Logger.getLogger(ArticleDao.class);
	/**
	 * コンストラクタ。
	 * @param obj JDBC接続可能オブジェクト。
	 * @throws Exception 例外。
	 */
	public ArticleDao(final JDBCConnectableObject obj) throws Exception {
		super(obj);
	}

	/**
	 * QueryFormで指定した条件で行う問い合わせクラスです。
	 */
	public static class ArticleTableQuery extends Query {
		/**
		 * コンストラクタ。
		 */
		public ArticleTableQuery() {
			ArticleTable table = new ArticleTable();
			FileCountSubQuery fileCount = new FileCountSubQuery();
			fileCount.setAlias("fc");
			ArticleCountSubQuery articleCount = new ArticleCountSubQuery();
			articleCount.setAlias("ac");
			LastUpdateSubQuery lastUpdate = new LastUpdateSubQuery();
			articleCount.setAlias("ac");
			FieldList flist = new FieldList();
			flist.addAll(table.getFieldList());
			flist.add(articleCount.getArticleCountField());
			flist.add(fileCount.getFileCountField());
			flist.add(lastUpdate.getLastUpdateTimestampField());
			this.setFieldList(flist);
			this.setMainTable(table);
			this.setLeftJoinTableList(new TableList(articleCount, lastUpdate, fileCount));
		}
	}

	/**
	 * 添付ファイルの問合せクラスです。
	 *
	 */
	private static class AttachFileQuery extends Query {
		/**
		 * コンストラクタ。
		 */
		public AttachFileQuery() {
			AttachFileTable table = new AttachFileTable();
			this.setFieldList(table.getFieldList());
			this.setMainTable(table);
			this.setOrderByFieldList(new FieldList(table.getSortOrderField()));
		}
	}


	/**
	 * 問い合わせ結果フォームのフィールドリストを取得します。
	 * @return 問い合わせ結果フォームのフィールドリスト。
	 */
	public static FieldList getQueryResultFieldList() {
		Query query = new ArticleTableQuery();
		FieldList list = new FieldList();
		list.addField(new RowNoField());
		list.addAll(query.getFieldList());
		list.remove(ArticleTable.Entity.ID_THREAD_ID);
		list.add(0, new ThreadSingleSelectField(ArticleTable.Entity.ID_THREAD_ID));
		TimestampField ct = (TimestampField) list.get(ArticleTable.Entity.ID_CREATE_TIMESTAMP);
		ct.setDateFormat("format.timestampfield");
		TimestampField ut = (TimestampField) list.get(LastUpdateSubQuery.Entity.ID_LAST_UPDATE_TIMESTAMP);
		ut.setDateFormat("format.timestampfield");
		return list;
	}

	public enum QueryType {
		ALL,	// 全記事
		THREAD_LIST,	// スレッドリスト
		THREAD_CONTENTS	// スレッド内の記事リスト
	};

	/**
	 * QueryFormから入力された条件から、テーブルを検索し、指定されたページの情報を返します。
	 * @param data 条件データ。
	 * @param flist 条件フィールドリスト。
	 * @return 検索結果。
	 * @throws Exception 例外。
	 */
	public Map<String, Object> queryPage(final Map<String, Object> data, final FieldList flist, final QueryType type) throws Exception {
		Query query = new ArticleTableQuery();
		query.setQueryFormFieldList(flist);
		query.setQueryFormData(data);
		if (type == QueryType.THREAD_LIST) {
			query.setCondition("m.article_id = m.thread_id");
		} else if (type == QueryType.THREAD_CONTENTS){
			query.setCondition("m.thread_id = :thread_id");
		}
		String sortOrder = (String) data.get("sortOrder");
		FieldList sflist = query.getFieldList().getOrderByFieldList(sortOrder);
		if (sflist.size() == 0) {
			query.setOrderByFieldList(query.getMainTable().getPkFieldList());
		} else {
			query.setOrderByFieldList(sflist);
		}
		return this.executePageQuery(query);
	}

	/**
	 * QueryFormから入力された条件から、テーブルを検索し、マッチするすべてのデータを返します。
	 * @param data 条件データ。
	 * @param flist 条件フィールドリスト。
	 * @return 検索結果。
	 * @throws Exception 例外。
	 */
	public List<Map<String, Object>> query(final Map<String, Object> data, final FieldList flist) throws Exception {
		Query query = new ArticleTableQuery();
		query.setQueryFormFieldList(flist);
		query.setQueryFormData(data);
		return this.executeQuery(query);
	}

	/**
	 * スレッド一覧を取得します。
	 * @return スレッド一覧。
	 * @throws Exception 例外。
	 */
	public List<Map<String, Object>> queryThreadList() throws Exception {
		Query query = new ArticleTableQuery();
		query.setCondition("m.thread_id = m.article_id");
		return this.executeQuery(query);
	}

	/**
	 * PKでレコードを限定し、データを取得します。
	 * @param data 条件データ PKの情報をすべて含むマップ。
	 * @return ヒットしたレコード。
	 * @throws Exception 例外。
	 */
	public Map<String, Object> query(final Map<String, Object> data) throws Exception {
		Query query = new ArticleTableQuery();
		query.setQueryFormFieldList(query.getMainTable().getPkFieldList());
		query.setQueryFormData(data);
		Map<String, Object> map = this.executeRecordQuery(query);
		Query fquery = new AttachFileQuery();
		fquery.setQueryFormFieldList(query.getMainTable().getPkFieldList());
		fquery.setQueryFormData(data);
		List<Map<String, Object>> list = this.executeQuery(fquery);
		map.put("attachFileTable", list);
		return map;
	}

	/**
	 * 指定された記事IDに付随する添付ファイルを取得します。
	 * @param articleId 記事ID。
	 * @return 添付ファイルリスト。
	 * @throws Exception 例外。
	 */
	public List<Map<String, Object>> queryAttachFile(final Long articleId) throws Exception {
		Query fquery = new AttachFileQuery();
		ArticleTable table = new ArticleTable();
		fquery.setQueryFormFieldList(table.getPkFieldList());
		AttachFileTable.Entity p = new AttachFileTable.Entity();
		p.setArticleId(articleId);
		fquery.setQueryFormData(p.getMap());
		List<Map<String, Object>> list = this.executeQuery(fquery);
		return list;
	}

	/**
	 * データを追加します。
	 * @param data データ。
	 * @return 追加件数。
	 * @throws Exception 例外。
	 */
	public int insert(final Map<String, Object> data) throws Exception {
		ArticleTable.Entity e = new ArticleTable.Entity(data);
		logger.debug("data=" + JSON.encode(data, true));
		ArticleTable table = new ArticleTable();
		int ret = this.executeInsert(table, data);
		if (e.getThreadId() == null) {
			e.setThreadId(e.getArticleId());
			this.executeUpdate(table, data);
		}
		List<Map<String, Object>> list = this.getAttachFileList(data);
		this.saveTable(new AttachFileTable(), list, data);
		return ret;
	}

	/**
	 * POSTされた情報から添付ファイルのリストを取得すします。
	 * @param data POSTされた情報。
	 * @return 添付ファイルリスト。
	 */
	private List<Map<String, Object>> getAttachFileList(final Map<String, Object> data) {
		ArticleTable.Entity ae = new ArticleTable.Entity(data);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = (List<Map<String, Object>>) data.get("attachFileTable");
		for (Map<String, Object> m: list) {
			AttachFileTable.Entity fe = new AttachFileTable.Entity(m);
			fe.setArticleId(ae.getArticleId());
		}
		return list;
	}


	/**
	 * データを更新します。
	 * @param data データ。
	 * @return 更新件数。
	 * @throws Exception 例外。
	 */
	public int update(final Map<String, Object> data) throws Exception {
		// 楽観ロックチェック
		Table table = new ArticleTable();
		boolean ret = this.isUpdatable(table, data);
		if (!ret) {
			throw new ApplicationException(this.getPage(), "error.notupdatable");
		}
		// データ更新
		int cnt = this.executeUpdate(table, data);
		List<Map<String, Object>> list = this.getAttachFileList(data);
		this.saveTable(new AttachFileTable(), list, data);
		return cnt;
	}

	/**
	 * 記事数の問合せクラスです。
	 *
	 */
	private static class ArticleCountQuery extends Query {
		/**
		 * コンストラクタ。
		 */
		public ArticleCountQuery() {
			ArticleTable table = new ArticleTable();
			FieldList flist = new FieldList();
			flist.addField(new CountField("cnt", table.getArticleIdField()));
			this.setFieldList(flist);
			this.setMainTable(table);
		}
	}
	
	/**
	 * データを削除します。
	 * @param data データ。
	 * @return 削除件数。
	 * @throws Exception 例外。
	 */
	public int delete(final Map<String, Object> data) throws Exception {
		ArticleTable.Entity e = new ArticleTable.Entity(data);
		Long articleId = e.getArticleId();
		ArticleCountQuery query = new ArticleCountQuery();
		query.setCondition("m.thread_id=:thread_id");
		ArticleTable.Entity p = new ArticleTable.Entity();
		p.setThreadId(articleId);
		query.setQueryFormData(p.getMap());
		Integer cnt = (Integer) this.executeScalarQuery(query);
		if (cnt > 1) {
			throw new ApplicationException(this.getPage(), "error.cannotdelete");
		} else {
			return this.executeRemove(new ArticleTable(), data); // レコードの論理削除(DeleteFlagの設定)
		}
//		return this.executeDelete(new ArticleTable(), data); // レコードの物理削除
	}
}

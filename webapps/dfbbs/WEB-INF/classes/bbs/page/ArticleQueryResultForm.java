package bbs.page;

import java.util.Map;

import bbs.dao.ArticleDao;
import bbs.dao.ArticleDao.QueryType;
import bbs.dao.ArticleTable;
import dataforms.controller.Page;
import dataforms.controller.QueryResultForm;
import dataforms.field.base.Field.SortOrder;
import dataforms.field.base.FieldList;
import dataforms.htmltable.PageScrollHtmlTable;


/**
 * 問い合わせ結果フォームクラス。
 */
public class ArticleQueryResultForm extends QueryResultForm {
	/**
	 * コンストラクタ。
	 */
	public ArticleQueryResultForm() {
		ArticleTable table = new ArticleTable();
		this.addPkFieldList(table.getPkFieldList());
		PageScrollHtmlTable htmltable = new PageScrollHtmlTable(Page.ID_QUERY_RESULT, ArticleDao.getQueryResultFieldList());
		htmltable.getFieldList().get(ArticleTable.Entity.ID_TITLE).setSortable(true);
		htmltable.getFieldList().get(ArticleTable.Entity.ID_THREAD_ID).setSortable(true, SortOrder.DESC);
		htmltable.getFieldList().get(ArticleTable.Entity.ID_AUTHER).setSortable(true);
		htmltable.getFieldList().get(ArticleTable.Entity.ID_CONTENTS).setSortable(true);
		htmltable.getFieldList().get(ArticleTable.Entity.ID_CREATE_TIMESTAMP).setSortable(true, SortOrder.ASC);

		this.addHtmlTable(htmltable);
	}

	/**
	 * 問い合わせを行い、1ページ分の問い合わせ結果を返します。
	 *
	 * @param data 問い合わせフォームの入力データ。
	 * @param queryFormFieldList 問い合わせフォームのフィールドリスト。
	 * @return 問い合わせ結果。
	 *
	 */
	@Override
	protected Map<String, Object> queryPage(final Map<String, Object> data, final FieldList queryFormFieldList) throws Exception {
		ArticleDao dao = new ArticleDao(this);
		return dao.queryPage(data, queryFormFieldList, QueryType.ALL);
	}

	/**
	 * 問い合わせ結果リストのデータを削除します。
	 * <pre>
	 * 問い合わせ結果リストからの削除が不要な場合、HTMLから削除ボタンを削除し、
	 * このメソッドを何もしないメソッドにしてください。
	 * </pre>
	 * @param data 選択したデータのPKの値を記録したマップ。
	 */
	@Override
	protected void deleteData(final Map<String, Object> data) throws Exception {
		ArticleDao dao = new ArticleDao(this);
		this.setUserInfo(data); // 更新を行うユーザIDを設定する.
		dao.delete(data);
	}
}

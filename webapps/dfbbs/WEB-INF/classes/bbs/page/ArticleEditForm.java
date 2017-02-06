package bbs.page;

import java.util.List;
import java.util.Map;

import bbs.dao.ArticleDao;
import bbs.dao.ArticleTable;
import bbs.dao.AttachFileTable;
import bbs.field.ThreadSingleSelectField;
import dataforms.controller.ApplicationException;
import dataforms.controller.EditForm;
import dataforms.controller.QueryForm;
import dataforms.dao.Table;
import dataforms.field.base.Field;
import dataforms.field.common.FileField;
import dataforms.htmltable.EditableHtmlTable;
import dataforms.validator.RequiredValidator;

/**
 * 編集フォームクラス。
 */
public class ArticleEditForm extends EditForm {
	/**
	 * コンストラクタ。
	 */
	public ArticleEditForm() {
		ArticleTable table = new ArticleTable();
		table.getTitleField().addValidator(new RequiredValidator());
		table.getAutherField().addValidator(new RequiredValidator());
		table.getContentsField().addValidator(new RequiredValidator());
		table.getFieldList().remove(ArticleTable.Entity.ID_THREAD_ID);
		this.addField(new ThreadSingleSelectField(ArticleTable.Entity.ID_THREAD_ID));
		this.addTableFields(table);
		AttachFileTable aft = new AttachFileTable();
		EditableHtmlTable tbl = new EditableHtmlTable("attachFileTable", aft.getFieldList());
		this.addHtmlTable(tbl);
	}

	/**
	 * フォームの初期化を行います。
	 * <pre>
	 * DBを使用した初期化処理はここに記述します。
	 * </pre>
	 */
	@Override
	public void init() throws Exception {
		super.init();
	}

	/**
	 * 編集対象のデータを取得します。
	 * <pre>
	 * 問い合わせ結果フォームに表示されたデータを選択した際に呼び出されます。
	 * dataには最低編集対象レコードのPKのマップが入ってきます。
	 * </pre>
	 * @param data 取得するデータのPKの値が入ってきます。
	 * @return 編集対象データ。
	 */
	@Override
	protected Map<String, Object> queryData(final Map<String, Object> data) throws Exception {
		ArticleDao dao = new ArticleDao(this);
		return dao.query(data);
	}

	/**
	 * 参照登録対象対象のデータを取得します。
	 * <pre>
	 * queryDataから取得したデータから、PK項目を削除します。
	 * </pre>
	 * @param data 取得するデータのPKの値が入ってきます。
	 * @return 編集対象データ。
	 */
	@Override
	protected Map<String, Object> queryReferData(final Map<String, Object> data) throws Exception {
		Table table = new ArticleTable();
		Map<String, Object> ret = this.queryData(data);
		for (Field<?> f: table.getPkFieldList()) {
			ret.remove(f.getId());
		}
		for (Field<?> f: table.getFieldList()) {
			if (f instanceof FileField) {
				ret.remove(f.getId());
			}
		}
		return ret;
	}


	/**
	 * 編集対象のデータを取得します。
	 * <pre>
	 * 問い合わせフォームと編集フォームのみが配置されたページ(問い合わせ結果フォームが存在しないページ)の場合、
	 * 問い合わせフォームの入力データが渡され、それを元に編集対象のデータを取得します。
	 * </pre>
	 * @param data 問い合わせフォームの入力データ。
	 * @return 編集対象データ。
	 */
	@Override
	protected Map<String, Object> queryDataByQueryFormCondition(final Map<String, Object> data) throws Exception {
		ArticleDao dao = new ArticleDao(this);
		QueryForm qf = (QueryForm) this.getPage().getComponent("queryForm");
		List<Map<String, Object>> list = dao.query(data, qf.getFieldList());
		if (list.size() == 0) {
			throw new ApplicationException(this.getPage(), "error.notfounddata");
		}
		if (list.size() > 1) {
			throw new ApplicationException(this.getPage(), "error.cannotidentifydata");
		}
		return list.get(0);
	}

	/**
	 * ポストされたデータが更新するのか新規追加するのかを判定します。
	 * <pre>
	 * 編集対象データにPKの入力があった場合、更新すべきと判断します。
	 * </pre>
	 * @param data 入力データ。
	 * @return 更新対象データの場合true。
	 */
	@Override
	protected boolean isUpdate(final Map<String, Object> data) throws Exception {
		Table table = new ArticleTable();
		boolean ret = this.isUpdate(table, data);
		return ret;
	}

	/**
	 * データを新規追加します。
	 * @param data ポストされたデータ。
	 */
	@Override
	protected void insertData(final Map<String, Object> data) throws Exception {
		ArticleDao dao = new ArticleDao(this);
		this.setUserInfo(data); // 更新を行うユーザIDを設定する.
		dao.insert(data);
	}

	/**
	 * データを更新します。
	 * @param data ポストされたデータ。
	 */
	@Override
	protected void updateData(final Map<String, Object> data) throws Exception {
		ArticleDao dao = new ArticleDao(this);
		this.setUserInfo(data); // 更新を行うユーザIDを設定する.
		dao.update(data);
	}

	/**
	 * データを削除します。
	 * @param data ポストされたデータ。
	 */
	@Override
	public void deleteData(final Map<String, Object> data) throws Exception {
		ArticleDao dao = new ArticleDao(this);
		this.setUserInfo(data); // 更新を行うユーザIDを設定する.
		dao.delete(data);
	}
}

package bbs.page;

import bbs.dao.ArticleDao;
import bbs.dao.ArticleTable;
import dataforms.app.page.base.BasePage;
import dataforms.dao.Dao;
import dataforms.dao.Table;


/**
 * ページクラス。
 */
public class BBSPage extends BasePage {
	/**
	 * コンストラクタ。
	 */
	public BBSPage() {
		this.addForm(new BBSQueryForm());
		this.addForm(new BBSQueryResultForm());
		this.addForm(new ThreadQueryResultForm());
		this.addForm(new ArticleEditForm());
		this.addDialog(new FileDialog());
	}

	/**
	 * Pageが配置されるパスを返します。
	 *
	 * @return Pageが配置されるパス。
	 */
	public String getFunctionPath() {
		return "/bbs";
	}

	/**
	 * 操作対象テーブルクラスを取得します。
	 * <pre>
	 * ページjavaクラス作成用のメソッドです。
	 * </pre>
	 * @return 操作対象テーブル。
	 */
	public Class<? extends  Table> getTableClass() {
		return ArticleTable.class;
	}

	/**
	 * テーブルを操作するためのDaoクラスを取得します。
	 * <pre>
	 * ページjavaクラス作成用のメソッドです。
	 * </pre>
	 * @return Daoクラス。
	 */
	public Class<? extends Dao> getDaoClass() {
		return ArticleDao.class;
	}

}

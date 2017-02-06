package bbs.page;

import bbs.dao.ArticleTable;
import dataforms.controller.QueryForm;
import dataforms.field.base.Field.MatchType;



/**
 * 問い合わせフォームクラス。
 */
public class BBSQueryForm extends QueryForm {
	/**
	 * コンストラクタ。
	 */
	public BBSQueryForm() {
		ArticleTable table = new ArticleTable();
		this.addField(table.getTitleField()).setMatchType(MatchType.PART);

	}
}

package bbs.page;

import bbs.dao.ArticleTable;
import bbs.field.ThreadSingleSelectField;
import dataforms.controller.QueryForm;
import dataforms.field.base.Field.MatchType;



/**
 * 問い合わせフォームクラス。
 */
public class ArticleQueryForm extends QueryForm {
	/**
	 * コンストラクタ。
	 */
	public ArticleQueryForm() {
		ArticleTable table = new ArticleTable();
		this.addField(table.getTitleField()).setMatchType(MatchType.PART);
		this.addField(table.getAutherField()).setMatchType(MatchType.PART);
		this.addField(table.getContentsField()).setMatchType(MatchType.PART);
		this.addField(new ThreadSingleSelectField("threadId"));

	}
}

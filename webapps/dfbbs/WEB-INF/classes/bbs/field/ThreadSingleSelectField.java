package bbs.field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bbs.dao.ArticleDao;
import bbs.dao.ArticleTable;
import dataforms.annotation.WebMethod;
import dataforms.controller.JsonResponse;
import dataforms.field.common.SingleSelectField;
import dataforms.util.NumberUtil;
import dataforms.util.StringUtil;

/**
 * スレッド選択フィールド。
 *
 */
public class ThreadSingleSelectField extends SingleSelectField<Long> {
	/**
	 * コンストラクタ。
	 */
	public ThreadSingleSelectField() {
		super(null);
	}

	/**
	 * コンストラクタ。
	 * @param id フィールドID。
	 */
	public ThreadSingleSelectField(final String id) {
		super(id);
	}

	@Override
	public void init() throws Exception {
		super.init();
		List<Map<String, Object>> olist = queryOptionList();
		this.setOptionList(olist, true);
	}

	private List<Map<String, Object>> queryOptionList() throws Exception {
		ArticleDao dao = new ArticleDao(this);
		List<Map<String, Object>> list = dao.queryThreadList();
		List<Map<String, Object>> olist = new ArrayList<Map<String, Object>>();

		for (Map<String, Object> m: list) {
			Map<String, Object> o = new HashMap<String, Object>();
			o.put("value", m.get(ArticleTable.Entity.ID_ARTICLE_ID));
			o.put("name", m.get(ArticleTable.Entity.ID_TITLE));
			olist.add(o);
		}
		return olist;
	}


	@Override
	public void setClientValue(final Object v) {
		if (!StringUtil.isBlank(v)) {
			this.setValue(Long.parseLong(((String) v).replaceAll(",", "")));
		} else {
			this.setValue(null);
		}
	}

	@Override
	public void setDBValue(final Object value) {
		this.setValue(NumberUtil.longValueObject(value));
	}

	@WebMethod
	public JsonResponse queryOption(final Map<String, Object> p) throws Exception {
		List<Map<String, Object>> list = this.queryOptionList();
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("value", "");
		m.put("name", "");
		list.add(0, m);
		JsonResponse resp = new JsonResponse(JsonResponse.SUCCESS, list);
		return resp;
	}
}

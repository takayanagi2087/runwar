package bbs.page;

import java.util.List;
import java.util.Map;

import bbs.dao.ArticleDao;
import bbs.dao.AttachFileTable;
import dataforms.annotation.WebMethod;
import dataforms.controller.Form;
import dataforms.controller.JsonResponse;
import dataforms.field.base.Field;
import dataforms.htmltable.HtmlTable;

public class FileListForm extends Form {
	public FileListForm() {
		super("fileListForm");
		AttachFileTable aft = new AttachFileTable();
		this.addField(aft.getArticleIdField());
		for (Field<?> f: aft.getFieldList()) {
			f.setReadonly(true);
		}
		HtmlTable tbl = new HtmlTable("attachFileTable", aft.getFieldList());
		this.addHtmlTable(tbl);
	}

	@WebMethod
	public JsonResponse getFileList(final Map<String, Object> p) throws Exception {
		Map<String, Object> data = this.convertToServerData(p);
		AttachFileTable.Entity e = new AttachFileTable.Entity(data);
		ArticleDao dao = new ArticleDao(this);
		List<Map<String, Object>> list = dao.queryAttachFile(e.getArticleId());
		data.put("attachFileTable", list);
		return new JsonResponse(JsonResponse.SUCCESS, this.convertToClientData(data));
	}
}

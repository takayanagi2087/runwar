package bbs.page;

import dataforms.controller.Dialog;

public class FileDialog extends Dialog {
	public FileDialog() {
		super("fileDialog");
		this.addForm(new FileListForm());
	}
}

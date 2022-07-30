/**
 * @fileOverview {@link SampleEditForm}クラスを記述したファイルです。
 */

'use strict';

/**
 * @class SampleEditForm
 *
 * @extends EditForm
 */
class SampleEditForm extends EditForm {
	/**
	 * コンストラクタ。
	 */
	constructor() {
		super();
	}

	/**
	 * HTMLエレメントとの対応付けを行います。
	 */
	attach() {
		super.attach();
		this.get("printButton").click(() => {
			this.print();
			return false;
		});
	}

	/**
	 * サーバの印刷処理を呼び出します。
	 */
	async print() {
		if (this.validate()) {
			let r = await this.submit("print");
			this.parent.resetErrorStatus();
			if (r.status == JsonResponse.SUCCESS) {
				logger.dir(r);
			} else {
				this.parent.setErrorInfo(this.getValidationResult(r), this);
			}
		}
	}


	/**
	 * フォームのバリデーション。
	 * <pre>
	 * フォーム内のフィールド関連チェックを実装します。
	 * </pre>
	 */
	validateForm() {
		let list = super.validateForm();
		if (list.length == 0) {
			var fv = new FieldValidator();
			if ((!fv.isBlank(this.get("sampleNumeric").val())) && fv.isBlank(this.get("sampleDate").val())) {
				list.push(new ValidationError("sampleDate", MessagesUtil.getMessage("error.requireddate")))
			}
		}
		return list;
	}

	// 独自のWebメソッドを呼び出す場合は、以下のコードを参考にしてください。
	/**
	 * Webメソッドの呼び出しサンプル。
	 *
	 */
/*
	async callWebMethod() {
		if (this.validate()) {
			let r = await this.submit("webMethod");
			this.parent.resetErrorStatus();
			if (r.status == JsonResponse.SUCCESS) {
				// TODO:成功時の処理を記述します。
				// 応答情報をログ表示
				logger.dir(r);
			} else {
				this.parent.setErrorInfo(this.getValidationResult(r), this);
			}
		}
	}
*/


	// フォーム単位のバリデーションを行う場合は以下のコメントを参考に実装してください。
	// フォームの計算処理を行う場合、以下の処理を参考にしてください。
	/**
	 * 計算イベント処理を行います。
	 * <pre>
	 * 計算イベントフィールドが更新された場合、このメソッドが呼び出されます。
	 * データ入力時の自動計算が必要な場合このメソッドをオーバーライドしてください。
	 * </pre>
	 * @param {jQuery} element イベントが発生した要素。初期表示の時等特定フィールドが要因でない場合はnullが設定されます。
	 *
	 */
/*
	onCalc(element) {
	}
*/


	// フォームの各種動作をカスタマイズするには以下のメソッドをオーバーライドしてください。

	/**
	 * 各フィールドにデータを設定します。
	 * <pre>
	 * 新規モードの場合、削除ボタンを隠します。
	 * </pre>
	 * @param {Object} data フォームデータ.
	 *
	 */
/*
	setFormData(data) {
		super.setFormData(data);
	}
*/

	/**
	 * 編集モードにします。
	 * <pre>
	 * 各フィールドを編集可能状態にします。
	 * </pre>
	 */
/*
	toEditMode() {
		super.toEditMode();
	}
*/

	/**
	 * 確認モードにします。
	 * <pre>
	 * 各フィールドを編集不可状態にします。
	 * </pre>
	 */
/*
	toConfirmMode() {
		super.toConfirmMode();
	}
*/

	/**
	 * 確認ボタンのイベント処理を行います。
	 * <pre>
	 * 対応するFormのconfirmメソッドを呼び出し、問題なければ確認モードに遷移します。
	 * ファイルアップロードフィールドはサーバーに送信されません。
	 * </pre>
	 */
/*
	confirm() {
		super.confirm();
	}
*/
		/**
	 * 新規登録モードにします。
	 * <pre>
	 * 対応するEditFormのgetNewDataを呼び出し、初期データを取得します。
	 * 各フィールドに取得データを設定し、編集モードにします。
	 * </pre>
	 */
/*
	newData() {
		super.newData();
	}
*/

		/**
	 * 更新登録モードにします。
	 * <pre>
	 * 対応するEditFormのgetDataを呼び出し、編集対象データを取得します。
	 * 各フィールドに取得データを設定し、編集モードにします。
	 * </pre>
	 */
/*
	updateData() {
		super.updateData();
	}
*/

	/**
	 * データを参照登録します。
	 * <pre>
	 * 対応するEditFormのgetReferDataを呼び出し、参照対象データを取得します。
	 * 各フィールドに取得データを設定し、編集モードにします。
	 * </pre>
	 *
	 */
/*
	referData() {
		super.referData();
	}
*/
	/**
	 * データを参照します。
	 * <pre>
	 * 対応するEditFormのgetDataを呼び出し、参照対象データを取得します。
	 * 各フィールドに取得データを設定し、参照モードにします。
	 * </pre>
	 */
/*
	viewData() {
		super.viewData();
	}
*/

	/**
	 * 保存や削除後の画面状態遷移を行います。
	 */
/*
	changeStateForAfterUpdate() {
		super.changeStateForAfterUpdate();
	}
*/

	/**
	 * 保存ボタンのイベント処理を行います。
	 * <pre>
	 * 対応するFormのsaveメソッドを呼び出し、保存処理を行います。
	 * ファイルアップロードフィールドもサーバーに送信されます。
	 * </pre>
	 */
/*
	save() {
		super.save();
	}
*/

	/**
	 * 保存ボタンのイベント処理を行います。
	 * <pre>
	 * 対応するEditFormのdeleteメソッドを呼び出し、保存処理を行います。
	 * </pre>
	 */
/*
	del() {
		super.del();
	}
*/
}


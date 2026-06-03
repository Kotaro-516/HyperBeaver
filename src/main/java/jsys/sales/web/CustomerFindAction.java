/**
 * CustomerFindAction.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsys.sales.web;

import jakarta.servlet.http.*;
import jsys.sales.common.*;
import jsys.sales.entity.*;
import jsys.sales.logic.*;

/**
 *
 * @author FLM
 * @version 1.0.0
 */
public class CustomerFindAction implements ActionIF{
	public String execute(HttpServletRequest request) {
		// 遷移先ページ名の設定
		String page = "V201_02CustomerFindResultView.jsp";

		try {
			// セッションチェック
			HttpSession session = request.getSession(false);
			if (session == null) { // セッションが存在しない
				throw new SalesSystemException("セッションが無効です。");
			}

			Employee loginEmployee =
					(Employee) session.getAttribute("loginEmployee");
			if (loginEmployee == null) { // ログイン従業員情報が存在しない
				throw new SalesSystemException("ログイン情報が存在しません。");
			}
			// パラメータの取得
			String custCode = request.getParameter("custCode");

			// パラメータ未送信または未入力の場合
			// SalesBusinessExceptionをスローする
			if (custCode == null || custCode.equals("")) {
				throw new SalesBusinessException("得意先コードを入力してください。");
			}

			// 業務Logic呼び出し
			CustomerFindLogic logic = new CustomerFindLogic();
			Customer customer= logic.findCustomer(custCode);

			// 処理結果の格納
			request.setAttribute("customer", customer);

		} catch (SalesBusinessException e) {
			// 業務エラー発生時
			// エラーメッセージリストをリクエストスコープに格納
			// 遷移先ページ名の設定
			request.setAttribute("errorMessage", e.getMessage());
			request.setAttribute("errorMessageList", e.getMessageList());
			page = "V201_01CustomerFindView.jsp";
		} catch (SalesSystemException e) {
			// システムエラー発生時
			// エラーメッセージをリクエストスコープに格納
			// 遷移先ページ名の設定
			request.setAttribute("errorMessage", e.getMessage());
			page = "V901_01SystemErrorPage.jsp";
		}
		// 遷移先ページ名を返却
		return page;
	}
}

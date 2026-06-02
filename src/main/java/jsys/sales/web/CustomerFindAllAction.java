/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * EmployeeFindAllAction.java
 *
 */

package jsys.sales.web;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.entity.Customer;
import jsys.sales.entity.Employee;
import jsys.sales.logic.CustomerFindAllLogic;

public class CustomerFindAllAction implements ActionIF {

	/**
	 * 従業員一覧ボタンクリック時の処理を実行する。
	 *
	 * @param request
	 *            リクエスト情報
	 * @return 遷移先ページ名
	 */
	public String execute(HttpServletRequest request) {
		// 遷移先ページ名の設定
		String page = "V205_01CustomerFindAllResultView.jsp";

		try {
			// セッションチェック
			HttpSession session = request.getSession(false);
			if (session == null) { // セッションが存在しない
				throw new SalesSystemException("セッションが無効です。");
			} else {
				Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
				if (loginEmployee == null) { // ログイン情報が存在しない
					throw new SalesSystemException("ログイン情報が存在しません。");
				}
			}

			// 業務Logic呼び出し
			CustomerFindAllLogic logic = new CustomerFindAllLogic();
			ArrayList<Customer> customerList = logic.findAllCustomer();

			// 処理結果の格納
			request.setAttribute("customerList", customerList);

		} catch (SalesBusinessException e) {
			// 業務エラー発生時
			// エラーメッセージリストをリクエストスコープに格納
			// 遷移先ページ名の設定
			request.setAttribute("errorMessage", e.getMessage());
			request.setAttribute("errorMessageList", e.getMessageList());
		} catch (SalesSystemException e) {
			// システムエラー発生時
			// エラーメッセージをリクエストスコープに格納
			// 遷移先ページ名の設定
			request.setAttribute("errorMessage", e.getMessage());
			page = "SystemErrorPage.jsp";
		}
		return page;
	}
}

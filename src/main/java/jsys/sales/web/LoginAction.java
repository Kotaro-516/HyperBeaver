/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * LoginAction.java
 *
 */

package jsys.sales.web;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.entity.Employee;
import jsys.sales.logic.LoginLogic;

public class LoginAction implements ActionIF {

	/**
	 * ログインボタンクリック時の処理を実行する。
	 *
	 * @param request
	 *            リクエスト情報
	 * @return 遷移先ページ名
	 */
	public String execute(HttpServletRequest request) {
		// 遷移先ページ名の設定
		String page = "SystemSelect.jsp";
		try {
			// パラメータの取得
			String empNo = request.getParameter("empNo");
			String password = request.getParameter("password");

			// パラメータ未送信または未入力の場合

			// エラーメッセージリストの生成
			ArrayList<String> errorMessageList = new ArrayList<>();
			if (empNo == null || empNo.equals("")) {
				errorMessageList.add("従業員番号またはパスワードが未入力です。");
			}
			if (password == null || password.equals("")) {
				errorMessageList.add("従業員番号またはパスワードが未入力です。");
			}
			if (!errorMessageList.isEmpty()) {
				throw new SalesBusinessException(errorMessageList);
			}

			// 業務Logic呼び出し
			LoginLogic logic = new LoginLogic();
			Employee employee = logic.login(empNo,password);

			// セッションの生成
			HttpSession session = request.getSession(true);
			// ログイン情報の格納
			session.setAttribute("loginEmployee", employee);
		} catch (SalesBusinessException e) {
			// 業務エラー発生時
			// エラー情報をリクエストスコープに格納
			request.setAttribute("errorMessage", e.getMessage());
			request.setAttribute("errorMessageList", e.getMessageList());
			// 遷移先ページ名の設定
			page = "EmployeeLoginPage.jsp";
		} catch (SalesSystemException e) {
			// システムエラー発生時
			// エラーメッセージをリクエストスコープに格納
			request.setAttribute("errorMessage", e.getMessage());
			// 遷移先ページ名の設定
			page = "SystemErrorPage.jsp";
		}
		return page;
	}
}

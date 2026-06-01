/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * LoginServletMVC.java
 *
 */
package jsys.sales.web;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.entity.Employee;
import jsys.sales.logic.LoginLogic;

@WebServlet(urlPatterns = { "/loginMVC" })
public class LoginServletMVC extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 遷移先ページ名の設定
		String page = "/WEB-INF/jsp/EmployeeMenu.jsp";
		try {
			// パラメータの取得
			request.setCharacterEncoding("UTF-8");
			String empNo = request.getParameter("empNo");
			String password = request.getParameter("password");

			// パラメータ未送信または未入力の場合
			// EmployeeBusinessExceptionをスローする
			String errorMessage = "";
			if (empNo == null || empNo.equals("")) {
				errorMessage += "従業員番号が未入力です。<br>";
			}
			if (password == null || password.equals("")) {
				errorMessage += "パスワードが未入力です。<br>";
			}
			if (!errorMessage.equals("")) {
				throw new SalesBusinessException(errorMessage);
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
			// エラーメッセージの格納
			request.setAttribute("errorMessage", e.getMessage());
			// 遷移先ページ名の設定
			page = "/jsp/LoginPage.jsp";
		} catch (SalesSystemException e) {
			// システムエラー発生時
			// エラーメッセージの格納
			request.setAttribute("errorMessage", e.getMessage());
			// 遷移先ページ名の設定
			page = "/jsp/SystemErrorPage.jsp";
		}
		// 結果画面に転送
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
}

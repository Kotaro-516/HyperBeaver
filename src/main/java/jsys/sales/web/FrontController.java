/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * FrontController.java
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

@WebServlet(urlPatterns = { "/jsysFC" })
public class FrontController extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 遷移先ページ名の設定
		String path="/WEB-INF/jsp/";
		String page = null;
		// Actionクラス
		ActionIF action = null;
		// パラメータの取得
		request.setCharacterEncoding("UTF-8");
		String buttonId = request.getParameter("buttonId");

		// パラメータ未送信または空文字の場合
		if (buttonId == null || buttonId.equals("")) {
			// TODO 1 プロトタイピング作成演習：得意先管理メニュー画面のbuttonIdをデフォルトとして設定してください。
			buttonId = "c000";
		}
		// リクエスト種別の判定
		switch (buttonId) {
			case "c000":
				page = "V100_01LoginEmployee.jsp";
				break;
			case "c001":
				// ログイン処理を実行する
				action = new LoginAction();
				page = action.execute(request);
				break;
			case "c002":
				// ログアウト処理を実行する
				action = new LogoutAction();
				page = action.execute(request);
				break;
			case "c100":
				// メインメニュー（システム選択画面）へ遷移
				page = "V101_01SystemSelect.jsp";
				break;
			case "c110":
				// 得意先管理メニューへ遷移
				page = "V200_01CustomerManagementMenu.jsp";
				break;
			case "c200":
				//得意先登録画面へ遷移
				page = "V202_01CustomerRegistView.jsp";
				break;
			case "c201":
				//得意先登録処理を実行する
				action = new CustomerRegistAction();
				page = action.execute(request);
				break;
			case "c310":
				// 売上集計メニューへ遷移
				page = "V300_01CustomerSummaryMenu.jsp";
				break;
			case "c101":
				// 得意先検索画面を表示するだけ（Actionは呼ばない）
				page = "V201_01CustomerFindView.jsp";
				break;
			case "c102":
				// 検索処理を実行する
				action = new CustomerFindAction();
				page = action.execute(request);
				break;

			case "c500":
				//画面一覧表示の遷移をする
				page = "V205_01CustomerFindAllResultView.jsp";
				break;

			default:
				// buttonIdが存在しない場合
				page = "V101_01SystemSelect.jsp";
		}
		// 結果画面に転送
		RequestDispatcher rd = request.getRequestDispatcher(path + page);
		rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}

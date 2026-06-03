/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * LogoutAction.java
 *
 */

package jsys.sales.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LogoutAction implements ActionIF {

	/**
	 * ログアウトボタンクリック時の処理を実行する。
	 *
	 * @param request
	 *            リクエスト情報
	 * @return 遷移先ページ名
	 */
	public String execute(HttpServletRequest request) {
		// 遷移先ページ名の設定
		String page = "V100_01LoginEmployee.jsp";
		// セッションの生成
		HttpSession session = request.getSession(false);
		// セッションの破棄
		session.invalidate();
		return page;
	}
}

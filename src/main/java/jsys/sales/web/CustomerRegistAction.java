/**
 * CustomerRegistAction.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsys.sales.web;

import java.util.ArrayList;

import jakarta.servlet.http.*;
import jsys.sales.common.*;
import jsys.sales.entity.*;
import jsys.sales.logic.*;

/**
 *
 * @author FLM
 * @version 1.0.0
 */
public class CustomerRegistAction implements ActionIF {

	public String execute(HttpServletRequest request) {
		// 遷移先ページ名の設定
		String page = "V202_02CustomerRegistResultView.jsp";

		try {
			// セッションチェック
			HttpSession session = request.getSession(false);
			if (session == null) { // セッションが存在しない
				throw new SalesSystemException("セッションが無効です。");
			}

			// ログイン従業員情報の取得
			Employee loginEmployee =
					(Employee) session.getAttribute("loginEmployee");
			if (loginEmployee == null) { // ログイン従業員情報が存在しない
				throw new SalesSystemException("ログイン情報が存在しません。");
			}

			// パラメータの取得
			String custName = request.getParameter("custName");
			String telNo = request.getParameter("telNo");
			String postalCode = request.getParameter("postalCode");
			String address = request.getParameter("address");
			String discountRateString = request.getParameter("discountRate");

			// エラーメッセージリストの生成
			ArrayList<String> errorMessageList = new ArrayList<String>();

			// パラメータ未送信または未入力の場合
			// エラーメッセージをエラーメッセージリストに格納する
			if (custName == null || custName.equals("")) {
				errorMessageList.add("得意先名が未入力です。");
			}
			if (telNo == null || telNo.equals("")) {
				errorMessageList.add("電話番号が未入力です。");
			}
			if (postalCode == null || postalCode.equals("")) {
				errorMessageList.add("郵便番号が未入力です。");
			}
			if (address == null || address.equals("")) {
				errorMessageList.add("住所が未入力です。");
			}
			if (discountRateString == null || discountRateString.equals("")) {
				errorMessageList.add("割引率が未入力です。");
			}

			// 電話番号の形式チェック
			// 電話番号が入力されており、数字とハイフン以外を含む場合
			if (telNo != null && !telNo.equals("")
					&& !telNo.matches("[0-9-]+")) {
				errorMessageList.add("電話番号は数字とハイフンで入力してください。");
			}

			// 郵便番号の形式チェック
			// 郵便番号が入力されており、「999-9999」の形式でない場合
			if (postalCode != null && !postalCode.equals("")
					&& !postalCode.matches("[0-9]{3}-[0-9]{4}")) {
				errorMessageList.add("郵便番号は999-9999の形式で入力してください。");
			}

			double discountRate = 0.0;
			if (discountRateString != null && !discountRateString.equals("")) {
				try {
					discountRate = Double.parseDouble(discountRateString);

					if (discountRate < 0.0 || discountRate > 99.0) {
						errorMessageList.add("割引率は0から99の範囲で入力してください。");
					}
				} catch (NumberFormatException e) {
					errorMessageList.add("割引率は数値で入力してください。");
				}
			}

			// 入力値に誤りがある場合
			// SalesBusinessExceptionをスローする
			if (!errorMessageList.isEmpty()) {
				throw new SalesBusinessException(errorMessageList);
			}

			// Customerオブジェクトの生成
			Customer customer = new Customer();

			// 得意先情報の設定
			customer.setCustName(custName);
			customer.setTelNo(telNo);
			customer.setPostalCode(postalCode);
			customer.setAddress(address);
			customer.setDiscountRate(discountRate);

			// 業務Logic呼び出し
			CustomerRegistLogic logic = new CustomerRegistLogic();
			customer = logic.registCustomer(customer);

			// 処理結果の格納
			request.setAttribute("customer", customer);

		} catch (SalesBusinessException e) {
			// 業務エラー発生時
			// エラーメッセージおよびエラーメッセージリストを
			// リクエストスコープに格納
			// 遷移先ページ名の設定
			request.setAttribute("errorMessage", e.getMessage());
			request.setAttribute("errorMessageList", e.getMessageList());
			page = "V202_01CustomerRegistView.jsp";

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
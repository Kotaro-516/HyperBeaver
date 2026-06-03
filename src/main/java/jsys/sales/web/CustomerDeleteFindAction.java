package jsys.sales.web;

import java.util.ArrayList;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.entity.Customer;
import jsys.sales.logic.CustomerFindLogic;

public class CustomerDeleteFindAction implements ActionIF {

	public String execute(HttpServletRequest request) {
		String page = "V204_01CustomerDeleteView.jsp";

		try {
			// セッションチェック
			HttpSession session = request.getSession(false);
			if (session == null) {
				throw new SalesSystemException("セッションが無効です。");
			}

			// パラメータ取得
			String custCode = request.getParameter("custCode");

			// 入力チェック
			if (custCode == null || custCode.isEmpty()) {
				throw new SalesBusinessException("得意先コードが未入力です。");
			}

			// 得意先情報の取得
			CustomerFindLogic findLogic = new CustomerFindLogic();
			Customer customer = findLogic.findCustomer(custCode);

			// 検索成功した得意先情報を格納
			request.setAttribute("customer", customer);

		} catch (SalesBusinessException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.setAttribute("errorMessageList", e.getMessageList());
		} catch (SalesSystemException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}

		return page;
	}
}

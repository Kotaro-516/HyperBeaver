package jsys.sales.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import jsys.sales.common.*;
import jsys.sales.logic.*;

public class CustomerDeleteAction implements ActionIF {

    public String execute(HttpServletRequest request) {

        String page = "V204_02CustomerDeleteResultView.jsp";

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
                throw new SalesBusinessException("得意先番号が未入力です。");
            }

            // Logic呼び出し
            CustomerDeleteLogic logic = new CustomerDeleteLogic();
            boolean result = logic.deleteCustomer(custCode);

            // 結果格納
            request.setAttribute("result", result);

        } catch (SalesBusinessException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("errorMessageList", e.getMessageList());
            page = "V204_01CustomerDeleteView.jsp";

        } catch (SalesSystemException e) {
            request.setAttribute("errorMessage", e.getMessage());
            page = "V901_01SystemErrorPage.jsp";
        }

        return page;
    }
}
``
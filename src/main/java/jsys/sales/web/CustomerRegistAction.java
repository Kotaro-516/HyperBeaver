/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * CustomerRegistAction.java
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
import jsys.sales.logic.CustomerRegistLogic;

/**
 * 得意先登録画面からのリクエストを処理するActionクラス。
 */
public class CustomerRegistAction implements ActionIF {
    private static final String RESULT_PAGE = "V202_02CustomerRegistResultView.jsp";
    private static final String INPUT_PAGE = "V202_01CustomerRegistView.jsp";
    private static final String SYSTEM_ERROR_PAGE = "V901_01SystemErrorPage.jsp";
    private static final String SYSTEM_ERROR_MESSAGE =
            "システムエラーが発生しました。システム管理者に連絡してください。";

    @Override
    public String execute(HttpServletRequest request) {
        String page = RESULT_PAGE;
        try {
            // セッションとログイン従業員情報の確認
            HttpSession session = request.getSession(false);
            if (session == null) {
                throw new SalesSystemException(SYSTEM_ERROR_MESSAGE);
            }
            Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
            if (loginEmployee == null) {
                throw new SalesSystemException(SYSTEM_ERROR_MESSAGE);
            }

            // 入力値の取得
            String custName = request.getParameter("custName");
            String telNo = request.getParameter("telNo");
            String postalCode = request.getParameter("postalCode");
            String address = request.getParameter("address");
            String discountRateValue = request.getParameter("discountRate");

            // 入力値チェック
            int discountRate = validateInput(custName, telNo, postalCode, address,
                    discountRateValue);

            // 得意先情報設定
            Customer customer = new Customer();
            customer.setCustName(custName);
            customer.setTelNo(telNo);
            customer.setPostalCode(postalCode);
            customer.setAddress(address);
            customer.setDiscountRate(discountRate);

            // 業務Logicの呼出し
            CustomerRegistLogic logic = new CustomerRegistLogic();
            customer = logic.registCustomer(customer);

            // 登録結果の格納
            request.setAttribute("customer", customer);
        } catch (SalesBusinessException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("errorMessageList", e.getMessageList());
            page = INPUT_PAGE;
        } catch (SalesSystemException e) {
            request.setAttribute("errorMessage", e.getMessage());
            page = SYSTEM_ERROR_PAGE;
        }
        return page;
    }

    /**
     * 画面入力値の必須・形式チェックを行う。
     */
    private int validateInput(String custName, String telNo, String postalCode,
            String address, String discountRateValue) throws SalesBusinessException {
        ArrayList<String> errorMessageList = new ArrayList<>();

        if (isBlank(custName)) {
            errorMessageList.add("得意先名が未入力です。");
        }
        if (isBlank(telNo)) {
            errorMessageList.add("電話番号が未入力です。");
        } else if (telNo.length() > 13 || !telNo.matches("[0-9-]+")) {
            errorMessageList.add("電話番号は13桁以内の数字とハイフンで入力してください。");
        }
        if (isBlank(postalCode)) {
            errorMessageList.add("郵便番号が未入力です。");
        } else if (!postalCode.matches("[0-9]{3}-[0-9]{4}")) {
            errorMessageList.add("郵便番号はXXX-XXXXの形式で入力してください。");
        }
        if (isBlank(address)) {
            errorMessageList.add("住所が未入力です。");
        } else if (address.length() > 40) {
            errorMessageList.add("住所は40文字以内で入力してください。");
        }
        if (isBlank(discountRateValue)) {
            errorMessageList.add("割引率が未入力です。");
        }

        int discountRate = 0;
        if (!isBlank(discountRateValue)) {
            try {
                discountRate = Integer.parseInt(discountRateValue);
                if (discountRate < 0 || discountRate > 99) {
                    errorMessageList.add("割引率は0から99の範囲で入力してください。");
                }
            } catch (NumberFormatException e) {
                errorMessageList.add("割引率は数値で入力してください。");
            }
        }
        if (!isBlank(custName) && custName.length() > 32) {
            errorMessageList.add("得意先名は32文字以内で入力してください。");
        }

        if (!errorMessageList.isEmpty()) {
            throw new SalesBusinessException(errorMessageList);
        }
        return discountRate;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}

package jsys.sales.web;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.entity.MonthlyOrderSummary;
import jsys.sales.logic.MonthlyOrderSummaryLogic;

/**
 * 月別受注集計処理を実行するActionクラス。
 */
public class MonthlyOrderSummaryAction implements ActionIF {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String yearParam = request.getParameter("year");
        String monthParam = request.getParameter("month");

        request.setAttribute("year", yearParam);
        request.setAttribute("month", monthParam);

        try {
            checkInput(yearParam, monthParam);

            int year = Integer.parseInt(yearParam);
            int month = Integer.parseInt(monthParam);

            MonthlyOrderSummaryLogic logic = new MonthlyOrderSummaryLogic();
            ArrayList<MonthlyOrderSummary> summaryList =
                    logic.findMonthlyOrderSummary(year, month);
            long total = logic.calculateTotal(summaryList);

            request.setAttribute("summaryList", summaryList);
            request.setAttribute("total", total);
            page = "V301_01MonthlyOrderSummary.jsp";
        } catch (SalesBusinessException e) {
            if (!e.getMessageList().isEmpty()) {
                request.setAttribute("errorMessageList", e.getMessageList());
            } else {
                request.setAttribute("errorMessage", e.getMessage());
            }
            page = "V301_01MonthlyOrderSummary.jsp";
        } catch (SalesSystemException e) {
            request.setAttribute("errorMessage", e.getMessage());
            page = "SystemErrorPage.jsp";
        }
        return page;
    }

    /**
     * 年・月の入力値をチェックする。
     *
     * @param yearParam 入力された年
     * @param monthParam 入力された月
     * @throws SalesBusinessException 入力内容が不正な場合
     */
    private void checkInput(String yearParam, String monthParam)
            throws SalesBusinessException {
        ArrayList<String> messageList = new ArrayList<>();

        if (yearParam == null || yearParam.isBlank()) {
            messageList.add("年を入力してください。");
        } else if (!yearParam.matches("[0-9]{4}")) {
            messageList.add("年は半角数字4桁で入力してください。");
        }

        if (monthParam == null || monthParam.isBlank()) {
            messageList.add("月を入力してください。");
        } else if (!monthParam.matches("[0-9]{1,2}")) {
            messageList.add("月は半角数字で入力してください。");
        } else {
            int month = Integer.parseInt(monthParam);
            if (month < 1 || month > 12) {
                messageList.add("月は1から12の範囲で入力してください。");
            }
        }

        if (!messageList.isEmpty()) {
            throw new SalesBusinessException(messageList);
        }
    }
}

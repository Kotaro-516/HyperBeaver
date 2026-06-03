package jsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.ArrayList;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.OrderDAO;
import jsys.sales.entity.MonthlyOrderSummary;

/**
 * 月別受注集計の業務処理を担当するLogicクラス。
 */
public class MonthlyOrderSummaryLogic {

    /**
     * 指定年月の受注を得意先ごとに集計する。
     * 入力形式・範囲のチェックはActionクラスで実施済みであることを前提とする。
     *
     * @param year 集計対象年
     * @param month 集計対象月
     * @return 月別受注集計結果の一覧
     * @throws SalesBusinessException 集計対象データが存在しない場合
     * @throws SalesSystemException データベースアクセスに失敗した場合
     */
    public ArrayList<MonthlyOrderSummary> findMonthlyOrderSummary(int year, int month)
            throws SalesBusinessException, SalesSystemException {

        YearMonth targetMonth = YearMonth.of(year, month);
        String startDate = targetMonth.atDay(1).toString();
        String endDate = targetMonth.plusMonths(1).atDay(1).toString();

        try (Connection con = ConnectionManager.getConnection()) {
            OrderDAO dao = new OrderDAO(con);
            ArrayList<MonthlyOrderSummary> summaryList =
                    dao.createOrderTotalListByCustomer(startDate, endDate);

            if (summaryList.isEmpty()) {
                throw new SalesBusinessException("指定された年月の受注情報は存在しません。");
            }
            return summaryList;
        } catch (SQLException e) {
            throw new SalesSystemException("データベースアクセスに失敗しました。");
        }
    }

    /**
     * 得意先別合計金額から総計を算出する。
     *
     * @param summaryList 得意先別集計結果
     * @return 総計
     */
    public long calculateTotal(ArrayList<MonthlyOrderSummary> summaryList) {
        long total = 0L;
        for (MonthlyOrderSummary summary : summaryList) {
            total += summary.getTotalPrice();
        }
        return total;
    }
}

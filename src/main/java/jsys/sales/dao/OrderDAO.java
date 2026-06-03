/**
 * OrderDAO.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsys.sales.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.sales.entity.MonthlyOrderSummary;

/**
 * 受注情報に関するデータベースアクセス処理を行うDAOクラス。
 *
 * @author FLM
 * @version 1.0.0
 */
public class OrderDAO {

	private Connection con;

	/**
	 * コンストラクタ。
	 *
	 * @param con データベース接続オブジェクト
	 */
	public OrderDAO(Connection con) {
		this.con = con;
	}

	/**
	 * 指定された期間内の受注情報を、得意先ごとに集計して取得する。
	 *
	 * @param startDate 集計開始日。対象月の初日をyyyy-MM-dd形式で受け取る
	 * @param endDate 集計終了境界日。対象月の翌月初日をyyyy-MM-dd形式で受け取る
	 * @return 得意先ごとの月別受注集計結果リスト。
	 *         該当する受注情報が存在しない場合は空のリスト
	 * @throws SQLException データベースアクセス処理で例外が発生した場合
	 */
	public ArrayList<MonthlyOrderSummary> createOrderTotalListByCustomer(
			String startDate, String endDate) throws SQLException {

		String sql = "SELECT c.customer_code, c.customer_name, "
				+ "SUM(o.total_price) AS total_price "
				+ "FROM customer c "
				+ "INNER JOIN orders o "
				+ "ON c.customer_code = o.customer_code "
				+ "WHERE o.order_date >= ? AND o.order_date < ? "
				+ "GROUP BY c.customer_code, c.customer_name "
				+ "ORDER BY c.customer_code";

		PreparedStatement stmt = null;
		ResultSet res = null;

		ArrayList<MonthlyOrderSummary> summaryList = new ArrayList<>();

		try {
			// PreparedStatementの作成
			stmt = con.prepareStatement(sql);

			// パラメータの設定
			stmt.setDate(1, Date.valueOf(startDate));
			stmt.setDate(2, Date.valueOf(endDate));

			// SQL文の実行
			res = stmt.executeQuery();

			// 結果セットから情報を取り出す
			while (res.next()) {
				// MonthlyOrderSummaryオブジェクトの生成
				MonthlyOrderSummary summary = new MonthlyOrderSummary(
						res.getString("customer_code"),
						res.getString("customer_name"),
						res.getLong("total_price")
						);

				// リストに追加
				summaryList.add(summary);
			}

		} finally {
			// クローズ処理
			if (res != null) {
				res.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}

		return summaryList;
	}
}
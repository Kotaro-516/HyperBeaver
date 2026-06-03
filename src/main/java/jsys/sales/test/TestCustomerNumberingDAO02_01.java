/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * TestCustomerNumberingDAO02_01.java
 *
 */

package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerNumberingDAO;
import jsys.sales.entity.CustomerNumbering;

/**
 * PT002_02_009 採番情報更新が成功する場合
 */
public class TestCustomerNumberingDAO02_01 {

	public static void main(String[] args) {
		Connection con = null;

		try {
			// テストのための準備としてデータベースに接続する。
			con = ConnectionManager.getConnection();

			// ここからテストを行う。
			CustomerNumberingDAO custNumberingDAO = new CustomerNumberingDAO(con);
			CustomerNumbering numbering = new CustomerNumbering(16);

			boolean result = custNumberingDAO.updateCustomerCode(numbering);

			System.out.println("更新結果：" + result);
			System.out.println("更新した得意先採番コード：" + numbering.getCustCode());

		} catch (SQLException e) {
			System.out.println("SQLExceptionがスローされました。");
			e.printStackTrace();
		} finally {
			try {
				// データベースへの接続を切断する。
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

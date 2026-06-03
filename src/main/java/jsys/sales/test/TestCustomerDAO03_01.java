/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * TestCustomerDAO03_01.java
 *
 */

package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.entity.Customer;

/**
 * PT002_02_004 得意先登録が成功する場合
 */
public class TestCustomerDAO03_01 {

	public static void main(String[] args) {
		Connection con = null;

		try {
			// テストのための準備としてデータベースに接続する。
			con = ConnectionManager.getConnection();

			// ここからテストを行う。
			CustomerDAO custDAO = new CustomerDAO(con);
			Customer customer = new Customer(
					"KA0016", "テストストア", "000-000-0000",
					"000-0000", "東京都大田区蒲田", 0.0);

			boolean result = custDAO.insertCustomer(customer);

			System.out.println("登録結果：" + result);
			System.out.println("登録した得意先コード：" + customer.getCustCode());
			System.out.println("登録した得意先名　　：" + customer.getCustName());

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

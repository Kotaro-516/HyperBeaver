/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * TestCustomerDAO02_01.java
 *
 */

package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.entity.Customer;

/**
 * PT002_02_001 電話番号検索が成功する場合
 */
public class TestCustomerDAO02_01 {

	public static void main(String[] args) {
		Connection con = null;

		try {
			// テストのための準備としてデータベースに接続する。
			con = ConnectionManager.getConnection();

			// ここからテストを行う。
			CustomerDAO custDAO = new CustomerDAO(con);
			Customer duplicateCustomer = custDAO.findCustomerByTelNo("045-128-3581");

			if (duplicateCustomer == null) {
				System.out.println("戻り値：null");
			} else {
				System.out.println("得意先コード：" + duplicateCustomer.getCustCode());
				System.out.println("得意先名　　：" + duplicateCustomer.getCustName());
				System.out.println("電話番号　　：" + duplicateCustomer.getTelNo());
				System.out.println("郵便番号　　：" + duplicateCustomer.getPostalCode());
				System.out.println("住所　　　　：" + duplicateCustomer.getAddress());
				System.out.println("割引率　　　：" + duplicateCustomer.getDiscountRate());
			}

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

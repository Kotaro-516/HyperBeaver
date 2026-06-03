/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * TestCustomerDAO05_04.java
 *
 */

package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.entity.Customer;


/**
 * PT205_01_004 得意先コード順に取得できる場合
 */
public class TestCustomerDAO05_04 {

	public static void main(String[] args) {
		Connection con = null;

		try {
			// テストのための準備としてデータベースに接続する。
			con = ConnectionManager.getConnection();

			// ここからテストを行う。
			CustomerDAO custDAO = new CustomerDAO(con);
			ArrayList<Customer> customerList = custDAO.findAllCustomer();

			System.out.println("取得された得意先コードの順序：");
			for (Customer customer : customerList) {
				System.out.println(customer.getCustCode());
			}

			/*
			 * DB_04 の確認：
			 * KA0003、KA0001、KA0002 の未削除データを準備して実行し、
			 * 戻り値が KA0001 → KA0002 → KA0003 の順に
			 * 表示されることを確認する。
			 *
			 * 本テストを満たすため、findAllCustomer() のSQLには
			 * ORDER BY customer_code が必要である。
			 */

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

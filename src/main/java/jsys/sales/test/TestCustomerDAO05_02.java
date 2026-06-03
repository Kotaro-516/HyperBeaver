/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * TestCustomerDAO05_02.java
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
 * PT205_01_002 削除済みの得意先が存在する場合
 */
public class TestCustomerDAO05_02 {

	public static void main(String[] args) {
		Connection con = null;

		try {
			// テストのための準備としてデータベースに接続する。
			con = ConnectionManager.getConnection();

			// ここからテストを行う。
			CustomerDAO custDAO = new CustomerDAO(con);
			ArrayList<Customer> customerList = custDAO.findAllCustomer();

			System.out.println("取得件数：" + customerList.size());
			System.out.println("取得された得意先コード：");
			for (Customer customer : customerList) {
				System.out.println(customer.getCustCode());
			}

			/*
			 * DB_02 の確認：
			 * KA0001：delete_flag = false
			 * KA0002：delete_flag = true
			 * KA0003：delete_flag = false
			 *
			 * 実行結果に KA0001 と KA0003 が表示され、
			 * KA0002 が表示されないことを確認する。
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

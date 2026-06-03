/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * TestCustomerDAO05_03.java
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
 * PT205_01_003 取得対象の得意先が存在しない場合
 */
public class TestCustomerDAO05_03 {

	public static void main(String[] args) {
		Connection con = null;

		try {
			// テストのための準備としてデータベースに接続する。
			con = ConnectionManager.getConnection();

			// ここからテストを行う。
			CustomerDAO custDAO = new CustomerDAO(con);
			ArrayList<Customer> customerList = custDAO.findAllCustomer();

			System.out.println("戻り値がnullでないこと：" + (customerList != null));
			System.out.println("リストが空であること　：" + customerList.isEmpty());
			System.out.println("取得件数　　　　　　：" + customerList.size());

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

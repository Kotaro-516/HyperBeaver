/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * TestCustomerDAO03_03.java
 *
 */

package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.entity.Customer;

/**
 * PT002_02_006 insertCustomer() 実行時にSQLExceptionが発生する場合
 */
public class TestCustomerDAO03_03 {

	public static void main(String[] args) {
		Connection con = null;

		try {
			// テストのための準備としてデータベースに接続する。
			con = ConnectionManager.getConnection();

			CustomerDAO custDAO = new CustomerDAO(con);
			Customer customer = new Customer(
					"KA0016", "テストストア", "000-000-0000",
					"000-0000", "東京都大田区蒲田", 0.0);

			/*
			 * デバッグ実行で、次のメソッド呼出し直前に処理を停止する。
			 * その後、データベースを停止する、またはcustomerテーブルへ
			 * 登録できない状態にしてから処理を再開する。
			 */
			custDAO.insertCustomer(customer);

			System.out.println("SQLExceptionがスローされませんでした。");

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

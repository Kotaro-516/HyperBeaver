/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * TestCustomerDAO05_05.java
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
 * PT205_01_005 findAllCustomer() 実行時にSQLExceptionが発生する場合
 */
public class TestCustomerDAO05_05 {

	public static void main(String[] args) {
		Connection con = null;

		try {
			// テストのための準備としてデータベースに接続する。
			con = ConnectionManager.getConnection();

			CustomerDAO custDAO = new CustomerDAO(con);

			/*
			 * デバッグ実行で、次のメソッド呼出し直前に処理を停止する。
			 * その後、データベースを停止する、またはcustomerテーブルを
			 * 参照できない状態にしてから処理を再開する。
			 */
			custDAO.findAllCustomer();

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

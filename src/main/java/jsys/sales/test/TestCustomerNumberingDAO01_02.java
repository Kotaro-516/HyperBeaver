/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * TestCustomerNumberingDAO01_02.java
 *
 */

package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerNumberingDAO;

/**
 * PT002_02_008 findCustomerCode() 実行時にSQLExceptionが発生する場合
 */
public class TestCustomerNumberingDAO01_02 {

	public static void main(String[] args) {
		Connection con = null;

		try {
			// テストのための準備としてデータベースに接続する。
			con = ConnectionManager.getConnection();

			/*
			 * デバッグ実行で、次のメソッド呼出し直前に処理を停止する。
			 * その後、データベースを停止する、またはcustomer_numberingテーブルを
			 * 参照できない状態にしてから処理を再開する。
			 */
			CustomerNumberingDAO custNumberingDAO = new CustomerNumberingDAO(con);
			custNumberingDAO.findCustomerCode();

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

/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * TestCustomerNumberingDAO02_02.java
 *
 */

package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerNumberingDAO;
import jsys.sales.entity.CustomerNumbering;

/**
 * PT002_02_010 updateCustomerCode() 実行時にSQLExceptionが発生する場合
 */
public class TestCustomerNumberingDAO02_02 {

	public static void main(String[] args) {
		Connection con = null;

		try {
			// テストのための準備としてデータベースに接続する。
			con = ConnectionManager.getConnection();

			CustomerNumberingDAO custNumberingDAO = new CustomerNumberingDAO(con);
			CustomerNumbering numbering = new CustomerNumbering(16);

			/*
			 * デバッグ実行で、次のメソッド呼出し直前に処理を停止する。
			 * その後、データベースを停止する、またはcustomer_numberingテーブルを
			 * 更新できない状態にしてから処理を再開する。
			 */
			custNumberingDAO.updateCustomerCode(numbering);

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

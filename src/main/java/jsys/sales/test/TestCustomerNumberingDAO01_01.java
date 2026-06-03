/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * TestCustomerNumberingDAO01_01.java
 *
 */

package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerNumberingDAO;
import jsys.sales.entity.CustomerNumbering;

/**
 * PT002_02_007 採番情報取得が成功する場合
 */
public class TestCustomerNumberingDAO01_01 {

	public static void main(String[] args) {
		Connection con = null;

		try {
			// テストのための準備としてデータベースに接続する。
			con = ConnectionManager.getConnection();

			// ここからテストを行う。
			CustomerNumberingDAO custNumberingDAO = new CustomerNumberingDAO(con);
			CustomerNumbering numbering = custNumberingDAO.findCustomerCode();

			if (numbering == null) {
				System.out.println("戻り値：null");
			} else {
				System.out.println("得意先採番コード：" + numbering.getCustCode());
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

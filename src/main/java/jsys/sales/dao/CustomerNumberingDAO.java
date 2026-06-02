/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * CustomerNumberingDAO.java
 *
 */
package jsys.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jsys.sales.entity.CustomerNumbering;

/**
 * 得意先採番テーブルへアクセスするDAOクラス。
 */
public class CustomerNumberingDAO {
    private final Connection con;

    public CustomerNumberingDAO(Connection con) {
        this.con = con;
    }

    /**
	 * 得意先採番情報を取得する。
	 *
	 * @return 得意先採番情報。採番情報が存在しない場合はnull
	 * @throws SQLException データベースアクセス処理で例外が発生した場合
	 */
	public CustomerNumbering findCustomerCode() throws SQLException {
		String sql = "SELECT customer_code FROM customer_numbering";
		PreparedStatement stmt = null;
		ResultSet res = null;
		CustomerNumbering numbering = null;

		try {
			// PreparedStatementの作成
			stmt = con.prepareStatement(sql);
			// SQL文の実行
			res = stmt.executeQuery();
			// 結果セットから情報を取り出す
			if (res.next()) {
				// CustomerNumberingオブジェクトの生成
				numbering = new CustomerNumbering(res.getInt("customer_code"));
			}

		} finally {
			// クローズ処理
			if (res != null) {
				res.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}

		return numbering;
	}


	/**
	 * 得意先採番情報を更新する。
	 *
	 * @param numbering 更新する得意先採番情報
	 * @return 更新できた場合はtrue、それ以外の場合はfalse
	 * @throws SQLException データベースアクセス処理で例外が発生した場合
	 */
	public boolean updateCustomerCode(CustomerNumbering numbering) throws SQLException {
		String sql = "UPDATE customer_numbering SET customer_code = ?";
		PreparedStatement stmt = null;
		boolean result = false;

		try {
			// PreparedStatementの作成
			stmt = con.prepareStatement(sql);
			// パラメータの設定
			stmt.setInt(1, numbering.getCustCode());
			// SQL文の実行
			int count = stmt.executeUpdate();
			// 更新結果の判定
			if (count == 1) {
				result = true;
			}

		} finally {
			// クローズ処理
			if (stmt != null) {
				stmt.close();
			}
		}

		return result;
	}

}

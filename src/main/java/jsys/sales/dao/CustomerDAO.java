/**
 * CustomerDAO.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsys.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jsys.sales.entity.Customer;

/**
 *
 * @author FLM
 * @version 1.0.0
 */
public class CustomerDAO {
	private Connection con;

	/**
	 * @param con
	 */
	public CustomerDAO(Connection con) {
		this.con = con;
	}

	public Customer findCustomer(String custCode) throws SQLException {
		String sql = "SELECT customer_code, customer_name, customer_telNo, customer_postalCode, customer_address, discount_rate FROM customer WHERE customer_code = ? AND delete_flag = 0";
		PreparedStatement stmt = null;
		ResultSet res = null;
		Customer customer = null;

		try {
			// PreparedStatementの作成
			stmt = con.prepareStatement(sql);
			// パラメータの設定
			stmt.setString(1, custCode);
			// SQL文の実行
			res = stmt.executeQuery();
			// 結果セットから情報を取り出す
			if (res.next()) {
				// Customerオブジェクトの生成
				customer = new Customer(
						res.getString("customer_code"), res.getString("customer_name"),
						res.getString("customer_telNo"), res.getString("customer_postalCode"),
						res.getString("customer_address"), res.getInt("discount_rate")
						);
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

		return customer;
	}

}
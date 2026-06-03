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
import java.util.ArrayList;
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
						res.getString("customer_address"), res.getDouble("discount_rate")
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

	/**
	 * 電話番号をもとに得意先情報を検索する。
	 *
	 * @param telNo 電話番号
	 * @return 得意先情報。該当する得意先が存在しない場合はnull
	 * @throws SQLException データベースアクセス処理で例外が発生した場合
	 */
	public Customer findCustomerByTelNo(String telNo) throws SQLException {
		String sql = "SELECT customer_code, customer_name, customer_telNo, customer_postalCode, customer_address, discount_rate, delete_flag FROM customer WHERE customer_telNo = ?";
		PreparedStatement stmt = null;
		ResultSet res = null;
		Customer customer = null;

		try {
			// PreparedStatementの作成
			stmt = con.prepareStatement(sql);
			// パラメータの設定
			stmt.setString(1, telNo);
			// SQL文の実行
			res = stmt.executeQuery();
			// 結果セットから情報を取り出す
			if (res.next()) {
				// Customerオブジェクトの生成
				customer = new Customer(
						res.getString("customer_code"), res.getString("customer_name"),
						res.getString("customer_telNo"), res.getString("customer_postalCode"),
						res.getString("customer_address"), res.getDouble("discount_rate")
						);
				customer.setDeleted(res.getBoolean("delete_flag"));
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

	/**
	 * 得意先情報を登録する。
	 *
	 * @param customer 登録する得意先情報
	 * @return 登録できた場合はtrue、それ以外の場合はfalse
	 * @throws SQLException データベースアクセス処理で例外が発生した場合
	 */
	public boolean insertCustomer(Customer customer) throws SQLException {
		String sql = "INSERT INTO customer (customer_code, customer_name, customer_telNo, customer_postalCode, customer_address, discount_rate, delete_flag) VALUES (?, ?, ?, ?, ?, ?, false)";
		PreparedStatement stmt = null;
		boolean result = false;

		try {
			// PreparedStatementの作成
			stmt = con.prepareStatement(sql);
			// パラメータの設定
			stmt.setString(1, customer.getCustCode());
			stmt.setString(2, customer.getCustName());
			stmt.setString(3, customer.getTelNo());
			stmt.setString(4, customer.getPostalCode());
			stmt.setString(5, customer.getAddress());
			stmt.setDouble(6, customer.getDiscountRate());
			// SQL文の実行
			int count = stmt.executeUpdate();
			// 登録結果の判定
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

	/**
	 * 得意先情報を削除する（論理削除）。
	 *
	 * @param custCode 得意先コード
	 * @return 削除できた場合は更新件数（通常は1）、それ以外は0
	 * @throws SQLException データベースアクセス処理で例外が発生した場合
	 */
	public boolean deleteCustomer(String custCode) throws SQLException {
		String sql
				= "UPDATE customer SET delete_flag = 1 WHERE customer_code = ? AND delete_flag = 0";
		PreparedStatement stmt = null;
		boolean result = false;

		try {
			// PreparedStatementの作成
			stmt = con.prepareStatement(sql);
			// パラメータの設定
			stmt.setString(1, custCode);
			// SQL文の実行
			int count = stmt.executeUpdate();
			if (count == 1) {
				result = true;
			}
		} finally {
			// クローズ処理
			if (stmt != null)
				stmt.close();
		}
		return result;
	}

	public ArrayList<Customer> findAllCustomer() throws SQLException {

        String sql = "SELECT customer_code, customer_name, customer_telNo, "
        + "customer_postalCode, customer_address, discount_rate "
        + "FROM customer WHERE delete_flag = 0 ORDER BY customer_code";


        PreparedStatement stmt = null;
        ResultSet res = null;

        ArrayList<Customer> customerList = new ArrayList<>();


        try {
            stmt = con.prepareStatement(sql);
            res = stmt.executeQuery();

            while (res.next()) {
                Customer customer = new Customer(
                    res.getString("customer_code"),
                    res.getString("customer_name"),
                    res.getString("customer_telNo"),
                    res.getString("customer_postalCode"),
                    res.getString("customer_address"),
                    res.getDouble("discount_rate")
                );
                customerList.add(customer);
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
		return customerList;
	}

	/////////////////////////////////////////////
	public int restoreCustomer(String custCode) throws SQLException {

	    String sql =
	        "UPDATE customer SET delete_flag = 0 " +
	        "WHERE customer_code = ? AND delete_flag = 1";

	    PreparedStatement stmt = null;
	    int count = 0;

	    try {
	        stmt = con.prepareStatement(sql);
	        stmt.setString(1, custCode);

	        count = stmt.executeUpdate();

	    } finally {
	        if (stmt != null) stmt.close();
	    }

	    return count;
	}


}
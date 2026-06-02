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
	/**
     * 電話番号をもとに得意先情報を検索する。
     * 削除済みデータを含めて検索し、電話番号の再登録を防止する。
     *
     * @param telNo 電話番号
     * @return 得意先情報。該当なしの場合はnull
     * @throws SQLException DBアクセスエラー
     */
    public Customer findCustomerByTelNo(String telNo) throws SQLException {
        String sql = "SELECT customer_code, customer_name, customer_telno, "
                + "customer_postalcode, customer_address, discount_rate, delete_flag "
                + "FROM customer WHERE customer_telno = ?";
        Customer customer = null;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, telNo);
            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    customer = createCustomer(res);
                }
            }
        }
        return customer;
    }
    /**
     * 得意先情報を登録する。
     *
     * @param customer 登録する得意先情報
     * @return 登録件数が1件の場合true、それ以外はfalse
     * @throws SQLException DBアクセスエラー
     */
    public boolean insertCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer (customer_code, customer_name, customer_telno, "
                + "customer_postalcode, customer_address, discount_rate, delete_flag) "
                + "VALUES (?, ?, ?, ?, ?, ?, false)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, customer.getCustCode());
            stmt.setString(2, customer.getCustName());
            stmt.setString(3, customer.getTelNo());
            stmt.setString(4, customer.getPostalCode());
            stmt.setString(5, customer.getAddress());
            stmt.setInt(6, customer.getDiscountRate());
            return stmt.executeUpdate() == 1;
        }
    }
    private Customer createCustomer(ResultSet res) throws SQLException {
        return new Customer(
                res.getString("customer_code"),
                res.getString("customer_name"),
                res.getString("customer_telno"),
                res.getString("customer_postalcode"),
                res.getString("customer_address"),
                res.getInt("discount_rate"));
    }



}
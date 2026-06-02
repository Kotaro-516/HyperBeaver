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
     * 現在の得意先採番情報を取得する。
     *
     * @return 得意先採番情報。データが存在しない場合はnull
     * @throws SQLException DBアクセスエラー
     */
    public CustomerNumbering findCustomerCode() throws SQLException {
        String sql = "SELECT customer_code FROM customer_numbering";

        try (PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet res = stmt.executeQuery()) {
            if (res.next()) {
                return new CustomerNumbering(res.getInt("customer_code"));
            }
            return null;
        }
    }

    /**
     * 得意先採番情報を更新する。
     *
     * @param numbering 更新する得意先採番情報
     * @return 更新件数が1件の場合true、それ以外はfalse
     * @throws SQLException DBアクセスエラー
     */
    public boolean updateCustomerCode(CustomerNumbering numbering) throws SQLException {
        String sql = "UPDATE customer_numbering SET customer_code = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, numbering.getCustCode());
            return stmt.executeUpdate() == 1;
        }
    }
}

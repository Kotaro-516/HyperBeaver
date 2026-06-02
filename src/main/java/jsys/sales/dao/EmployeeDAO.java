/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * EmployeeDAO.java
 *
 */

package jsys.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jsys.sales.entity.Employee;

public class EmployeeDAO {

	/** データベースの接続 */
	private Connection con;

	/**
	 * コンストラクタ
	 *
	 * @param con
	 *            データベースの接続オブジェクト
	 */
	public EmployeeDAO(Connection con) {
		this.con = con;
	}

	/**
	 * 従業員を検索する。
	 *
	 * @param empNo
	 * @param password
	 *            従業員番号
	 * @return 従業員
	 * @throws SQLException
	 *             データベースエラーが発生した場合
	 */
	
	public Employee findEmployee(String empNo, String password) throws SQLException {
		String sql = "SELECT employee_no, employee_name, password "
				+ "FROM employee WHERE employee_no = ? AND password = ?";
		PreparedStatement stmt = null;
		ResultSet res = null;
		Employee employee = null;

		try {
			// PreparedStatementの作成
			stmt = con.prepareStatement(sql);
			// パラメータの設定
			stmt.setString(1, empNo);
			stmt.setString(2, password);
			// SQL文の実行
			res = stmt.executeQuery();
			// 結果セットから情報を取り出す
			if (res.next()) {
				// Employeeオブジェクトの生成
				employee = new Employee(res.getString("employee_no"), res.getString("employee_name"), res.getString("password"));
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

		return employee;
	}

}

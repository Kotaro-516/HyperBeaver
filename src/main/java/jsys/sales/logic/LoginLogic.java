/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * LoginLogic.java
 *
 */

package jsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.EmployeeDAO;
import jsys.sales.entity.Employee;

public class LoginLogic {

	/**
	 * ログイン情報を検索する。
	 *
	 * @param empId
	 *            従業員番号
	 * @param password
	 *            パスワード
	 * @return 従業員
	 * @throws EmployeeBusinessException
	 *             業務エラーが発生した場合
	 * @throws EmployeeSystemException
	 *             システムエラーが発生した場合
	 */
	public Employee login(String empNo, String password)
			throws SalesBusinessException, SalesSystemException {
		Connection con = null;
		Employee employee = null;
		try {
			// データベースの接続を取得する
			con = ConnectionManager.getConnection();

			// DAOを生成し、メソッドを呼び出す
			EmployeeDAO employeeDAO = new EmployeeDAO(con);
			employee = employeeDAO.findEmployee(empNo, password);
			// 戻り値がnullの場合、業務エラーを発生させる
			if (employee == null) {
				throw new SalesBusinessException("ログインに失敗しました。\n従業員コードまたはパスワードを確認してください。");
			}
		} catch (SQLException e) {
			// データベースエラーの場合、システムエラーを発生させる
			throw new SalesSystemException("システムエラーが発生しました。管理者に連絡してください。");
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new SalesSystemException("システムエラーが発生しました。管理者に連絡してください。");
			}
		}
		return employee;
	}
}
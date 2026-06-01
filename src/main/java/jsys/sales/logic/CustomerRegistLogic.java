/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * EmployeeRegistLogic.java
 *
 */

package jsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;


import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.entity.Customer;


public class CustomerRegistLogic {
	/**
	 * 従業員を登録する。
	 *
	 * @param employee
	 *            従業員
	 * @return 従業員
	 * @throws EmployeeBusinessException
	 *             業務エラーが発生した場合
	 * @throws EmployeeSystemException
	 *             システムエラーが発生した場合
	 */
	public Customer registEmployee(Customer customer)
			throws SalesBusinessException, SalesSystemException {
		Connection con = null;

		try {
			// データベースの接続を取得する
			con = ConnectionManager.getConnection();

			// 自動コミットモードの解除
			con.setAutoCommit(false);

			// DAOを生成し、メソッドを呼び出す
			CustomerDAO customerDAO = new CustomerDAO(con);

			Customer findCustomer = customerDAO.findCustomer(customer.getCustCode());

			// 検索結果がある場合、業務エラーを発生させる
			if (findCustomer != null) {
				// ロールバック
				con.rollback();
				throw new SalesBusinessException("得意先は既に登録されています。");
			}
			// 得意先を登録する
			customerDAO.insertCustomer(customer);



			// コミット
			con.commit();

		} catch (SQLException e) {
			try {
				// ロールバック
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
		return customer;
	}
}

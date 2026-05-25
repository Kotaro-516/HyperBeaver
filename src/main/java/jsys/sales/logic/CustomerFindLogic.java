package jsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.entity.Customer;


public class CustomerFindLogic {

	/**
	 * 従業員を検索する。
	 *
	 * @param empId
	 *            従業員番号
	 * @return 従業員
	 * @throws EmployeeBusinessException
	 *             業務エラーが発生した場合
	 * @throws EmployeeSystemException
	 *             システムエラーが発生した場合
	 */
	public Customer findCustomer(String custCode)
			throws SalesBusinessException, SalesSystemException {
		Connection con = null;
		Customer customer = null;
		try {
			// データベースの接続を取得する
			con = ConnectionManager.getConnection();

			// DAOを生成し、メソッドを呼び出す
			CustomerDAO customerDAO = new CustomerDAO(con);
			customer = customerDAO.findCustomer(custCode);

			// 検索結果がない場合、業務エラーを発生させる
			if (customer == null) {
				throw new SalesBusinessException("得意先は、存在しません。");
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
		return customer;
	}
}

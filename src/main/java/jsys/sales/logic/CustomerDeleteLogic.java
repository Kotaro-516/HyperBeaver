package jsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.entity.Customer;

public class CustomerDeleteLogic {


	/**
	 * 従業員を検索する。
	 *
	 * @param empId
	 *            従業員番号
	 * @return 従業員
	 * @throws SalesBusinessException
	 *             業務エラーが発生した場合
	 * @throws SalesSystemException
	 *             システムエラーが発生した場合
	 */
	public boolean deleteCustomer(String custCode)
			throws SalesBusinessException, SalesSystemException {
		Connection con = null;

		try {
			// データベースの接続を取得する
			con = ConnectionManager.getConnection();

			// DAOを生成し、メソッドを呼び出す
			CustomerDAO customerDAO = new CustomerDAO(con);
			int rows = customerDAO.deleteCustomer(custCode);

			// 検索結果がない場合、業務エラーを発生させる
			if (rows != 1) {
				throw new SalesBusinessException("得意先は存在しないか、既に削除されています。");
			}

			return true;

		} catch (SQLException e) {
			// データベースエラーの場合、システムエラーを発生させる
			throw new SalesSystemException("システムエラーが発生しました。管理者に連絡してください。");
		} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						throw new SalesSystemException("システムエラーが発生しました。管理者に連絡してください。");
					}
				}
		}
	}
}
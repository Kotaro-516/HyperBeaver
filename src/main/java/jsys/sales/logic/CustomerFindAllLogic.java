/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * CustomerFindAllLogic.java
 *
 */
package jsys.sales.logic;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.entity.Customer;

public class CustomerFindAllLogic {
	/**
	 * @throws CustomerBusinessException
	 *             業務エラーが発生した場合
	 * @throws CustomerSystemException
	 *             システムエラーが発生した場合
	 */
	public ArrayList<Customer> findAllCustomer()
			throws SalesBusinessException, SalesSystemException {
		Connection con = null;
		ArrayList<Customer> customerList = null;
		try {
			// データベースの接続を取得する
			con = ConnectionManager.getConnection();
			// DAOを生成し、メソッドを呼び出す
			CustomerDAO customerDAO = new CustomerDAO(con);
			customerList = customerDAO.findAllCustomer();

			// 検索結果がない場合、業務エラーを発生させる
			if (customerList.isEmpty()) {
				throw new SalesBusinessException("得意先が登録されていないか、削除されています。");
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
		return customerList;
	}
}
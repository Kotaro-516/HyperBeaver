/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * CustomerRegistLogic.java
 *
 */

package jsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.common.SalesBusinessException;
import jsys.sales.common.SalesSystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.dao.CustomerNumberingDAO;
import jsys.sales.entity.Customer;
import jsys.sales.entity.CustomerNumbering;

public class CustomerRegistLogic {

	/**
	 * 得意先を登録する。
	 *
	 * @param customer
	 *            登録する得意先情報
	 * @return 登録した得意先情報
	 * @throws SalesBusinessException
	 *             業務エラーが発生した場合
	 * @throws SalesSystemException
	 *             システムエラーが発生した場合
	 */
	public Customer registCustomer(Customer customer)
			throws SalesBusinessException, SalesSystemException {
		Connection con = null;

		try {
			// データベースの接続を取得する
			con = ConnectionManager.getConnection();

			// 得意先情報登録処理と得意先採番更新処理を
			// 同一トランザクション内で実行するため、自動コミットを解除する
			con.setAutoCommit(false);

			// DAOを生成し、電話番号に該当する得意先情報を検索する
			CustomerDAO customerDAO = new CustomerDAO(con);
			Customer duplicateCustomer = customerDAO.findCustomerByTelNo(customer.getTelNo());

			// 同一電話番号の得意先が存在する場合、業務エラーを発生させる
			if (duplicateCustomer != null) {
				throw new SalesBusinessException("同一電話番号の得意先は既に登録されています。");
			}

			// DAOを生成し、得意先採番情報を取得する
			CustomerNumberingDAO numberingDAO = new CustomerNumberingDAO(con);
			CustomerNumbering numbering = numberingDAO.findCustomerCode();

			// 得意先採番情報が取得できない場合、システムエラーを発生させる
			if (numbering == null) {
				throw new SalesSystemException("システムエラーが発生しました。管理者に連絡してください。");
			}

			// 得意先コードを生成し、登録する得意先情報に設定する
			int currentCode = numbering.getCustCode();
			String customerCode = createCustomerCode(currentCode + 1);
			customer.setCustCode(customerCode);

			// 得意先情報を登録する
			boolean customerResult = customerDAO.insertCustomer(customer);
			if (!customerResult) {
				throw new SalesSystemException("システムエラーが発生しました。管理者に連絡してください。");
			}

			// 得意先採番情報を更新する
			numbering.setCustCode(currentCode + 1);
			boolean numberingResult = numberingDAO.updateCustomerCode(numbering);
			if (!numberingResult) {
				throw new SalesSystemException("システムエラーが発生しました。管理者に連絡してください。");
			}

			// 処理結果を確定する
			con.commit();

		} catch (SalesBusinessException e) {
			try {
				// 業務エラーの場合、処理結果を取り消す
				if (con != null) {
					con.rollback();
				}
			} catch (SQLException ex) {
				throw new SalesSystemException("システムエラーが発生しました。管理者に連絡してください。");
			}
			throw e;

		} catch (SalesSystemException e) {
			try {
				// システムエラーの場合、処理結果を取り消す
				if (con != null) {
					con.rollback();
				}
			} catch (SQLException ex) {
				throw new SalesSystemException("システムエラーが発生しました。管理者に連絡してください。");
			}
			throw e;

		} catch (SQLException e) {
			try {
				// データベースエラーの場合、処理結果を取り消す
				if (con != null) {
					con.rollback();
				}
			} catch (SQLException ex) {
				// ロールバック時にエラーが発生しても、システムエラーとして通知する
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

	/**
	 * 得意先コードを生成する。
	 *
	 * @param custCode
	 *            得意先コードの数値部分
	 * @return 得意先コード
	 */
	private String createCustomerCode(int custCode) {
		return String.format("KA%04d", custCode);
	}
}

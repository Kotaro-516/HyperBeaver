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

/**
 * 得意先登録の業務ロジッククラス。
 */
public class CustomerRegistLogic {
    private static final String SYSTEM_ERROR_MESSAGE =
            "システムエラーが発生しました。システム管理者に連絡してください。";

    /**
     * 得意先を登録する。
     *
     * @param customer 登録対象の得意先情報
     * @return 得意先コードを設定した登録済み得意先情報
     * @throws SalesBusinessException 電話番号が登録済みの場合
     * @throws SalesSystemException DB処理等でシステムエラーが発生した場合
     */
    public Customer registCustomer(Customer customer)
            throws SalesBusinessException, SalesSystemException {
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
            con.setAutoCommit(false);

            CustomerDAO customerDAO = new CustomerDAO(con);
            CustomerNumberingDAO numberingDAO = new CustomerNumberingDAO(con);

            // 電話番号の重複確認
            Customer duplicateCustomer = customerDAO.findCustomerByTelNo(customer.getTelNo());
            if (duplicateCustomer != null) {
                con.rollback();
                throw new SalesBusinessException("同一電話番号の得意先は既に登録されています。");
            }

            // 採番情報の取得と得意先コードの生成
            CustomerNumbering numbering = numberingDAO.findCustomerCode();
            if (numbering == null) {
                con.rollback();
                throw new SalesSystemException(SYSTEM_ERROR_MESSAGE);
            }
            int nextCode = numbering.getCustCode() + 1;
            String customerCode = createCustomerCode(nextCode);
            customer.setCustCode(customerCode);
            customer.setDeleteFlag(false);

            // 得意先登録と採番情報更新は同一トランザクションで実行する
            if (!customerDAO.insertCustomer(customer)) {
                con.rollback();
                throw new SalesSystemException(SYSTEM_ERROR_MESSAGE);
            }
            numbering.setCustCode(nextCode);
            if (!numberingDAO.updateCustomerCode(numbering)) {
                con.rollback();
                throw new SalesSystemException(SYSTEM_ERROR_MESSAGE);
            }

            con.commit();
            return customer;
        } catch (SalesBusinessException | SalesSystemException e) {
            throw e;
        } catch (SQLException e) {
            rollbackQuietly(con);
            throw new SalesSystemException(SYSTEM_ERROR_MESSAGE);
        } finally {
            closeConnection(con);
        }
    }

    /**
     * 数値の採番値から得意先コードを生成する。
     */
    private String createCustomerCode(int custCode) throws SalesSystemException {
        if (custCode < 1 || custCode > 9999) {
            throw new SalesSystemException(SYSTEM_ERROR_MESSAGE);
        }
        return String.format("KA%04d", custCode);
    }

    private void rollbackQuietly(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException e) {
                // 元のDB例外をシステム例外として上位へ通知するため、ここでは再送出しない。
            }
        }
    }

    private void closeConnection(Connection con) throws SalesSystemException {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new SalesSystemException(SYSTEM_ERROR_MESSAGE);
            }
        }
    }
}

/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * CustomerNumbering.java
 *
 */
package jsys.sales.entity;

import java.io.Serializable;

/**
 * 得意先採番情報を保持するエンティティクラス。
 */
public class CustomerNumbering implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 最後に発行した得意先コードの数値部分 */
    private int custCode;

    public CustomerNumbering() {
    }

    public CustomerNumbering(int custCode) {
        this.custCode = custCode;
    }

    public int getCustCode() {
        return custCode;
    }

    public void setCustCode(int custCode) {
        this.custCode = custCode;
    }
}

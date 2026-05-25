/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * Customer.java
 *
 */
package jsys.sales.entity;

import java.io.Serializable;

public class Customer implements Serializable{

	/** 得意先コード */
	private String custCode;

	/** 得意先名 */
	private String custName;

	/** 電話番号 */
	private String telNo;

	/** 郵便番号 */
	private String postalCode;

	/** 住所 */
	private String address;

	/** 割引率 */
	private int discountRate;

	/**
	 * コンストラクタ（引数なし）
	 */
	public Customer() {
	}

	/**
	 * コンストラクタ:引数で指定した値を設定する。
	 * @param custCode 得意先コード
	 * @param custName 得意先名
	 * @param telNo 電話番号
	 * @param postalCode 郵便番号
	 * @param address 住所
	 * @param discountRate 割引率
	 */
	public Customer(String custCode, String custName, String telNo,
			String postalCode, String address,int discountRate) {
		this.custCode = custCode;
		this.custName = custName;
		this.telNo = telNo;
		this.postalCode = postalCode;
		this.address = address;
		this.discountRate = discountRate;
	}

	/**
	 * 得意先コードを取得する。
	 * @return 得意先コード
	 */
	public String getCustCode() {
		return custCode;
	}
	/**
	 * 得意先コードを設定する。
	 * @param custCode 得意先コード
	 */
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	/**
	 * 得意先名を取得する。
	 * @return 得意先名
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * 得意先名を設定する。
	 * @param custName 得意先名
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * 電話番号を取得する。
	 * @return 電話番号
	 */
	public String getTelNo() {
		return telNo;
	}
	/**
	 * 電話番号を設定する。
	 * @param telNo 電話番号
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	/**
	 * 郵便番号を取得する。
	 * @return 郵便番号
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * 郵便番号を設定する。
	 * @param postalCode 郵便番号
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * 住所を取得する。
	 * @return 住所
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 住所を設定する。
	 * @param address 住所
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 割引率を取得する。
	 * @return 割引率
	 */
	public int getDiscountRate() {
		return discountRate;
	}
	/**
	 * 割引率を設定する。
	 * @param discountRate 割引率
	 */
	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}
}
/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * Employee.java
 *
 */
package jsys.sales.entity;

import java.io.Serializable;

public class Employee implements Serializable{

	/** 従業員番号 */
	private String empNo;

	/** 従業員名 */
	private String empName;

	/** パスワード */
	private String password;

	/**
	 * コンストラクタ（引数なし）
	 */
	public Employee() {
	}

	/**
	 * @param empNo
	 * @param empName
	 * @param password
	 */
	public Employee(String empNo, String empName, String password) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.password = password;
	}

	/**
	 * empNoのGetter
	 * @return empNo
	 */
	public String getEmpNo() {
		return empNo;
	}

	/**
	 * empNoのSetter
	 * @param empNo
	 */
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	/**
	 * empNameのGetter
	 * @return empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * empNameのSetter
	 * @param empName
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * passwordのGetter
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * passwordのSetter
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}



}

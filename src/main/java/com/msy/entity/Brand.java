package com.msy.entity;

import java.io.Serializable;
import java.util.Date;

public class Brand implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	/** 品牌名称 */
	private String name;

	/** 厂商联系人 */
	private String person_in_charge;

	/** 联系电话 */
	private String telephone;

	/** 厂家地址 */
	private String address;

	/** 添加时间 */
	private Date create_date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPerson_in_charge() {
		return person_in_charge;
	}

	public void setPerson_in_charge(String person_in_charge) {
		this.person_in_charge = person_in_charge;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	@Override
	public String toString() {
		return "Brand{" +
				"id=" + id +
				", name='" + name + '\'' +
				", person_in_charge='" + person_in_charge + '\'' +
				", telephone='" + telephone + '\'' +
				", address='" + address + '\'' +
				", create_date=" + create_date +
				'}';
	}
}

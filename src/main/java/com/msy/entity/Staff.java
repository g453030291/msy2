package com.msy.entity;

import java.util.Date;

public class Staff {

	private Integer id;

	private Integer client_id;

	private String name;

	private String telephone;

	private Integer age;

	private String sex;

	private Date birthday;

	private String work_city;

	private String address;
	/** 状态(在职/离职) */
	private String state;

	private Date create_date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClient_id() {
		return client_id;
	}

	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getWork_city() {
		return work_city;
	}

	public void setWork_city(String work_city) {
		this.work_city = work_city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
}

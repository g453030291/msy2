package com.msy.entity;

import java.util.Date;

public class Repo {

	private Integer id;

	private String name;

	private String province;

	private String city;

	private String district;

	private String address;

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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
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
		return "Repo{" +
				"id=" + id +
				", name='" + name + '\'' +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", district='" + district + '\'' +
				", address='" + address + '\'' +
				", create_date=" + create_date +
				'}';
	}
}

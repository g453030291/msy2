package com.msy.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Goods {

	private Integer id;
	private String oil_type;
	private Integer brand_id;
	private String brand_name;
	private String goods_name;
	private String level_name;
	private String model_name;
	private String guige_name;
	private BigDecimal price;
	private String state;
	private String img1;
	private Date create_date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOil_type() {
		return oil_type;
	}

	public void setOil_type(String oil_type) {
		this.oil_type = oil_type;
	}

	public Integer getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getLevel_name() {
		return level_name;
	}

	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}

	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public String getGuige_name() {
		return guige_name;
	}

	public void setGuige_name(String guige_name) {
		this.guige_name = guige_name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	@Override
	public String toString() {
		return "Goods{" +
				"id=" + id +
				", oil_type='" + oil_type + '\'' +
				", brand_id=" + brand_id +
				", brand_name='" + brand_name + '\'' +
				", goods_name='" + goods_name + '\'' +
				", level_name='" + level_name + '\'' +
				", model_name='" + model_name + '\'' +
				", guige_name='" + guige_name + '\'' +
				", price=" + price +
				", state='" + state + '\'' +
				", img1='" + img1 + '\'' +
				", create_date=" + create_date +
				'}';
	}
}

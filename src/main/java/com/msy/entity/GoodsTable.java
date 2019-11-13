package com.msy.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GoodsTable implements Serializable {

	private static final long serialVersionUID = 1L;

	/** id */
	private Integer id;

	/** 商品名称 */
	private String name;

	/** 级别名称 */
	private String level_name;

	/** 型号名称 */
	private String model_name;

	/** 规格名称 */
	private String guige_name;

	/** 单价 */
	private BigDecimal price;

	/** 油品类型 */
	private String oil_type;

	/** 品牌id */
	private Integer brand_id;

	/** 品牌名称 */
	private String brand_name;

	/** 上架/下架 */
	private String state;

	/** base64图片 */
	private String img1;

	/** 创建时间 */
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
		return "GoodsTable{" +
				"id=" + id +
				", name='" + name + '\'' +
				", level_name='" + level_name + '\'' +
				", model_name='" + model_name + '\'' +
				", guige_name='" + guige_name + '\'' +
				", price=" + price +
				", oil_type='" + oil_type + '\'' +
				", brand_id=" + brand_id +
				", brand_name='" + brand_name + '\'' +
				", state='" + state + '\'' +
				", img1='" + img1 + '\'' +
				", create_date=" + create_date +
				'}';
	}
}

package com.msy.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@ToString
public class OrderGoods {

	private Integer id;

	private String brand_name;

	private String goods_name;

	private String level_name;

	private String model_name;

	private String guige_name;

	private Integer order_id;

	private Integer goods_id;

	private Integer buy_count;

	private BigDecimal goods_price;

	private BigDecimal total_money;

	private Date create_date;
}

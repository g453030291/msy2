package com.msy.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 微信购物车专用分页传输类
 */
@Getter
@Setter
@ToString
public class GoodsDTO4 {

	private Integer id;
	private String goods_name;
	private String brand_name;
	private String guige_name;
	private Integer num;
	private BigDecimal price;
	private String img1;
}

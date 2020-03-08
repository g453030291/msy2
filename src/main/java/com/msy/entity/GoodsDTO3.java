package com.msy.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 微信专用分页传输类
 */
@Getter
@Setter
@ToString
public class GoodsDTO3 {

	private Integer id;
	private String brand_name;
	private String goods_name;
//	private String level_name;
//	private String model_name;
	private String guige_name;
	private BigDecimal price;
	private String img1;
}

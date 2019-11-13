package com.msy.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class GoodsDTO {

	private String telephone;

	private Integer order_id;

	private Integer goods_id;

	private Integer buy_count;

	private BigDecimal goods_price;
}

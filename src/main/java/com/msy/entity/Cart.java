package com.msy.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 购物车
 */
@Getter
@Setter
@ToString
public class Cart {

	private Integer id;

	private Integer client_id;

	private Integer goods_id;

	private Integer num;
}

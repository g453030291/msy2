package com.msy.entity;

import java.math.BigDecimal;

public class AddStockResult {

	/**
	 * 进货表id
	 */
	private Integer stockId;

	/**
	 * 进货-商品中间表id
	 */
	private Integer stockGoodsId;

	/**
	 * 总价
	 */
	private BigDecimal total_price;

	private Goods goods;

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public Integer getStockGoodsId() {
		return stockGoodsId;
	}

	public void setStockGoodsId(Integer stockGoodsId) {
		this.stockGoodsId = stockGoodsId;
	}

	public BigDecimal getTotal_price() {
		return total_price;
	}

	public void setTotal_price(BigDecimal total_price) {
		this.total_price = total_price;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
}

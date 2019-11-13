package com.msy.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Stock {

	private Integer id;

	private Integer repo_id;

	/**
	 * 仓库名称
	 */
	private String repo_name;

	/**
	 * 进货总价
	 */
	private BigDecimal money;

	/**
	 * 进货时间
	 */
	private Date stock_date;

	/**
	 * 创建时间
	 */
	private Date create_date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRepo_id() {
		return repo_id;
	}

	public void setRepo_id(Integer repo_id) {
		this.repo_id = repo_id;
	}

	public String getRepo_name() {
		return repo_name;
	}

	public void setRepo_name(String repo_name) {
		this.repo_name = repo_name;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Date getStock_date() {
		return stock_date;
	}

	public void setStock_date(Date stock_date) {
		this.stock_date = stock_date;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
}

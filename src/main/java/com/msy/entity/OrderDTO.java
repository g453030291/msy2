package com.msy.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderDTO {

	private String brand_name;

	private String level_name;

	private String model_name;

	private String guige_name;

	private Integer buy_count;

	private BigDecimal price;

	private Integer repo_id;

	private String telephone;

	private String pay_state;

	private String pay_type;

	private Date order_date;

	private Integer send_id;

	private BigDecimal money;

	private Date send_date;

	private Date pay_date;

	private String is_arrears;

	private BigDecimal arrears_money;

	private Date arrears_date;

}

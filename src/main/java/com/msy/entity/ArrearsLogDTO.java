package com.msy.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@ToString
public class ArrearsLogDTO {

	private Integer id;

	private Integer order_id;

	private BigDecimal arrears_money;

	private String pay_type;

	private String arrears_date;

}

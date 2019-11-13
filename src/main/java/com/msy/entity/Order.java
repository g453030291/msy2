package com.msy.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@ToString
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	/** 用户id */
	private Integer client_id;

	/** 用户姓名 */
	private String client_name;

	/** 商铺名称 */
	private String store_name;

	/** 用户联系方式 */
	private String telephone;

	/** 配送员工id */
	private Integer send_id;

	/** 配送员工名称 */
	private String send_name;

	/** 订单金额 */
	private BigDecimal money;

	/** 下单时间 */
	private Date order_date;

	/** 配送到货时间 */
	private Date send_date;

	/** 支付时间 */
	private Date pay_date;

	/** 支付状态(未支付,已支付) */
	private String pay_state;

	/** 支付方式(现金,微信,支付宝,银联) */
	private String pay_type;

	/** 是否有欠款(是/否) */
	private String is_arrears;

	/** 欠款金额 */
	private BigDecimal arrears_money;

	/** 催款时间 */
	private Date arrears_date;

	/** 还款时间 */
	private Date to_arrears_date;

	/** 出货仓库id */
	private Integer repo_id;

	/** 出货仓库名称 */
	private String repo_name;

	private Date create_date;

}

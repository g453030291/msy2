package com.msy.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @author gms
 */
@Getter
@Setter
@ToString
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	/** 用户姓名 */
	private String name;
	/** 性别 */
	private String sex;
	/** 联系方式1 */
	private String telephone1;
	/** 联系方式2 */
	private String telephone2;
	/** 联系方式3 */
	private String telephone3;
	/** 商户名称 */
	private String store_name;
	/** 商户地址 */
	private String address;
	/** 实名认证(已认证,未认证) */
	private String verified;
	/** 用户等级 */
	private Integer level;
	/** 添加时间 */
	private Date create_date;
	/** 语言 */
	private String language;
	/** 国家 */
	private String country;
	/** 省份 */
	private String province;
	/** 城市 */
	private String city;
	/** 区县 */
	private String district;

	private String img1;

	private String img2;

	private String img3;

	private String img4;

	private String img5;

	private String access_token;

	private Long expires_in;

	private String refresh_token;

	private String openid;

	private String scope;

	private String nickname;

	private String headimgurl;
	/** 用户特权信息 */
	private String privilege;
	/** 省份 */
	private String send_province;
	/** 城市 */
	private String send_city;
	/** 区县 */
	private String send_district;

	private String id_card;
}

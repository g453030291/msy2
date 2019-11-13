package com.msy.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @author gms
 */
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getTelephone3() {
		return telephone3;
	}

	public void setTelephone3(String telephone3) {
		this.telephone3 = telephone3;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getImg3() {
		return img3;
	}

	public void setImg3(String img3) {
		this.img3 = img3;
	}

	public String getImg4() {
		return img4;
	}

	public void setImg4(String img4) {
		this.img4 = img4;
	}

	public String getImg5() {
		return img5;
	}

	public void setImg5(String img5) {
		this.img5 = img5;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	@Override
	public String toString() {
		return "Client{" +
				"id=" + id +
				", name='" + name + '\'' +
				", sex='" + sex + '\'' +
				", telephone1='" + telephone1 + '\'' +
				", telephone2='" + telephone2 + '\'' +
				", telephone3='" + telephone3 + '\'' +
				", store_name='" + store_name + '\'' +
				", address='" + address + '\'' +
				", verified='" + verified + '\'' +
				", level=" + level +
				", create_date=" + create_date +
				", country='" + country + '\'' +
				", language='" + language + '\'' +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", district='" + district + '\'' +
				", img1='" + img1 + '\'' +
				", img2='" + img2 + '\'' +
				", img3='" + img3 + '\'' +
				", img4='" + img4 + '\'' +
				", img5='" + img5 + '\'' +
				", access_token='" + access_token + '\'' +
				", expires_in=" + expires_in +
				", refresh_token='" + refresh_token + '\'' +
				", openid='" + openid + '\'' +
				", scope='" + scope + '\'' +
				", nickname='" + nickname + '\'' +
				", headimgurl='" + headimgurl + '\'' +
				", privilege='" + privilege + '\'' +
				'}';
	}
}

package com.msy.entity.wx;

import com.google.gson.Gson;

import java.util.List;

/**
 * {"openid":"ouJlHwTKKYaqAum6gNoFybPCk_3Q","nickname":"好","sex":1,"language":"zh_CN","city":"阳泉","province":"山西","country":"中国","url":"http:\/\/thirdwx.qlogo.cn\/mmopen\/vi_32\/Q0j4TwGTfTK2QVa1yomApMVxIHUrB8ibIribd6elLTVPAFiaZiborKbDLRpFtcxMNlnE8NRZzwpzQHiaeoQmE3AdicxQ\/132","privilege":[]}
 *
 * {"openid":"ouJlHwTKKYaqAum6gNoFybPCk_3Q","nickname":"好","sex":1,"language":"zh_CN","city":"阳泉","province":"山西","country":"中国","url":"http:\/\/thirdwx.qlogo.cn\/mmopen\/vi_32\/Q0j4TwGTfTK2QVa1yomApMVxIHUrB8ibIribd6elLTVPAFiaZiborKbDLRpFtcxMNlnE8NRZzwpzQHiaeoQmE3AdicxQ\/132","privilege":[]}
 */
public class WxUserInfo {

	/**
	 * 用户的唯一标识
	 */
	private String openid;

	/**
	 * 用户昵称
	 */
	private String nickname;

	/**
	 * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 */
	private Integer sex;

	/**
	 * 语言
	 */
	private String language;

	/**
	 * 普通用户个人资料填写的城市
	 */
	private String city;

	/**
	 * 用户个人资料填写的省份
	 */
	private String province;

	/**
	 * 国家，如中国为CN
	 */
	private String country;

	/**
	 * 用户头像
	 */
	private String headimgurl;

	/**
	 * 用户特权信息，json 数组
	 */
	private List<String> privilege;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public List<String> getPrivilege() {
		return privilege;
	}

	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}

	@Override
	public String toString() {
		return "WxUserInfo{" +
				"openid='" + openid + '\'' +
				", nickname='" + nickname + '\'' +
				", sex=" + sex +
				", language='" + language + '\'' +
				", city='" + city + '\'' +
				", province='" + province + '\'' +
				", country='" + country + '\'' +
				", headimgurl='" + headimgurl + '\'' +
				", privilege=" + privilege +
				'}';
	}
}

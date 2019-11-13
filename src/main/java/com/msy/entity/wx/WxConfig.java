package com.msy.entity.wx;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wx")
public class WxConfig {

	/**
	 * 开发者ID(AppID)
	 */
	private String appid;

	/**
	 * 开发者密码(AppSecret)
	 */
	private String appsecret;

	/**
	 * 回调地址
	 */
	private String redirecturi;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getRedirecturi() {
		return redirecturi;
	}

	public void setRedirecturi(String redirecturi) {
		this.redirecturi = redirecturi;
	}
}

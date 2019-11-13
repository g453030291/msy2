package com.msy.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.msy.entity.wx.AccessToken;
import com.msy.entity.wx.ErrorResult;
import com.msy.entity.wx.WxConfig;
import com.msy.entity.wx.WxUserInfo;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class WxUtil {

	private static Gson gson = new GsonBuilder().create();

	private static OkHttpClient client = new OkHttpClient();

	/**
	 * 根据用户授权返回code获取access_token
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 * @throws IOException
	 */
	public AccessToken getAccessToken(String appid,String secret,String code) throws IOException {
		Request request = new Request.Builder()
				.url("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code")
				.build();
		AccessToken accessToken = null;
		try (Response response = client.newCall(request).execute()){
			try {
				accessToken = gson.fromJson(response.body().string(), AccessToken.class);
			}catch (JsonSyntaxException e){
				log.error(response.body().string());
			}
		}
		return accessToken;
	}

	/**
	 * 刷新access_token
	 * @param appid
	 * @param token
	 * @return
	 * @throws IOException
	 */
	public AccessToken refreshAccessToken(String appid,String token) throws IOException{
		Request request = new Request.Builder()
				.url("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+appid+"&grant_type=refresh_token&refresh_token="+token)
				.build();
		AccessToken accessToken = null;
		try (Response response = client.newCall(request).execute()){
			String result = response.body().string();
			accessToken = gson.fromJson(result, AccessToken.class);
		}
		return accessToken;
	}

	/**
	 * 获取用户信息
	 * @param token
	 * @param openId
	 * @return
	 * @throws IOException
	 */
	public WxUserInfo getUserInfo(String token,String openId) throws IOException{
		Request request = new Request.Builder()
				.url("https://api.weixin.qq.com/sns/userinfo?access_token="+token+"&openid="+openId+"&lang=zh_CN")
				.build();
		WxUserInfo info = null;
		try (Response response = client.newCall(request).execute()){
			String result = response.body().string();
			if(result.contains("invalid openid") || result.contains("errcode") || result.contains("40003") || result.contains("errmsg")){
				log.error(result);
				return null;
			}
			info = gson.fromJson(result, WxUserInfo.class);
		}
		return info;
	}

	/**
	 * 验证access_token是否有效
	 * @param token
	 * @param openId
	 * @return
	 * @throws IOException
	 */
	public boolean testAccessToken(String token,String openId) throws IOException {
		Request request = new Request.Builder()
				.url("https://api.weixin.qq.com/sns/auth?access_token="+token+"&openid="+openId)
				.build();
		boolean flag = false;
		try (Response response = client.newCall(request).execute()){
			String result = response.body().string();
			ErrorResult errorResult = gson.fromJson(result, ErrorResult.class);
			if(errorResult.getErrcode()==0 && "ok".equals(errorResult.getErrmsg())){
				flag = true;
			}
		}
		return flag;
	}

}

package com.msy.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.msy.entity.Client;
import com.msy.entity.wx.AccessToken;
import com.msy.entity.wx.WxConfig;
import com.msy.entity.wx.WxUserInfo;
import com.msy.service.ClientService;
import com.msy.util.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/wx")
public class WxController {

	@Autowired
	private WxConfig wxConfig;
	
	@Autowired
	private WxUtil wxUtil;

	@Autowired
	private ClientService clientService;

//	@RequestMapping("/getBaseUserAuth.req")
//	public String wxUserBaseAuthorization(){
//		String url = null;
//		try {
//			url = URLEncoder.encode(wxConfig.getRedirecturi(),"utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		String URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+wxConfig.getAppid()+"&redirect_uri="+url+"&response_type=code&scope=snsapi_base&state=state#wechat_redirect";
//		return "redirect:"+URL;
//	}

	@RequestMapping("/getUserAuth.req")
	public String wxUserAuthorization(){
		String url = null;
		try {
			url = URLEncoder.encode(wxConfig.getRedirecturi(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+wxConfig.getAppid()+"&redirect_uri="+url+"&response_type=code&scope=snsapi_userinfo&state=state#wechat_redirect";
		return "redirect:"+URL;
	}

	@RequestMapping("/toIndex.page")
	public String toIndexPage(HttpServletRequest request,HttpServletResponse response,
							  String code,String state) throws IOException {
		System.out.println("code:"+code);
		System.out.println("state:"+state);
		Cookie cookie = null;
		Client client = null;
		AccessToken accessToken = wxUtil.getAccessToken(wxConfig.getAppid(), wxConfig.getAppsecret(), code);
		if(accessToken==null){
			//access token获取失败
			return null;
		}
		//根据access token查询用户是否为注册用户
		client = clientService.findClientByToken(accessToken);
		if(client==null){
			//数据库中没有该用户,此用户为第一次访问
			WxUserInfo userInfo = wxUtil.getUserInfo(accessToken.getAccess_token(), accessToken.getOpenid());
			if(userInfo!=null){
				//成功获取到用户信息,保存到数据库
				Client saveClient = new Client();
				saveClient.setAccess_token(accessToken.getAccess_token());
				saveClient.setExpires_in(accessToken.getExpires_in());
				saveClient.setRefresh_token(accessToken.getRefresh_token());
				saveClient.setOpenid(accessToken.getOpenid());
				saveClient.setScope(accessToken.getScope());
				saveClient.setNickname(userInfo.getNickname());
				if(userInfo.getSex()==0){ saveClient.setSex("未知"); }
				if(userInfo.getSex()==1){ saveClient.setSex("男"); }
				if(userInfo.getSex()==2){ saveClient.setSex("女"); }
				saveClient.setProvince(userInfo.getProvince());
				saveClient.setCity(userInfo.getCity());
				saveClient.setCountry(userInfo.getCountry());
				saveClient.setHeadimgurl(userInfo.getHeadimgurl());
				saveClient.setPrivilege(userInfo.getPrivilege().toString());
				clientService.addClient(saveClient);
			}
			cookie = new Cookie("openid",accessToken.getOpenid());
		}else{
			//数据库中有该用户,不是新用户
			cookie = new Cookie("openid",client.getOpenid());
		}
		response.addCookie(cookie);
		return "/wx/index";
	}

	@RequestMapping("/toOrder.page")
	public String toOrderPage(String queryType){
		System.out.println(queryType);
		return "/wx/order";
	}

	@RequestMapping("/toMy.page")
	public String toMyPage(){
		return "/wx/my";
	}

	@RequestMapping("/toRegister.page")
	public String toRegisterPage(){
		return "/wx/register";
	}

	@RequestMapping("/toShopping.page")
	public String toShoppingPage(){
		return "/wx/shopping";
	}

	@RequestMapping("/toCart.page")
	public String toCartPage(){
		return "/wx/cart";
	}


}

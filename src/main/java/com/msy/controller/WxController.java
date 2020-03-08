package com.msy.controller;

import com.msy.entity.*;
import com.msy.entity.wx.AccessToken;
import com.msy.entity.wx.WxConfig;
import com.msy.entity.wx.WxPageUtil;
import com.msy.entity.wx.WxUserInfo;
import com.msy.service.CartService;
import com.msy.service.ClientService;
import com.msy.service.GoodsService;
import com.msy.service.OrderService;
import com.msy.util.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/wx")
public class WxController extends BaseController {

	private static final String OPENID = "ouJlHwTKKYaqAum6gNoFybPCk_3Q";

	@Autowired
	private WxConfig wxConfig;
	
	@Autowired
	private WxUtil wxUtil;

	@Autowired
	private ClientService clientService;

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;

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

	@Transactional(rollbackFor = Exception.class)
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
	public String toOrderPage(HttpServletRequest request,Model model,String queryType){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Client client = null;
		String openid = getOpenID(request);
		if(!StringUtils.isEmpty(openid)){
			client = clientService.findClientByOpenId(openid);
		}
		List<Order> orders = null;
		if(client != null && "all".equals(queryType)){
			orders = orderService.findOrderByClientId(client.getId());
			for (Order o:orders){
				//repo_name代替order_date,向页面展示格式化过的时间
				o.setRepo_name(sdf.format(o.getOrder_date()));
			}
		}
		model.addAttribute("orders",orders);
		return "/wx/order";
	}

	@RequestMapping("/toMy.page")
	public String toMyPage(HttpServletRequest request, Model model){
		Client client = new Client();
		String openid = getOpenID(request);
		if(!StringUtils.isEmpty(openid)){
			client = clientService.findClientByOpenId(openid);
		}
		model.addAttribute("client",client);
		return "/wx/my";
	}

	@RequestMapping("/toRegister.page")
	public String toRegisterPage(HttpServletRequest request, Model model){
		Client client = new Client();
		String openid = getOpenID(request);
		if(!StringUtils.isEmpty(openid)){
			client = clientService.findClientByOpenId(openid);
		}
		model.addAttribute("client",client);
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

	@ResponseBody
	@RequestMapping("/getCartGoods.json")
	public WxPageUtil<List<GoodsDTO4>> getCartGoods(HttpServletRequest request, WxPageUtil wxPageUtil){
		String openid = getOpenID(request);
		if(StringUtils.isEmpty(openid)){
			return null;
		}
		WxPageUtil<List<GoodsDTO4>> goodsDTO4s = cartService.listCartGoods(openid,wxPageUtil);
		return goodsDTO4s;
	}

	@ResponseBody
	@RequestMapping("/getBranByOilType.json")
	public List<Goods> getBranByOilType(String oilType){
		List<Goods> goodsList = goodsService.listGoodsByOilType(oilType);
		return goodsList;
	}

	@ResponseBody
	@RequestMapping("/getLevelByOilTypeAndBrand.json")
	public List<Goods> getLevelByOilTypeAndBrand(String oilType,String brandVal){
		List<Goods> goodsList = goodsService.getLevelByOilTypeAndBrand(oilType,brandVal);
		return goodsList;
	}

	@ResponseBody
	@RequestMapping("/getLevelByOilTypeAndBrandAndLevel.json")
	public List<Goods> getLevelByOilTypeAndBrandAndLevel(String oilType,String brandVal,String oilLevelVal){
		List<Goods> goodsList = goodsService.getLevelByOilTypeAndBrandAndLevel(oilType,brandVal,oilLevelVal);
		return goodsList;
	}

	@ResponseBody
	@RequestMapping("/getGoodsByGoodsDTO2.json")
	public WxPageUtil<List<GoodsDTO3>> getGoodsByGoodsDTO2(WxPageUtil wxPageUtil, GoodsDTO2 goodsDTO2){
		WxPageUtil<List<GoodsDTO3>> page = goodsService.pageGoodsByGoodsDTO2(wxPageUtil,goodsDTO2);
		return page;
	}

	@ResponseBody
	@RequestMapping("/addCart.json")
	@Transactional(rollbackFor = Exception.class)
	public boolean addCart(HttpServletRequest request,Integer num,Integer id){
		String openid = getOpenID(request);
		if(StringUtils.isEmpty(openid)){
			return false;
		}
		//先查询本人购物车内有没有相同goods_id的商品
		Cart cart = cartService.countGoodsByGoodsId(openid,id);
		Integer count = 0;
		if(cart != null){
			//这里不需要更改购物车总价,购物车总价是计算出来的,购物车表并没有这个字段
			//添加商品数量,价格
			count = cartService.addCartGoodsCount(cart.getId(),num);
		}else {
			//添加购物车
			count = cartService.addCart(openid,num,id);
		}
		return count > 0;
	}

	@ResponseBody
	@RequestMapping("/deleteCart.json")
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteCart(HttpServletRequest request,Integer id){
		String openid = getOpenID(request);
		if(StringUtils.isEmpty(openid)){
			return false;
		}
		Integer count = cartService.deleteCart(id);
		return count > 0;
	}

	@ResponseBody
	@RequestMapping("/addOrder.json")
	@Transactional(rollbackFor = Exception.class)
	public String addOrder(HttpServletRequest request){
		String openid = getOpenID(request);
		if(StringUtils.isEmpty(openid)){
			return null;
		}
		//判断实名认证开关是否开启
		Integer verified = clientService.findVerifiedState();
		Client client = null;
		if(verified == 1){
			//如果设定为1,表示当前系统需要进行实名认证才可以下单,
			//这时候需要去查询当前用户实名状态是否是已认证
			client = clientService.findClientByOpenId(openid);
			//判断是否实名认证
			if(!"已认证".equals(client.getVerified())){
				return "尚未实名认证,无法下单!";
			}
			if(StringUtils.isEmpty(client.getTelephone1())){
				return "用户手机号为空,无法下单!";
			}
		}else if(verified == 0){
			//实名开关关闭,用户未经实名认证也可以下单
		}
		List<Cart> carts = cartService.listByClientId(client.getId());
		if(carts.size() <= 0){
			return "请先添加商品!";
		}
		//查询出订单总价
		BigDecimal totalMoney = cartService.cartTotalMoneyByClientId(client.getId());
		//查询所有订单商品
		List<Goods> cartGoods = goodsService.cartGoodsByIds(carts);
		//添加订单
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setMoney(totalMoney);
		orderDTO.setTelephone(client.getTelephone1());
		orderDTO.setPay_state("未支付");
		orderDTO.setIs_arrears("是");
		orderDTO.setArrears_money(totalMoney);
		Integer order_id = orderService.addOrder(orderDTO);
		//添加订单-商品中间表
		List<GoodsDTO> goodsDTOS = new ArrayList<>(cartGoods.size());
		for (Goods goods:cartGoods){
			GoodsDTO g = new GoodsDTO();
			g.setOrder_id(order_id);
			g.setTelephone(client.getTelephone1());
			g.setGoods_id(goods.getId());
			g.setGoods_price(goods.getPrice());
			for (Cart c: carts){
				if (c.getGoods_id().equals(goods.getId())){
					g.setBuy_count(c.getNum());
				}
			}
			goodsDTOS.add(g);
		}
		orderService.addOrderGoods(goodsDTOS);
		//清空购物车
		cartService.deleteCarts(carts);
		return "下单成功";
	}

	@ResponseBody
	@RequestMapping("/registerClient.json")
	@Transactional(rollbackFor = Exception.class)
	public String registerClient(HttpServletRequest request,Client registerClient){
		String openid = getOpenID(request);
		if(StringUtils.isEmpty(openid)){
			return "资料提交失败!";
		}
		Client client = clientService.findClientByOpenId(openid);
		if(client == null){
			return "资料提交失败!";
		}
		boolean result = clientService.updateRegisterClient(registerClient,client.getId());
		if(result){
			return "资料上传成功!";
		}else {
			return "资料提交失败!";
		}
	}

	@ResponseBody
	@RequestMapping("/upload.img")
	@Transactional(rollbackFor = Exception.class)
	public boolean uploadImg(HttpServletRequest request,String fileVal){
		String openid = getOpenID(request);
		if(StringUtils.isEmpty(openid)){
			return false;
		}
		Client client = clientService.findClientByOpenId(openid);
		if(client==null){
			return false;
		}
		if(StringUtils.isEmpty(client.getImg1())){
			clientService.addImg1(fileVal,client.getId());
			return true;
		}
		if(StringUtils.isEmpty(client.getImg2())){
			clientService.addImg2(fileVal,client.getId());
			return true;
		}
		if(StringUtils.isEmpty(client.getImg3())){
			clientService.addImg3(fileVal,client.getId());
			return true;
		}
		if(StringUtils.isEmpty(client.getImg4())){
			clientService.addImg4(fileVal,client.getId());
			return true;
		}
		if(StringUtils.isEmpty(client.getImg5())){
			clientService.addImg5(fileVal,client.getId());
			return true;
		}
		return false;
	}

}

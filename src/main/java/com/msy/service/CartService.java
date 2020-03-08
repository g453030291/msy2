package com.msy.service;

import com.msy.dao.CartDao;
import com.msy.dao.ClientDao;
import com.msy.entity.Cart;
import com.msy.entity.Client;
import com.msy.entity.GoodsDTO4;
import com.msy.entity.wx.WxPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {

	@Autowired
	private CartDao cartDao;

	@Autowired
	private ClientDao clientDao;

	public Integer addCart(String openid, Integer num, Integer id) {
		Client client = clientDao.findClientByOpenID(openid);
		return cartDao.addCart(client.getId(),num,id);
	}

	public WxPageUtil<List<GoodsDTO4>> listCartGoods(String openid, WxPageUtil wxPageUtil) {
		wxPageUtil.setData(openid);
		wxPageUtil.setNum((wxPageUtil.getNum()-1)*wxPageUtil.getSize());
		List<GoodsDTO4> goodsDTO4s = cartDao.listCartGoodsByOpenID(wxPageUtil);
		Integer count = cartDao.countCartGoodsByOpenID(wxPageUtil);
		WxPageUtil<List<GoodsDTO4>> page = new WxPageUtil<>();
		page.setNum(wxPageUtil.getNum());
		page.setSize(wxPageUtil.getSize());
		page.setData(goodsDTO4s);
		page.setTotalSize(count);
		return page;
	}

	public Integer deleteCart(Integer cart_id) {
		return cartDao.deleteCart(cart_id);
	}

	public Cart countGoodsByGoodsId(String openid, Integer id) {
		Client client = clientDao.findClientByOpenID(openid);
		return cartDao.countGoodsByGoodsId(client.getId(),id);
	}

	public Integer addCartGoodsCount(Integer cart_id, Integer num) {
		return cartDao.addCartGoodsCount(cart_id,num);
	}

	public List<Cart> listByClientId(Integer id) {
		return cartDao.listByClientId(id);
	}

	public void deleteCarts(List<Cart> carts) {
		cartDao.deleteCarts(carts);
	}

	public BigDecimal cartTotalMoneyByClientId(Integer id) {
		return cartDao.cartTotalMoneyByClientId(id);
	}
}

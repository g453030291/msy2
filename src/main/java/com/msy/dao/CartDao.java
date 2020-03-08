package com.msy.dao;

import com.msy.entity.Cart;
import com.msy.entity.GoodsDTO4;
import com.msy.entity.wx.WxPageUtil;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CartDao {

	@Insert("insert into cart(client_id,goods_id,num) values(#{0},#{2},#{1})")
	Integer addCart(Integer client_id,Integer num, Integer id);

	@Select("select c.id,g.goods_name,g.guige_name,g.brand_name,c.num,(g.price * c.num) price,g.img1 from goods g,cart c,client t where g.id = c.goods_id and c.client_id = t.id and g.state = '上架' and t.openid = #{data} limit ${num},${size}")
	List<GoodsDTO4> listCartGoodsByOpenID(WxPageUtil wxPageUtil);

	@Select("select count(*) from goods g,cart c,client t where g.id = c.goods_id and c.client_id = t.id and g.state = '上架' and t.openid = #{data}")
	Integer countCartGoodsByOpenID(WxPageUtil wxPageUtil);

	@Delete("delete from cart where id = #{0}")
	Integer deleteCart(Integer cart_id);

	@Select("select * from cart where client_id = #{0} and goods_id = #{1}")
	Cart countGoodsByGoodsId(Integer client_id, Integer id);

	@Update("update cart set num = (num + #{1}) where id = #{0}")
	Integer addCartGoodsCount(Integer cart_id, Integer num);

	@Select("select * from cart where client_id = #{0}")
	List<Cart> listByClientId(Integer id);

	void deleteCarts(List<Cart> carts);

	@Select("select sum(c.num * g.price) from goods g,cart c,client t where g.id = c.goods_id and c.client_id = t.id and t.id = #{0}")
	BigDecimal cartTotalMoneyByClientId(Integer client_id);
}

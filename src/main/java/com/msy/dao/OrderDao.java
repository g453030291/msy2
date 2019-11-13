package com.msy.dao;

import com.msy.entity.*;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderDao {

	List<Order> listOrder(TableModel<Order> tableModel);

	Long orderCount(TableModel<Order> tableModel);

	@Insert("insert into msy_order(client_id,client_name,store_name,telephone,send_id,send_name,money,order_date,send_date,pay_date,pay_state,pay_type,is_arrears,arrears_money,arrears_date,to_arrears_date,repo_id,repo_name,create_date) " +
			"values(#{client_id},#{client_name},#{store_name},#{telephone},#{send_id},#{send_name},#{money},#{order_date},#{send_date},#{pay_date},#{pay_state},#{pay_type},#{is_arrears},#{arrears_money},#{arrears_date},#{to_arrears_date},#{repo_id},#{repo_name},now())")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
	int addOrder(Order order);

	@Select("select * from goods where id = #{0}")
	Client selectOrder(String orderId);

	@Update("update goods set name = #{name},price=#{price} where id = #{id}")
	Integer updateOrder(Order order);

	Integer addOrderGoods(List<OrderGoods> orderGoods);

	@Select("select * from msy_order where is_arrears = '是'")
	List<Order> arrearsOrder();

	@Select("select * from msy_order where id = #{0}")
	Order findOrderById(Integer id);

	@Select("select * from order_goods where order_id = #{0}")
	List<OrderGoods> findOrderGoodsByOrderId(Integer id);

	@Select("update msy_order set is_arrears = #{1},arrears_money = #{2},arrears_date = #{3} where id = #{0}")
	Integer updateArrearsOrder(Integer id, String is_arrears, BigDecimal arrears_money, Date arrears_date);

	@Insert("insert into arrears_log(order_id,arrears_money,pay_type,arrears_date) values(#{order_id},#{arrears_money},#{pay_type},now())")
	void addArrearsLog(ArrearsLog al);

	@Select("select * from arrears_log where order_id = #{0}")
	List<ArrearsLog> findArrearsLog(Integer orderId);
}
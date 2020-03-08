package com.msy.service;

import com.msy.dao.OrderDao;
import com.msy.entity.*;
import com.msy.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private ClientService clientService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private RepoService repoService;

	@Autowired
	private GoodsService goodsService;

	public PageResult<List<Order>> listOrder(TableModel<Order> tableModel){
		tableModel.setPage((tableModel.getPage()-1)*tableModel.getLimit());
		List<Order> orderList = orderDao.listOrder(tableModel);
		Long count = orderDao.orderCount(tableModel);
		PageResult pr = new PageResult();
		pr.setCode(0);
		pr.setCount(count);
		pr.setMsg("数据查询成功");
		pr.setData(orderList);
		return pr;
	}

	/**
	 * 返回订单的id
	 * @param orderDTO
	 * @return
	 */
	public Integer addOrder(OrderDTO orderDTO) {
		if(StringUtils.isEmpty(orderDTO.getTelephone())){
			return 0;
		}
		List<Client> clients = clientService.findClientByTelephone(orderDTO.getTelephone());
		Order order = new Order();
		if(clients.size()!=1){
			return 0;
		}
		Staff staff = staffService.findStaffById(orderDTO.getSend_id());
		if(staff!=null){
			order.setSend_name(staff.getName());
		}
		Repo repo = repoService.findRepoById(orderDTO.getRepo_id());
		if(repo!=null){
			order.setRepo_name(repo.getName());
		}
		order.setClient_id(clients.get(0).getId());
		order.setClient_name(clients.get(0).getName());
		order.setStore_name(clients.get(0).getStore_name());
		order.setTelephone(orderDTO.getTelephone());
		order.setSend_id(orderDTO.getSend_id());
		order.setMoney(orderDTO.getMoney());
		order.setPay_state(orderDTO.getPay_state());
		order.setOrder_date(orderDTO.getOrder_date()==null?new Date():orderDTO.getOrder_date());
		order.setSend_date(orderDTO.getSend_date()==null?new Date():orderDTO.getSend_date());
		order.setPay_date(orderDTO.getPay_date()==null?new Date():orderDTO.getPay_date());
		order.setPay_type(orderDTO.getPay_type());
		order.setIs_arrears(orderDTO.getIs_arrears());
		order.setArrears_money(orderDTO.getArrears_money());
		order.setArrears_date(orderDTO.getArrears_date());
		order.setRepo_id(orderDTO.getRepo_id());
		orderDao.addOrder(order);
		return order.getId();
	}

	public Integer addOrderGoods(List<GoodsDTO> goodsDTOS) {
		List<OrderGoods> orderGoods = new ArrayList<>(goodsDTOS.size());
		for (GoodsDTO g : goodsDTOS){
			Goods goods = goodsService.findGoodsById(g.getGoods_id());
			OrderGoods og = new OrderGoods();
			og.setBrand_name(goods.getBrand_name());
			og.setGoods_name(goods.getGoods_name());
			og.setLevel_name(goods.getLevel_name());
			og.setModel_name(goods.getModel_name());
			og.setGuige_name(goods.getGuige_name());
			og.setOrder_id(g.getOrder_id());
			og.setGoods_id(goods.getId());
			og.setBuy_count(g.getBuy_count());
			og.setGoods_price(g.getGoods_price());
			og.setTotal_money(g.getGoods_price().multiply(new BigDecimal(g.getBuy_count()).setScale(2,BigDecimal.ROUND_HALF_UP)));
			orderGoods.add(og);
		}
		return orderDao.addOrderGoods(orderGoods);
	}

	/**
	 * 查询出所有欠款订单
	 * @return
	 */
	public List<Order> arrearsOrder() {
		return orderDao.arrearsOrder();
	}

	public Order findOrderById(Integer id) {
		return orderDao.findOrderById(id);
	}

	public List<OrderGoods> findOrderGoodsByOrderId(Integer id) {
		return orderDao.findOrderGoodsByOrderId(id);
	}

	public Integer updateArrearsOrder(Integer id, String is_arrears, BigDecimal arrears_money, Date arrears_date) {
		return orderDao.updateArrearsOrder(id,is_arrears,arrears_money,arrears_date);
	}

	public void addArrearsLog(ArrearsLog al) {
		orderDao.addArrearsLog(al);
	}

	public List<ArrearsLog> findArrearsLog(Integer orderId) {
		return orderDao.findArrearsLog(orderId);
	}

	public List<Order> findOrderByClientId(Integer id) {
		return orderDao.findOrderByClientId(id);
	}
}

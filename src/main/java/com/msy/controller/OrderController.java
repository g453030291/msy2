package com.msy.controller;

import com.msy.entity.*;
import com.msy.service.*;
import com.msy.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单控制层
 */
@Controller
@RequestMapping("/pc/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private RepoService repoService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private ClientService clientService;

	@RequestMapping("toOrderList.page")
	public String toOrderListPage(){
		return "/pc/order/orderList";
	}

	@ResponseBody
	@RequestMapping("/getOrderList.json")
	public PageResult<List<Order>> getClientList(TableModel<Order> tableModel,Order order){
		tableModel.setData(order);
		if(StringUtils.isEmpty(tableModel.getField())){
			tableModel.setField("id");
		}
		if(StringUtils.isEmpty(tableModel.getOrder())){
			tableModel.setOrder("desc");
		};
		PageResult<List<Order>> listPageResult = orderService.listOrder(tableModel);
		return listPageResult;
	}

	@RequestMapping("toAddOrder.page")
	public String toAddOrderPage(Model model){
		List<Brand> brands = brandService.listAllBrand();
		List<Repo> repos = repoService.listAllRepo();
		List<Staff> staffs = staffService.allStaff();
		model.addAttribute("brands",brands);
		model.addAttribute("repos",repos);
		model.addAttribute("staffs",staffs);
		return "pc/order/addOrder";
	}

	@ResponseBody
	@RequestMapping("/findGoodsByblmg.json")
	public Goods findGoodsByblmg(String brand_name,String level_name,String model_name,
									String guige_name,Integer buy_count,BigDecimal price){
		Goods goods = goodsService.findGoodsByblmg(brand_name,level_name,model_name,guige_name);
		return goods;
	}

	@ResponseBody
	@RequestMapping("/addOrder.form")
	@Transactional(rollbackFor = Exception.class)
	public Integer addOrder(OrderDTO orderDTO){
		Integer orderId = orderService.addOrder(orderDTO);
		return orderId;
	}

	@ResponseBody
	@RequestMapping("/addOrderGoods.form")
	@Transactional(rollbackFor = Exception.class)
	public String addOrderGoods(@RequestBody List<GoodsDTO> goodsDTOS){
		if(goodsDTOS.get(0).getOrder_id()==null || goodsDTOS.get(0).getOrder_id()==0){
			return null;
		}
		Integer count = orderService.addOrderGoods(goodsDTOS);
		List<Client> clients = clientService.findClientByTelephone(goodsDTOS.get(0).getTelephone());
		if(clients.size()==0){
			return "该手机号没有找到注册用户,已默认存储为非会员订单";
		}else if(clients.size()==1){
			return "用户"+clients.get(0).getName()+"订单,已存储";
		}else {
			return "同一手机号,找到多个用户,已存储为非会员订单";
		}
	}

	@RequestMapping("/orderDetail.page")
	public String orderDetail(Integer orderId,Model model){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Order order = orderService.findOrderById(orderId);
		OrderDTO2 orderDTO2 = new OrderDTO2();
		orderDTO2.setOrder_date(sdf.format(order.getOrder_date()));
		orderDTO2.setSend_date(sdf.format(order.getSend_date()));
		orderDTO2.setPay_date(sdf.format(order.getPay_date()));
		orderDTO2.setArrears_date(sdf.format(order.getArrears_date()));
		List<OrderGoods> orderGoods = orderService.findOrderGoodsByOrderId(orderId);
		model.addAttribute("order",order);
		model.addAttribute("order2",orderDTO2);
		model.addAttribute("orderGoods",orderGoods);
		return "/pc/order/orderDetail";
	}

	@RequestMapping("/toArrears.page")
	public String toArrears(Integer orderId,Model model){
		Order order = orderService.findOrderById(orderId);
		model.addAttribute("arrearsMoney",order.getArrears_money());
		model.addAttribute("orderId",orderId);
		return "/pc/order/arrears";
	}

	@ResponseBody
	@RequestMapping("/updateArrears.form")
	@Transactional(rollbackFor = Exception.class)
	public String updateArrears(Order order){
		ArrearsLog al = new ArrearsLog();
		al.setOrder_id(order.getId());
		al.setArrears_money(order.getArrears_money());
		al.setPay_type(order.getPay_type());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Order order1 = orderService.findOrderById(order.getId());
		if(order1.getArrears_money().compareTo(order.getArrears_money()) == 0){
			//欠款还清
			Integer count = orderService.updateArrearsOrder(order.getId(),"否",new BigDecimal(0),new Date());
			orderService.addArrearsLog(al);
			return "欠款已结清";
		}else if(order1.getArrears_money().compareTo(order.getArrears_money()) == 1){
			BigDecimal arrears_money = order1.getArrears_money().subtract(order.getArrears_money()).setScale(2,BigDecimal.ROUND_HALF_UP);
			Integer count = orderService.updateArrearsOrder(order.getId(),"是",arrears_money,order.getArrears_date());
			orderService.addArrearsLog(al);
			return "本次还款金额为:"+order.getArrears_money()+"剩余还款金额为:"+arrears_money+"下次催款时间为:"+sdf.format(order.getArrears_date());
		}else{
			return "还款金额大于欠款金额,还款失败";
		}
	}

	@RequestMapping("/toArrearsLog.page")
	public String toArrearsLog(Integer orderId,Model model){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<ArrearsLog> arrearsLog = orderService.findArrearsLog(orderId);
		List<ArrearsLogDTO> dtos = new ArrayList<>();
		for (ArrearsLog a:arrearsLog){
			ArrearsLogDTO dto = new ArrearsLogDTO();
			dto.setPay_type(a.getPay_type());
			dto.setOrder_id(a.getOrder_id());
			dto.setArrears_money(a.getArrears_money());
			dto.setId(a.getId());
			dto.setArrears_date(sdf.format(a.getArrears_date()));
			dtos.add(dto);
		}
		model.addAttribute("arrearsLog",dtos);
		return "/pc/order/arrearsLog";
	}

}

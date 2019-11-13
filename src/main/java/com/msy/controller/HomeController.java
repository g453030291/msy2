package com.msy.controller;

import com.msy.entity.Order;
import com.msy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/pc")
public class HomeController {

	@Autowired
	private OrderService orderService;

	@RequestMapping("/toHome.page")
	public String toHomePage(Model model){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Order> orders = orderService.arrearsOrder();
		for (int i = 0; i < orders.size(); i++){
			Date arrears = orders.get(i).getArrears_date();
			//这里用is_arrears代替arrears_date,保存格式过的时间
			orders.get(i).setIs_arrears(sdf.format(arrears));
		}
		model.addAttribute("orders",orders);
		return "/home";
	}
}

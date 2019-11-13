package com.msy.controller;

import com.msy.entity.*;
import com.msy.service.BrandService;
import com.msy.service.GoodsService;
import com.msy.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/pc/goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private BrandService brandService;

	@RequestMapping("/toGoodsList.page")
	public String toGoodsListPage(Model model){
		List<Brand> brands = brandService.listAllBrand();
		model.addAttribute("brands",brands);
		return "/goodsList";
	}

	@ResponseBody
	@RequestMapping("/getGoodsList.json")
	public PageResult<List<Goods>> getClientList(TableModel<Goods> tableModel,Goods goods){
		tableModel.setData(goods);
		if(StringUtils.isEmpty(tableModel.getField())){
			tableModel.setField("id");
		}
		if(StringUtils.isEmpty(tableModel.getOrder())){
			tableModel.setOrder("desc");
		}
		PageResult<List<Goods>> listPageResult = goodsService.listGoods(tableModel);
		return listPageResult;
	}

	@RequestMapping("/toAddGoods.page")
	public String toAddGoods(Model model,Integer id){
		Goods goods = null;
		List<Brand> brands = brandService.listAllBrand();
		if(StringUtils.isEmpty(id)){
			//add
			goods = new Goods();
		}else{
			//update 根据id查找商品
			goods = goodsService.findGoodsById(id);
		}
		model.addAttribute("goods",goods);
		model.addAttribute("brands",brands);
		return "pc/goods/addGoods";
	}

	@ResponseBody
	@RequestMapping("/addGoods.form")
	@Transactional(rollbackFor = Exception.class)
	public String addGoods(Goods goods){
		//根据品牌id查找品牌
		Brand brand = brandService.findBrandById(goods.getBrand_id().toString());
		goods.setBrand_name(brand.getName());
		goods.setState("下架");
		Goods goods1 = goodsService.addGoods(goods);
		return goods1.getId().toString();
	}

	@ResponseBody
	@RequestMapping("/updateGoods.form")
	public String updateGoods(Goods goods){
		//根据品牌id查找品牌
		Brand brand = brandService.findBrandById(goods.getBrand_id().toString());
		goods.setBrand_name(brand.getName());
		Integer count = goodsService.updateGoods(goods);
		return count.toString();
	}

//	@RequestMapping("/toUpdateGoods.page")
//	public String toUpdateGoods(String id, Model model){
//		Goods goods = null;
//		Level level = null;
//		com.msy.entity.Model model1 = null;
//		Guige guige = null;
//		List<Brand> brands = brandService.listAllBrand();
//		if(StringUtils.isEmpty(id)){
//			goods = new Goods();
//			level = new Level();
//			model1 = new com.msy.entity.Model();
//			guige = new Guige();
//		}else{
//			//根据id查询规格
//			guige = brandService.findguigeById(id);
//			//根据id查询型号
//			model1 = brandService.findModelById(guige.getModel_id());
//			//根据id查询级别
//			level = brandService.findLevelById(model1.getLevel_id());
//			//根据id查询商品
//			goods = goodsService.findGoodsById(level.getGoods_id());
//		}
//		model.addAttribute("goods",goods);
//		model.addAttribute("brands",brands);
//		model.addAttribute("model",model1);
//		model.addAttribute("level",level);
//		model.addAttribute("guige",guige);
//		return "pc/goods/updateGoods";
//	}

	@ResponseBody
	@RequestMapping("/upDown.json")
	public String upDown(String id,String state){
		Integer count = null;
		if("上架".equals(state)){
			count = goodsService.upDown(id,"下架");
		}else{
			count = goodsService.upDown(id,"上架");
		}
		return count.toString();
	}


}

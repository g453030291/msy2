package com.msy.controller;

import com.msy.entity.Brand;
import com.msy.entity.Client;
import com.msy.entity.TableModel;
import com.msy.service.BrandService;
import com.msy.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/pc/brand")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@RequestMapping("/toBrandList.page")
	public String toBrandList(){
		return "/pc/brandList";
	}

	@ResponseBody
	@RequestMapping("/getBrandList.json")
	public PageResult<List<Brand>> getBrandList(TableModel<Brand> tableModel, Brand brand){
		tableModel.setData(brand);
		if(StringUtils.isEmpty(tableModel.getField())){
			tableModel.setField("id");
		}
		if(StringUtils.isEmpty(tableModel.getOrder())){
			tableModel.setOrder("desc");
		};
		PageResult<List<Brand>> listPageResult = brandService.listBrand(tableModel);
		return listPageResult;
	}

	@RequestMapping("/toAddBrand.page")
	public String toAddBrandPage(Model model,String id){
		Brand brand = null;
		if(StringUtils.isEmpty(id)){
			brand = new Brand();
		}else{
			brand = brandService.findBrandById(id);
		}
		model.addAttribute("brand",brand);
		return "pc/updateBrand";
	}

	@ResponseBody
	@RequestMapping("/addBrand.form")
	public String addBrand(Brand brand){
		System.out.println(brand.toString());
		Integer count = brandService.addBrand(brand);
		return count.toString();
	}

	@ResponseBody
	@RequestMapping("/updateBrand.form")
	public String editClient(Brand brand){
		Integer count = brandService.updateBrand(brand);
		return count.toString();
	}

	@ResponseBody
	@RequestMapping("/delGuige.json")
	public int delGuige(String id){
		return brandService.delGuige(id);
	}

}

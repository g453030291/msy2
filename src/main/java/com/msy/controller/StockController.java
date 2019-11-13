package com.msy.controller;

import com.msy.entity.*;
import com.msy.service.BrandService;
import com.msy.service.GoodsService;
import com.msy.service.RepoService;
import com.msy.service.StockService;
import com.msy.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/pc/stock")
public class StockController {

	@Autowired
	private StockService stockService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private RepoService repoService;

	@RequestMapping("/toStockLogList.page")
	public String toStockLogList(){
		return "/pc/stock/stockList";
	}

	@ResponseBody
	@RequestMapping("/getStockList.json")
	public PageResult<List<Stock>> getClientList(TableModel<Stock> tableModel, Stock stock){
		tableModel.setData(stock);
		if(StringUtils.isEmpty(tableModel.getField())){
			tableModel.setField("id");
		}
		if(StringUtils.isEmpty(tableModel.getOrder())){
			tableModel.setOrder("desc");
		}
		PageResult<List<Stock>> listPageResult = stockService.listStock(tableModel);
		return listPageResult;
	}

	@RequestMapping("/toAddStock.page")
	public String toAddStock(Model model){
		//品牌
		List<Brand> brands = brandService.listAllBrand();
		//仓库
		List<Repo> repos = repoService.listAllRepo();
		model.addAttribute("brands",brands);
		model.addAttribute("repos",repos);
		return "/pc/stock/addStock";
	}

	@ResponseBody
	@RequestMapping("/findLevelByBrand.json")
	public List<String> getLevelByBrand(String brand_name){
		List<String> levels = goodsService.findLevelByBrand(brand_name);
		return levels;
	}

	@ResponseBody
	@RequestMapping("/findModelBybl.json")
	public List<String> findModelBybl(String brand_name,String level_name){
		List<String> models = goodsService.findModelBybl(brand_name,level_name);
		return models;
	}

	@ResponseBody
	@RequestMapping("/findGuigeByblm.json")
	public List<String> findGuigeByblm(String brand_name,String level_name,String model_name){
		List<String> guiges = goodsService.findGuigeByblm(brand_name,level_name,model_name);
		return guiges;
	}

	@ResponseBody
	@RequestMapping("/findGoodsByblmg.json")
	@Transactional(rollbackFor = Exception.class)
	public AddStockResult findGoodsByblmg(String brand_name, String level_name, String model_name, String guige_name, String repo, BigDecimal buy_count, BigDecimal price, BigDecimal total_price, Integer stock_id, String stock_date) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Goods goods = goodsService.findGoodsByblmg(brand_name,level_name,model_name,guige_name);
		//根据repo_name查询仓库
		Repo reRepo = repoService.findRepoByName(repo);
		//根据stock_id判断是生成新纪录,还是存储旧记录
		if(stock_id == null || stock_id == 0){
			Stock stock = new Stock();
			stock.setRepo_id(reRepo.getId());
			stock.setRepo_name(reRepo.getName());
			stock.setStock_date(sdf.parse(stock_date));
			stock.setMoney(buy_count.multiply(price));
			Stock reStock = stockService.addStock(stock);
			stock_id = reStock.getId();
		}else{
			stockService.updateStockMoneyById(buy_count.multiply(price),stock_id);
		}
		//存储进货中间表
		Integer stockGoodsId = stockService.addStockAndGoods(stock_id,goods.getId(),buy_count,price,buy_count.multiply(price));
		//查询出来本次进货所有商品的总价
		BigDecimal total_price2 = stockService.totalPriceByStock_id(stock_id);
		AddStockResult asr = new AddStockResult();
		asr.setStockId(stock_id);
		asr.setStockGoodsId(stockGoodsId);
		asr.setTotal_price(total_price2);
		asr.setGoods(goods);
		return asr;
	}

	@RequestMapping("/toStockDetail.page")
	public String toStockLogList(Model model,Integer id){
		Stock stock = stockService.findStockById(id);
		List<DetailStockResult> detailStock = stockService.findStockDetailByStockId(stock.getId());
		model.addAttribute("stock",stock);
		model.addAttribute("detailStock",detailStock);
		return "/pc/stock/stockDetail";
	}
}

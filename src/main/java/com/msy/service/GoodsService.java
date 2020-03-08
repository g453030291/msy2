package com.msy.service;

import com.msy.dao.GoodsDao;
import com.msy.entity.*;
import com.msy.entity.wx.WxPageUtil;
import com.msy.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsService {

	@Autowired
	private GoodsDao goodsDao;

	public PageResult<List<Goods>> listGoods(TableModel<Goods> tableModel){
		tableModel.setPage((tableModel.getPage()-1)*tableModel.getLimit());
		List<Goods> goodsList = goodsDao.listGoods(tableModel);
		Long count = goodsDao.goodsCount(tableModel);
		PageResult pr = new PageResult();
		pr.setCode(0);
		pr.setCount(count);
		pr.setMsg("数据查询成功");
		pr.setData(goodsList);
		return pr;
	}

	public Goods addGoods(Goods goods) {
		goodsDao.addGoods(goods);
		return goods;
	}

	public Goods selectGoods(String id) {
		return goodsDao.selectGoods(id);
	}

	public Goods findGoodsById(Integer goods_id) {
		return goodsDao.findGoodsById(goods_id);
	}

	@Transactional(rollbackFor = Exception.class)
	public Integer updateGoods(Goods goods) {
		return goodsDao.updateGoods(goods);
	}

	@Transactional(rollbackFor = Exception.class)
	public Integer upDown(String id, String state) {
		return goodsDao.upDown(id,state);
	}

	public List<String> findLevelByBrand(String brand_name) {
		return goodsDao.findLevelByBrand(brand_name);
	}

	public List<String> findModelBybl(String brand_name, String level_name) {
		return goodsDao.findModelBybl(brand_name,level_name);
	}

	public List<String> findGuigeByblm(String brand_name, String level_name, String model_name) {
		return goodsDao.findGuigeByblm(brand_name,level_name,model_name);
	}

	public Goods findGoodsByblmg(String brand_name, String level_name, String model_name, String guige_name) {
		return goodsDao.findGoodsByblmg(brand_name,level_name,model_name,guige_name);
	}

	public List<Goods> listGoodsByOilType(String oilType) {
		return goodsDao.listGoodsByOilType(oilType);
	}

	public WxPageUtil<List<GoodsDTO3>> pageGoodsByGoodsDTO2(WxPageUtil wxPageUtil, GoodsDTO2 goodsDTO2) {
		wxPageUtil.setData(goodsDTO2);
		wxPageUtil.setNum((wxPageUtil.getNum()-1)*wxPageUtil.getSize());
		List<GoodsDTO3> goodsDTO3s = goodsDao.listGoodsByGoodsDTO2(wxPageUtil);
		Integer count = goodsDao.countGoodsByGoodsDTO2(wxPageUtil);
		WxPageUtil<List<GoodsDTO3>> page = new WxPageUtil<>();
		page.setNum(wxPageUtil.getNum());
		page.setSize(wxPageUtil.getSize());
		page.setData(goodsDTO3s);
		page.setTotalSize(count);
		return page;
	}

	public List<Goods> getLevelByOilTypeAndBrand(String oilType, String brandVal) {
		return goodsDao.getLevelByOilTypeAndBrand(oilType,brandVal);
	}

	public List<Goods> getLevelByOilTypeAndBrandAndLevel(String oilType, String brandVal, String oilLevelVal) {
		return goodsDao.getLevelByOilTypeAndBrandAndLevel(oilType,brandVal,oilLevelVal);
	}

	public List<Goods> cartGoodsByIds(List<Cart> carts) {
		return goodsDao.cartGoodsByIds(carts);
	}
}

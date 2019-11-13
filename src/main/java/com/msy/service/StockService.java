package com.msy.service;

import com.msy.dao.StockDao;
import com.msy.entity.DetailStockResult;
import com.msy.entity.Stock;
import com.msy.entity.TableModel;
import com.msy.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StockService {

	@Autowired
	private StockDao stockDao;

	public PageResult<List<Stock>> listStock(TableModel<Stock> tableModel){
		tableModel.setPage((tableModel.getPage()-1)*tableModel.getLimit());
		List<Stock> repoList = stockDao.listStock(tableModel);
		Long count = stockDao.stockCount(tableModel);
		PageResult pr = new PageResult();
		pr.setCode(0);
		pr.setCount(count);
		pr.setMsg("数据查询成功");
		pr.setData(repoList);
		return pr;
	}

	public Stock addStock(Stock stock) {
		stockDao.addStock(stock);
		return stock;
	}

	public void updateStockMoneyById(BigDecimal money, Integer stock_id) {
		stockDao.updateStockMoneyById(money,stock_id);
	}

	public Integer addStockAndGoods(Integer stock_id, Integer goods_id, BigDecimal buy_count, BigDecimal price, BigDecimal total_price) {
		return stockDao.addStockAndGoods(stock_id,goods_id,buy_count,price,total_price);
	}

	public BigDecimal totalPriceByStock_id(Integer stock_id) {
		BigDecimal result = stockDao.totalPriceByStock_id(stock_id);
		System.out.println(result);
		return result == null?result = new BigDecimal(0.00):result;
	}

	public Stock findStockById(Integer id) {
		return stockDao.findStockById(id);
	}

	public List<DetailStockResult> findStockDetailByStockId(Integer stock_id) {
		return stockDao.findStockDetailByStockId(stock_id);
	}
}

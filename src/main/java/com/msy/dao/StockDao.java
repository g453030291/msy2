package com.msy.dao;

import com.msy.entity.DetailStockResult;
import com.msy.entity.Stock;
import com.msy.entity.TableModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface StockDao {

	List<Stock> listStock(TableModel<Stock> tableModel);

	Long stockCount(TableModel<Stock> tableModel);

	@Insert("insert into stock(repo_id,repo_name,money,stock_date,create_date) values(#{repo_id},#{repo_name},#{money},#{stock_date},now())")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
	Integer addStock(Stock stock);

	@Update("update stock set money = money + #{0} where id = #{1}")
	void updateStockMoneyById(BigDecimal money, Integer stock_id);

	@Insert("insert into stock_goods(stock_id,goods_id,goods_count,price,total_price) values(#{0},#{1},#{2},#{3},#{4})")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
	Integer addStockAndGoods(Integer stock_id, Integer goods_id, BigDecimal buy_count, BigDecimal price, BigDecimal total_price);

	@Select("select sum(total_price) from stock_goods where stock_id = #{0}")
	BigDecimal totalPriceByStock_id(Integer stock_id);

	@Select("select * from stock where id = #{0}")
	Stock findStockById(Integer id);

	@Select("select g.brand_name,g.goods_name,g.level_name,g.model_name,g.guige_name,sg.goods_count buy_count,sg.price,sg.total_price from stock_goods sg,goods g where sg.goods_id = g.id and stock_id = #{0}")
	List<DetailStockResult> findStockDetailByStockId(Integer stock_id);
}

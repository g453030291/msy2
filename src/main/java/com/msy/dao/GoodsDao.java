package com.msy.dao;

import com.msy.entity.Goods;
import com.msy.entity.TableModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsDao {

	List<Goods> listGoods(TableModel<Goods> tableModel);

	Long goodsCount(TableModel<Goods> tableModel);

	@Insert("insert into goods(oil_type,brand_id,brand_name,goods_name,level_name,model_name,guige_name,price,state,img1,create_date) " +
			"values(#{oil_type},#{brand_id},#{brand_name},#{goods_name},#{level_name},#{model_name},#{guige_name},#{price},#{state},#{img1},now())")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
	int addGoods(Goods goods);

	@Select("select * from goods where id = #{0}")
	Goods selectGoods(String goodsId);

	@Select("select * from goods where id = #{0}")
	Goods findGoodsById(Integer goods_id);

	@Update("update goods set oil_type = #{oil_type},brand_id = #{brand_id},brand_name = #{brand_name},goods_name = #{goods_name},level_name = #{level_name},model_name = #{model_name},guige_name = #{guige_name},price = #{price},img1 = #{img1} where id = #{id}")
	Integer updateGoods(Goods goods);

	@Update("update goods set state = #{1} where id = #{0}")
	Integer upDown(String id, String state);

	@Select("select level_name from goods where brand_name = #{0} group by level_name")
	List<String> findLevelByBrand(String brand_name);

	@Select("select model_name from goods where brand_name = #{0} and level_name = #{1} group by model_name")
	List<String> findModelBybl(String brand_name, String level_name);

	@Select("select guige_name from goods where brand_name = #{0} and level_name = #{1} and model_name = #{2} group by guige_name")
	List<String> findGuigeByblm(String brand_name, String level_name, String model_name);

	@Select("select * from goods where brand_name = #{0} and level_name = #{1} and model_name = #{2} and guige_name = #{3}")
	Goods findGoodsByblmg(String brand_name, String level_name, String model_name, String guige_name);
}

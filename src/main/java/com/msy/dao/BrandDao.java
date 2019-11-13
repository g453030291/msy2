package com.msy.dao;

import com.msy.entity.Brand;
import com.msy.entity.TableModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandDao {
	
	List<Brand> listBrand(TableModel<Brand> tableModel);

	Long listBrandCount(TableModel<Brand> tableModel);

	@Insert("insert into brand(name,person_in_charge,telephone,address,create_date) values(#{name},#{person_in_charge},#{telephone},#{address},now())")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
	Integer addBrand(Brand brand);

	@Update("update brand set name = #{name},person_in_charge = #{person_in_charge},telephone = #{telephone},address = #{address} where id = #{id}")
	Integer updateBrand(Brand brand);

	@Select("select * from brand where id = #{0}")
	Brand findBrandById(String id);

	@Delete("delete from brand_guige where id = #{id}")
	int delGuige(String id);

	@Select("select * from brand")
	List<Brand> listAllBrand();

	@Update("update level_guige set state = #{1} where id =#{0}")
	Integer upDown(String id, String state);
}

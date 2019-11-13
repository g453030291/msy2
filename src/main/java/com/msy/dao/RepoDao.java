package com.msy.dao;

import com.msy.entity.Repo;
import com.msy.entity.TableModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoDao {

	List<Repo> listRepo(TableModel<Repo> tableModel);

	Long repoCount(TableModel<Repo> tableModel);

	@Select("select * from repo where id = #{0}")
	Repo findRepoById(Integer id);

	@Insert("insert into repo(name,province,city,district,address,create_date) values(#{name},#{province},#{city},#{district},#{address},now())")
	Integer addRepo(Repo repo);

	@Update("update repo set name = #{name},province = #{province},city = #{city},district = #{district},address = #{address} where id = #{id}")
	Integer updateRepo(Repo repo);

	@Select("select * from repo")
	List<Repo> listAllRepo();

	@Select("select * from repo where name = #{0}")
	Repo findRepoByName(String repo);
}

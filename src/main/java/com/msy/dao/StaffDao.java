package com.msy.dao;

import com.msy.entity.Staff;
import com.msy.entity.TableModel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffDao {

	List<Staff> listStaff(TableModel<Staff> tableModel);

	Long staffCount(TableModel<Staff> tableModel);

	@Insert("insert into staff(client_id,name,telephone,age,sex,birthday,work_city,address,state,create_date)" +
			"values(#{client_id},#{name},#{telephone},#{age},#{sex},#{birthday},#{work_city},#{address},#{state},now())")
	Integer addStaff(Staff staff);

	@Update("update staff set state = #{1} where id = #{0}")
	Integer updateStaffState(Integer id, String state);

	@Select("select * from staff")
	List<Staff> allStaff();

	@Select("select * from staff where id = #{0}")
	Staff findStaffById(Integer send_id);
}

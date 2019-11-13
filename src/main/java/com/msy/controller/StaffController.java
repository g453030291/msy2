package com.msy.controller;

import com.msy.entity.Staff;
import com.msy.entity.TableModel;
import com.msy.service.StaffService;
import com.msy.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/pc/staff")
public class StaffController {

	@Autowired
	private StaffService staffService;

	@RequestMapping("/toStaffList.page")
	public String toStaffList(){
		return "pc/staff/staffList";
	}

	@ResponseBody
	@RequestMapping("/getStaffList.json")
	public PageResult<List<Staff>> getClientList(TableModel<Staff> tableModel, Staff staff){
		tableModel.setData(staff);
		if(StringUtils.isEmpty(tableModel.getField())){
			tableModel.setField("id");
		}
		if(StringUtils.isEmpty(tableModel.getOrder())){
			tableModel.setOrder("desc");
		};
		PageResult<List<Staff>> listPageResult = staffService.listStaff(tableModel);
		return listPageResult;
	}

	@RequestMapping("/toAddStaff.page")
	public String toAddStaff(){
		return "pc/staff/addStaff";
	}

	@ResponseBody
	@RequestMapping("/addStaff.form")
	@Transactional(rollbackFor = Exception.class)
	public Integer addStaff(Staff staff){
		return staffService.addStaff(staff);
	}

	@ResponseBody
	@RequestMapping("/updateStaffState.json")
	@Transactional(rollbackFor = Exception.class)
	public Integer updateStaffState(Integer id,String state){
		return staffService.updateStaffState(id,state);
	}

}

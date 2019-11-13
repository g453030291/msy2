package com.msy.service;

import com.msy.dao.StaffDao;
import com.msy.entity.Staff;
import com.msy.entity.TableModel;
import com.msy.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StaffService {

	@Autowired
	private StaffDao staffDao;

	public PageResult<List<Staff>> listStaff(TableModel<Staff> tableModel){
		tableModel.setPage((tableModel.getPage()-1)*tableModel.getLimit());
		List<Staff> clientList = staffDao.listStaff(tableModel);
		Long count = staffDao.staffCount(tableModel);
		PageResult pr = new PageResult();
		pr.setCode(0);
		pr.setCount(count);
		pr.setMsg("数据查询成功");
		pr.setData(clientList);
		return pr;
	}

	public Integer addStaff(Staff staff) {
		return staffDao.addStaff(staff);
	}

	public Integer updateStaffState(Integer id, String state) {
		if("在职".equals(state)){
			state = "离职";
		}else {
			state = "在职";
		}
		return staffDao.updateStaffState(id,state);
	}

	public List<Staff> allStaff() {
		return staffDao.allStaff();
	}

	public Staff findStaffById(Integer send_id) {
		return staffDao.findStaffById(send_id);
	}
}

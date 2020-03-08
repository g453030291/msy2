package com.msy.service;

import com.msy.dao.BrandDao;
import com.msy.entity.Brand;
import com.msy.entity.TableModel;
import com.msy.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class BrandService {

	@Autowired
	private BrandDao brandDao;

	public PageResult<List<Brand>> listBrand(TableModel<Brand> tableModel){
		tableModel.setPage((tableModel.getPage()-1)*tableModel.getLimit());
		List<Brand> brandList = brandDao.listBrand(tableModel);
		Long count = brandDao.listBrandCount(tableModel);
		PageResult pr = new PageResult();
		pr.setCode(0);
		pr.setCount(count);
		pr.setMsg("数据查询成功");
		pr.setData(brandList);
		return pr;
	}

	public Integer addBrand(Brand brand) {
		return brandDao.addBrand(brand);
	}

	@Transactional(rollbackFor = Exception.class)
	public Integer updateBrand(Brand brand) {
		return brandDao.updateBrand(brand);
	}

	public Brand findBrandById(String id) {
		return brandDao.findBrandById(id);
	}


	@Transactional(rollbackFor = Exception.class)
	public int delGuige(String id) {
		return brandDao.delGuige(id);
	}

	public List<Brand> listAllBrand() {
		return brandDao.listAllBrand();
	}

	public Integer upDown(String id, String state) {
		return brandDao.upDown(id,state);
	}

	public int brandDown(String id) {
		return brandDao.brandDown(id);
	}
}

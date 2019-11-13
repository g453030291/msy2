package com.msy.service;

import com.msy.dao.RepoDao;
import com.msy.entity.Repo;
import com.msy.entity.TableModel;
import com.msy.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RepoService {

	@Autowired
	private RepoDao repoDao;

	public PageResult<List<Repo>> listRepo(TableModel<Repo> tableModel){
		tableModel.setPage((tableModel.getPage()-1)*tableModel.getLimit());
		List<Repo> repoList = repoDao.listRepo(tableModel);
		Long count = repoDao.repoCount(tableModel);
		PageResult pr = new PageResult();
		pr.setCode(0);
		pr.setCount(count);
		pr.setMsg("数据查询成功");
		pr.setData(repoList);
		return pr;
	}

	public Repo findRepoById(Integer id) {
		return repoDao.findRepoById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public Integer addRepo(Repo repo) {
		return repoDao.addRepo(repo);
	}

	@Transactional(rollbackFor = Exception.class)
	public Integer updateRepo(Repo repo) {
		return repoDao.updateRepo(repo);
	}

	public List<Repo> listAllRepo() {
		return repoDao.listAllRepo();
	}

	public Repo findRepoByName(String repo) {
		return repoDao.findRepoByName(repo);
	}
}

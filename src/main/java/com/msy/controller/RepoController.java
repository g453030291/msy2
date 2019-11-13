package com.msy.controller;

import com.msy.entity.Repo;
import com.msy.entity.TableModel;
import com.msy.service.RepoService;
import com.msy.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/pc/repo")
public class RepoController {

	@Autowired
	private RepoService repoService;

	@RequestMapping("/toRepoList.page")
	public String toRepoList(){
		return "/pc/repo/repoList";
	}

	@ResponseBody
	@RequestMapping("/getRepoList.json")
	public PageResult<List<Repo>> getClientList(TableModel<Repo> tableModel, Repo repo){
		tableModel.setData(repo);
		if(StringUtils.isEmpty(tableModel.getField())){
			tableModel.setField("id");
		}
		if(StringUtils.isEmpty(tableModel.getOrder())){
			tableModel.setOrder("desc");
		}
		PageResult<List<Repo>> listPageResult = repoService.listRepo(tableModel);
		return listPageResult;
	}

	@RequestMapping("/toUpdateRepo.page")
	public String toAddGoods(Model model, Integer id){
		Repo repo = null;
		if(StringUtils.isEmpty(id)){
			//add
			repo = new Repo();
		}else{
			//update 根据id查找商品
			repo = repoService.findRepoById(id);
		}
		model.addAttribute("repo",repo);
		return "pc/repo/updateRepo";
	}

	@ResponseBody
	@RequestMapping("/addRepo.form")
	public String addRepo(Repo repo){
		return repoService.addRepo(repo).toString();
	}

	@ResponseBody
	@RequestMapping("/updateRepo.form")
	public String updateRepo(Repo repo){
		return repoService.updateRepo(repo).toString();
	}
}

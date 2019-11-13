package com.msy.controller;

import com.msy.entity.Client;
import com.msy.entity.TableModel;
import com.msy.service.ClientService;
import com.msy.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户控制层
 */
@Controller
public class ClientController {

	@Autowired
	private ClientService clientService;

	@RequestMapping("/toClientList.page")
	public String toUserListPage(){
		return "/clientList";
	}

	@ResponseBody
	@RequestMapping("/getClientList.json")
	public PageResult<List<Client>> getClientList(TableModel<Client> tableModel, Client client){
		tableModel.setData(client);
		if(StringUtils.isEmpty(tableModel.getField())){
			tableModel.setField("id");
		}
		if(StringUtils.isEmpty(tableModel.getOrder())){
			tableModel.setOrder("desc");
		};
		PageResult<List<Client>> listPageResult = clientService.listClient(tableModel);
		return listPageResult;
	}

	@RequestMapping("/toAddClient.page")
	public String toAddClient(String clientId, Model model){
		System.out.println(clientId);
		Client client = null;
		if(!StringUtils.isEmpty(clientId)){
			client = clientService.selectClient(clientId);
		}else{
			client = new Client();
		}
		model.addAttribute("client",client);
		return "/addEditClient";
	}

	@RequestMapping("/toEditClient.page")
	public String toEditClient(Integer id){
		return "";
	}

	@ResponseBody
	@RequestMapping("/updateClient.form")
	public String editClient(Client client){
		Integer count = clientService.updateClient(client);
		return count.toString();
	}

	@ResponseBody
	@RequestMapping("/addClient.form")
	public String addClient(Client client){
		Integer count = clientService.addClient(client);
		System.out.println(client.getId()+"-----------------");
		return count.toString();
	}

	@ResponseBody
	@RequestMapping("/searchClientByNickName.json")
	public List<Client> searchClientByNickName(String nick_name){
		List<Client> clients = clientService.searchClientByNickName(nick_name);
		return clients;
	}
}

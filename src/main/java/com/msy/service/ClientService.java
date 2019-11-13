package com.msy.service;

import com.msy.dao.ClientDao;
import com.msy.entity.Client;
import com.msy.entity.TableModel;
import com.msy.entity.wx.AccessToken;
import com.msy.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

	@Autowired
	private ClientDao clientDao;

	public PageResult<List<Client>> listClient(TableModel<Client> tableModel){
		tableModel.setPage((tableModel.getPage()-1)*tableModel.getLimit());
		List<Client> clientList = clientDao.listClient(tableModel);
		Long count = clientDao.listClientCount(tableModel);
		PageResult pr = new PageResult();
		pr.setCode(0);
		pr.setCount(count);
		pr.setMsg("数据查询成功");
		pr.setData(clientList);
		return pr;
	}

	@Transactional(rollbackFor = Exception.class)
	public Integer addClient(Client client){
		return clientDao.addClient(client);
	}

	public Client selectClient(String clientId) {
		return clientDao.selectClient(clientId);
	}

	@Transactional(rollbackFor = Exception.class)
	public Integer updateClient(Client client){
		return clientDao.updateClient(client);
	}

	public Client findClientByOpenIdAndToken(String token, String openId) {
		return clientDao.findClientByOpenIdAndToken(token,openId);
	}

	public Client findClientByToken(AccessToken accessToken) {
		return clientDao.findClientByToken(accessToken);
	}

	public List<Client> searchClientByNickName(String nick_name) {
		return clientDao.searchClientByNickName("%"+nick_name+"%");
	}

	public List<Client> findClientByTelephone(String telephone) {
		return clientDao.findClientByTelephone(telephone);
	}
}

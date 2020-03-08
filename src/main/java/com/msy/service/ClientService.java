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
		client.setVerified("未认证");
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

	public Client findClientByOpenId(String openid){
		return clientDao.findClientByOpenID(openid);
	}

	public boolean updateRegisterClient(Client registerClient, Integer id) {
		registerClient.setId(id);
		return clientDao.updateRegisterClient(registerClient);
	}

	public void addImg1(String fileVal, Integer id) {
		clientDao.addImg1(fileVal,id);
	}

	public void addImg2(String fileVal, Integer id) {
		clientDao.addImg2(fileVal,id);
	}

	public void addImg3(String fileVal, Integer id) {
		clientDao.addImg3(fileVal,id);
	}

	public void addImg4(String fileVal, Integer id) {
		clientDao.addImg4(fileVal,id);
	}

	public void addImg5(String fileVal, Integer id) {
		clientDao.addImg5(fileVal,id);
	}

	public Integer findVerifiedState() {
		return clientDao.findVerifiedState();
	}

	public Integer updateVerified(Integer verified) {
		return clientDao.updateVerified(verified);
	}
}

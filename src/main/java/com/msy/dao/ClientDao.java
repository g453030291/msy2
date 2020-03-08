package com.msy.dao;

import com.msy.entity.Client;
import com.msy.entity.TableModel;
import com.msy.entity.wx.AccessToken;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDao {

	List<Client> listClient(TableModel<Client> tableModel);

	Long listClientCount(TableModel<Client> tableModel);

	@Insert("insert into client(name,sex,telephone1,telephone2,telephone3,store_name,address," +
			"verified,level,create_date,language,country,province,city,district,img1,img2,img3,img4,img5," +
			"access_token,expires_in,refresh_token,openid,scope,nickname,headimgurl,privilege) " +
			"values(#{name},#{sex},#{telephone1},#{telephone2},#{telephone3},#{store_name},#{address}," +
			"#{verified},#{level},#{create_date},#{language},#{country},#{province},#{city},#{district},#{img1},#{img2}," +
			"#{img3},#{img4},#{img5},#{access_token},#{expires_in},#{refresh_token},#{openid},#{scope},#{nickname}," +
			"#{headimgurl},#{privilege})")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
	int addClient(Client client);

	@Select("select * from client where id = #{0}")
	Client selectClient(String clientId);

	@Update("update client set name = #{name},sex=#{sex},telephone1=#{telephone1},telephone2=#{telephone2},telephone3=#{telephone3},store_name=#{store_name},address=#{address},level=#{level},province=#{province},city=#{city},district=#{district} where id = #{id}")
	Integer updateClient(Client client);

	@Select("select * from client where access_token = #{token} and openid = #{openId}")
	Client findClientByOpenIdAndToken(String token, String openId);

	@Select("select * from client where openid = #{openid}")
	Client findClientByToken(AccessToken accessToken);

	@Select("select * from client where nickname like #{0}")
	List<Client> searchClientByNickName(String nick_name);

	@Select("select * from client where telephone1 = #{0} or telephone2 = #{0} or telephone3 = #{0}")
	List<Client> findClientByTelephone(String telephone);

	@Select("select * from client where openid = #{0}")
	Client findClientByOpenID(String openid);

	@Update("update client set name=#{name},telephone1=#{telephone1},id_card=#{id_card},sex=#{sex},send_province=#{send_province},send_city=#{send_city},send_district=#{send_district},address=#{address}" +
			"where id = #{id}")
	boolean updateRegisterClient(Client registerClient);

	@Update("update client set img1 = #{0} where id = #{1}")
	void addImg1(String fileVal, Integer id);

	@Update("update client set img2 = #{0} where id = #{1}")
	void addImg2(String fileVal, Integer id);

	@Update("update client set img3 = #{0} where id = #{1}")
	void addImg3(String fileVal, Integer id);

	@Update("update client set img4 = #{0} where id = #{1}")
	void addImg4(String fileVal, Integer id);

	@Update("update client set img5 = #{0} where id = #{1}")
	void addImg5(String fileVal, Integer id);

	@Select("select verified from verified where id = 1")
	Integer findVerifiedState();

	@Update("update verified set verified=#{verified} where id = 1")
	Integer updateVerified(Integer verified);
}

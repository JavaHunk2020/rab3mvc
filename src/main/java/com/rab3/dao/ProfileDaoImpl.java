package com.rab3.dao;

import java.io.IOException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.rab3.dao.entity.ProfileEntity;

//java8
@Repository
public class ProfileDaoImpl  implements ProfileDao{
	
	@Autowired
	@Qualifier("pkdataSource")
	private DataSource ds;
	
	@Override
	public String forgetPassword(String email) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		String sql = "select password  from profiles_tbl where email =?";
		String password=null;
		try {
			 password = jdbcTemplate.queryForObject(sql, new Object[] { email }, String.class);
		} catch (DataAccessException e) {
		}
		return password;
	}
	
	@Override
	public String saveProfile(ProfileEntity profileEntity) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		Timestamp timestamp=new Timestamp(new Date().getTime());
        LobHandler lobHandler=new DefaultLobHandler();
        SqlLobValue sqlLobValue=null;
		try {
			sqlLobValue = new SqlLobValue(profileEntity.getPhoto().getBytes(),lobHandler);
		} catch (IOException e) {
			e.printStackTrace();
		}
        jdbcTemplate.update("insert into profiles_tbl(username,password,name,email,gender,photo,doe)  values(?,?,?,?,?,?,?)",
				new Object[]{profileEntity.getUsername(),profileEntity.getPassword(),profileEntity.getName(),
						profileEntity.getEmail(),profileEntity.getGender(),sqlLobValue,timestamp},
				new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.BLOB,Types.TIMESTAMP});
		return "success";
	}
	
	@Override
	public byte[] findPhotoById(int aid) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		String sql = "select photo from profiles_tbl where aid =" + aid;
		byte[]  photo = jdbcTemplate.queryForObject(sql, byte[].class);
		return photo;
	}
	
	@Override
	public ProfileEntity findById(int aid) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		String sql = "select  aid,username,password,name,email,gender,doe,role from profiles_tbl where aid =" + aid;
		ProfileEntity profileEntity = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ProfileEntity.class));
		return profileEntity;
	}
	
	@Override
	public String update(ProfileEntity profileEntity) {
		 LobHandler lobHandler=new DefaultLobHandler();
	        SqlLobValue sqlLobValue=null;
			JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
			try {
				if(profileEntity.getPhoto()==null || profileEntity.getPhoto().getBytes().length<3) {
					jdbcTemplate.update("update profiles_tbl set username=? ,name =? ,email =? ,gender =?  where aid =?",
							new Object[]{profileEntity.getUsername(),profileEntity.getName(),
									profileEntity.getEmail(),profileEntity.getGender(),profileEntity.getAid()},
							new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER});
				}else {
					 sqlLobValue = new SqlLobValue(profileEntity.getPhoto().getBytes(),lobHandler);
					jdbcTemplate.update("update profiles_tbl set username=? ,name =? ,email =? ,gender =? ,photo= ?  where aid =?",
							new Object[]{profileEntity.getUsername(),profileEntity.getName(),
									profileEntity.getEmail(),profileEntity.getGender(),sqlLobValue,profileEntity.getAid()},
							new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.BLOB,Types.INTEGER});
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return "success";
	}

	@Override
	public List<ProfileEntity> findAll() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		List<ProfileEntity> profileEntities = jdbcTemplate.query(
				"select  aid,username,password,name,email,gender,doe,role  from profiles_tbl",
				new BeanPropertyRowMapper<>(ProfileEntity.class));
		return profileEntities;
	}

}

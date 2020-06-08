package com.rab3.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rab3.controller.dto.ProfileDTO;
import com.rab3.dao.ProfileDao;
import com.rab3.dao.entity.ProfileEntity;

@Service
public class ProfileServiceImpl  implements  ProfileService{
   
	@Autowired
	@Qualifier("profileOrmDaoImpl")
	private ProfileDao profileDao;
	
	@Override
	public  ProfileDTO auth(String username, String password) {
		ProfileEntity result=profileDao.auth(username,password);
		ProfileDTO profileDTO=new ProfileDTO();
		BeanUtils.copyProperties(result, profileDTO);
		return profileDTO;
	}
	
	
	@Override
	public byte[] findPhotoById(int aid) {
		byte[] imaga=profileDao.findPhotoById(aid);
		return imaga;
	}
	
	@Override
	public String findPassword(String email) {
		String result=profileDao.forgetPassword(email);
		return result;
	}
	
	@Override
	public String persist(ProfileDTO profileDTO) {
		ProfileEntity profileEntity=new ProfileEntity();
		BeanUtils.copyProperties(profileDTO, profileEntity);
		try {
			profileEntity.setHphoto(profileDTO.getPhoto().getBytes());
			profileEntity.setRole("customer");
			profileEntity.setDoe(new Timestamp(new Date().getTime()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String result=profileDao.saveProfile(profileEntity);
		return result;
	}
	
	@Override
	public ProfileDTO findProfileById(int aid) {
		ProfileEntity entity=profileDao.findById(aid);
		ProfileDTO profileDTO=new  ProfileDTO();
		BeanUtils.copyProperties(entity, profileDTO);
		return profileDTO;
	}
	
	@Override
public 	String updateProfila(ProfileDTO profileDTO) {
		ProfileEntity profileEntity=new ProfileEntity();
		BeanUtils.copyProperties(profileDTO, profileEntity);
		String result=profileDao.update(profileEntity);
		return result;
	}
	
	@Override
	public List<ProfileDTO> findProfiles(){
		 List<ProfileEntity> lista=profileDao.findAll();
		 List<ProfileDTO> profileDTOs=new ArrayList<>();
		 for(ProfileEntity entity:lista) {
			 ProfileDTO profileDTO=new  ProfileDTO();
				BeanUtils.copyProperties(entity, profileDTO);
				profileDTOs.add(profileDTO);
		 }
		 return profileDTOs;
	}
}

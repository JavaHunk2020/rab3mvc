package com.rab3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rab3.controller.dto.ProfileDTO;
import com.rab3.dao.ProfileDao;

@Service
public class ProfileServiceImpl  implements  ProfileService{
   
	@Autowired
	private ProfileDao profileDao;
	
	@Override
	public String findPassword(String email) {
		String result=profileDao.forgetPassword(email);
		return result;
	}
	
	@Override
	public String persist(ProfileDTO profileDTO) {
		String result=profileDao.saveProfile(profileDTO);
		return result;
	}
	
	@Override
	public ProfileDTO findProfileById(int aid) {
		ProfileDTO profileDTO=profileDao.findById(aid);
		return profileDTO;
	}
	
	@Override
public 	String updateProfila(ProfileDTO profileDTO) {
		String result=profileDao.update(profileDTO);
		return result;
	}
	
	@Override
	public List<ProfileDTO> findProfiles(){
		 List<ProfileDTO> profileDTOs=profileDao.findAll();
		 return profileDTOs;
	}
}

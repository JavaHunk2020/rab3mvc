package com.rab3.dao;

import java.util.List;

import com.rab3.dao.entity.ProfileEntity;

public interface ProfileDao {

	String forgetPassword(String email);
	String saveProfile(ProfileEntity profileEntity);
	ProfileEntity findById(int aid);
	String update(ProfileEntity profileEntity);
	List<ProfileEntity> findAll();
	byte[] findPhotoById(int aid);
	//This default version was introduced in java8
	default ProfileEntity auth(String username, String password) {
		return null;
	}
}

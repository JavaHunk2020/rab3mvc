package com.rab3.service;

import java.util.List;

import com.rab3.controller.dto.ProfileDTO;

public interface ProfileService {

	public abstract String findPassword(String email);

	String persist(ProfileDTO profileDTO);

	ProfileDTO findProfileById(int aid);

	String updateProfila(ProfileDTO profileDTO);

	List<ProfileDTO> findProfiles();

	byte[] findPhotoById(int aid);

}

package com.rab3.dao.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="profile_hbm_tbl")
public class ProfileEntity {
	private int aid;
	private String username;
	private String password;
	private String name;
	private String email;
	private String gender;
	private MultipartFile photo;
	private Timestamp doe;
	private String role;
	private byte[]  hphoto;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getDoe() {
		return doe;
	}

	public void setDoe(Timestamp doe) {
		this.doe = doe;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	@Column(length=90)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(length=23)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length=100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	@Transient
	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	
	
  @Column(columnDefinition="longblob")
	public byte[] getHphoto() {
		return hphoto;
	}

	public void setHphoto(byte[] hphoto) {
		this.hphoto = hphoto;
	}

	@Override
	public String toString() {
		return "ProfileDTO [aid=" + aid + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", email=" + email + ", gender=" + gender + ", photo=" + photo + "]";
	}
}

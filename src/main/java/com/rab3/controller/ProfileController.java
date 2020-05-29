package com.rab3.controller;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rab3.controller.dto.ProfileDTO;

@Controller
public class ProfileController {
	

	@Autowired
	@Qualifier("pkdataSource")
	private DataSource ds;
	
	
	@GetMapping("/editProfile")
	public String editProfile(@RequestParam int aid,Model  model) {
			JdbcTemplate jdbcTemplate=new JdbcTemplate(ds);
			String sql="select  *  from profiles_tbl where aid ="+aid;
			ProfileDTO profileDTO =jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(ProfileDTO.class));
			model.addAttribute("profileDTO", profileDTO);
			return "editProfile";
	}
	
	@PostMapping("/updateProfile")
	public String postEditProfile(@ModelAttribute ProfileDTO profileDTO,Model  model) {
		//Code to save all these data into the database!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 ///JDBC Programming
		//
		String sql="update profiles_tbl set username=? ,name =? ,email =? ,gender =? ,photo= ?  where aid =?";
		Object[] data= {profileDTO.getUsername(),profileDTO.getName(),profileDTO.getEmail(),profileDTO.getGender(),profileDTO.getPhoto(),profileDTO.getAid()};
		JdbcTemplate jdbcTemplate=new JdbcTemplate(ds);
		jdbcTemplate.update(sql,data);
		
		model.addAttribute("magic", profileDTO);
		model.addAttribute("msg", "Your profile is successfully updated!!");
		return "home";
	}
	
	@GetMapping("/profiles")
	public String showProfiles(Model  model) {
			JdbcTemplate jdbcTemplate=new JdbcTemplate(ds);
			List<ProfileDTO> profileDTOs=jdbcTemplate.query("select  aid,username,password,name,email,gender,photo,doe,role  from profiles_tbl",
					new BeanPropertyRowMapper(ProfileDTO.class));
			model.addAttribute("profileDTOs", profileDTOs);
			return "profiles";
	}
}

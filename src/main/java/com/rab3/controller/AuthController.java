package com.rab3.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rab3.controller.dto.ProfileDTO;
import com.rab3.utils.AppDBConnection;

@Controller
public class AuthController {
	
	@Autowired
	@Qualifier("pkdataSource")
	private DataSource ds;
	
	@GetMapping("/foo")
	public String helloWorld() {
		return "aha";   //   /aha.jsp
	}
	
	//action=auth
	//method = POST
	@PostMapping("/auth")
	public String auth(@RequestParam String username,@RequestParam String password,Model model,HttpSession  session) {
			JdbcTemplate jdbcTemplate=new JdbcTemplate(ds);
			String sql="select  *  from profiles_tbl where username =? and password = ?";
			List<ProfileDTO> profileDTOs=jdbcTemplate.query(sql, new Object[] {username,password},new BeanPropertyRowMapper(ProfileDTO.class));
			if(profileDTOs.size()==1) {
				session.setAttribute("profileDTO", profileDTOs.get(0));
				model.addAttribute("magic", profileDTOs.get(0));
				return "home";
			}else {
				model.addAttribute("msg", "Sorry!! username and password are not valid!!!!!!!!!!!!!!!");
				return "login";
			}
	}

}

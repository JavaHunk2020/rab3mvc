package com.rab3.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rab3.controller.dto.ProfileDTO;
import com.rab3.dao.ProfileDao;

@Controller
public class ProfileController {

	@Autowired
	private ProfileDao profileDao;

	@PostMapping("/forgotPassword")
	public String forgetPasswordPage(@RequestParam String email, Model model) {
		   String passsoword=profileDao.forgetPassword(email);
			if(passsoword!=null) {
				model.addAttribute("password", "Hello your password is  =  " + passsoword);				
			}else {
				model.addAttribute("password", "Sorry!! email is not valid!!!!!!!!!!!!!!!");
			}
		   return "forgotPassword";
	}

	@GetMapping("/forgetPass")
	public String forgetPasswordPage() {
		return "forgotPassword";
	}

	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}

	@PostMapping("/register")
	public String registerPostPage(@ModelAttribute ProfileDTO profileDTO, Model model) {
        profileDao.saveProfile(profileDTO);
		model.addAttribute("msg", "You are successfully registered!!!");
		return "register";
	}

	@GetMapping("/editProfile")
	public String editProfile(@RequestParam int aid, Model model) {
		ProfileDTO profileDTO = profileDao.findById(aid);
		model.addAttribute("profileDTO", profileDTO);
		return "editProfile";
	}

	@PostMapping("/updateProfile")
	public String postEditProfile(@ModelAttribute ProfileDTO profileDTO, Model model) {
		profileDao.update(profileDTO);
		model.addAttribute("magic", profileDTO);
		model.addAttribute("msg", "Your profile is successfully updated!!");
		return "home";
	}

	@GetMapping("/profiles")
	public String showProfiles(Model model) {
		List<ProfileDTO> profileDTOs = profileDao.findAll();
		model.addAttribute("profileDTOs", profileDTOs);
		return "profiles";
	}
}

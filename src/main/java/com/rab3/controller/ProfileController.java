package com.rab3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rab3.controller.dto.ProfileDTO;
import com.rab3.service.ProfileService;

@Controller
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	
	
	@GetMapping("/imageLoader")
	public void showImage(@RequestParam int aid,
			HttpServletResponse httpServletResponse) throws IOException {
		byte[] photo = profileService.findPhotoById(aid);
		//I am going to write photo into reponse
		httpServletResponse.setContentType("image/png");
		ServletOutputStream outputStream=httpServletResponse.getOutputStream();
		if(photo!=null && photo.length>0) {
			//writtng photo as a byte array into the response body
			outputStream.write(photo);
		}else {
			outputStream.write(new byte[] {});
		}
		//go to the client
		outputStream.flush();
	}

	@PostMapping("/forgotPassword")
	public String forgetPasswordPage(@RequestParam String email, Model model) {
		   String passsoword=profileService.findPassword(email);
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
		profileService.persist(profileDTO);
		model.addAttribute("msg", "You are successfully registered!!!");
		return "register";
	}

	@GetMapping("/editProfile")
	public String editProfile(@RequestParam int aid, Model model) {
		ProfileDTO profileDTO = profileService.findProfileById(aid);
		model.addAttribute("profileDTO", profileDTO);
		return "editProfile";
	}

	@PostMapping("/updateProfile")
	public String postEditProfile(@ModelAttribute ProfileDTO profileDTO, Model model) {
		profileService.updateProfila(profileDTO);
		model.addAttribute("magic", profileDTO);
		model.addAttribute("msg", "Your profile is successfully updated!!");
		return "home";
	}

	@GetMapping("/profiles")
	public String showProfiles(Model model) {
		List<ProfileDTO> profileDTOs = profileService.findProfiles();
		model.addAttribute("profileDTOs", profileDTOs);
		return "profiles";
	}
}

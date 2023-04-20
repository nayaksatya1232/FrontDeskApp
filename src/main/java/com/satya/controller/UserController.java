package com.satya.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.satya.binding.DashboardData;
import com.satya.binding.LoginForm;
import com.satya.binding.RegistrationForm;
import com.satya.binding.UnlockForm;
import com.satya.service.EnquiryService;
import com.satya.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private EnquiryService enqService;

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginData", new LoginForm());
		return "login";
	}

	@PostMapping("/login")
	public String loginHandler(@ModelAttribute("loginData") LoginForm loginData, HttpSession session,Model model) {
		boolean status = this.userService.login(loginData);

		if (status) {
			DashboardData dashboard = this.enqService.getDashboard(loginData);
			if (dashboard != null) {
				session.setAttribute("dashData", dashboard);
				session.setAttribute("currentUser", loginData);
				return "dashboard";
			}
		} else {
			model.addAttribute("failed", "Login Failed..");
		}
		return "login";
	}

	@GetMapping("/register")
	public String signup(Model model) {
		model.addAttribute("regForm", new RegistrationForm());
		return "signup";
	}

	@PostMapping("/register")
	public String signupHandler(@ModelAttribute("regForm") RegistrationForm regForm, Model model) {
		boolean status = this.userService.signup(regForm);
		if (status) {
			model.addAttribute("success", "Successfully Registered!, Check Your Email To Unlock Your Account");
		} else {
			model.addAttribute("failure", "Provide A Different Email! Not Registered..");
		}
		return "signup";
	}

	@GetMapping("/unlock")
	public String unlock(Model model) {
		model.addAttribute("unlockForm", new UnlockForm());
		return "unlock";
	}

	@PostMapping("/unlock")
	public String unlockHandler(@ModelAttribute("unlockForm") UnlockForm unlockForm, Model model) {
		if (!unlockForm.getNewPwd().equals(unlockForm.getCnfPwd())) {
			model.addAttribute("failure", "Password Mismatch..");
			return "unlock";
		}
		boolean status = this.userService.unlock(unlockForm);
		if (status) {
			model.addAttribute("success", "Your Account has been Unlocked..");
			model.addAttribute("loginData", new LoginForm());
			return "login";
		} else {
			model.addAttribute("failure", "Your Password is wrong..");
		}
		return "unlock";
	}

	@GetMapping("/forgot")
	public String pwdReset() {
		return "forgotpwd";
	}

	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}
}

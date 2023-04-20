package com.satya.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satya.binding.LoginForm;
import com.satya.binding.RegistrationForm;
import com.satya.binding.UnlockForm;
import com.satya.entity.User;
import com.satya.repo.UserRepo;
import com.satya.util.EmailSender;
import com.satya.util.PasswordGenerator;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private EmailSender mailSender;

	@Override
	public boolean login(LoginForm loginData) {
		List<User> userList = this.userRepo.findByEmailAndPwd(loginData.getEmail(), loginData.getPwd());
		User user = null;
		if (!userList.isEmpty())
			user = userList.get(0);

		if (user == null || user.getAccountStatus().equals("LOCKED")) {
			return false;
		}
		return true;
	}

	@Override
	public boolean signup(RegistrationForm registrationData) {
		if (!this.userRepo.findByEmail(registrationData.getEmail()).isEmpty()) {
			return false;
		}
		User user = new User();
		BeanUtils.copyProperties(registrationData, user);

		String tmpPwd = PasswordGenerator.generatePassword();
		user.setAccountStatus("LOCKED");
		user.setPwd(tmpPwd);

		this.userRepo.save(user);

		String to = registrationData.getEmail();
		String subject = "--Unlock Account--";
		StringBuffer body = new StringBuffer("");
		body.append("Temporary Password: " + tmpPwd);
		body.append("<br>");
		body.append("<a href=\"http://localhost:8080/unlock?email=" + to + "\">Click here to Unlock Account..</a>");

		boolean isSent = this.mailSender.sendMail(subject, body.toString(), to);
		return isSent;
	}

	@Override
	public String forgot(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean unlock(UnlockForm unlockData) {
		List<User> userList = this.userRepo.findByPwd(unlockData.getTmpPwd());
		User user = null;
		if (!userList.isEmpty())
			user = userList.get(0);
		
		if (user == null || user.getAccountStatus().equals("UNLOCKED")) {
			return false;
		}
		user.setPwd(unlockData.getNewPwd());
		user.setAccountStatus("UNLOCKED");
		this.userRepo.save(user);
		return true;
	}

}

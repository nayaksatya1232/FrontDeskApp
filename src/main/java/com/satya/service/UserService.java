package com.satya.service;

import com.satya.binding.LoginForm;
import com.satya.binding.RegistrationForm;
import com.satya.binding.UnlockForm;

public interface UserService {
	public boolean login(LoginForm loginData);
	public boolean signup(RegistrationForm registrationData);
	public String forgot(String email);
	public boolean unlock(UnlockForm unlockData);
}

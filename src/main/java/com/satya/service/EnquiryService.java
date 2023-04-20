package com.satya.service;

import java.util.List;

import com.satya.binding.AddEnquiryForm;
import com.satya.binding.DashboardData;
import com.satya.binding.EnquiryCriteria;
import com.satya.binding.LoginForm;
import com.satya.entity.StudentEnquiry;

public interface EnquiryService {
	public DashboardData getDashboard(LoginForm loginData);
	public boolean addEnquiry(AddEnquiryForm enquiryData);
	public List<StudentEnquiry> getEnquiries(Integer userId);
	public List<StudentEnquiry> getEnquiries(Integer userId,EnquiryCriteria criteria);
	public StudentEnquiry updateEnquiry();
}

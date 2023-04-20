package com.satya.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satya.binding.AddEnquiryForm;
import com.satya.binding.DashboardData;
import com.satya.binding.EnquiryCriteria;
import com.satya.binding.LoginForm;
import com.satya.entity.StudentEnquiry;
import com.satya.entity.User;
import com.satya.repo.StudentEnquiryRepo;
import com.satya.repo.UserRepo;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private StudentEnquiryRepo enqRepo;
	@Autowired
	private UserRepo usrRepo;

	@Override
	public DashboardData getDashboard(LoginForm loginData) {
		List<User> userList = this.usrRepo.findByEmail(loginData.getEmail());
		int userId;
		if (!userList.isEmpty()) {
			userId = userList.get(0).getId();
			int total = this.enqRepo.findAllEnq(userId).size();
			int enrolled = this.enqRepo.getTotalEnrolled(userId).size();
			DashboardData data = new DashboardData();
			data.setTotalEnrolled(total);
			data.setTotalEnrolled(enrolled);
			data.setLost(total-enrolled);
			return data;
		}
		return null;
	}

	@Override
	public boolean addEnquiry(AddEnquiryForm enqData) {
		StudentEnquiry std = new StudentEnquiry();
		if(enqData.getStdName()==null || enqData.getPhNo()==null||enqData.getCourseName()==null
				||enqData.getClassMode()==null||enqData.getEnqStatus()==null) {
			return false;
		}
		BeanUtils.copyProperties(enqData, std);
		this.enqRepo.save(std);
		return true;
	}

	@Override
	public List<StudentEnquiry> getEnquiries(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentEnquiry> getEnquiries(Integer userId, EnquiryCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentEnquiry updateEnquiry() {
		// TODO Auto-generated method stub
		return null;
	}

}

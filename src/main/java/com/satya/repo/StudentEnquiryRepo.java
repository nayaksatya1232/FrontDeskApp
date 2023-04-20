package com.satya.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.satya.entity.StudentEnquiry;
import com.satya.entity.User;

public interface StudentEnquiryRepo extends JpaRepository<StudentEnquiry, Integer> {
	@Query("select enq.user from StudentEnquiry enq where enq.user.id=:id")
	public List<User> findAllEnq(@Param("id")int userId);

	@Query("select enq from StudentEnquiry enq where enq.user.id=:id and enq.enqStatus='Enrolled'")
	public List<User> getTotalEnrolled(@Param("id") int id);
}

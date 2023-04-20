package com.satya.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.satya.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	public List<User> findByEmail(String email);
	public List<User> findByPwd(String passwd);
	@Query("select u from User u where u.email=:e and u.pwd=:p")
	public List<User> findByEmailAndPwd(@Param("e")String email,@Param("p")String pwd);
}

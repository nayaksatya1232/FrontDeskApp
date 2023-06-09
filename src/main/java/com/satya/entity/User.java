package com.satya.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="Staff_User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String userName;
	private String email;
	private String phNo;
	private String pwd;
	private String accountStatus;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<StudentEnquiry> enquiries;
}

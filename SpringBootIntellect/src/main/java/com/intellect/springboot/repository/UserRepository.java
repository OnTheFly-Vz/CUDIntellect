package com.intellect.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intellect.springboot.jpa.UserApp;

public interface UserRepository extends JpaRepository<UserApp,String>{
	
	UserApp findByeMailidAndIsActive(String eMailId,boolean status);
	
	UserApp findByIdAndIsActive(Long id,boolean status);
	
	UserApp findById(Long id);

}

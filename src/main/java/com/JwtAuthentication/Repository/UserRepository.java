package com.JwtAuthentication.Repository;

import org.springframework.data.repository.CrudRepository;

import com.JwtAuthentication.Entity.MyUser;

public interface UserRepository extends CrudRepository<MyUser, Integer>{
	
	public MyUser findByUsername(String username); 

}

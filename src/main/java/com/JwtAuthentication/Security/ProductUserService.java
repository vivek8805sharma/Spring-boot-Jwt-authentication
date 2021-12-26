package com.JwtAuthentication.Security;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.JwtAuthentication.Dto.UserDto;
import com.JwtAuthentication.Entity.MyUser;
import com.JwtAuthentication.Repository.UserRepository;

@Service
public class ProductUserService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser myUser= userRepo.findByUsername(username);
		return myUser;
		
	}
	
	public MyUser addNewUser(UserDto user) {
		MyUser newUser=new MyUser(user.getUsername(),user.getPassword());
		return userRepo.save(newUser);
		
	}

}

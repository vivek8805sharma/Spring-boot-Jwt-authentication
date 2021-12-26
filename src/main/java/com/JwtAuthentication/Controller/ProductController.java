package com.JwtAuthentication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JwtAuthentication.Dto.ProductDto;
import com.JwtAuthentication.Dto.UserDto;
import com.JwtAuthentication.Entity.LoginDetails;
import com.JwtAuthentication.Entity.MyUser;
import com.JwtAuthentication.Entity.Product;
import com.JwtAuthentication.Security.JwtUtil;
import com.JwtAuthentication.Security.ProductUserService;
import com.JwtAuthentication.Service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	AuthenticationManager auth;
	
	@Autowired
	ProductUserService myUserService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts(){
		return new ResponseEntity<List<Product>>(productService.getAllProducts(),HttpStatus.OK);
	}
	
	@PostMapping("/product")
	public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto product){
		return new ResponseEntity<Product>(productService.addNewProduct(product), HttpStatus.OK);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<MyUser> signIn(@RequestBody UserDto user){
		return new ResponseEntity<>(myUserService.addNewUser(user), HttpStatus.OK);
	}
	
	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDetails loginDetails) throws Exception{
		try {
			System.out.println(loginDetails.getPassword());
			auth.authenticate(new UsernamePasswordAuthenticationToken(loginDetails.getUsername(), loginDetails.getPassword()));

		}
		catch(BadCredentialsException e) {
			throw new Exception("Invalid username password",e);
		}
		
		UserDetails myuserDetails=myUserService.loadUserByUsername(loginDetails.getUsername());
		String jwt=jwtUtil.generateToken(myuserDetails);
		
		return new ResponseEntity<>(jwt,HttpStatus.OK);
	}

}

package com.JwtAuthentication.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.JwtAuthentication.Dto.ProductDto;
import com.JwtAuthentication.Entity.Product;
import com.JwtAuthentication.Repository.ProductRepository;

@Component
public class ProductService {
	
	@Autowired
	ProductRepository productRepo;
	
	public List<Product> getAllProducts(){
		return (List<Product>) productRepo.findAll();
	}
	
	public Product addNewProduct(ProductDto product) {
		Product newProduct=new Product(product.getName(), product.getQuantity(), product.getPrice());
		return productRepo.save(newProduct);
	}

}

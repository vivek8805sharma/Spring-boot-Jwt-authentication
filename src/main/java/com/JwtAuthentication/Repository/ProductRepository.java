package com.JwtAuthentication.Repository;

import org.springframework.data.repository.CrudRepository;

import com.JwtAuthentication.Entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

}

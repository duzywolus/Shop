package com.onlineshop.admin.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.common.entity.Product;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> listAll(){
		return (List<Product>) productRepository.findAll();
	}

}

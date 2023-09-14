package com.onlineshop.admin.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.common.entity.Brand;

@Service
public class BrandService {
	
	@Autowired
	private BrandRepository brandRepository;
	
	public List<Brand> listAll() {
		return (List<Brand>) brandRepository.findAll();
	}

}

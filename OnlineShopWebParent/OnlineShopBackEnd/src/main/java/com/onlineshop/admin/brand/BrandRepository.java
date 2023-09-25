package com.onlineshop.admin.brand;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.onlineshop.common.entity.Brand;

public interface BrandRepository extends PagingAndSortingRepository<Brand, Integer>, CrudRepository<Brand, Integer> {

	public Long countById(Integer id);
	
	public Brand findByName(String name);
}

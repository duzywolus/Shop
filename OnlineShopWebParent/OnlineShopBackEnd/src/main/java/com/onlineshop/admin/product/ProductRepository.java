package com.onlineshop.admin.product;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.onlineshop.common.entity.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

}

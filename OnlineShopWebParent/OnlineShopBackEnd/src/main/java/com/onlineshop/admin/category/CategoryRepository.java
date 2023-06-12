package com.onlineshop.admin.category;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.onlineshop.common.entity.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {

}

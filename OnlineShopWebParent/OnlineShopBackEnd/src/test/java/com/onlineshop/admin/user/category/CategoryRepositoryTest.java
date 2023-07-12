package com.onlineshop.admin.user.category;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.onlineshop.admin.category.CategoryRepository;
import com.onlineshop.common.entity.Category;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	public void testCreateRootCategory() {
		Category category = new Category("Electronics");
		Category savedCategory = categoryRepository.save(category);

		assertThat(savedCategory.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateSubCategory() {
		Category parent = new Category(7);
		Category subCategory = new Category("iPhone", parent);
		Category savedCategory = categoryRepository.save(subCategory);

		assertThat(savedCategory.getId()).isGreaterThan(0);
	}

	@Test
	public void testGetCategory() {
		Category category = categoryRepository.findById(2).get();
		System.out.println(category.getName());

		Set<Category> children = category.getChildren();

		for (Category subCategory : children) {
			System.out.println(subCategory.getName());
		}

		assertThat(children.size()).isGreaterThan(0);
	}

	@Test
	public void testPrintHierarchicalCategories() {
		Iterable<Category> categories = categoryRepository.findAll();

		for (Category category : categories) {
			if (category.getParent() == null) {
				System.out.println(category.getName());

				Set<Category> children = category.getChildren();

				for (Category subCategory : children) {
					System.out.println("--" + subCategory.getName());
					printChildren(subCategory, 1);
				}
			}
		}

	}
	
	private void printChildren(Category parent, int subLevel) {
		int newSubLevel = subLevel +1;
		Set<Category> children = parent.getChildren();
		
		for (Category subCategory : children) {
			for (int i = 0; i < newSubLevel; i++) {
				System.out.print("--");
			}
			
			System.out.println(subCategory.getName());
			
			printChildren(subCategory, newSubLevel);
		}
		
	}
	
	@Test
	public void testListRootCategories() {
		List<Category> rootCategories = categoryRepository.findRootCategories();
		
		rootCategories.forEach(cat -> System.out.println(cat.getName()));
	}
	
	@Test
	public void testFindByName() {
		String name = "Computers";
		Category category = categoryRepository.findByName(name);
		
		assertThat(category).isNotNull();
		assertThat(category.getName()).isEqualTo(name);
	}
	
	@Test
	public void testFindByAlias() {
		String alias = "Electronics";
		Category category = categoryRepository.findByAlias(alias);
		
		assertThat(category).isNotNull();
		assertThat(category.getAlias()).isEqualTo(alias);
	}

}

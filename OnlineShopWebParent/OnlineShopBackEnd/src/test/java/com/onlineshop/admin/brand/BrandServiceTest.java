package com.onlineshop.admin.brand;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.onlineshop.common.entity.Brand;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class BrandServiceTest {

	@MockBean
	private BrandRepository brandRepository;
	
	@InjectMocks
	private BrandService brandService;
	
	@Test
	public void testCheckUniqueInNewModeReturnDuplicate(){
		Integer id = null;
		String name = "Acer";
		Brand brand = new Brand(name);
		
		Mockito.when(brandRepository.findByName(name)).thenReturn(brand);
		
		String result = brandService.checkUnique(id, name);
		assertThat(result).isEqualTo("Duplicate");
	}
	
	@Test
	public void testCheckUniqueInNewModelReturnOK() {
		Integer id = null;
		String name = "Intel";
		
		Mockito.when(brandRepository.findByName(name)).thenReturn(null);
		
		String result = brandService.checkUnique(id, name);
		assertThat(result).isEqualTo("OK");
		
		
		}
	
	@Test
	public void testCheckUniqueInEditModeReturnDuplicate() {
		Integer id = 1;
		String name =  "Canon";
		Brand brand = new Brand(name);
		
		Mockito.when(brandRepository.findByName(name)).thenReturn(brand);
		
		String result = brandService.checkUnique(2, "Canon");
		assertThat(result).isEqualTo("Duplicate");
	}
	
	@Test
	public void testUniqueInEditModeReturnOK() {
		Integer id = 1;
		String name = "Acer";
		Brand brand = new Brand(name);
		
		Mockito.when(brandRepository.findByName(name)).thenReturn(brand);
		
		String result = brandService.checkUnique(1, "Canon");
		assertThat(result).isEqualTo("OK");
	}
}

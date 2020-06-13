package com.productapi.resources;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productapi.domain.Product;
import com.productapi.dto.ProductDTO;
import com.productapi.services.ProductService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ObjectMapper objMapper;
	
	@ApiOperation(value = "Lista todos os produtos da base")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Product>> list() {
		List<Product> products = productService.list();
		
		return ResponseEntity.ok().body(products);
	}
	
	@ApiOperation(value = "Cria um produto")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Product> create(@RequestBody Map<String, Object> product) {	 	
		ProductDTO productDTO = objMapper.convertValue(product, ProductDTO.class);
		
		Product newProduct = productService.create(productDTO);
		
		return ResponseEntity.ok().body(newProduct);
	}
	
	@ApiOperation(value = "Edita um produto dado um id")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Product> edit(@RequestBody Map<String, Object> body, @PathVariable Integer id) {
		ProductDTO productDTO = objMapper.convertValue(body, ProductDTO.class);
		
		Product product = productService.edit(id, productDTO);
		
		return ResponseEntity.ok().body(product);
	}
	
	@ApiOperation(value = "Deleta um produto dado um id") 
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void	 delete(@PathVariable Integer id) {
		productService.delete(id);
	}
}

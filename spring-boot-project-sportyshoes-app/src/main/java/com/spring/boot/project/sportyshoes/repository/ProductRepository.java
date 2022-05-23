package com.spring.boot.project.sportyshoes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sportyshoes.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer>{
	
	@Query(value = "select p from Product p where p.category=:category")
	List<Products> findAllByCategory(@Param("category") String category);

	@Query(value = "select p from Product p where p.productName=:name")
	Optional<Products> findByName(String name);
}

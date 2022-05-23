package com.spring.boot.project.sportyshoes.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.spring.boot.project.sportyshoes.model.Product;
import com.spring.boot.sportyshoes.model.products;
import com.sportyshoes.model.Purchase;
import com.sportyshoes.model.users;
import com.sportyshoes.service.productsService;
import com.sportyshoes.service.PurchaseService;
import com.sportyshoes.service.usersService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	productsService productsService;

	@Autowired
	usersService usersService;

	@Autowired
	private PurchaseService PurchaseService;

	@GetMapping("/productss")
	public ResponseEntity<List<products>> getAllproductss() {
		List<products> allproductss = productsService.getAllproductss();
		if (allproductss.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		ResponseEntity<List<products>> responseEntity = new ResponseEntity<List<products>>(allproductss, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/productss/categorize/{category}")
	public ResponseEntity<List<products>> getAllproductssBasedOnCategory(@PathVariable("category") String category) {
		System.out.println("Category to look for -> " + category);
		List<products> allproductssBasedOnCategory = productsService.getAllproductsBasedOnCatogary(category);
		if (allproductssBasedOnCategory.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		ResponseEntity<List<products>> responseEntity = new ResponseEntity<List<products>>(allproductssBasedOnCategory,
				HttpStatus.OK);
		return responseEntity;
	}

	@PostMapping("/productss")
	public ResponseEntity<products> addproducts(@RequestBody products products) {
		products temp = productsService.addproducts(products);
		if (temp == null) {
			return new ResponseEntity<products>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<products>(temp, HttpStatus.OK);

	}

	@GetMapping("/productss/{productsId}")
	public ResponseEntity<products> getproductsById(@PathVariable("productsId") int id) {
		Optional<products> products = productsService.getproductsById(id);
		if (!products.isPresent()) {
			return new ResponseEntity<products>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<products>(products.get(), HttpStatus.OK);
	}

	@DeleteMapping("/productss/{productsId}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("productsId") int id) {
		productsService.deleteproductsById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/userss")
	public ResponseEntity<List<users>> getAllSignedUpuserss() {
		List<users> allSignedUpuserss = usersService.allSignedUpuserss();
		if (allSignedUpuserss.isEmpty()) {
			return new ResponseEntity<List<users>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<users>>(allSignedUpuserss, HttpStatus.OK);

	}

	@GetMapping("/userss/{usersName}")
	public ResponseEntity<users> getSignedUpusers(@PathVariable String usersName) {
		Optional<users> signedUpusers = usersService.getSignedUpusersByName(usersName);
		if (!signedUpusers.isPresent()) {
			return new ResponseEntity<users>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<users>(signedUpusers.get(), HttpStatus.OK);
	}

	@GetMapping("/Purchase")
	public ResponseEntity<List<Purchase>> getPurchase() {
		List<Purchase> Purchase = PurchaseService.getAllPurchase();
		if (Purchase.isEmpty()) {
			return new ResponseEntity<List<Purchase>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Purchase>>(Purchase, HttpStatus.OK);

	}
	
	@GetMapping("/Purchase/category/{category}")
	public ResponseEntity<List<Purchase>> getPurchaseBasedOnCategory(@PathVariable String category) {
		List<Purchase> PurchaseBasedOnCategory = PurchaseService.getPurchaseBasedOnCategory(category);
		if (PurchaseBasedOnCategory.isEmpty()) {
			return new ResponseEntity<List<Purchase>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Purchase>>(PurchaseBasedOnCategory, HttpStatus.OK);

	}
	
	@GetMapping("/Purchase/date/{date}")
	public ResponseEntity<List<Purchase>> getPurchaseBasedOnDate(@PathVariable String date) throws ParseException {
		System.out.println("Date from url is : " + date);
		List<Purchase> PurchaseBasedOnCategory = PurchaseService.getPurchaseBasedOnDate(date);
		if (PurchaseBasedOnCategory.isEmpty()) {
			return new ResponseEntity<List<Purchase>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Purchase>>(PurchaseBasedOnCategory, HttpStatus.OK);

	}
	
}

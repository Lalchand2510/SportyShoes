package com.spring.boot.project.sportyshoes.controller;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoes.model.Product;
import com.sportyshoes.model.userss;
import com.sportyshoes.service.ProductService;
import com.sportyshoes.service.PurchaseReportService;
import com.sportyshoes.service.userssService;

@RestController
@RequestMapping("/usersss")
public class userssController {
	@Autowired
	private userssService userssService;

	@Autowired
	private ProductService productService;

	@Autowired
	private PurchaseService purchaseService;
	
	@PostMapping("/signup")
	public @ResponseBody String register(@RequestBody(required = false) usersss usersss) {
		if (userss == null) {
			return "Enter Valid userss Details - userss details should not be Null";
		}else if(userss.getuserssName() == null || userss.getuserssPassword()== null || userss.getuserssEmail() == null) {
			return "Enter Valid userss Details - All the fields(Name, Password, Email) are mandatory";
		}
		int strength = 10;
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
		String encodedPassword = bCryptPasswordEncoder.encode(userss.getuserssPassword());
		users.setuserssPassword(encodedPassword);
		users.setuserssName(users.getuserssName().toLowerCase());
		usersService.signUp(users);
		return "Signed Up Successfully!";
	}
	
	@PostMapping("/{userssId}/buy/{productName}")
	@Transactional
	public @ResponseBody String buyProductByName(@PathVariable(name = "usersId") int userssID,
			@PathVariable(name = "productName") String productName) {
		Optional<Product> product = productService.getProductByName(productName);
		if (product.isPresent()) {
			Optional<userss> users = userssService.getSignedUpuserssById(userssID);
			if (userss.isPresent()) {
				userss userss2 = users.get();
				userss2.addProduct(product.get());
				Product product2 = product.get();
				product2.adduserss(users.get());
				usersService.saveuserssWithProduct(userss2);
				productService.addProduct(product2);
				purchaseReportService.savePurchaseReport(product2.getProductName(), product2.getCategory(),
						product2.getProductPrice(), userss2.getuserssName(), userss2.getuserssEmail(), new Date());
				return "You have successfully bought : " + product.get().getProductName();
			} else {
				return "userss Not Found! to buy the Product";
			}
		}
		return "Product Not Found!";
	}



}

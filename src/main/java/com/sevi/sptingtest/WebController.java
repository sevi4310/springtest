package com.sevi.sptingtest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
	@RequestMapping(value = "/grindex")
	public String index() {
		return "index";
	}

	@RequestMapping("/view-grproducts")
	public String viewProducts() {
		return "view-grproducts";
	}

	@RequestMapping("/view-grproduct")
	public String viewProduct() {
		return "view-grproduct";
	}

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
}

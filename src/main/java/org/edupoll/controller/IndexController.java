package org.edupoll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {

	@GetMapping("/")
	public String indexHandle() {
		return "index";
	}
	
	@GetMapping("/status")
	@RequestMapping
	public Object statusHandle(HttpSession session) {
		return session.getAttribute("SPRING_SECURITY_CONTEXT");
	}
}

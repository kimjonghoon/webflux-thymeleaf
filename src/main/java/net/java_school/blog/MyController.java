package net.java_school.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("blog")
	public String getBlogHome() {
		return "blog/index";
	}
}

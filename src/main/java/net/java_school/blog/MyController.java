package net.java_school.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MyController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping(value={"{chapter:java|jdbc|jsp|css-layout|jsp-pjt|spring|javascript|google-cloud}", 
			"{chapter:java|jdbc|jsp|css-layout|jsp-pjt|spring|javascript|google-cloud}/{lecture}"})
	public String lecture(@PathVariable("chapter") String chapter, 
			@PathVariable(value="lecture", required=false) String lecture) {

		if (lecture != null) {
			return chapter + "/" + lecture;
		} else {
			return chapter + "/index";	
		}
	}
	
	@GetMapping("blog")
	public String getBlogHome() {
		return "blog/index";
	}

	@GetMapping("blog/{year:\\d+}/{post}")
	public String getPost(@PathVariable("year") Integer year, @PathVariable("post") String post) {
		return "blog/" + year + "/" + post;
	}
}

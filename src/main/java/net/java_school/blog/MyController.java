package net.java_school.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import reactor.core.publisher.Mono;
import static reactor.core.publisher.Mono.just;

@Controller
public class MyController {

	@GetMapping("/")
	public Mono<String> index() {
		Mono<String> view = just("index");
		return view;
	}
	
	@GetMapping(value={"{chapter:java|jdbc|jsp|css-layout|jsp-pjt|spring|javascript|google-cloud}", 
			"{chapter:java|jdbc|jsp|css-layout|jsp-pjt|spring|javascript|google-cloud}/{lecture}"})
	public Mono<String> lecture(@PathVariable("chapter") String chapter, 
			@PathVariable(value="lecture", required=false) String lecture) {

		if (lecture != null) {
			Mono<String> view = just(chapter + "/" + lecture);
			return view;
		} else {
			Mono<String> view = just(chapter + "/index");
			return view;
		}
	}
	
	@GetMapping("blog")
	public Mono<String> getBlogHome() {
		Mono<String> view = just("blog/index");
		return view;
	}

	@GetMapping("blog/{year:\\d+}/{post}")
	public Mono<String> getPost(@PathVariable("year") Integer year, @PathVariable("post") String post) {
		Mono<String> view = just("blog/" + year + "/" + post);
		return view;
	}
}

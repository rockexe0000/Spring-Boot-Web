package com.zaifu.SpringBootWeb;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class userController {

	@RequestMapping("/MyFirstPage")
	public String greeting(@RequestParam(value="title", required=false, defaultValue="xiao") String title, Model model) {
		model.addAttribute("name", title);
		return "index";
	}

	private String message = "鐵人賽第七天加油!!!";
	 
	@GetMapping("/")
	public String index(Map<String, Object> model) {
		model.put("message", this.message);
		return "index";
	}
	 
}
	 

package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String home() {		
		return "main";
	}
 
	@RequestMapping("/admin34/index.do")
	public String sbadmin_index() {		
		return "sbadmin/index";
	}
	@RequestMapping("/admin34/login.do")
	public String sbadmin_login() {		
		return "sbadmin/login";
	}	
 
	/*********************************************************************/
	
	@GetMapping("/admin34/buttons.do")
	public String sbadmin_buttons() {		
		return "sbadmin/buttons";
	}
	@GetMapping("/admin34/cards.do")
	public String sbadmin_cards() {		
		return "sbadmin/cards";
	}
	@GetMapping("/admin34/utilities-color.do")
	public String sbadmin_utilities_color() {		
		return "sbadmin/utilities-color";
	}
	@GetMapping("/admin34/utilities-border.do")
	public String sbadmin_utilities_border() {		
		return "sbadmin/utilities-border";
	}
	@GetMapping("/admin34/utilities-animation.do")
	public String sbadmin_utilities_animation() {		
		return "sbadmin/utilities-animation";
	}
	@GetMapping("/admin34/utilities-other.do")
	public String sbadmin_utilities_other() {		
		return "sbadmin/utilities-other";
	}
	@GetMapping("/admin34/register.do")
	public String sbadmin_register() {		
		return "sbadmin/register";
	}
	@GetMapping("/admin34/forgot-password.do")
	public String sbadmin_forgot_password() {		
		return "sbadmin/forgot-password";
	}
	@GetMapping("/admin34/404.do")
	public String sbadmin_404() {		
		return "sbadmin/404";
	}
	@GetMapping("/admin34/blank.do")
	public String sbadmin_blank() {		
		return "sbadmin/blank";
	}
	@GetMapping("/admin34/charts.do")
	public String sbadmin_charts() {		
		return "sbadmin/charts";
	}
	@GetMapping("/admin34/tables.do")
	public String sbadmin_tables() {		
		return "sbadmin/tables";
	}








}

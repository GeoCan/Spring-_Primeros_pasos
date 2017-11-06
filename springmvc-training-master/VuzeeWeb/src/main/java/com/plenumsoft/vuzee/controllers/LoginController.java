package com.plenumsoft.vuzee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value={"/login"})
public class LoginController {
	
	private String prefix = "login/";
	
	@RequestMapping(value = { "/", "" })
	public String Get(){
		
		return prefix + "index";
	}

	// Login form with error
	  @RequestMapping("/login-error.html")
	  public String loginError(Model model) {
	    model.addAttribute("loginError", true);
	    return "login.html";
	  }
}

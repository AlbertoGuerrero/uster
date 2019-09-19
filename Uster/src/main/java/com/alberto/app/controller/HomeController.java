package com.alberto.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	// Muestra la página principal
	@RequestMapping( value="/home", method=RequestMethod.GET )
	public String goHomeButton() {
		return "home";
	}
	
	// Muestra la página principal
	@RequestMapping( value="/", method=RequestMethod.GET )
	public String goHome() {
		return "home";
	}
}

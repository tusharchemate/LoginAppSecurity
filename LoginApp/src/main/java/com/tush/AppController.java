package com.tush;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

	@Autowired
	private UserRepository userrepo;

	@GetMapping("/home")
	public String homepage() {

		return "home";
	}

	@GetMapping("/login")
	public String loginPage() {

		return "login";
	}

	@GetMapping("/register")

	public String register(Model model) {

		model.addAttribute("user", new User());

		return "register";
	}

	@PostMapping("/process_registration")
	public String proceess_Registration(User user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		userrepo.save(user);

		return "login";

	}

}

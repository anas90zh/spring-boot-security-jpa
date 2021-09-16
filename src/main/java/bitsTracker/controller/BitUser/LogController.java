package bitsTracker.controller.BitUser;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import bitsTracker.entity.bitUser.BitUserRegistrationDto;
import bitsTracker.entity.bitUser.Role;
import bitsTracker.service.interfaces.BitUserService;

@Controller
public class LogController {

	@Autowired
	private BitUserService bitUserService;

	@GetMapping("/registration")
	public String viewRegistrationForm(@ModelAttribute("bitUser") BitUserRegistrationDto bitUserRD) {
		return "registration-form";
	}

	@PostMapping("/saveBitUser")
	public String registerNewBitUser(@Valid @ModelAttribute("bitUser") BitUserRegistrationDto bitUserRD,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "registration-form";
		} else {
			bitUserService.saveBitUser(bitUserRD, Role.Code.USER);
			return "redirect:/registration?success";
		}
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	

}

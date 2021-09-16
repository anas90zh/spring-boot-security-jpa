package bitsTracker.controller.BitUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import bitsTracker.entity.bitUser.BitUser;
import bitsTracker.service.interfaces.BitUserService;

@Controller
public class BitUserController {

	@Autowired
	private BitUserService bitUserService;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("bitUsersList", bitUserService.getAllBitUsers());
		return "bitUsers";
	}

	
	@GetMapping("/updateBitUser/{id}")
	public String updateBitUser(@PathVariable ( value = "id") long id, Model model) {
	 
	 // get employee from the service
	 BitUser bitUser = bitUserService.getBitUserByID(id);
	 
	 // set employee as a model attribute to pre-populate the form
	 model.addAttribute("bitUser", bitUser);
	 return "update_bitUser";
	}
	
	@GetMapping("/deleteBitUser/{id}")
	public String deleteBitUser(@PathVariable (value = "id") long id) {
	 
	 // call delete employee method 
		bitUserService.deleteBitUser(id);
	 return "redirect:/";
	}
	 
	
}

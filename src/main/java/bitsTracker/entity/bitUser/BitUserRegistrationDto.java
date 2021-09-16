package bitsTracker.entity.bitUser;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class BitUserRegistrationDto {
	
	
	@NotEmpty
	@NotNull
	@Size(min=2, message ="BitUser is too short ")
	private String userName;
	
	@Email
	@NotNull
	@NotEmpty
	private String email;	
	
	@NotEmpty
	@NotNull
	@Size(min = 8 , message ="Your password should be a minimum of 8 characters and contain at least one number and special character. ")
	private String password;
	
	private String name;
	
	
}

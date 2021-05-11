package DTO;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.meritamerica.assignment7.model.AccountHolder;

import lombok.Data;

@Data
public class SignupRequest {
	@NotBlank
	@Size(max = 50)
	//@Email
	private String username;
	
	@NotBlank
	@Size(min = 3, max = 50)
	private String password;
	
	private boolean active;
	private String role;
	
}

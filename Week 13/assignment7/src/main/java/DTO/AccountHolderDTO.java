package DTO;

import lombok.Data;

@Data
public class AccountHolderDTO {
	
	private Long userId;
	private String firstName;
	private String middleName; 
	private String lastName;
	private String ssn;
	
}

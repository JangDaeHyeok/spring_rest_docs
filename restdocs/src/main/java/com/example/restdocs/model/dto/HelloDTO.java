package com.example.restdocs.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloDTO {
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String phoneKind;
	
	@NotEmpty
	private String gender;
	
	@Email
	@NotEmpty
	private String email;
}

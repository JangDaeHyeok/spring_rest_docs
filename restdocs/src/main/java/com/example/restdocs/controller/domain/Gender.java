package com.example.restdocs.controller.domain;

import com.example.restdocs.controller.util.EnumType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender implements EnumType {
	MALE("남자"),
	FEMALE("여자");
	
	private String text;
	
	Gender(String text) { 
		this.text = text;
	}

	
	@Override
	public String getId() {
		return this.name();
	}
	
	@Override
	public String getText() {
		return this.text;
	}
}

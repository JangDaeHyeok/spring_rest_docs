package com.example.restdocs.controller.domain;

import com.example.restdocs.controller.util.EnumType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PhoneKind implements EnumType {
	ANDROID("안드로이드"),
	IOS("IOS");
	
	private String text;
	
	PhoneKind(String text) { 
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

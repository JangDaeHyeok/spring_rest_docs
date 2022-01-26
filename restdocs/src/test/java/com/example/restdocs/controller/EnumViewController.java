package com.example.restdocs.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restdocs.controller.domain.Gender;
import com.example.restdocs.controller.domain.PhoneKind;
import com.example.restdocs.controller.util.EnumType;

@RestController
public class EnumViewController {
	enum phoneKind {ANDROID, IOS};
	
	@GetMapping("docs")
	public Map<String, Object> commonDocs() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		dataMap.put("phoneKind", getDocs(PhoneKind.values()));
		dataMap.put("gender", getDocs(Gender.values()));
		
		returnMap.put("data", dataMap);
		returnMap.put("result", "success");
		returnMap.put("msg", "성공");
		return returnMap;
	}
	
	private Map<String, String> getDocs(EnumType[] enumTypes) {
		return Arrays.stream(enumTypes)
			.collect(Collectors.toMap(EnumType::getId, EnumType::getText));
	}
}

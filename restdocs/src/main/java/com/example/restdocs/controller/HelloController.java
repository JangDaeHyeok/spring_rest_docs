package com.example.restdocs.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restdocs.model.dto.HelloDTO;

@RestController
public class HelloController {
	// reqeust body Map
	@PostMapping("hello")
	public Map<String, Object> hello(@RequestBody Map<String, Object> input, HttpServletRequest req) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String returnStr = "hello";
		
		if(input.containsKey("data")) {
			returnStr += " " + input.get("data").toString() + "님!";
		}else {
			returnStr += " " + "world";
		}
		
		returnMap.put("data", returnStr);
		returnMap.put("result", "success");
		returnMap.put("msg", "성공");
		
		return returnMap;
	}
	
	// request param
	@PostMapping("hello2")
	public Map<String, Object> hello2(@RequestParam(required = false) String name, HttpServletRequest req) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String returnStr = "hello";
		
		if(name != null) {
			returnStr += " " + name + "님!";
		}else {
			returnStr += " " + "world";
		}
		
		returnMap.put("data", returnStr);
		returnMap.put("result", "success");
		returnMap.put("msg", "성공");
		
		return returnMap;
	}
	
	// path param
	@PostMapping("hello3/{name}")
	public Map<String, Object> hello3(@PathVariable String name, HttpServletRequest req) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String returnStr = "hello";
		
		if(name != null) {
			returnStr += " " + name + "님!";
		}else {
			returnStr += " " + "world";
		}
		
		returnMap.put("data", returnStr);
		returnMap.put("result", "success");
		returnMap.put("msg", "성공");
		
		return returnMap;
	}
	
	// request body DTO
	@PostMapping("hello4")
	public Map<String, Object> hello4(@RequestBody HelloDTO helloDTO, HttpServletRequest req) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		returnMap.put("data", helloDTO);
		returnMap.put("result", "success");
		returnMap.put("msg", "성공");
		
		return returnMap;
	}
}

package com.example.restdocs.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.restdocs.controller.domain.Gender;
import com.example.restdocs.controller.domain.PhoneKind;
import com.example.restdocs.model.dto.HelloDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(HelloController.class)
@AutoConfigureRestDocs
public class HelloControllerTest {
	@Autowired MockMvc mockMvc;
	
	@Autowired ObjectMapper objectMapper;
	
	@Test
	@DisplayName("테슷흐")
	void testHello() throws Exception {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("data", "테스트1");
		String body = objectMapper.writeValueAsString(input);
		
		mockMvc.perform(
				post("/hello").content(body).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("hello",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint()),
						PayloadDocumentation.requestFields(
								PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.STRING)
									.description("입력 데이터")
								),
						PayloadDocumentation.responseFields(
								PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.STRING)
								.attributes(Attributes.key("format").value("아무렇게나!"))
								.description("반환 데이터").optional(),
								PayloadDocumentation.fieldWithPath("result").type(JsonFieldType.STRING)
								.description("결과"),
								PayloadDocumentation.fieldWithPath("msg").type(JsonFieldType.STRING)
								.description("메시지")
								)
						)
				);
	}
	
	@Test
	@DisplayName("테슷흐2")
	void testHello2() throws Exception {
		
		mockMvc.perform(
				RestDocumentationRequestBuilders.post("/hello2").param("name", "테스트2"))
				.andExpect(status().isOk())
				.andDo(document("hello2",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint()),
						requestParameters(parameterWithName("name").description("입력 파라미터").optional()),
						PayloadDocumentation.responseFields(
								PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.STRING)
								.attributes(Attributes.key("format").value("아무렇게나!!"))
								.description("반환 데이터").optional(),
								PayloadDocumentation.fieldWithPath("result").type(JsonFieldType.STRING)
								.description("결과"),
								PayloadDocumentation.fieldWithPath("msg").type(JsonFieldType.STRING)
								.description("메시지")
								)
						)
				);
	}
	
	@Test
	@DisplayName("테슷흐3")
	void testHello3() throws Exception {
		String name = "테스트3";
		
		mockMvc.perform(
				RestDocumentationRequestBuilders.post("/hello3/{name}", name))
				.andExpect(status().isOk())
				.andDo(document("hello3",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint()),
						pathParameters(parameterWithName("name").description("입력 파라미터")),
						PayloadDocumentation.responseFields(
								PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.STRING)
								.attributes(Attributes.key("format").value("아무렇게나!!"))
								.description("반환 데이터").optional(),
								PayloadDocumentation.fieldWithPath("result").type(JsonFieldType.STRING)
								.description("결과"),
								PayloadDocumentation.fieldWithPath("msg").type(JsonFieldType.STRING)
								.description("메시지")
								)
						)
				);
	}
	
	@Test
	@DisplayName("테슷흐4")
	void testHello4() throws Exception {
		HelloDTO hDTO = new HelloDTO();
		hDTO.setName("이름");
		hDTO.setGender(Gender.MALE.getText());
		hDTO.setPhoneKind(PhoneKind.ANDROID.getText());
		hDTO.setEmail("이메일");
		
		String body = objectMapper.writeValueAsString(hDTO);
		
		// get validation
		ConstraintDescriptions helloDTOConstraints = new ConstraintDescriptions(HelloDTO.class);
		
		mockMvc.perform(
				post("/hello4").content(body).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(document("hello_dto",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				PayloadDocumentation.requestFields(
						PayloadDocumentation.fieldWithPath("name").type(JsonFieldType.STRING)
							.description("이름").attributes(key("constraint").value(helloDTOConstraints.descriptionsForProperty("name"))),
						PayloadDocumentation.fieldWithPath("gender").type(JsonFieldType.STRING)
							.description("성별").attributes(key("constraint").value(helloDTOConstraints.descriptionsForProperty("gender"))),
						PayloadDocumentation.fieldWithPath("phoneKind").type(JsonFieldType.STRING)
							.description("모바일기기 종류").attributes(key("constraint").value(helloDTOConstraints.descriptionsForProperty("phoneKind"))),
						PayloadDocumentation.fieldWithPath("email").type(JsonFieldType.STRING)
							.description("email").attributes(key("constraint").value(helloDTOConstraints.descriptionsForProperty("email")))
						),
				PayloadDocumentation.responseFields(
						PayloadDocumentation.fieldWithPath("result").type(JsonFieldType.STRING)
						.description("결과"),
						PayloadDocumentation.fieldWithPath("msg").type(JsonFieldType.STRING)
						.description("메시지"),
						PayloadDocumentation.fieldWithPath("data.name").type(JsonFieldType.STRING)
						.description("이름").optional(),
						PayloadDocumentation.fieldWithPath("data.gender").type(JsonFieldType.STRING)
						.description("성별").optional(),
						PayloadDocumentation.fieldWithPath("data.phoneKind").type(JsonFieldType.STRING)
						.description("모바일기기 종류").optional(),
						PayloadDocumentation.fieldWithPath("data.email").type(JsonFieldType.STRING)
						.description("email").optional()
						)
				)
			);
	}
}

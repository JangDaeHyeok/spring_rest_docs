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
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

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
	@DisplayName("?????????")
	void testHello() throws Exception {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("data", "?????????1");
		String body = objectMapper.writeValueAsString(input);
		
		mockMvc.perform(
				post("/hello").content(body).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("hello",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint()),
						requestFields(
								fieldWithPath("data").type(JsonFieldType.STRING)
									.description("?????? ?????????")
								),
						responseFields(
								fieldWithPath("data").type(JsonFieldType.STRING)
								.attributes(Attributes.key("format").value("???????????????!"))
								.description("?????? ?????????").optional(),
								fieldWithPath("result").type(JsonFieldType.STRING)
								.description("??????"),
								fieldWithPath("msg").type(JsonFieldType.STRING)
								.description("?????????")
								)
						)
				);
	}
	
	@Test
	@DisplayName("?????????2")
	void testHello2() throws Exception {
		
		mockMvc.perform(
				RestDocumentationRequestBuilders.post("/hello2").param("name", "?????????2"))
				.andExpect(status().isOk())
				.andDo(document("hello2",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint()),
						requestParameters(parameterWithName("name").description("?????? ????????????").optional()),
						responseFields(
								fieldWithPath("data").type(JsonFieldType.STRING)
								.attributes(Attributes.key("format").value("???????????????!!"))
								.description("?????? ?????????").optional(),
								fieldWithPath("result").type(JsonFieldType.STRING)
								.description("??????"),
								fieldWithPath("msg").type(JsonFieldType.STRING)
								.description("?????????")
								)
						)
				);
	}
	
	@Test
	@DisplayName("?????????3")
	void testHello3() throws Exception {
		String name = "?????????3";
		
		mockMvc.perform(
				RestDocumentationRequestBuilders.post("/hello3/{name}", name))
				.andExpect(status().isOk())
				.andDo(document("hello3",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint()),
						pathParameters(parameterWithName("name").description("?????? ????????????")),
						responseFields(
								fieldWithPath("data").type(JsonFieldType.STRING)
								.attributes(Attributes.key("format").value("???????????????!!"))
								.description("?????? ?????????").optional(),
								fieldWithPath("result").type(JsonFieldType.STRING)
								.description("??????"),
								fieldWithPath("msg").type(JsonFieldType.STRING)
								.description("?????????")
								)
						)
				);
	}
	
	@Test
	@DisplayName("?????????4")
	void testHello4() throws Exception {
		HelloDTO hDTO = new HelloDTO();
		hDTO.setName("??????");
		hDTO.setGender(Gender.MALE.getText());
		hDTO.setPhoneKind(PhoneKind.ANDROID.getText());
		hDTO.setEmail("?????????");
		
		String body = objectMapper.writeValueAsString(hDTO);
		
		// get validation
		ConstraintDescriptions helloDTOConstraints = new ConstraintDescriptions(HelloDTO.class);
		
		mockMvc.perform(
				post("/hello4").content(body).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(document("hello_dto",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				requestFields(
						fieldWithPath("name").type(JsonFieldType.STRING)
							.description("??????").attributes(key("constraint").value(helloDTOConstraints.descriptionsForProperty("name"))),
						fieldWithPath("gender").type(JsonFieldType.STRING)
							.description("??????").attributes(key("constraint").value(helloDTOConstraints.descriptionsForProperty("gender"))),
						fieldWithPath("phoneKind").type(JsonFieldType.STRING)
							.description("??????????????? ??????").attributes(key("constraint").value(helloDTOConstraints.descriptionsForProperty("phoneKind"))),
						fieldWithPath("email").type(JsonFieldType.STRING)
							.description("email").attributes(key("constraint").value(helloDTOConstraints.descriptionsForProperty("email")))
						),
				responseFields(
						fieldWithPath("result").type(JsonFieldType.STRING)
						.description("??????"),
						fieldWithPath("msg").type(JsonFieldType.STRING)
						.description("?????????"),
						fieldWithPath("data.name").type(JsonFieldType.STRING)
						.description("??????").optional(),
						fieldWithPath("data.gender").type(JsonFieldType.STRING)
						.description("??????").optional(),
						fieldWithPath("data.phoneKind").type(JsonFieldType.STRING)
						.description("??????????????? ??????").optional(),
						fieldWithPath("data.email").type(JsonFieldType.STRING)
						.description("email").optional()
						)
				)
			);
	}
}

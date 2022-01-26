package com.example.restdocs.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.beneathPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import com.example.restdocs.ApiDocumentTest;
import com.example.restdocs.controller.domain.Gender;
import com.example.restdocs.controller.domain.PhoneKind;
import com.example.restdocs.controller.util.EnumType;
import com.example.restdocs.custom.CustomResponseFieldsSnippet;


public class CommonDocsTest extends ApiDocumentTest{
	@Test
	public void commons() throws Exception {
		this.mockMvc.perform(
			get("/docs").accept(MediaType.APPLICATION_JSON)
		)
			.andExpect(status().isOk())
			.andDo(document("common",
					customResponseFields("common-response", null,
							attributes(key("title").value("공통응답")),
							fieldWithPath("result").type(JsonFieldType.STRING).description("결과"),
							fieldWithPath("msg").type(JsonFieldType.STRING).description("메시지")
					),
					customResponseFields("custom-response", beneathPath("data.phoneKind"),
							attributes(key("title").value("모바일기기 종류")),
							enumConvertFieldDescriptor(PhoneKind.values())
					),
					customResponseFields("custom-response", beneathPath("data.gender"),
							attributes(key("title").value("성별")),
							enumConvertFieldDescriptor(Gender.values())
					)
		));
	}
	
	private FieldDescriptor[] enumConvertFieldDescriptor(EnumType[] enumTypes) {
		return Arrays.stream(enumTypes)
				.<Object>map(enumType -> fieldWithPath(enumType.getId()).description(enumType.getText()))
				.toArray(FieldDescriptor[]::new);
	}
	
	public static CustomResponseFieldsSnippet customResponseFields(String type,
																	PayloadSubsectionExtractor<?> subsectionExtractor,
																	Map<String, Object> attributes, FieldDescriptor... descriptors) {
		return new CustomResponseFieldsSnippet(type, subsectionExtractor, Arrays.asList(descriptors), attributes, true);
	}
}

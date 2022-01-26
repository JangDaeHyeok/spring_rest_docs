package com.example.restdocs;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.restdocs.controller.EnumViewController;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(EnumViewController.class)
@AutoConfigureRestDocs
public abstract class ApiDocumentTest {
	@Autowired protected MockMvc mockMvc;
	@Autowired protected ObjectMapper objectMapper;
}

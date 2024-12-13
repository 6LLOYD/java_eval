//package com.example.eval_java;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class EvalJavaApplicationTests {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//
//	@Test
//	@WithMockUser(username = "admin", roles = {"ADMINISTRATEUR"})
//	void shouldAllowAccessToAdminUrlForAdmin() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/conventions/add"))
//				.andExpect(status().isOk());
//	}
//
//	@Test
//	@WithMockUser(username = "aplpha", roles = {"ENTREPRISE"})
//	void shouldDenyAccessToAdminUrlForEntreprise() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/conventions/add"))
//				.andExpect(status().isForbidden());
//	}
//
//
//	@Test
//	@WithMockUser(username = "aplpha", roles = {"ENTREPRISE"})
//	void shouldAllowAccessToEntrepriseUrlForEntreprise() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/entreprises/update/2"))
//				.andExpect(status().isOk());
//	}
//
//
//}

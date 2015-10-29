package com.sprhib.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sprhib.init.BaseTestConfig;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=BaseTestConfig.class)
@SqlGroup({
	@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:OrganizationController-before.sql"),
	@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:OrganizationController-after.sql")
})
public class OrganizationControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testAdd() throws Exception {
		mockMvc.perform(get("/organization/add"))
			.andExpect(status().isOk())
			.andExpect(view().name("add-organization-form"));
	}

	@Test
	public void testAddPost() throws Exception {
		mockMvc.perform(post("/organization/add")
				.param("name", "test"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"));
	}

	@Test
	public void testList() throws Exception {
		mockMvc.perform(get("/organization/list"))
			.andExpect(status().isOk())
			.andExpect(view().name("list-of-organizations"));
	}

	@Test
	public void testEdit() throws Exception {
		mockMvc.perform(get("/organization/edit/20"))
			.andExpect(status().isOk())
			.andExpect(view().name("edit-organization-form"));
	}

	@Test
	public void testEditPost() throws Exception {
		mockMvc.perform(post("/organization/edit/20"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/organization/list"));
	}

	@Test
	public void testDelete() throws Exception {
		mockMvc.perform(get("/organization/delete/20"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/organization/list"));
	}
}

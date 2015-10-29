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
	@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:TeamController-before.sql"),
	@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:TeamController-after.sql")
})
public class TeamControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testAdd() throws Exception {
		mockMvc.perform(get("/team/add"))
			.andExpect(status().isOk())
			.andExpect(view().name("add-team-form"));
	}

	@Test
	public void testAddPost() throws Exception {
		mockMvc.perform(post("/team/add")
				.param("name", "test"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"));
	}

	@Test
	public void testList() throws Exception {
		mockMvc.perform(get("/team/list"))
			.andExpect(status().isOk())
			.andExpect(view().name("list-of-teams"));
	}

	@Test
	public void testEdit() throws Exception {
		mockMvc.perform(get("/team/edit/101"))
			.andExpect(status().isOk())
			.andExpect(view().name("edit-team-form"));
	}

	@Test
	public void testEditPost() throws Exception {
		mockMvc.perform(post("/team/edit/101"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/team/list"));
	}

	@Test
	public void testDelete() throws Exception {
		mockMvc.perform(get("/team/delete/101"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/team/list"));
	}
}

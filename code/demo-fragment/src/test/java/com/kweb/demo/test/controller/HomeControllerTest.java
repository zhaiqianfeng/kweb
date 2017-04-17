/**
* @author Zhai　Qianfeng
* @date April 17, 2017
* @blog www.zhaiqianfeng.com
*/

package com.kweb.demo.test.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring/*.xml" })
public class HomeControllerTest {
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	
	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.alwaysDo(print())
				.build();
	}

	@Test
	public void _000testDoShowHomeView() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(status().isOk());
	}
}

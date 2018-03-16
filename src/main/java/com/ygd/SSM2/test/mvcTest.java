package com.ygd.SSM2.test;

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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
/*@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:spring-mvc.xml" })*/
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml" })
public class mvcTest {

	@Autowired
	WebApplicationContext context;

	MockMvc mockMvc;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testMvc() throws Exception {
		/*MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/Employee/emps"))
				.andReturn();*/
		
		/*MockHttpServletRequest request = result.getRequest();
		
		MockHttpServletResponse response = result.getResponse();*/
		
		
		/*for(Employee employee:emp){
			System.out.println("name="+employee.getEmpName()+" ---- ID="+employee.getEmpId());
		}*/

	}

}

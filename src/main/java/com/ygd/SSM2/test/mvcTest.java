package com.ygd.SSM2.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.Page;
import com.ygd.SSM2.entity.Employee;

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

	@SuppressWarnings("unchecked")
	@Test
	public void testMvc() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/Employee/allList"))
				.andReturn();
		
		MockHttpServletRequest request = result.getRequest();
		
		Page<Employee> page =  (Page<Employee>) request.getAttribute("page");
		
		/*for(Employee employee:emp){
			System.out.println("name="+employee.getEmpName()+" ---- ID="+employee.getEmpId());
		}*/

	}

}

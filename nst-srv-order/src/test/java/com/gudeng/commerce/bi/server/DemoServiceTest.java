package com.gudeng.commerce.bi.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.gdeng.nst.api.server.order.DemoService;
import junit.framework.TestCase;

public class DemoServiceTest extends TestCase {
	
	ApplicationContext context;
	DemoService demoService;
	
	@Override
	protected void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("classpath:spring.xml");
		demoService = context.getBean(DemoService.class);
	}

	@Override
	protected void tearDown() throws Exception {
		context = null;
	}
	
	public void testDataSourceSwitch(){
		System.out.println(demoService);
	}
	
}

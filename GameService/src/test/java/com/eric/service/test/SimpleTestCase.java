package com.eric.service.test;


import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * 单元测试基类，记录测试每个方法的时差
 * @author Eric
 *
 */
@ActiveProfiles("test")
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml"})
public abstract class SimpleTestCase extends AbstractJUnit4SpringContextTests{

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleTestCase.class); 
	
	private Long startTime;
	private Long endTime;
	
	@Before
	public void before(){
		startTime = System.currentTimeMillis();
	}
	
	@After
	public void after(){
		endTime = System.currentTimeMillis();
		LOGGER.info("执行方法时差：{}ms",(endTime-startTime));
	}
}

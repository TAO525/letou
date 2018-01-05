package com.tao;

import com.tao.service.LetouService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LetouApplicationTests {

	@Resource
	private LetouService letouService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void test_luck(){
		letouService.luck();
	}

}

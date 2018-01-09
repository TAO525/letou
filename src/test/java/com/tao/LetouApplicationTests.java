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
		for (int i=0;i<2000000;i++) {
			letouService.luck();
		}
	}

	@Test
	public void test_s(){
		System.out.println(letouService.getCountByNumber4S(11,null));
	}

	@Test
	public void test_totals(){
		System.out.println(letouService.getTotalCount4S(11));
	}

	@Test
	public void test_C(){
		System.out.println(letouService.getCountByNumber4C(1,11,null));
		System.out.println(letouService.getCountByNumber4C(2,11,null));
		System.out.println(letouService.getCountByNumber4C(3,11,null));
		System.out.println(letouService.getCountByNumber4C(4,11,null));
		System.out.println(letouService.getCountByNumber4C(5,11,null));
		System.out.println(letouService.getCountByNumber4C(6,11,null));
	}

	@Test
	public void test_totalc(){
		System.out.println(letouService.getTotalCount4C(11));
	}


}

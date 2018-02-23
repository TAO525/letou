package com.tao;

import com.tao.domain.LogFeedback;
import com.tao.mapper.LogFeedbackMapper;
import com.tao.service.LetouService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LetouApplicationTests {

	@Resource
	private LetouService letouService;

	@Resource
	private LogFeedbackMapper logFeedbackMapper;

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

	@Test
	public void test_third(){
		List<Integer> integers = Arrays.asList(2, 9, 13, 15, 18, 26, 5);
		System.out.println(letouService.getThirdCount(integers));
	}

	@Test
	public void test_two(){
		List<Integer> integers = Arrays.asList(5, 9, 13, 15, 18, 26, 15);
		System.out.println(letouService.getTwoCount(integers));
	}

	@Test
	public void test_one(){
		List<Integer> integers = Arrays.asList(5, 9, 13, 15, 18, 26, 5);
		System.out.println(letouService.getOneCount(integers));
	}

	@Test
	public void test_feedback(){
		List<LogFeedback> list = logFeedbackMapper.getList(new LogFeedback());
		System.out.println(list);
	}
}

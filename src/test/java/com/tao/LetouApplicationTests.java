package com.tao;

import com.tao.domain.LogFeedback;
import com.tao.domain.Whole;
import com.tao.mapper.LogFeedbackMapper;
import com.tao.service.LetouService;
import com.tao.service.WholeService;
import org.apache.ibatis.annotations.Case;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LetouApplicationTests {

	@Resource
	private LetouService letouService;

	@Resource
	private LogFeedbackMapper logFeedbackMapper;

	@Resource
	private WholeService wholeService;

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
		List<LogFeedback> list = logFeedbackMapper.getListDesc();
		System.out.println(list.get(0).getId());
	}

	@Test
	public void test_single(){
		Whole whole = new Whole();
		whole.setC1(1);
		whole.setC2(2);
		whole.setC3(3);
		whole.setC4(4);
		whole.setC5(5);
		whole.setC6(6);
		whole.setS1(6);
		wholeService.insert(whole);
	}

	@Test
	public void test_whole(){
		int c1,c2,c3,c4,c5,c6;
		int s1;
		int count = 0;
		Instant now = Instant.now();
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for(s1=1;s1<=16;s1++) {
			for (c1 = 1; c1 <= 28; c1++) {
				for (c2 = c1 + 1; c2 <= 29; c2++) {
					for (c3 = c2 + 1; c3 <= 30; c3++) {
						for (c4 = c3 + 1; c4 <= 31; c4++) {
							for (c5 = c4 + 1; c5 <= 32; c5++) {
								for (c6 = c5 + 1; c6 <= 33; c6++) {
									count++;
									final Whole whole = new Whole(c1, c2, c3, c4, c5, c6, s1);
//									int mod = (c1+c2+c3+c4+c5+c6+s1)% 10;
									executorService.submit(new Runnable() {
										@Override
										public void run() {
											wholeService.insert(whole);										}
									});

								}
							}
						}
					}
				}
			}
		}
		System.out.println(count);
		System.out.println(Duration.between(now,Instant.now()).getSeconds());
	}
}

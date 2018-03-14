package com.tao.controller;

import com.tao.domain.ResultDTO;
import com.tao.domain.Whole;
import com.tao.service.WholeService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @Author TAO
 * @Date 2018/1/3 20:06
 */
@RestController
@RequestMapping("rest")
public class LetouRestController {


    @Resource
    private WholeService wholeService;


    @RequestMapping("/pandect")
    public ResultDTO pandect(@RequestBody List<Integer> nums){
        List<Integer> integers = nums.subList(0, 6);
        Collections.sort(integers);
        Whole whole = new Whole(integers.get(0), integers.get(1), integers.get(2)
                , integers.get(3), integers.get(4), integers.get(5), nums.get(6));
        Whole bySelect = wholeService.getBySelect(whole);
        if(bySelect == null){
            return ResultDTO.error("100","号码错误");
        }else {
            return ResultDTO.ok(bySelect);
        }
    }
}

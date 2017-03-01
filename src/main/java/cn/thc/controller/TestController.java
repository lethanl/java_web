package cn.thc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by thc on 2016/12/1
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/index")
    public String index(){
        redisTemplate.opsForValue().set("spring-test","springtest");
        return "index";
    }

}

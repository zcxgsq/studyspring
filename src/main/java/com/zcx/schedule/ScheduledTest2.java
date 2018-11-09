package com.zcx.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zcx
 * @Title 定时器
 * @date 2018年08月06日 19:07
 **/

@Component
public class ScheduledTest2 {
    @Scheduled(cron = "0 0/1 * * * ?")
    public void runFunction(){
        System.out.println(new Date() + " package.controller scheduled test --> mahaha") ;
    }
}

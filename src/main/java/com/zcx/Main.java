package com.zcx;

import com.zcx.application.DemoPublisher;
import com.zcx.application.EventConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zcx
 * @Title 启动方法
 * @date 2018年11月09日 10:25
 **/
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(EventConfig.class);
        DemoPublisher demoPublisher = applicationContext.getBean(DemoPublisher.class);

        demoPublisher.publish("hello application");

        applicationContext.close();
    }

}

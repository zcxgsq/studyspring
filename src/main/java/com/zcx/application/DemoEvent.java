package com.zcx.application;

import org.springframework.context.ApplicationEvent;

/**
 * @author zcx
 * @Title 自定义事件
 * @date 2018年11月09日 10:17
 **/
public class DemoEvent extends ApplicationEvent {

    private String msg;

    public DemoEvent(Object source,String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

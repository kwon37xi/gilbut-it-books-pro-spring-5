package com.apress.prospring5.ch4;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Publisher implements ApplicationEventPublisherAware {
    // ApplicationContext 전체를 주입받기 보다는 ApplicationEventPublisher 만 주입받는게 낫다.
    // 직접 주입 혹은, ApplicationEventPublisherAware 인터페이스 구현으로 주입받는다.
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

//    private ApplicationContext ctx;
//
//    public void setApplicationContext(ApplicationContext applicationContext)
//            throws BeansException {
//        this.ctx = applicationContext;
//    }

    public void publish(String message) {
        applicationEventPublisher.publishEvent(new MessageEvent(this, message));
    }

    public static void main(String... args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "classpath:spring/app-context-xml.xml");

        Publisher pub = (Publisher) ctx.getBean("publisher");
        pub.publish("세상에 SOS를 보낸다... ");
        pub.publish("...누군가가 병에 담긴...");
        pub.publish("... 이 메시지를 받았으면 좋겠다.");
    }
}

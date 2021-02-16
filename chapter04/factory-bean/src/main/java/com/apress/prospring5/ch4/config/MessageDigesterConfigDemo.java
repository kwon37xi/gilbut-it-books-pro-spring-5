package com.apress.prospring5.ch4.config;

import com.apress.prospring5.ch4.MessageDigestFactoryBean;
import com.apress.prospring5.ch4.MessageDigester;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

import java.security.MessageDigest;

public class MessageDigesterConfigDemo {

    @Configuration
    static class MessageDigesterConfig {

        @Bean
        public MessageDigestFactoryBean shaDigest() {
            MessageDigestFactoryBean factoryOne = new MessageDigestFactoryBean();
            factoryOne.setAlgorithmName("SHA1");
            return factoryOne;
        }

        @Bean
        public MessageDigestFactoryBean defaultDigest() {
            return new MessageDigestFactoryBean();
        }

        // MessageDigest 를 주입받는 방식
        @Bean
        MessageDigester digester(@Qualifier("defaultDigest") MessageDigest defaultDigest, @Qualifier("shaDigest") MessageDigest shaDigest) throws Exception {
            MessageDigester messageDigester = new MessageDigester();
            messageDigester.setDigest1(shaDigest);
            messageDigester.setDigest2(defaultDigest);
            return messageDigester;
        }

        // 책에 나온 방식
//        @Bean MessageDigester digester() throws Exception {
//            MessageDigester messageDigester = new MessageDigester();
//            messageDigester.setDigest1(shaDigest().getObject());
//            messageDigester.setDigest2(defaultDigest().getObject());
//            return messageDigester;
//        }
    }

    public static void main(String... args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(MessageDigesterConfig.class);

        MessageDigester digester = (MessageDigester) ctx.getBean("digester");
        digester.digest("Hello World!");
        ctx.close();
    }
}

package com.apress.prospring5.ch4;

import java.io.File;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ResourceDemo {
    public static void main(String... args) throws Exception{
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext();
        ctx.refresh();

        File file = File.createTempFile("test", "txt");
        file.deleteOnExit();

        // ResourceLoaderAware 로 resourceLoader 를 주입받거나 ResourceLoader 를 직접 사용한다.
        // ctx.getBean(ResourceLoader.class); 는 실패하지만, 실제 애플리케이션에서는 주입 받으려고 하면 주입이 된다.
        ResourceLoader resourceLoader = ctx;

        Resource res1 = resourceLoader.getResource("file://" + file.getPath());
        displayInfo(res1);

        Resource res2 = resourceLoader.getResource("classpath:test.txt");
        displayInfo(res2);

        Resource res3 = resourceLoader.getResource("http://www.google.com");
        displayInfo(res3);
    }

    private static void displayInfo(Resource res) throws Exception{
        System.out.println(res.getClass());
        System.out.println(res.getURL().getContent());
        System.out.println("");
    }
}

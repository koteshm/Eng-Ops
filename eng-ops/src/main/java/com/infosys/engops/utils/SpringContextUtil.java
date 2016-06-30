package com.infosys.engops.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Saurabh_Nayar on 5/26/2016.
 */
public class SpringContextUtil {

    ApplicationContext context = null;

    public static void setInstance(SpringContextUtil instance) {
        SpringContextUtil.instance = instance;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    static SpringContextUtil instance = null;
    private SpringContextUtil() {
        context =
                new ClassPathXmlApplicationContext("spring-config.xml");

        System.out.println(context.getBean("dataSource"));
        System.out.println(context.getBean("entityManagerFactory"));
    }

    public synchronized static SpringContextUtil getInstance() {
        if(instance == null) {
            instance = new SpringContextUtil();
        }
        return instance;
    }
}

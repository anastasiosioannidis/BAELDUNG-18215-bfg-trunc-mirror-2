package com.baeldung.spring.jms;

import com.baeldung.spring.jms.SampleJmsMessageSender;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DefaultTextMessageSenderTest {

    private static SampleJmsMessageSender messageProducer;

    @SuppressWarnings("resource")
    @BeforeClass
    public static void setUp() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] { "classpath:EmbeddedActiveMQ.xml", "classpath:applicationContext.xml" });
        messageProducer = (SampleJmsMessageSender) applicationContext.getBean("SampleJmsMessageSender");
    }

    @Test
    public void testSimpleSend() {
        messageProducer.simpleSend();
    }

}

package com.baeldung.logback;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MapAppenderIntegrationTest {

    private Logger rootLogger;

    @Before
    public void setUp() {
        rootLogger = (Logger) LoggerFactory.getLogger("ROOT");
    }

    @Test
    public void whenLoggerEmitsLoggingEvent_thenAppenderReceivesEvent() {
        rootLogger.info("Test from {}", this.getClass().getSimpleName());
        MapAppender appender = (MapAppender) rootLogger.getAppender("map");

        List<String> messages = appender.getEventMap().values().stream().map(ILoggingEvent::getMessage).collect(toList());
        assertThat(messages, hasItems("Test from {}"));
    }

    @Test
    public void givenNoPrefixSet_whenLoggerEmitsEvent_thenAppenderReceivesNoEvent() {
        rootLogger.info("Test from {}", this.getClass().getSimpleName());
        MapAppender appender = (MapAppender) rootLogger.getAppender("badMap");
        assertEquals(appender.getEventMap().size(), 0);
    }
}

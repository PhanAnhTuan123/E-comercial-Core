package vn.com.anhTuan.commons.utils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class LoggingFilter  extends Filter<ILoggingEvent> {

    private static final Pattern HEALTH_OR_PROMETHEUS = Pattern.compile("^(health|prometheus)$");

    private static final String COMPLETED = "Completed 200 OK";

    private static final Pattern ZIPKIN = Pattern.compile("^(zipkin)$");

    private final Set<String> threadsToIgnore = new HashSet<>();


    @Override
    public FilterReply decide(ILoggingEvent iLoggingEvent) {
        String message = iLoggingEvent.getMessage();
        String threadName = iLoggingEvent.getThreadName();
        if(isHealthOrPrometheus(message) || isZipkin(message)) {
            threadsToIgnore.add(threadName);
            return FilterReply.DENY;
        } else if (isThreadToIgnore(threadName)) {
            if(isCompleted200Ok(message)) {
                threadsToIgnore.remove(message);
            }
            return FilterReply.DENY;
        }
        return FilterReply.ACCEPT;
    }

    private boolean isHealthOrPrometheus(String message) {
        return HEALTH_OR_PROMETHEUS.matcher(message).find();
    }

    private boolean isZipkin(String message) {
        return ZIPKIN.matcher(message).find();
    }

    private boolean isThreadToIgnore(String threadName) {
        return threadsToIgnore.contains(threadName);
    }

    private boolean isCompleted200Ok(String message) {
        return message.contains(COMPLETED);
    }

}

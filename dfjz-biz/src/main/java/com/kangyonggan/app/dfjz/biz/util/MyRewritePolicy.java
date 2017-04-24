package com.kangyonggan.app.dfjz.biz.util;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.rewrite.RewritePolicy;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.message.SimpleMessage;

/**
 * @author kangyonggan
 * @since 2017/4/24 0024
 */
@Plugin(name = "MyRewritePolicy", category = "Core", elementType = "rewritePolicy", printObject = true)
public final class MyRewritePolicy implements RewritePolicy {

    @Override
    public LogEvent rewrite(final LogEvent event) {
        String message = event.getMessage().getFormattedMessage();
        // TODO 处理日志的逻辑

        SimpleMessage simpleMessage = new SimpleMessage(message);
        LogEvent result = new Log4jLogEvent(event.getLoggerName(), event.getMarker(),
                event.getLoggerFqcn(), event.getLevel(), simpleMessage,
                event.getThrown(), event.getContextMap(), event.getContextStack(),
                event.getThreadName(), event.getSource(), event.getTimeMillis());

        return result;
    }

//    @PluginFactory
    public static MyRewritePolicy createAppender() {
        return new MyRewritePolicy();
    }
}

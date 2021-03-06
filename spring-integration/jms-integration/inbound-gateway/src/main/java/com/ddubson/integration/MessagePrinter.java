package com.ddubson.integration;

import com.ddubson.LogAdapter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.ddubson.ANSIColor.ANSI_CYAN;
import static com.ddubson.ANSIColor.ANSI_YELLOW;

@Component
public class MessagePrinter {
    private LogAdapter logAdapter;

    public MessagePrinter(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    public String print(Message<?> message) {
        logAdapter.info("[4] Message consumed off of jmsChannel: " + message, ANSI_YELLOW);
        logAdapter.info("[5] Returning message to ActiveMQ.", ANSI_CYAN);
        return "1 message worked.";
    }
}

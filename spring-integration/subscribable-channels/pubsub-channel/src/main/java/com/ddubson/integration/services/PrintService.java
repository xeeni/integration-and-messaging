package com.ddubson.integration.services;

import com.ddubson.ANSIColor;
import com.ddubson.LogAdapter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class PrintService {
    private final LogAdapter logAdapter;

    public PrintService(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    public void print(Message<String> msg) {
        logAdapter.info(
                format("[2] Consuming pub sub channel -- [%s] %s", Thread.currentThread().getName(),
                        msg.getPayload()), ANSIColor.ANSI_GREEN);
    }
}

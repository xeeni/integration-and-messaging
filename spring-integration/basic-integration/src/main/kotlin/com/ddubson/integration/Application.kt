package com.ddubson.integration

import com.ddubson.ANSIColor
import com.ddubson.CommandLineLogAdapter
import com.ddubson.LogAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.integration.channel.DirectChannel
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.GenericMessage
import org.springframework.messaging.support.MessageBuilder

@SpringBootApplication
open class Application(val outputChannel:DirectChannel,
                       val inputChannel:DirectChannel,
                       val logAdapter:LogAdapter) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        val message = GenericMessage<Any>("Hello World", createMessageHeaders())
        val message2 = MessageBuilder
                .withPayload("Hello World (Builder Pattern")
                .setHeader("newKey", "newValue")
                .build()

        outputChannel.subscribe(
                { msg -> logAdapter.info("[3] Output channel received: $msg", ANSIColor.ANSI_BLUE) })
        logAdapter.info("[1] Send two messages into input channel.", ANSIColor.ANSI_RED)
        inputChannel.send(message)
        inputChannel.send(message2)
    }

    private fun createMessageHeaders(): MessageHeaders? {
        val map: Map<String, Any?> = hashMapOf(Pair("key", "value"))
        return MessageHeaders(map)
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }
}


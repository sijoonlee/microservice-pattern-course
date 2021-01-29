package com.course.microservice.broker.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.MasterPayrollMessage;

@Component
public class MasterPayrollDltListener {

	private static final Logger LOG = LoggerFactory.getLogger(MasterPayrollDltListener.class);

	// This method is for dead letter topic (Lecture : Communication Error Handling)
	@KafkaListener(topics = "t.masterpayroll.DLT")
	public void listenMasterPayrollDlt(@Payload MasterPayrollMessage message,
			@Header(KafkaHeaders.DLT_EXCEPTION_MESSAGE) String dltExceptionMessage,
			@Header(KafkaHeaders.DLT_ORIGINAL_TOPIC) String originalTopic) {
		LOG.debug("[Async-DLT] Start listening masterpayroll dead letter topic");
		LOG.info("[Async-DLT] Message in DLT : {}", message);
		LOG.info("[Async-DLT] Exception message : {}", dltExceptionMessage);
		LOG.info("[Async-DLT] Original topic : {}", originalTopic);
	}

}

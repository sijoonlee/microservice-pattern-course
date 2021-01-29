package com.course.microservice.broker.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.MasterPayrollMessage;
import com.course.microservice.broker.message.MasterPayrollResponseMessage;
import com.course.microservice.command.service.MasterPayrollService;

@Component
public class MasterPayrollListener {

	private static final Logger LOG = LoggerFactory.getLogger(MasterPayrollListener.class);

	@Autowired
	private MasterPayrollService masterPayrollService;

	// This method is for async request / response (Lecture : Inter Process
	// Communication)
	// Comment either listenMasterPayroll or listenMasterPayrollWithException to
	// avoid confusion
//	@KafkaListener(topics = "t.masterpayroll")
//	@SendTo(value = "t.masterpayroll.response")
//	public MasterPayrollResponseMessage listenMasterPayroll(MasterPayrollMessage message) {
//		LOG.debug("[Async] Start listening from message broker, disabling master payroll");
//
//		masterPayrollService.disablePayroll(message.getEmployeeId(), message.getEffectiveDate());
//
//		var masterPayrollResponseMessage = new MasterPayrollResponseMessage();
//		masterPayrollResponseMessage.setEmployeeId(message.getEmployeeId());
//		masterPayrollResponseMessage
//				.setMessage("Done simulating disable master payroll for " + message.getEmployeeId());
//
//		LOG.debug("[Async] Finish listening from message broker, disabling master payroll");
//
//		return masterPayrollResponseMessage;
//	}

	// container factory is for dead letter topic (Lecture : Error handling)
	// Comment either listenMasterPayroll or listenMasterPayrollWithException to
	// avoid confusion
	@KafkaListener(topics = "t.masterpayroll", containerFactory = "deadLetterContainerFactory")
	@SendTo(value = "t.masterpayroll.response")
	public MasterPayrollResponseMessage listenMasterPayrollWithException(MasterPayrollMessage message) {
		LOG.debug("[Async] Start listening from message broker, disabling master payroll");

		// hardcoded to simulate message processing failure (wrong data)
		if (message.getEmployeeId().equalsIgnoreCase("emp-0000")) {
			LOG.warn("[Async] Invalid employee (hardcoded) : {}", message.getEmployeeId());
			throw new IllegalArgumentException("Invalid employee (hardcoded) : " + message.getEmployeeId());
		}

		masterPayrollService.disablePayroll(message.getEmployeeId(), message.getEffectiveDate());

		var masterPayrollResponseMessage = new MasterPayrollResponseMessage();
		masterPayrollResponseMessage.setEmployeeId(message.getEmployeeId());
		masterPayrollResponseMessage
				.setMessage("Done simulating disable master payroll for " + message.getEmployeeId());

		LOG.debug("[Async] Finish listening from message broker, disabling master payroll");

		return masterPayrollResponseMessage;
	}

}

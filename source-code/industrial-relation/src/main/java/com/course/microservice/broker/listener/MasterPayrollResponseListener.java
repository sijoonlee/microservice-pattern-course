package com.course.microservice.broker.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.MasterPayrollResponseMessage;
import com.course.microservice.command.service.TerminationAsyncService;

@Component
public class MasterPayrollResponseListener {

	private static final Logger LOG = LoggerFactory.getLogger(MasterPayrollResponseListener.class);

	@Autowired
	private TerminationAsyncService masterPayrollService;

	@KafkaListener(topics = "t.masterpayroll.response")
	public void listenMasterPayrollResponse(MasterPayrollResponseMessage message) {
		LOG.debug("[Async-listen] Start listening from message broker, response from disabling master payroll");

		masterPayrollService.finalizeEmployeeTerminationApproval(message);

		LOG.debug("[Async-listen] Finish listening from message broker, response from disabling master payroll");
	}

}

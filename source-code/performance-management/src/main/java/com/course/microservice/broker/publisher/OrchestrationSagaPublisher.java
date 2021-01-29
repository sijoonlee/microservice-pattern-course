package com.course.microservice.broker.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.course.microservice.broker.message.FinalizeAppraisalMessage;
import com.course.microservice.broker.message.PayBonusMessage;
import com.course.microservice.broker.message.RecordBonusErrorMessage;
import com.course.microservice.broker.message.RecordBonusMessage;

@Component
public class OrchestrationSagaPublisher {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void publishRecordBonusError(RecordBonusErrorMessage recordBonusErrorMessage) {
		kafkaTemplate.send("t.saga04.performancemanagement.request", recordBonusErrorMessage);
		kafkaTemplate.send("t.saga04.payrollcompensation.request", recordBonusErrorMessage);
	}

	public void publishToBonusPayment(PayBonusMessage appraisalApprovedMessage) {
		kafkaTemplate.send("t.saga03.payrollcompensation.request", appraisalApprovedMessage);
	}

	public void publishToCompensatingBonusPayment(PayBonusMessage payBonusMessage) {
		kafkaTemplate.send("t.saga04.payrollcompensation.request", payBonusMessage);
	}

	public void publishToCompensatingRecordBonus(RecordBonusMessage recordBonusMessage) {
		kafkaTemplate.send("t.saga04.organizationdevelopment.request", recordBonusMessage);
	}

	public void publishToFinalizeAppraisal(FinalizeAppraisalMessage finalizeAppraisalMessage) {
		kafkaTemplate.send("t.saga03.performancemanagement.request", finalizeAppraisalMessage);
	}

	public void publishToRecordBonus(RecordBonusMessage recordBonusMessage) {
		kafkaTemplate.send("t.saga03.organizationdevelopment.request", recordBonusMessage);
	}

}

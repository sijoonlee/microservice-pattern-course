package com.course.microservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {

	@Autowired
	private KafkaProperties kafkaProperties;

	private ConsumerFactory<Object, Object> consumerFactory() {
		var properties = kafkaProperties.buildConsumerProperties();
		return new DefaultKafkaConsumerFactory<>(properties);
	}

	@Bean(value = "deadLetterContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<Object, Object> deadLetterContainerFactory(
			ConcurrentKafkaListenerContainerFactoryConfigurer configurer, KafkaTemplate<Object, Object> template) {
		var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
		configurer.configure(factory, consumerFactory());

		var recoverer = new DeadLetterPublishingRecoverer(template);

		factory.getContainerProperties().setAckOnError(false);

		var errorHandler = new SeekToCurrentErrorHandler(recoverer, new FixedBackOff(3000, 4));
		factory.setErrorHandler(errorHandler);

		return factory;
	}

}

package com.course.microservice.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
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

	private ConsumerFactory<Object, Object> consumerFactoryWithStringDeserializer() {
		var properties = kafkaProperties.buildConsumerProperties();

		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

		return new DefaultKafkaConsumerFactory<>(properties);
	}

	@Bean(value = "stringDeserializerContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<Object, Object> deadLetterContainerFactory(
			ConcurrentKafkaListenerContainerFactoryConfigurer configurer, KafkaTemplate<Object, Object> template) {
		var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
		configurer.configure(factory, consumerFactoryWithStringDeserializer());

		var recoverer = new DeadLetterPublishingRecoverer(template);

		factory.getContainerProperties().setAckOnError(false);

		var errorHandler = new SeekToCurrentErrorHandler(recoverer, new FixedBackOff(3000, 4));
		factory.setErrorHandler(errorHandler);

		return factory;
	}

}

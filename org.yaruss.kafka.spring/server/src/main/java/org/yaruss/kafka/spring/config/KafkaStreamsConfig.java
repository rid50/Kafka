package org.yaruss.kafka.spring.config;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
public class KafkaStreamsConfig {

	private boolean isEnabled = true;

	@Value("${kafka.input.topic}")
	private String kafkaInputTopic;

	@Value("${kafka.output.topic}")
	private String kafkaOutputTopic;

	@Value("${spring.kafka.bootstrap-servers}")
	private String kafkaBootstrapServer;

	public void enableSheduling() {
		isEnabled = true;
	}
	
	public void disableSheduling() {
		isEnabled = false;
	}	

	@Bean
	public KStream<String, String> kstream() {
	// public Properties prop() {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "yaruss-stomp-websocket");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServer);
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

		//if (isEnabled) {
		StreamsBuilder builder = new StreamsBuilder();

        // KStream<String, String> stream = builder.stream(kafkaInputTopic);
        // builder.stream(kafkaInputTopic).to(kafkaOutputTopic);
				
		final KStream<String, String> stream = builder.stream(kafkaInputTopic,
				Consumed.with(Serdes.String(), Serdes.String()));

		stream.map((key, value) -> KeyValue.pair(key, value)).to(kafkaOutputTopic,
				Produced.with(Serdes.String(), Serdes.String()));

		KafkaStreams streams = new KafkaStreams(builder.build(), props);
		streams.start();

        //Add shutdown hook to close the streams application gracefully
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
		
		return stream;
		// return props;
	}

}

package org.yaruss.kafka.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//registry.addEndpoint("/websocket").withSockJS();
		registry.addEndpoint("/websocket").setAllowedOriginPatterns("*");
		//registry.addEndpoint("/websocket").setAllowedOrigins("http://localhost:4200").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		//config.enableSimpleBroker("/topic");
		config.enableSimpleBroker("/topic", "/queue"); 
		config.setApplicationDestinationPrefixes("/app");
	}
	
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setMessageSizeLimit(256 * 1024);
		registration.setSendTimeLimit(15 * 1000).setSendBufferSizeLimit(768 * 1024);
    }	

}

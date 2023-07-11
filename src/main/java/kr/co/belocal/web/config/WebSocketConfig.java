package kr.co.belocal.web.config;



import com.sun.nio.sctp.NotificationHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;


@Slf4j
@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // 웹소켓 configuration의 addHandler 메소드와 유사
    // cors, SockJS 설정 가능
    /*
       STOMP를 사용하면 웹소켓만 사용할 때와 다르게 하나의 연결주소마다 핸들러 클래스를 따로 구현할 필요없이
       Controller 방식으로 간편하게 사용할 수 있다.
     */



    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // stomp 접속 주소 url => /ws-stomp
        registry.addEndpoint("/ws-stomp").setAllowedOriginPatterns("*").withSockJS(); // SocketJS 를 연결한다는 설정
    }

    // STOMP에서 사용하는 메시지 브로커 설정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // 메시지를 구독하는 요청 url => 즉 메시지 받을 때
        registry.enableSimpleBroker("/sub");

        // 메시지를 발행하는 요청 url => 즉 메시지 보낼 때
        registry.setApplicationDestinationPrefixes("/pub");
    }


}

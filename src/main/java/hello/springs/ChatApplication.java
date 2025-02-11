package hello.springs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "hello.springs.websocket.chat")
public class ChatApplication {
    public static void main(String[] args){
        SpringApplication.run(ChatApplication.class, args);
    }
}

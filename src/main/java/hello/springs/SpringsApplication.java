package hello.springs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//core application run : hello.core안
//file - settings - gradle 검색 후 intelliJ 로 바꿔줄 것
public class SpringsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsApplication.class, args);
	}

}

package hello.springs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "hello.springs")
//자동 빈 주입, Component, ComponentScan
public class AutoAppConfig {
}

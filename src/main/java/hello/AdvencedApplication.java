package hello;

import hello.proxy.config.AppV1Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(AppV1Config.class)
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의
public class AdvencedApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvencedApplication.class, args);
	}

}

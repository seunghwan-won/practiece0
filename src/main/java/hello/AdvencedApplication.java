package hello;

import hello.proxy.config.v1_proxy.ConcreteProxyConfig;
import hello.proxy.config.v1_proxy.InterfaceProxyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//@Import({AppV1Config.class, AppV2Config.class})
@Import({InterfaceProxyConfig.class, ConcreteProxyConfig.class})
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의
public class AdvencedApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvencedApplication.class, args);
	}

}

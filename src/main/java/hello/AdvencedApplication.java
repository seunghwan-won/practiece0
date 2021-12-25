package hello;

import hello.proxy.config.v1_proxy.ConcreteProxyConfig;
import hello.proxy.config.v1_proxy.InterfaceProxyConfig;
import hello.proxy.config.v2_dynamic_proxy.DynamicFilterProxyConfig;
import hello.proxy.config.v2_dynamic_proxy.DynamicProxyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//@Import({AppV1Config.class, AppV2Config.class})
//@Import({InterfaceProxyConfig.class, ConcreteProxyConfig.class})
@Import({DynamicFilterProxyConfig.class})
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의
public class AdvencedApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvencedApplication.class, args);
	}

}

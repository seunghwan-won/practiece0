package hello;

import hello.proxy.config.v5_auto_proxy.AutoProxyConfig;
import hello.proxy.config.v6_aspect.AspectConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//@Import({AppV1Config.class, AppV2Config.class})
//@Import({InterfaceProxyConfig.class, ConcreteProxyConfig.class})
//@Import({DynamicFilterProxyConfig.class})
//@Import({ProxyFactoryConfigV2.class})
//@Import({PostProcessorConfig.class})
//@Import({AutoProxyConfig.class})
@Import({AspectConfig.class})
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의
public class AdvencedApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvencedApplication.class, args);
	}

}

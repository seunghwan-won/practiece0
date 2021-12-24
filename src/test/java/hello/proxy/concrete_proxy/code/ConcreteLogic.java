package hello.proxy.concrete_proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteLogic {

    public String operation() {
        log.info("Concrete Logic 실행");
        return "data";
    }
}

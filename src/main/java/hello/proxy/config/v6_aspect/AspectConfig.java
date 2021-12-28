package hello.proxy.config.v6_aspect;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v6_aspect.aspect.LogTraceAspect;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AspectConfig {
    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

    @Bean
    public LogTraceAspect advisor(LogTrace logTrace) {
        LogTraceAspect logTraceAspect = new LogTraceAspect(logTrace);
        return logTraceAspect;
    }
}

package hello.advenced.app.config;

import hello.advenced.app.trace.LogTrace;
import hello.advenced.app.trace.callback.TraceTemplate;
import hello.advenced.app.trace.logTrace.FieldLogTrace;
import hello.advenced.app.trace.logTrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
//        return new FieldLogTrace();
        return new ThreadLocalLogTrace();
    }

    @Bean
    public TraceTemplate traceTemplate(LogTrace trace) {
        return new TraceTemplate(trace);
    }
}

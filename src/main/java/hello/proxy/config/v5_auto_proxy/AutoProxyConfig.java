package hello.proxy.config.v5_auto_proxy;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v3_prox_factory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {
    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

//    @Bean
    public Advisor advisor1(LogTrace logTrace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");
        Advice advice = new LogTraceAdvice(logTrace);
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
        return advisor;
    }

//    @Bean
    public Advisor advisor2(LogTrace logTrace) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* hello.proxy.app..*(..))");
        Advice advice = new LogTraceAdvice(logTrace);
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
        return advisor;
    }

    @Bean
    public Advisor advisor3(LogTrace logTrace) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* hello.proxy.app..*(..)) && !execution(* hello.proxy.app..nolog(..))");
        Advice advice = new LogTraceAdvice(logTrace);
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        return advisor;
    }
}

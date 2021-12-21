package hello.advenced.app.trace.logTrace;

import hello.advenced.app.trace.LogTrace;
import hello.advenced.app.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThreadLocalLogTraceTest {

    LogTrace logTrace = new ThreadLocalLogTrace();

    @Test
    void begin() {
        TraceStatus begin1 = logTrace.begin("begin1");
        TraceStatus begin2 = logTrace.begin("begin2");
        logTrace.end(begin2);
        logTrace.end(begin1);
    }

    @Test
    void exception() {
        TraceStatus begin1 = logTrace.begin("begin1");
        TraceStatus begin2 = logTrace.begin("begin2");
        logTrace.exception(begin2, new IllegalArgumentException());
        logTrace.exception(begin1, new IllegalArgumentException());
    }
}
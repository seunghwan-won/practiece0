package hello.advenced.app.trace.helloTrace;

import hello.advenced.app.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloTraceV2Test {

    @Test
    void beginSync() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus first = trace.begin("hello");
        TraceStatus next = trace.beginSync(first.getTraceId(), "hello");
        trace.end(next);
        trace.end(first);
    }

    @Test
    void exception() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus first = trace.begin("hello");
        TraceStatus next = trace.beginSync(first.getTraceId(), "hello");
        trace.exception(next, new IllegalArgumentException());
        trace.exception(first, new IllegalArgumentException());
    }
}
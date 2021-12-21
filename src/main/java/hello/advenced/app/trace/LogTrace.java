package hello.advenced.app.trace;

import hello.advenced.app.trace.TraceStatus;

public interface LogTrace {
     TraceStatus begin(String message);
     void end(TraceStatus traceStatus);
     void exception(TraceStatus traceStatus, Exception e);
}

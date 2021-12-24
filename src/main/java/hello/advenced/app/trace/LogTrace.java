package hello.advenced.app.trace;

public interface LogTrace {
     TraceStatus begin(String message);
     void end(TraceStatus traceStatus);
     void exception(TraceStatus traceStatus, Exception e);
}

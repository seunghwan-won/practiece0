package hello.advenced.app.trace.logTrace;

import hello.advenced.app.trace.LogTrace;
import hello.advenced.app.trace.TraceId;
import hello.advenced.app.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldLogTrace implements LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";
    private TraceId traceHolder;
    @Override
    public TraceStatus begin(String message) {
        syncTraceId();
        long startTimeMs = System.currentTimeMillis();
        //로그 출력
        log.info("[{}] {}{}", traceHolder.getId(), addSpace(START_PREFIX, traceHolder.getLevel()), message);
        return new TraceStatus(traceHolder, startTimeMs, message);
    }

    @Override
    public void end(TraceStatus traceStatus) {
        complete(traceStatus, null);
    }

    @Override
    public void exception(TraceStatus traceStatus, Exception e) {
        complete(traceStatus, e);
    }

    private void syncTraceId() {
        if(traceHolder == null) {
            traceHolder = new TraceId();
        } else {
            traceHolder = traceHolder.createNextId();
        }
    }

    private void releaseTraceId() {
        if(traceHolder.isFirstLevel()) {
            traceHolder = null;
        } else {
            traceHolder = traceHolder.createPreviousId();
        }
    }

    private void complete(TraceStatus traceStatus, Exception e) {
        long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - traceStatus.getStartTimeMs();
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceHolder.getId(), addSpace(COMPLETE_PREFIX, traceHolder.getLevel()),
                    traceStatus.getMessage(), resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceHolder.getId(), addSpace(EX_PREFIX , traceHolder.getLevel()),
                    traceStatus.getMessage(), resultTimeMs, e.toString());
        }
        releaseTraceId();
    }

    private String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }
}

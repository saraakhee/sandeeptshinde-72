package com.example.demo.logger;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
public class TraceIdFilter implements Filter {
    private static final Logger log =
            LoggerFactory.getLogger(TraceIdFilter.class);

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        log.info(">>> TraceIdFilter invoked");  //MUST SEE THIS
        try {
            String traceId =
                    Optional.ofNullable(
                                    ((HttpServletRequest) request)
                                            .getHeader("X-Trace-Id"))
                            .orElse(UUID.randomUUID().toString());

            MDC.put("traceId", traceId);
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}

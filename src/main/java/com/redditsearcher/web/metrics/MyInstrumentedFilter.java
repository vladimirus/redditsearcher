package com.redditsearcher.web.metrics;


import com.codahale.metrics.servlet.InstrumentedFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * MyInstrumentedFilter to exclude some urls.
 */
public class MyInstrumentedFilter extends InstrumentedFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String path = ((HttpServletRequest) request).getRequestURI();
        if (!path.startsWith("/_admin") && !"/".equals(path)) { //ignore and homepage for now, only /search matters
            super.doFilter(request, response, chain);
        } else {
            chain.doFilter(request, response);
        }
    }
}

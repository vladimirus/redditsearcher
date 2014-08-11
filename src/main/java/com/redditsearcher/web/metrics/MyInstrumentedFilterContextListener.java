package com.redditsearcher.web.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlet.InstrumentedFilterContextListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;

/**
 * MyInstrumentedFilterContextListener.
 */
public class MyInstrumentedFilterContextListener extends InstrumentedFilterContextListener {
    private MetricRegistry registry;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        WebApplicationContext parentApplicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        this.registry = parentApplicationContext.getBean(MetricRegistry.class);
        super.contextInitialized(event);
    }

    @Override
    protected MetricRegistry getMetricRegistry() {
        return registry;
    }
}

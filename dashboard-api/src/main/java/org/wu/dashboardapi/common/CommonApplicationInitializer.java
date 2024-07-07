package org.wu.dashboardapi.common;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public abstract class CommonApplicationInitializer extends SpringBootServletInitializer {
    protected abstract SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder);
}

package ru.yandex.qatools.blog;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;

/**
 * Created by azee on 02.10.14.
 */
public class Server extends ResourceConfig {
    public Server() {
        register(LoggingFilter.class);
        register(FreemarkerMvcFeature.class);
        packages(Server.class.getPackage().getName());
    }
}

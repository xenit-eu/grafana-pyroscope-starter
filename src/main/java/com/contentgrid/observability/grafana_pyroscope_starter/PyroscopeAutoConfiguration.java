package com.contentgrid.observability.grafana_pyroscope_starter;

import static io.pyroscope.javaagent.PyroscopeAgent.start;

import io.pyroscope.http.Format;
import io.pyroscope.javaagent.EventType;
import io.pyroscope.javaagent.config.Config;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(PyroscopeProperties.class)
public class PyroscopeAutoConfiguration {

    @Bean
    @ConditionalOnProperty(value = "pyroscope.server-address")
    public Object pyroscope(@Value("${spring.application.name}") String applicationName, PyroscopeProperties pyroscopeProperties) {
        return new Object() {

            @PostConstruct
            public void init() {
                start(
                        new Config.Builder()
                                .setApplicationName(applicationName)
                                .setProfilingEvent(EventType.ITIMER)
                                .setFormat(Format.JFR)
                                .setServerAddress(pyroscopeProperties.getServerAddress())
                                .setBasicAuthUser(pyroscopeProperties.getBasicAuthUser())
                                .setBasicAuthPassword(pyroscopeProperties.getBasicAuthPassword())
                                .build()
                );
            }
        };
    }

}

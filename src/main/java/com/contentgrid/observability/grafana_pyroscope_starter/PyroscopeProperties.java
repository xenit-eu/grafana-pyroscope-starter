package com.contentgrid.observability.grafana_pyroscope_starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "pyroscope")
public class PyroscopeProperties {

    private String serverAddress;
    private String basicAuthUser;
    private String basicAuthPassword;
}

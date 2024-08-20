# Grafana Pyroscope Starter for Spring Boot

This project provides an auto-configuration starter for integrating [Pyroscope](https://pyroscope.io/) with a Spring Boot application. The starter automatically configures and starts the Pyroscope Java agent to profile your application and send performance data to a Pyroscope server.

## Features

- **Automatic Agent Configuration**: Automatically configures and starts the Pyroscope agent for your Spring Boot application.
- **Spring Boot Integration**: Leverages Spring Boot's auto-configuration and properties management for seamless integration.
- **Conditional Loading**: The Pyroscope agent is only started if the `pyroscope.server-address` property is set, ensuring the agent is only configured when needed.

## Installation

To include this starter in your Spring Boot project, add the following dependency to your `pom.xml` (if it's not already included):

```xml
<dependency>
    <groupId>com.contentgrid.observability</groupId>
    <artifactId>grafana-pyroscope-starter</artifactId>
    <version>1.0.0</version>
    <scope>runtime</scope>
</dependency>
```

Alternatively, if you are using Gradle, add the following to your `build.gradle`:

```groovy
runtimeOnly 'com.contentgrid.observability:grafana-pyroscope-starter:1.0.0'
```

## Usage

To enable Pyroscope in your Spring Boot application, you need to configure the `pyroscope.server-address` property in your `application.properties` or `application.yml` file.

### Example Configuration

#### `application.properties`:

```properties
spring.application.name=my-spring-boot-app
pyroscope.server-address=http://localhost:4040
pyroscope.basic-auth-user=myUsername
pyroscope.basic-auth-password=myPassword
```

#### `application.yml`:

```yaml
spring:
  application:
    name: my-spring-boot-app

pyroscope:
  server-address: http://localhost:4040
  basic-auth-user: myUsername
  basic-auth-password: myPassword
```

### Configurable Properties

The following properties can be configured to customize the Pyroscope integration:

- **`pyroscope.server-address`** (required): The address of the Pyroscope server where profiling data will be sent.
- **`pyroscope.basic-auth-user`** (optional): The username for basic authentication to the Pyroscope server.
- **`pyroscope.basic-auth-password`** (optional): The password for basic authentication to the Pyroscope server.
- **`spring.application.name`** (required): The name of the application, which will be used as the `applicationName` in Pyroscope.

### How It Works

When the `pyroscope.server-address` property is set, the `PyroscopeAutoConfiguration` class configures and starts the Pyroscope agent. The agent is initialized with the following settings:

- **Application Name**: Set from `spring.application.name`.
- **Profiling Event**: Defaulted to `EventType.ITIMER`.
- **Data Format**: Defaulted to `Format.JFR` (Java Flight Recorder).
- **Server Address**: Set from `pyroscope.server-address`.
- **Basic Auth**: If provided, `pyroscope.basic-auth-user` and `pyroscope.basic-auth-password` are used for authentication.

The agent is started using the `start()` method in a `@PostConstruct` annotated method, ensuring it begins profiling when the Spring context is fully initialized.

## Contributing

If you encounter any issues or have suggestions for improvements, feel free to open an issue or submit a pull request.

## License

This project is licensed under the Apache 2 License. See the [license](LICENSE) file for details.

## Acknowledgments

Special thanks to the Pyroscope team for their excellent profiling tools.

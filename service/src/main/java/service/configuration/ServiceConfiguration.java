package service.configuration;

import com.varvashevich.configuration.DatabaseConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("service")
@Import(DatabaseConfiguration.class)
public class ServiceConfiguration {
}
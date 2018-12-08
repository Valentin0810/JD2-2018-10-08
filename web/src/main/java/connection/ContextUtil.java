package connection;

import lombok.experimental.UtilityClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.configuration.ServiceConfiguration;

@UtilityClass
public class ContextUtil {

    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ServiceConfiguration.class);

    public static AnnotationConfigApplicationContext getContext() {
        return context;
    }
}
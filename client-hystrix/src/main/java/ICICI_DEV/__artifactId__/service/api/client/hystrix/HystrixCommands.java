package ICICI_DEV.${artifactId}.service.api.client.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;

public enum HystrixCommands {;

    public static HystrixCommandGroupKey groupKey(Class<?> service, Class<?> command) {
        String serviceName = service.getSimpleName().replaceFirst("Service$", "");
        String commandName = command.getSimpleName().replaceFirst("Command$", "");
        return HystrixCommandGroupKey.Factory.asKey(serviceName + "." + commandName);
    }

}

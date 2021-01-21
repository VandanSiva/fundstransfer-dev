package ICICI_DEV.${artifactId}.service.api.exception;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("ThrowableInstanceNeverThrown")
public enum ServiceExceptionFixture {;

    public static ServiceException serviceException1 = new ServiceException("error #1");

    public static ServiceException serviceException2 = new ServiceException("error #2");

    public static ServiceException serviceException3 = new ServiceException("error #3");

    public static ServiceException serviceException4 = new ServiceException("error #4");

    public static List<ServiceException> serviceExceptions = Arrays.asList(
            serviceException1,
            serviceException2,
            serviceException3,
            serviceException4);

}

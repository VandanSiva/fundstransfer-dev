package ICICI_DEV.${artifactId}.service.api.v1.exception;

import ICICI_DEV.${artifactId}.service.api.exception.ServiceException;
import ICICI_DEV.${artifactId}.service.api.transport.v1.Transportable;

public enum TransportableServiceException {;

    public static Transportable.ServiceException from(ServiceException serviceException) {
        return Transportable.ServiceException.newBuilder()
                .setMessage(serviceException.getMessage())
                .build();
    }

    public static ServiceException to(Transportable.ServiceException transportable) {
        return new ServiceException(transportable.getMessage());
    }

}

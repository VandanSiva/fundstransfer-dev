package ICICI_DEV.${artifactId}.service.api.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

}

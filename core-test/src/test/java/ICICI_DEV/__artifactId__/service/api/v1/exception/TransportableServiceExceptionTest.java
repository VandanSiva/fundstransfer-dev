package ICICI_DEV.${artifactId}.service.api.v1.exception;

import ICICI_DEV.${artifactId}.service.api.exception.ServiceException;
import ICICI_DEV.${artifactId}.service.api.exception.ServiceExceptionFixture;
import ICICI_DEV.${artifactId}.service.api.transport.v1.Transportable;
import org.junit.Assert;
import org.junit.Test;

public class TransportableServiceExceptionTest {

    @Test
    public void shouldConvertFixtures() {
        for (ServiceException expectedServiceException : ServiceExceptionFixture.serviceExceptions) {
            Transportable.ServiceException transportable = TransportableServiceException.from(expectedServiceException);
            ServiceException actualServiceException = TransportableServiceException.to(transportable);
            Assert.assertEquals(expectedServiceException, actualServiceException);
        }
    }

}

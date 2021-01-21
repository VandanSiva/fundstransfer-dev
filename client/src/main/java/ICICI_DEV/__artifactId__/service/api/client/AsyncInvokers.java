package ICICI_DEV.${artifactId}.service.api.client;

import com.google.protobuf.Message;
import ICICI_DEV.${artifactId}.service.api.exception.ServiceException;
import ICICI_DEV.${artifactId}.service.api.v1.exception.TransportableServiceException;
import ICICI_DEV.${artifactId}.service.api.transport.Transportables;
import ICICI_DEV.${artifactId}.service.api.transport.v1.Transportable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public enum AsyncInvokers {;

    private static final Logger logger = LoggerFactory.getLogger(AsyncInvokers.class);

    public static CompletableFuture<Void> request(AsyncInvoker invoker, String method, Entity<?> entity) {
        CompletableFuture<Void> result = new CompletableFuture<>();
        invoker.method(method, entity, new InvocationCallback<Response>() {

            @Override
            public void completed(Response response) {
                try {
                    requireSuccessfulResponseStatus(response);
                    result.complete(null);
                }
                finally { response.close(); }
            }

            @Override
            public void failed(Throwable throwable) {
                result.completeExceptionally(throwable);
            }

        });
        return result;
    }

    public static <T extends Message> CompletableFuture<Optional<T>>
    request(AsyncInvoker invoker, String method, Entity<?> entity, Class<T> clazz) {
        CompletableFuture<Optional<T>> result = new CompletableFuture<>();
        invoker.method(method, entity, new InvocationCallback<Response>() {

            @Override
            public void completed(Response response) {
                try { result.complete(read(response, clazz)); }
                finally { response.close(); }
            }

            @Override
            public void failed(Throwable throwable) {
                result.completeExceptionally(throwable);
            }

        });
        return result;
    }

    private static <T extends Message> Optional<T> read(Response response, Class<T> clazz) {
        if (Response.Status.NOT_FOUND.equals(response.getStatusInfo())) return Optional.empty();
        requireSuccessfulResponseStatus(response);
        return Optional.of(responseEntityStreamMessage(response, clazz));
    }

    private static void requireSuccessfulResponseStatus(Response response) {
        if (!Response.Status.Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily())) {
            String message = "unsuccessful response status: " + response.getStatusInfo();
            logger.debug(message);
            Optional<ServiceException> optionalServiceException = serviceException(response);
            if (optionalServiceException.isPresent()) throw optionalServiceException.get();
            else throw new RuntimeException(message);
        }
    }

    private static Optional<ServiceException> serviceException(Response response) {
        Optional<ServiceException> result = Optional.empty();
        try {
            if (response.hasEntity())
                result = Optional.of(
                        TransportableServiceException.to(
                                responseEntityStreamMessage(
                                        response, Transportable.ServiceException.class)));
        } catch (Throwable error) { logger.debug("failed reading service exception", error.getMessage()); }
        return result;
    }

    private static <T extends Message> T responseEntityStreamMessage(Response response, Class<T> clazz) {
        InputStream entityStream = responseEntityStream(response);
        T transportable;
        try { transportable = Transportables.readBinary(clazz, entityStream); }
        catch (IllegalAccessException | InvocationTargetException | IOException | NoSuchMethodException exception) {
            String message = "failed reading transportable";
            logger.debug(message, exception);
            throw new RuntimeException(message, exception);
        }
        return transportable;
    }

    private static InputStream responseEntityStream(Response response) {
        Object entity = responseEntity(response);
        if (!(entity instanceof InputStream)) {
            String message = "expecting response entity of type input stream";
            logger.debug(message + ", found: {}", entity.getClass());
            throw new RuntimeException(message);
        }
        return (InputStream) entity;
    }

    private static Object responseEntity(Response response) {
        Object entity = response.getEntity();
        if (!response.hasEntity() || entity == null) {
            String message = "response has no entity";
            logger.debug(message);
            throw new RuntimeException(message);
        }
        return entity;
    }

}

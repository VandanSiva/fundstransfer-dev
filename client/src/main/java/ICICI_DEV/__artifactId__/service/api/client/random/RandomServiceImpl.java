package ICICI_DEV.${artifactId}.service.api.client.v1.random;

import ICICI_DEV.${artifactId}.service.api.client.AsyncInvokers;
import ICICI_DEV.${artifactId}.service.api.v1.common.TransportableFloatDatum;
import ICICI_DEV.${artifactId}.service.api.v1.common.TransportableIntDatum;
import ICICI_DEV.${artifactId}.service.api.v1.random.RandomService;
import ICICI_DEV.${artifactId}.service.api.transport.v1.Transportable;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.Future;

import static ICICI_DEV.${artifactId}.service.api.v1.random.RandomResourceDescription.*;

public class RandomServiceImpl implements RandomService {

    private final AsyncInvoker nextIntInvoker;

    private final AsyncInvoker nextFloatInvoker;

    private final WebTarget seedTarget;

    public RandomServiceImpl(WebTarget rootWebTarget) {

        WebTarget serviceWebTarget = rootWebTarget.path(ROOT_PATH);

        this.nextIntInvoker = serviceWebTarget
                .path(GetNextInt.RELATIVE_PATH)
                .request(MediaType.APPLICATION_OCTET_STREAM_TYPE)
                .async();

        this.nextFloatInvoker = serviceWebTarget
                .path(GetNextFloat.RELATIVE_PATH)
                .request(MediaType.APPLICATION_OCTET_STREAM_TYPE)
                .async();

        this.seedTarget = serviceWebTarget.path(SetSeed.RELATIVE_PATH);

    }

    @Override
    public Future<Integer> nextInt() {
        return AsyncInvokers
                .request(nextIntInvoker, GetNextInt.METHOD, null, Transportable.IntDatum.class)
                .thenApply(Optional::get)
                .thenApply(TransportableIntDatum::to);
    }

    @Override
    public Future<Float> nextFloat() {
        return AsyncInvokers
                .request(nextFloatInvoker, GetNextFloat.METHOD, null, Transportable.FloatDatum.class)
                .thenApply(Optional::get)
                .thenApply(TransportableFloatDatum::to);
    }

    @Override
    public Future<Void> seed(long value) {
        AsyncInvoker invoker = seedTarget
                .resolveTemplate(SetSeed.Param.VALUE, value)
                .request(MediaType.APPLICATION_OCTET_STREAM_TYPE)
                .async();
        return AsyncInvokers.request(invoker, SetSeed.METHOD, null);
    }

}

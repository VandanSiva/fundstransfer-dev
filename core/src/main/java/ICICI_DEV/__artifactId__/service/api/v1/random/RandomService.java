package ICICI_DEV.${artifactId}.service.api.v1.random;

import java.util.concurrent.Future;

public interface RandomService {

    Future<Integer> nextInt();

    Future<Float> nextFloat();

    Future<Void> seed(long value);

}

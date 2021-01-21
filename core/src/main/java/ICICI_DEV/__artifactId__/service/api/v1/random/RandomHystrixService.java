package ICICI_DEV.${artifactId}.service.api.v1.random;

import com.netflix.hystrix.HystrixCommand;

public interface RandomHystrixService {

    HystrixCommand<Integer> nextInt();

    HystrixCommand<Float> nextFloat();

    HystrixCommand<Void> seed(long seed);

}

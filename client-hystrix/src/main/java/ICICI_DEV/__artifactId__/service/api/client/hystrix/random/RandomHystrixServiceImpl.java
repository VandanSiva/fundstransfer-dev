package ICICI_DEV.${artifactId}.service.api.client.hystrix.v1.random;

import com.netflix.hystrix.HystrixCommand;
import ICICI_DEV.${artifactId}.service.api.client.hystrix.HystrixCommands;
import ICICI_DEV.${artifactId}.service.api.v1.random.RandomHystrixService;
import ICICI_DEV.${artifactId}.service.api.v1.random.RandomResourceDescription;
import ICICI_DEV.${artifactId}.service.api.v1.random.RandomService;

public class RandomHystrixServiceImpl implements RandomHystrixService {

    public final RandomService randomService;

    public RandomHystrixServiceImpl(RandomService randomService) { this.randomService = randomService; }

    public class GetNextIntCommand extends HystrixCommand<Integer> {

        protected GetNextIntCommand() {
            super(HystrixCommands.groupKey(RandomService.class, GetNextIntCommand.class));
        }

        @Override
        protected Integer run() throws Exception {
            return randomService.nextInt().get();
        }

    }

    @Override
    public GetNextIntCommand nextInt() { return new GetNextIntCommand(); }

    public class GetNextFloatCommand extends HystrixCommand<Float> {

        protected GetNextFloatCommand() {
            super(HystrixCommands.groupKey(RandomService.class, GetNextFloatCommand.class));
        }

        @Override
        protected Float run() throws Exception {
            return randomService.nextFloat().get();
        }

    }

    @Override
    public GetNextFloatCommand nextFloat() { return new GetNextFloatCommand(); }

    public class SetSeedCommand extends HystrixCommand<Void> {

        public final long seed;

        protected SetSeedCommand(long seed) {
            super(HystrixCommands.groupKey(RandomService.class, SetSeedCommand.class));
            this.seed = seed;
        }

        @Override
        protected Void run() throws Exception {
            return randomService.seed(seed).get();
        }

    }

    @Override
    public SetSeedCommand seed(long seed) { return new SetSeedCommand(seed); }

}

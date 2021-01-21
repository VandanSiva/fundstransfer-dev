package ICICI_DEV.${artifactId}.service.api.v1.common;

import ICICI_DEV.${artifactId}.service.api.transport.v1.Transportable;
import org.junit.Assert;
import org.junit.Test;

public class TransportableIntDatumTest {

    @Test
    public void shouldConvertData() {
        for (int expectedValue : new int[] { Integer.MIN_VALUE, 0, Integer.MAX_VALUE }) {
            Transportable.IntDatum transportable = TransportableIntDatum.from(expectedValue);
            int actualValue = TransportableIntDatum.to(transportable);
            Assert.assertEquals(expectedValue, actualValue);
        }
    }

}

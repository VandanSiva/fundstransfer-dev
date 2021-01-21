package ICICI_DEV.${artifactId}.service.api.v1.common;

import ICICI_DEV.${artifactId}.service.api.transport.v1.Transportable;
import org.junit.Assert;
import org.junit.Test;

public class TransportableFloatDatumTest {

    @Test
    public void shouldConvertData() {
        for (float expectedValue : new float[] { Float.MIN_VALUE, 0, Float.MAX_VALUE }) {
            Transportable.FloatDatum transportable = TransportableFloatDatum.from(expectedValue);
            float actualValue = TransportableFloatDatum.to(transportable);
            Assert.assertEquals(expectedValue, actualValue, 0e5);
        }
    }

}

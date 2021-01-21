package ICICI_DEV.${artifactId}.service.api.v1.common;

import ICICI_DEV.${artifactId}.service.api.transport.v1.Transportable;
import org.junit.Assert;
import org.junit.Test;

public class TransportableStringDatumTest {

    @Test
    public void shouldConvertData() {
        for (String expectedValue : new String[] { "", "foo", "bar baz" }) {
            Transportable.StringDatum transportable = TransportableStringDatum.from(expectedValue);
            String actualValue = TransportableStringDatum.to(transportable);
            Assert.assertEquals(expectedValue, actualValue);
        }
    }

}

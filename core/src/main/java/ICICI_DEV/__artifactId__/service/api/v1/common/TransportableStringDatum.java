package ICICI_DEV.${artifactId}.service.api.v1.common;

import ICICI_DEV.${artifactId}.service.api.transport.v1.Transportable;

public enum TransportableStringDatum {;

    public static Transportable.StringDatum from(String value) {
        return Transportable.StringDatum.newBuilder()
                .setValue(value)
                .build();
    }

    public static String to(Transportable.StringDatum transportable) {
        return transportable.getValue();
    }

}

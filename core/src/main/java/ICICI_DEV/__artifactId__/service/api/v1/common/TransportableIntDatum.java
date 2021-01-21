package ICICI_DEV.${artifactId}.service.api.v1.common;

import ICICI_DEV.${artifactId}.service.api.transport.v1.Transportable;

public enum TransportableIntDatum {;

    public static Transportable.IntDatum from(int value) {
        return Transportable.IntDatum.newBuilder()
                .setValue(value)
                .build();
    }

    public static int to(Transportable.IntDatum transportable) {
        return transportable.getValue();
    }

}

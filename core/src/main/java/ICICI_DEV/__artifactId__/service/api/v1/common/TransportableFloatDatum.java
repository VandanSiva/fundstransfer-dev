package ICICI_DEV.${artifactId}.service.api.v1.common;

import ICICI_DEV.${artifactId}.service.api.transport.v1.Transportable;

public enum TransportableFloatDatum {;

    public static Transportable.FloatDatum from(float value) {
        return Transportable.FloatDatum.newBuilder()
                .setValue(value)
                .build();
    }

    public static float to(Transportable.FloatDatum transportable) {
        return transportable.getValue();
    }

}

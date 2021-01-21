package ICICI_DEV.${artifactId}.service.api.v1.random;

import ICICI_DEV.${artifactId}.service.api.util.ServiceVersion;

public enum RandomResourceDescription {;

    public static final String ROOT_PATH = "/" + ServiceVersion.V1 + "/random";

    public enum GetNextInt {;

        public static final String METHOD = "GET";

        public static final String RELATIVE_PATH = "/int";

    }

    public enum GetNextFloat {;

        public static final String METHOD = "GET";

        public static final String RELATIVE_PATH = "/float";

    }

    public enum SetSeed {;

        public static final String METHOD = "POST";

        public enum Param {;

            public static final String VALUE = "value";

        }

        public static final String RELATIVE_PATH = "/seed/{" + Param.VALUE + "}";

    }

}

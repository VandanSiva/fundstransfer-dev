package ICICI_DEV.${artifactId}.service.api.transport;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;
import com.googlecode.protobuf.format.XmlFormat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public enum Transportables {;

    private static final JsonFormat jsonFormat = new JsonFormat();

    private static final XmlFormat xmlFormat = new XmlFormat();

    public static void writeBinary(Message message, OutputStream outputStream) throws IOException {
        outputStream.write(message.toByteArray());
    }

    @SuppressWarnings("unchecked")
    public static <T extends Message> T readBinary(Class<T> clazz, InputStream inputStream)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        GeneratedMessage.Builder<?> builder = generatedMessageBuilder(clazz);
        return (T) builder.mergeFrom(inputStream).build();
    }

    public static void writeJson(Message message, OutputStream outputStream) throws IOException {
        jsonFormat.print(message, outputStream);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Message> T readJson(Class<T> clazz, InputStream inputStream)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException {
        GeneratedMessage.Builder<?> builder = generatedMessageBuilder(clazz);
        jsonFormat.merge(inputStream, builder);
        return (T) builder.build();
    }

    public static void writeXml(Message message, OutputStream outputStream) throws IOException {
        xmlFormat.print(message, outputStream);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Message> T readXml(Class<T> clazz, InputStream inputStream)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException {
        GeneratedMessage.Builder<?> builder = generatedMessageBuilder(clazz);
        xmlFormat.merge(inputStream, builder);
        return (T) builder.build();
    }

    private static <T extends Message> GeneratedMessage.Builder<?> generatedMessageBuilder(Class<T> clazz)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method newBuilderMethod = clazz.getMethod("newBuilder");
        return (GeneratedMessage.Builder<?>) newBuilderMethod.invoke(clazz);
    }

}

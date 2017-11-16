package com.zenith.livinghistory.api.zenithlivinghistoryapi.converters;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;

public class JsonConverter<T> extends BaseConverter<T, String> {
    private JsonSerializer serializer;
    private ObjectMapper mapper;


    public JsonConverter(Class<T> sourceType) {
        this.mapper = new ObjectMapper();
        JsonSerializer serializer = new JsonSerializer();
        this(sourceType, SerializationUtils.);
    }

    public JsonConverter(Class<T> sourceType, JsonSerializer serializer) {
        super(sourceType, String.class);
        this.serializer = serializer;
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        try {
            return serializer.serialize(attribute, sourceType);
        } catch (SerializationException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        try {
            return serializer.deserialize(dbData, sourceType);
        } catch (SerializationException ex) {
            throw new RuntimeException(ex);
        }
    }

}

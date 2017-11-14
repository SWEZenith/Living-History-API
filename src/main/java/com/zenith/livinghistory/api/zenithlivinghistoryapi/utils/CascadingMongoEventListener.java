package com.zenith.livinghistory.api.zenithlivinghistoryapi.utils;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mapping.model.MappingException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.List;

public class CascadingMongoEventListener extends AbstractMongoEventListener<Object> {
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    @Ignore
    public void onBeforeConvert(BeforeConvertEvent event) {
        Object source = event.getSource();

        ReflectionUtils.doWithFields(source.getClass(), field -> {
            ReflectionUtils.makeAccessible(field);

//            if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(CascadeSave.class)) {
//                final Object fieldValue = field.get(source);
//                if (fieldValue == null) {
//                    return;
//                }
//
//                DbRefFieldCallback callback = new DbRefFieldCallback();
//
//                ReflectionUtils.doWithFields(fieldValue.getClass(), callback);
//
//                if (!callback.isIdFound()) {
//                    throw new MappingException("Cannot perform cascade save on child object without id set");
//                }
//
//                if (fieldValue instanceof List<?>) {
//                    for (Object item : (List<?>) fieldValue){
//                        checkNSave(item);
//                    }
//                } else {
//                    checkNSave(fieldValue);
//                }
//
////                mongoOperations.save(fieldValue);
//            }

            if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(CascadeSave.class)) {
                final Object fieldValue = field.get(source);
                if (fieldValue != null) {

                    Class fieldClass = fieldValue.getClass();
                    if (Collection.class.isAssignableFrom(field.getType())) {
//                        fieldClass = getParameterType(field);
                    }

                    DbRefFieldCallback callback = new DbRefFieldCallback();

                    ReflectionUtils.doWithFields(fieldClass, callback);

                    if (!callback.isIdFound()) {
                        throw new MappingException("Cannot perform cascade save on child object without id set");
                    }

                    if (Collection.class.isAssignableFrom(field.getType())) {
                        @SuppressWarnings("unchecked")
                        Collection<Object> models = (Collection<Object>) fieldValue;
                        for (Object model : models) {
                            mongoOperations.save(model);
                        }
                    } else {
                        mongoOperations.save(fieldValue);
                    }
                }
            }
        });
    }

    private static class DbRefFieldCallback implements ReflectionUtils.FieldCallback {
        private boolean idFound;

        public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
            ReflectionUtils.makeAccessible(field);

            if (field.isAnnotationPresent(Id.class)) {
                idFound = true;
            }
        }

        public boolean isIdFound() {
            return idFound;
        }
    }
}
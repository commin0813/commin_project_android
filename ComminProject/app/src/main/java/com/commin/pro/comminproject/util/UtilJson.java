package com.commin.pro.comminproject.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * Created by user on 2016-01-04.
 */
public class UtilJson {

    private static final ThreadLocal<ObjectMapper> mapperLocal = new ThreadLocal<ObjectMapper>() {
        @Override
        protected ObjectMapper initialValue() {
            return new ObjectMapper();
        }
    };

    public static Map<String, Object> toMap(String values) throws Exception {
        ObjectMapper mapper = mapperLocal.get();
        try {
            Map<String, Object> result = mapper.readValue(values, new TypeReference<Map<String, Object>>() {
            });
            return result;
        } catch (JsonMappingException e) {
            throw new Exception(e);
        } catch (Exception e) {
            throw e;
        }
    }

    public static <T> T toObject(String value, Class<T> cls) throws Exception {
        ObjectMapper mapper = mapperLocal.get();
        try {
            T result = mapper.readValue(value, cls);
            return result;
        } catch (JsonMappingException e) {
            throw new Exception(e);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String toString(Object value) throws Exception {
        ObjectMapper mapper = mapperLocal.get();
        try {
            return mapper.writeValueAsString(value);
        } catch (Exception e) {
            throw e;
        }
    }

}

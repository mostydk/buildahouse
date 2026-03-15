package com.mosty.buildahouse.client.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import elemental2.core.JsArray;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;

public abstract class JsReader {
	public static String getString(JsPropertyMap<?> map, String key) {
		Object val = map.get(key);
        return val == null ? null : Js.asString(map.get(key));
    }
	
	public static Integer getInteger(JsPropertyMap<?> map, String key) {
		Object val = map.get(key);
		return val == null ? null : (int) Js.asDouble(val);
	}
	
    public static Long getLong(JsPropertyMap<?> map, String key) {
        Object val = map.get(key);
        return val == null ? null : (long) Js.asDouble(val);
    }

    public static <E extends Enum<E>> E getEnum(JsPropertyMap<?> map, String key, Function<String, E> enumLookup) {
        Object val = map.get(key);
        if (val == null) return null;
        try {
        	return enumLookup.apply(Js.asString(val));
        } catch (Exception e) {
            return null;
        }
    }
    
    public static <T> List<T> getList(Object jsonArray, Function<Object, T> mapper) {
        List<T> list = new ArrayList<>();
        if (jsonArray == null) return list;

        JsArray<Object> jsArray = Js.uncheckedCast(jsonArray);
        
        for (int i = 0; i < jsArray.length; i++) {
            list.add(mapper.apply(jsArray.getAt(i)));
        }
        
        return list;
    }
}

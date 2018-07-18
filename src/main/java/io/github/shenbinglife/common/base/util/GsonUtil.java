package io.github.shenbinglife.common.base.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

/**
 * Json操作工具类
 * 
 * @version [版本号 日期]
 * @author SHEN
 * @since 1.0.0
 *
 */
public class GsonUtil {

    private static final Gson gson = new Gson();

    private GsonUtil() {

    }

    /**
     * 将json默认转换为List或者Map、String。
     * <P>
     * json为数组时，返回List
     * </P>
     * <P>
     * json为对象时，返回Map
     * </P>
     * <P>
     * json为单个属性值时，返回String
     * </P>
     * <P>
     * 字符串为空或者为'null'时，默认返回null
     * </P>
     * 
     * @param json  json字符串
     * @return  Java对象
     */
    public static Object fromJson(String json) {
        if (json == null)
            return null;
        JsonElement jsonElement = gson.fromJson(json, JsonElement.class);

        if (jsonElement == null) {
            return null;
        } else if (jsonElement.isJsonObject()) {
            return gson.fromJson(jsonElement, Map.class);
        } else if (jsonElement.isJsonArray()) {
            return gson.fromJson(jsonElement, List.class);
        } else if (jsonElement.isJsonNull()) {
            return null;
        } else if (jsonElement.isJsonPrimitive()) {
            return jsonElement.getAsString();
        } else {
            throw new IllegalArgumentException("不支持Json字符串解析");
        }
    }

    public static void main(String[] args) {
        System.out.println(fromJson("[1,2, {\"s\":21}]"));
        System.out.println(fromJson("{'s':'ds'}"));
        System.out.println(fromJson("null"));
    }

    /**
     * 将json字符串转为对象
     * 
     * @param json json字符串
     * @param clazz 对象类型
     * @return
     * @author SHEN
     * @since [起始版本]
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    /**
     * 将json字符串转为对象
     * 
     * @param json
     * @param type
     * @return
     * @author SHEN
     * @since [起始版本]
     */
    public static <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    /**
     * 将Json数组字符串转为集合对象
     * 
     * @param json JSON字符串
     * @param tClass 集合中元素类型
     * @param <T> 集合中元素类型
     * @return 元素对象集合
     */
    public static <T> List<T> fromJsonArray(String json, Class<T> tClass) {
        return gson.fromJson(json, new ParameterizedTypeImpl(List.class, new Class[] {tClass}));
    }

    /**
     * 将Json数组对象转为集合元素对象
     * 
     * @param jsonArray JsonArray
     * @param tClass 集合中元素类型
     * @param <T> 集合中元素类型
     * @return 元素对象集合
     */
    public static <T> List<T> fromJsonArray(JsonArray jsonArray, Class<T> tClass) {
        return gson.fromJson(jsonArray, new ParameterizedTypeImpl(List.class, new Class[] {tClass}));
    }

    /**
     * 将Json元素对象转为目标类型对象
     * 
     * @param element
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(JsonElement element, Class<T> clazz) {
        return gson.fromJson(element, clazz);
    }

    /**
     * 将对象转为json字符串, 当对象为null时，返回为字符串"null"
     * 
     * @param obj
     * @return
     * @author SHEN
     * @since [起始版本]
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * 将对象转为json字符串,如果对象为null，直接返回null
     * 
     * @param obj
     * @return
     * @author SHEN
     * @since [起始版本]
     */
    public static String toJsonOrNull(Object obj) {
        if (obj == null) {
            return null;
        } else {
            return gson.toJson(obj);
        }
    }

    public static class ParameterizedTypeImpl implements ParameterizedType {
        private final Class raw;
        private final Type[] args;

        public ParameterizedTypeImpl(Class raw, Type[] args) {
            this.raw = raw;
            this.args = args != null ? args : new Type[0];
        }

        @Override
        public Type[] getActualTypeArguments() {
            return args;
        }

        @Override
        public Type getRawType() {
            return raw;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }

}

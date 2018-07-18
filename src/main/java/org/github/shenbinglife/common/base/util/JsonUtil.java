package org.github.shenbinglife.common.base.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.github.shenbinglife.common.base.exception.UncheckedException;

/**
 * CLASS_NAME
 *
 * @author shenbing
 * @version 2018/1/2
 * @since since
 */
public class JsonUtil {

    private final static ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private JsonUtil() {

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
     * json为单个属性值时，返回String.
     * 如果是Json是String值必须用引号包装，否则抛出异常
     * </P>
     * <P>
     * 字符串为空或者为'null'时，默认返回null
     * </P>
     *
     * @param json  json字符串
     * @return  Java对象
     */
    public static Object fromJson(String json) {
        try {
            if(StringUtils.isBlank(json) ) {
                return null;
            }
            JsonNode jsonNode = objectMapper.readTree(json);
            switch (jsonNode.getNodeType()) {
                case NULL:
                case MISSING:
                    return null;
                case POJO:
                case OBJECT:
                    return objectMapper.convertValue(jsonNode, Map.class);
                case ARRAY:
                    return objectMapper.convertValue(jsonNode, List.class);
                case BOOLEAN:
                case NUMBER:
                case STRING:
                    return objectMapper.convertValue(jsonNode, String.class);
                    default:
                        throw new IllegalArgumentException("不支持解析的Json字符串");
            }
        } catch (IOException e) {
            throw new UncheckedException(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        Object o = fromJson("");
        System.out.println(o);
        System.out.println(o.getClass());
    }

    /**
     * 将对象转为Json字符串
     * 
     * @param object 对象
     * @return json字符串
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new UncheckedException(e.getMessage(), e);
        }
    }

    /**
     * 将json字符串转为对象
     * 
     * @param json json字符串
     * @param tClass 对象类型
     * @param <T> 对象类型泛型
     * @return 目标对象实例
     */
    public static <T> T fromJson(String json, Class<T> tClass) {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (IOException e) {
            throw new UncheckedException(e.getMessage(), e);
        }
    }

    /**
     * 将json数组字符串转为集合对象
     * 
     * @param json json数组字符串
     * @param tClass 集合元素类型
     * @param <T> 集合元素类型泛型
     * @return 集合对象
     */
    public static <T> List<T> fromJsonArray(String json, Class<T> tClass) {
        try {
            List<Map<String, Object>> list = objectMapper.readValue(json, new TypeReference<List<T>>() {});
            List<T> result = new ArrayList<T>();
            for (Map<String, Object> map : list) {
                result.add(map2pojo(map, tClass));
            }
            return result;
        } catch (IOException e) {
            throw new UncheckedException(e.getMessage(), e);
        }
    }

    /**
     * 将map转为pojo实例
     * 
     * @param map map对象
     * @param clazz pojo对象类型
     * @param <T> pojo对象类型泛型
     * @return pojo实例
     */
    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }
}

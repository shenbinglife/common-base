package org.github.shenbinglife.common.base.util;

import java.util.Objects;

/**
 * 断言工具
 *
 * @author SHEN
 * @version 2017/4/5
 * @since 1.0.0
 */
public class Assert {

    /**
     * 判断表达式是否为TRUE, false时抛出IllegalArgumentException
     * @param result    表达式
     * @param message   异常信息
     */
    public static void isTrue(boolean result, String message){
        if(!result){
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 判断两对象是否相等, 不相等时抛出IllegalArgumentException
     * 当两对象同时为null，也表示相等
     * @param o1            对象1
     * @param o2            对象2
     * @param message       异常信息
     */
    public static void objectEquals(Object o1, Object o2, String message){
        if(!Objects.equals(o1, o2)){
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 判断两个不为null对象的equals, 当o1或o2为null抛出NullPointerException，当o1与o2不相等时抛出IllegalArgumentException异常
     * @param o1        对象1
     * @param o2        对象2
     * @param message   异常信息
     */
    public static void notNullEquals(Object o1, Object o2, String message){
        if(o1 == null || o2 == null){
            throw new NullPointerException(message);
        }else {
            Assert.objectEquals(o1, o2, message);
        }
    }

    public static void notNull(Object obj, String message){
        if(obj == null){
            throw new NullPointerException(message);
        }
    }
}

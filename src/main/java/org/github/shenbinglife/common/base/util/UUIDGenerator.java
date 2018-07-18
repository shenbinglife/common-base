package org.github.shenbinglife.common.base.util;

import java.util.UUID;

/**
 * UUID生成器
 *
 * @author SHEN
 * @version 2017/3/28
 * @since 1.0.0
 */
public class UUIDGenerator {

    private UUIDGenerator() {

    }
    /**
     * 生成32位UUID
     * @return  uuid
     */
    public static String generate(){
        return generate(true);
    }

    /**
     * 生成32位或36位的UUID
     * @param replace   是否将36位UUID中的中划线替换
     * @return  replace：true表示返回32位UUID，false表示返回36位带中划线的UUID
     */
    public static String generate(boolean replace) {
        String s = UUID.randomUUID().toString();
        return replace? s.replace("-", "") : s;
    }
}

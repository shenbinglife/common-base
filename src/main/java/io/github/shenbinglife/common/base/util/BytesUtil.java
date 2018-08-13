package io.github.shenbinglife.common.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字节工具类，提供单位转换
 * @author SHEN
 * @version 2016/10/10
 * @since since
 */
public class BytesUtil {
    /**
     * 字节大小单位
     */
    public enum Unit{
        BYTE(0, "bytes") ,KB(1, "KB"), MB(2, "MB"), GB(3, "GB"), TB(4, "TB"), PB(5, "PB");

        /** 字节类型每扩大1024倍，就增加1*/
        private final int code;
        private final String name;
        Unit(int code, String name){
            this.code = code;
            this.name = name;
        }
        public int getCode(){return code;}
        public String getName(){return name;}

        public Unit higher(){
            return codeOf(this.code + 1);
        }

        public Unit lower(){
            return codeOf(this.code - 1);
        }

        private static Unit codeOf(int code){
            for(Unit unit : Unit.values()){
                if(unit.getCode() == code){
                    return unit;
                }
            }
            throw new IllegalArgumentException("error code of Unit : " + code);
        }

        public static Unit nameOf(String name){
            for(Unit unit : Unit.values()){
                if(unit.getName().equalsIgnoreCase(name)){
                    return unit;
                }
            }
            throw new IllegalArgumentException("error name of Unit : " + name);
        }
    }

    /**
     * 字节大小进行单位转换
     * @param size  字节大小
     * @param from  原字节单位
     * @param to    目标字节单位
     * @return      转换后的字节大小数字
     */
    public static double unitConvert(double size, Unit from, Unit to){
        return size * Math.pow(1024, (from.getCode() - to.getCode()));
    }

    /**
     * 对字节大小单位进行转换，返回带单位的字符串<P>
     * 将字节大小转换为大于1小于1024并保留2位小数的数字<P>
     * example: 1024.0, Unit.KB -> "1.00 MB"
     * @param size  字节大小
     * @param unit  当前字节单位
     * @return      单位转换后的带单位名称字符串
     */
    public static String unitSuit(double size, Unit unit){
        return unitSuit(size, unit, null, null);
    }

    /**
     * 对字节大小单位进行转换，返回带单位的字符串<P>
     * 将字节大小转换为大于1小于1024并保留2位小数的数字<P>
     * example: 1024.0, Unit.KB -> "1.00 MB"
     * @param size      字节大小
     * @param unit      当前字节单位
     * @param max       转换后允许的最大单位
     * @param min       转换后允许的最小单位
     * @return          单位转换后的带单位名称字符串
     */
    public static String unitSuit(double size, Unit unit, Unit max, Unit min){
        if(max != null && min != null && max.getCode() < min.getCode()){
            throw new IllegalArgumentException("max unit can not be smaller than min unit");
        }

        double suitSize = size;
        Unit suitUnit = unit;
        while(suitSize >= 1024){
            suitSize = suitSize / 1024;
            suitUnit = suitUnit.higher();
        }
        while (suitSize < 1 && suitSize > 0){
            suitSize = suitSize * 1024;
            suitUnit = suitUnit.lower();
        }
        if(max != null && suitUnit.getCode() > max.getCode()){
            suitSize = unitConvert(suitSize, suitUnit, max);
            return String.format("%.2f %s", suitSize, max.getName());
        }else if(min != null && suitUnit.getCode() < min.getCode()){
            suitSize = unitConvert(suitSize, suitUnit, min);
            return String.format("%.2f %s", suitSize, min.getName());
        }
        return String.format("%.2f %s", suitSize, suitUnit.getName());
    }

    /**
     * 将带单位的字节字符串转为字节大小
     * 例如：1KB -> 1024
     * @param sizeString
     * @return
     */
    public static long getBytes(String sizeString){
        Pattern p  = Pattern.compile("[a-zA-Z].+$");
        Matcher matcher = p.matcher(sizeString);
        if(matcher.find()){
            String group = matcher.group();
            Unit unit = Unit.nameOf(group);
            double size = Double.parseDouble(sizeString.replace(group, "").trim());
            double bytes = unitConvert(size, unit, Unit.BYTE);
            return Math.round(bytes);
        }else{
            throw new IllegalArgumentException("error size string to get bytes size : " + sizeString);
        }
    }
}

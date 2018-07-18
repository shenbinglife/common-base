package org.github.shenbinglife.common.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.github.shenbinglife.common.base.exception.UncheckedException;


/**
 * Md5工具类
 *
 * @author SHEN
 * @version 2017/3/27
 * @since 1.0.0
 */
public class Md5Util {

    private Md5Util() {

    }

    /**
     * 计算字节数组的MD5值
     * @param bytes 字节数组
     * @return  Hex编码后的md5值
     */
    public static String getMd5(byte[] bytes){
        return DigestUtils.md5Hex(bytes);
    }

    /**
     * 计算文件的MD5值
     * @param file 文件
     * @return  Hex编码后的md5值
     */
    public static String getMd5(File file){
        try (FileInputStream fis = new FileInputStream(file)){
            return DigestUtils.md5Hex(fis);
        } catch (IOException e) {
            throw new UncheckedException("计算Md5失败", e);
        }
    }

    /**
     * 计算数据流的MD5值
     * @param inputStream 数据输入流
     * @return  Hex编码后的md5值
     */
    public static String getMd5(InputStream inputStream){
        try {
            return DigestUtils.md5Hex(inputStream);
        } catch (IOException e) {
            throw new UncheckedException("计算Md5失败", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}

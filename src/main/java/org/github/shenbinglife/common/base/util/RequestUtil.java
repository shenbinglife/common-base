package org.github.shenbinglife.common.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

/**
 * HttpServletRequest 工具类
 *
 * @author shenbing
 * @version 2018/1/2
 * @since since
 */
public class RequestUtil {

    private RequestUtil() {}

    /**
     * 判断是否Ajax请求
     * @param request
     * @return  true：当request中存在固定的Header信息"X-Requested-With：XMLHttpRequest"
     */
    public static boolean isAjax(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(requestType);
    }

    /**
     * 将请求体中的流转为字符串
     * <P>
     * 默认发生转换异常时，返回空字符串
     * </P>
     * @param request   HttpServletRequest
     * @param charset   字符串编码
     * @return  Http请求体中的字符串
     */
    public static String getRequestBody(HttpServletRequest request, Charset charset) {
        try (InputStream in = request.getInputStream()) {
            return IOUtils.toString(in, charset);
        } catch (IOException e) {
            return "";
        }
    }

    public static <T> T getRequestBody(HttpServletRequest request, Charset charset, Class<T> tClass) {
        String requestBody = getRequestBody(request, charset);
        return JsonUtil.fromJson(requestBody, tClass);
    }
}

package io.github.shenbinglife.common.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;

import io.github.shenbinglife.common.base.exception.UncheckedException;
import org.apache.commons.io.IOUtils;


/**
 * CLASS_NAME
 *
 * @author shenbing
 * @version 2018/1/2
 * @since since
 */
public class ResponseUtil {

    private ResponseUtil() {}

    /**
     * 将输入流写出到Http的响应流中
     * @param response  HttpServletResponse
     * @param inputStream   输入流
     */
    public static void write(HttpServletResponse response, InputStream inputStream) {
        try (OutputStream out = response.getOutputStream()){
            IOUtils.copy(inputStream, out);
        }catch (IOException e) {
            throw new UncheckedException(e.getMessage(), e);
        }
    }

    /**
     * 将字符串作为HTML写出到Http的响应流，默认"Content-Type:text/html;charset=utf-8"
     * @param response  HttpServletResponse
     * @param html  html字符串
     */
    public static void writeHtml(HttpServletResponse response, String html) {
        response.setContentType("text/html;charset=utf-8");
        try (OutputStream out = response.getOutputStream()){
            out.write(html.getBytes(StandardCharsets.UTF_8));
        }catch (IOException e) {
            throw new UncheckedException(e.getMessage(), e);
        }
    }

    /**
     * 将字符串作为JSON写出到Http的响应流，默认"Content-Type:application/json"
     * @param response
     * @param json
     */
    public static void writeJson(HttpServletResponse response, String json) {
        response.setContentType("application/json");
        try (OutputStream out = response.getOutputStream()) {
            out.write(json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedException(e.getMessage(), e);
        }
    }

    /**
     * 将对象转为Json字符串后写出到Http的响应流，默认"Content-Type:application/json"
     * @param response
     * @param object
     */
    public static void writeJson(HttpServletResponse response, Object object) {
        String json = JsonUtil.toJson(object);
        writeJson(response, json);
    }
}

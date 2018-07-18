package io.github.shenbinglife.common.base.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 用java原生的URLConnection实现基本的http请求
 *
 * @author SHEN
 * @version 2017/4/6
 * @since 1.0.0
 */
public class UrlConnectionUtil {
    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final String BOUNDARY = "---------------------------123821742118716";

//    private Map<String, String> headers;
//
//    public UrlConnectionUtil() {
//    }
//
//    public UrlConnectionUtil(Map<String, String> headers) {
//        this.headers = headers;
//    }
//
    private static void addHeaders(HttpURLConnection conn, Map<String, String> headers){
        if(headers != null){
            for(Map.Entry<String, String> entry : headers.entrySet()){
                if(StringUtils.isNotBlank((entry.getKey()))){
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    public static InputStream get(String url, Map<String, String> headers){
        HttpURLConnection conn;

        try{
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            conn = (HttpURLConnection) realUrl.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            addHeaders(conn, headers);
            // 建立实际的连接,getOutputStream会隐含的进行connect
            conn.connect();
            return conn.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException("HTTP连接异常", e);
        }
    }

    public static String post(String url, Map<String, String> map, Map<String, String> headers){
        InputStream resultStream = null;
        HttpURLConnection conn = null;

        OutputStream out = null;
        StringBuilder buffer = new StringBuilder();
        for(Map.Entry<String, String> entry : map.entrySet()){
            buffer.append(entry.getKey()).append("=").append(entry.getValue());
            buffer.append("&");
        }
        buffer.deleteCharAt(buffer.length() - 1);

        try{
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            conn = (HttpURLConnection) realUrl.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            addHeaders(conn, headers);

            out = conn.getOutputStream();
            out.write(buffer.toString().getBytes(DEFAULT_CHARSET));

            resultStream = conn.getInputStream();
            return IOUtils.toString(resultStream, Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException("HTTP连接异常", e);
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(resultStream);
            if(conn != null){
                conn.disconnect();
            }
        }
    }

    public static String postJson(String url, String json, Map<String, String> headers){
        InputStream resultStream = null;
        HttpURLConnection conn = null;

        ByteArrayInputStream in = null;
        OutputStream out = null;
        try{
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            conn = (HttpURLConnection) realUrl.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("Content-Type", "application/json");
            addHeaders(conn, headers);
            in = new ByteArrayInputStream(json.getBytes(DEFAULT_CHARSET));
            out = conn.getOutputStream();
            IOUtils.copy(in, out);
            resultStream = conn.getInputStream();
            return IOUtils.toString(resultStream, Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException("HTTP连接异常", e);
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(resultStream);
            if(conn != null){
                conn.disconnect();
            }
        }
    }

    public static String postFile(String url, Map<String, String> params, String fileField, File file, Map<String, String> headers){
        HttpURLConnection conn = null;
        OutputStream out = null;
        InputStream resultStream = null;
        InputStream fis = null;
        try{
            URL realUrl = new URL(url);
            conn = (HttpURLConnection) realUrl.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data;charset=utf-8; boundary=" + BOUNDARY);
            addHeaders(conn, headers);

            out = conn.getOutputStream();
            StringBuilder buffer = new StringBuilder();
            for(Map.Entry<String, String> entry : params.entrySet()){
                buffer.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
                buffer.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"\r\n\r\n");
//                buffer.append("Content-Type: text/plain; charset=utf-8\r\n\r\n");
                buffer.append(entry.getValue());
            }
            out.write(buffer.toString().getBytes("utf-8"));

            buffer.delete(0, buffer.length());
            buffer.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
            buffer.append("Content-Disposition: form-data; name=\"" + fileField + "\"; filename=\"" + file.getName() + "\"\r\n");
            buffer.append("Content-Type:" + URLConnection.guessContentTypeFromName(file.getName()) + "\r\n\r\n");
            out.write(buffer.toString().getBytes(DEFAULT_CHARSET));

            fis = new FileInputStream(file);
            IOUtils.copy(fis, out);

            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes(DEFAULT_CHARSET);
            out.write(endData);

            resultStream = conn.getInputStream();
            return IOUtils.toString(resultStream, Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException("HTTP连接异常", e);
        }finally {
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(resultStream);
            if(conn != null){
                conn.disconnect();
            }
        }
    }
}

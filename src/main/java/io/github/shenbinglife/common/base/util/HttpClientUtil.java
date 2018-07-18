package io.github.shenbinglife.common.base.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.github.shenbinglife.common.base.exception.UncheckedException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 网络请求工具
 *
 * @author SHEN
 * @version 2016/11/14
 * @since since
 */
public class HttpClientUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * 执行POST请求
     *
     * @param url
     * @param params
     * @return
     */
    public static byte[] simplePost(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        byte[] bytes;
        try {
            HttpPost httpRequest = new HttpPost(url);

            // 测试参数
            if (params != null) {
                List<NameValuePair> pairList = new ArrayList<>();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    pairList.add(new BasicNameValuePair(entry.getKey(), entry
                            .getValue()));
                }
                try {
                    httpRequest.setEntity(new UrlEncodedFormEntity(pairList, "UTF-8"));
                } catch(UnsupportedEncodingException e) {
                    logger.error("HTTP请求体编码异常", e);
                    throw new UncheckedException("HTTP请求体编码异常", e);
                }
            }
            bytes = execRequest(httpClient, httpRequest);
        } finally {
            IOUtils.closeQuietly(httpClient);
        }
        return bytes;
    }

    /**
     * 执行GET请求
     *
     * @param url
     * @return
     */
    public static byte[] simpleGet(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        byte[] bytes;
        try {
            HttpGet httpRequest = new HttpGet(url);
            bytes = execRequest(httpClient, httpRequest);
        } finally {
            IOUtils.closeQuietly(httpClient);
        }
        return bytes;
    }

    /**
     * 执行GET请求
     * @param url           url
     * @param params        追加到url的参数
     * @param encodeParams  是否对参数编码
     * @return
     */
    public static byte[] simpleGet(String url, Map<String, String> params, boolean encodeParams) {
        StringBuilder newUrl = new StringBuilder(url);
        if (url.contains("?")) {
            newUrl.append("&");
        } else {
            newUrl.append("?");
        }
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (encodeParams) {
                    try {
                        newUrl.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
                    } catch(UnsupportedEncodingException e) {
                        throw new UncheckedException("url encode exception", e);
                    }
                } else {
                    newUrl.append("&").append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }
            }
            newUrl.deleteCharAt(newUrl.length() -1);
            return simpleGet(newUrl.toString());
        } else {
            return simpleGet(url);
        }
    }

    private static byte[] execRequest(CloseableHttpClient httpClient, HttpUriRequest httpRequest) {
        byte[] bytes ;
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode < 200 || statusCode > 300) {
                UncheckedException e = new UncheckedException("远程服务器访问失败，Http状态码： " + statusCode);
                logger.error("HTTP请求失败，错误码： " + statusCode, e);
                throw e;
            }

            // 保存结果
            HttpEntity entity = response.getEntity();
            bytes = IOUtils.toByteArray(entity.getContent());
        } catch(IOException e) {
            throw new UncheckedException("网络请求执行异常", e);
        } finally {
            IOUtils.closeQuietly(response);
        }
        return bytes;
    }
}

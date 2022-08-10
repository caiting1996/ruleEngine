package com.example.ruleengine.utils;


import com.example.ruleengine.model.HttpResult;
import lombok.Data;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author xiexinyuan
 * @Date 2019-09-09 15:18
 * @Description http工具类
 **/
@Data
public class HttpUtil {

    private CloseableHttpClient httpClient;

    private RequestConfig config;


    public HttpUtil(CloseableHttpClient httpClient, RequestConfig config) {
        this.httpClient = httpClient;
        this.config = config;
    }

    /**
     * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public HttpResult doGet(String url) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);

        // 装载配置信息
        httpGet.setConfig(config);

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }

    /**
     * 带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public HttpResult doGet(String url, Map<String, Object> map) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);

        if ( map != null && !map.isEmpty() ) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        // 调用不带参数的get请求
        return this.doGet(uriBuilder.build().toString());

    }

    /**
     * 带参数的get请求并需要设置请求头
     *
     * @param url
     * @return
     * @throws Exception
     */
    public HttpResult doGet(String url, Map<String, Object> map, Map<String, Object> headerMap) throws Exception {

        if ( map != null && !map.isEmpty() ) {
            // 遍历map,拼接请求参数
            List<NameValuePair> valuePairs = new ArrayList<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                valuePairs.add(new BasicNameValuePair(entry.getKey(),
                        entry.getValue().toString()));
            }
            url = url + "?" + URLEncodedUtils.format(valuePairs, "UTF-8");
        }
        HttpGet httpGet = new HttpGet(url);
        // 设置请求头
        if ( headerMap != null && !headerMap.isEmpty() ) {
            for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
                httpGet.setHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));

    }

    /**
     * 带参数的post请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url, Map<String, Object> map) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);
        httpPost.setHeader("Content-Type", "application/json");

        // 判断map是否为空，不为空则转换为json字符串
        if ( map != null && !map.isEmpty() ) {
            httpPost.setEntity(new StringEntity(JsonUtil.obj2String(map), StandardCharsets.UTF_8));
        }

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }

    /**
     * 带参数的post请求
     *
     * @param url
     * @param json
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url, String json) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);
        httpPost.setHeader("Content-Type", "application/json");

        // 判断map是否为空，不为空则转换为json字符串
        if ( json != null ) {
            httpPost.setEntity(new StringEntity(json, StandardCharsets.UTF_8));
        }

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }

    /**
     * 带参数并需要设置请求头的post请求
     *
     * @param url
     * @param map
     * @param headerMap
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url, Map<String, Object> map, Map<String, Object> headerMap) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);

        // 设置请求头
        if ( headerMap != null && !headerMap.isEmpty() ) {
            for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
                httpPost.setHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        // 加入配置信息
        httpPost.setConfig(config);
        httpPost.setHeader("Content-Type", "application/json");

        // 判断map是否为空，不为空则转换为json字符串
        if ( map != null && !map.isEmpty() ) {
            httpPost.setEntity(new StringEntity(JsonUtil.obj2String(map), StandardCharsets.UTF_8));
        }
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }

    /**
     * 带参数并需要设置请求头的post请求
     *
     * @param url
     * @param reqParams
     * @param headerMap
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url, String reqParams, Map<String, Object> headerMap) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);

        // 设置请求头
        if ( headerMap != null && !headerMap.isEmpty() ) {
            for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
                httpPost.setHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        // 加入配置信息
        httpPost.setConfig(config);
        httpPost.setHeader("Content-Type", "application/json");


        // 判断map是否为空，不为空则转换为json字符串
        if ( reqParams != null && !reqParams.isEmpty() ) {
            httpPost.setEntity(new StringEntity(reqParams, StandardCharsets.UTF_8));
        }
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }

    /**
     * 带参数的put请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public HttpResult doPut(String url, Map<String, Object> map) throws Exception {
        // 声明httpPost请求
        HttpPut httpPut = new HttpPut(url);
        // 加入配置信息
        httpPut.setConfig(config);

        // 判断map是否为空，不为空则转换为json字符串
        if ( map != null && !map.isEmpty() ) {
            httpPut.setEntity(new StringEntity(JsonUtil.obj2String(map), StandardCharsets.UTF_8));
        }

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPut);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }

    /**
     * 带参数的put请求并需要设备请求头
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public HttpResult doPut(String url, Map<String, Object> map, Map<String, Object> headerMap) throws Exception {
        // 声明httpPost请求
        HttpPut httpPut = new HttpPut(url);

        // 设置请求头
        if ( headerMap != null && !headerMap.isEmpty() ) {
            for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
                httpPut.setHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }

        // 加入配置信息
        httpPut.setConfig(config);

        // 判断map是否为空，不为空则转换为json字符串
        if ( map != null && !map.isEmpty() ) {
            httpPut.setEntity(new StringEntity(JsonUtil.obj2String(map), StandardCharsets.UTF_8));
        }

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPut);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }

    /**
     * 不带参数put请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public HttpResult doPut(String url) throws Exception {
        return this.doPut(url, null);
    }


    /**
     * 带参数的delete请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public HttpResult doDelete(String url, Map<String, Object> map) throws Exception {
        // 声明httpPost请求
        HttpDelete httpDelete = new HttpDelete(url);
        // 加入配置信息
        httpDelete.setConfig(config);

        // 判断map是否为空
        if ( map != null && !map.isEmpty() ) {
            URIBuilder uriBuilder = new URIBuilder(url);
            for (String key : map.keySet()) {
                uriBuilder.setParameter(key, (String) map.get(key));
            }
            URI uri = uriBuilder.build();
            httpDelete.setURI(uri);
        }
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpDelete);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }

    /**
     * 带参数的delete请求并需要设备请求头
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public HttpResult doDelete(String url, Map<String, Object> map, Map<String, Object> headerMap) throws Exception {
        // 声明httpPost请求
        HttpDelete httpDelete = new HttpDelete(url);

        // 设置请求头
        if ( headerMap != null && !headerMap.isEmpty() ) {
            for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
                httpDelete.setHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }

        // 加入配置信息
        httpDelete.setConfig(config);

        // 判断map是否为空
        if ( map != null && !map.isEmpty() ) {
            URIBuilder uriBuilder = new URIBuilder(url);
            for (String key : map.keySet()) {
                uriBuilder.setParameter(key, (String) map.get(key));
            }
            URI uri = uriBuilder.build();
            httpDelete.setURI(uri);
        }

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpDelete);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }

    /**
     * 不带参数delete请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public HttpResult doDelete(String url) throws Exception {
        return this.doDelete(url, null);
    }
}

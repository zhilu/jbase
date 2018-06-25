package ald.rc.commom.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.*;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 基于httpclient的工具类
 * @author shiguihong
 * @version 1.0
 */
public class HttpUtil {

    private static CloseableHttpClient httpClient;
    private static RequestConfig requestConfig;
    private static final int CONNECTION_REQUEST_TIMEOUT = 2000;//从连接池中获取连接所需要等待的时间
    private static final int CONNECT_TIMEOUT = 8000;//连接上服务器(握手成功)的时间
    private static final int SOCKET_TIMEOUT = 20000;//服务器返回数据(response)的时间


    static {
        SSLContext sslContext = SSLContexts.createSystemDefault();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", sslsf).build();
        SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
        MessageConstraints messageConstraints = MessageConstraints.custom().setMaxHeaderCount(200).setMaxLineLength(2000).build();
        ConnectionConfig connectionConfig = ConnectionConfig.custom().setMalformedInputAction(CodingErrorAction.IGNORE)
                .setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(Consts.UTF_8).setMessageConstraints(messageConstraints).build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setDefaultConnectionConfig(connectionConfig);
        cm.setDefaultSocketConfig(socketConfig);
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(10);

        requestConfig = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();

        httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .evictExpiredConnections()
                .evictIdleConnections(5L, TimeUnit.SECONDS)
                .setDefaultRequestConfig(requestConfig)
                .setRetryHandler((e, executionCount, httpContext) -> {
                    if (executionCount >= 3) {
                        return false;
                    }
                    if (e instanceof NoHttpResponseException | e instanceof HttpHostConnectException) {
                        return true;
                    } else if (e instanceof ClientProtocolException) {
                        return true;
                    }
                    return false;
                })
                .build();

    }


    /**
     * 发送 GET 请求，K-V形式
     *
     * @param url 请求地址
     * @param headers header参数
     * @param params 地址参数
     * @return 返回字符串
     */
    public static String doGet(String url, Map<String, String> headers, Map<String, Object> params) {
        String result = null;
        String apiUrl = HttpUtil.toGetUrl(url, params);
        HttpEntity entity = null;
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpUtil.addHeader(httpGet, headers);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            entity = response.getEntity();
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            EntityUtils.consumeQuietly(entity);
        }
        return result;
    }

    private static String toGetUrl(String url, Map<String, Object> params) {
        StringBuilder getUrl = new StringBuilder(url);
        boolean isFirst = true;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (isFirst) {
                getUrl.append("?");
                isFirst = false;
            } else {
                getUrl.append("&");
            }
            getUrl.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return getUrl.toString();
    }

    private static void addHeader(HttpMessage httpMessage, Map<String, String> headers) {
        if (Objects.isNull(headers)) {
            return;
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpMessage.addHeader(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 发送 POST 请求
     * @param postUrl    API接口URL
     * @param headers    header参数
     * @param httpEntity 参数
     * @return 返回字符串
     */
    public static String doPost(String postUrl, Map<String, String> headers, HttpEntity httpEntity) {
        String result = null;
        HttpEntity entity = null;
        try {
            HttpPost httpPost = new HttpPost(postUrl);
            HttpUtil.addHeader(httpPost, headers);
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(httpEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            EntityUtils.consumeQuietly(entity);
        }
        return result;
    }

    public static HttpEntity toEntity(Map<String, Object> params) {
        List<NameValuePair> pairList = new ArrayList<>(params.size());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (StringUtils.isBlank(key) || Objects.isNull(value)) {
                continue;
            }
            NameValuePair pair = new BasicNameValuePair(key, String.valueOf(value));
            pairList.add(pair);
        }
        return new UrlEncodedFormEntity(pairList, Consts.UTF_8);
    }

    public static HttpEntity toEntity(Object json) {
        StringEntity stringEntity = new StringEntity(json.toString(), Consts.UTF_8);
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/json");
        return stringEntity;
    }
}

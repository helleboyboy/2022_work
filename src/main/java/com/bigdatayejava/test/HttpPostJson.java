package com.bigdatayejava.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @Author: Java页大数据
 * @Date: 2022-09-21:22:42
 * @Describe:
 */
public class HttpPostJson {
    public static void main(String[] args) {
        JSONObject param = new JSONObject();
        param.put("cluster", "clusterA");
        param.put("service", "HDFS");
        param.put("component", "NAMENODE");
        param.put("componentState", "INSTALLED");
        param.put("host", "host_my_leader");
        param.put("ip", "192.168.52.11");
        JSONObject results = new JSONObject();
        final String url = "http://192.168.52.19:4444/test_json_by_http";
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        StringEntity entity = new StringEntity(param.toString(), "UTF-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        try(CloseableHttpResponse response = client.execute(httpPost);){
            if (response.getStatusLine().getStatusCode() == 200){
                results = JSON.parseObject(EntityUtils.toString(response.getEntity(), "UTF-8"));
                System.out.println(results);
            }
        }catch (Exception e){
            e.printStackTrace();
            results.put("error", "connect error");
        }
    }
}

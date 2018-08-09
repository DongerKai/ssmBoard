package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.CarDao;
import com.heitian.ssm.model.Car;
import com.heitian.ssm.model.Add;
import com.heitian.ssm.service.CarService;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.*;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import javax.json.Json;
import java.io.IOException;
import java.util.*;


import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

@Service
@Transactional(rollbackFor = Exception.class)
public class CarServiceImpl implements CarService {
    private Logger log = Logger.getLogger(CarServiceImpl.class);
    @Resource
    private CarDao carDao;

    public List<Car> getAllInfo() {
        return carDao.selectAllInfo();
    }

    public List<Add> addUser() {
        return carDao.addUser();
    }

    public String restMessage() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> urlVariables = new HashMap<>();
        urlVariables.put("begin", "1532079684937");
        urlVariables.put("end", "1532504047937");
        urlVariables.put("match", "VNO");
        urlVariables.put("range", "systime");
        urlVariables.put("value", "浙FAA023");
        String message = restTemplate.getForObject("http://192.168.1.150:8082/elasticsearch/api/getSearch/ec1548f49f4c4995874854bbba2ac82d", String.class, urlVariables);

        return message;
    }

    public String doPostJson() {
        String url = "http://192.168.1.150:8082/elasticsearch/api/getSearch/ec1548f49f4c4995874854bbba2ac82d";
        String json = "{\"begin\": \"1532079684937\", " +
                "\"end\": \"1532504047937\"," +
                "\"match\": \"VNO\"," +
                "\"range\": \"systime\"," +
                "\"value\": \"浙FAA023\"" +
                "}";
        log.info(json);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
//            httpPost.setHeaders(Header[] headers);
            httpPost.setHeader(new BasicHeader("Content-Type", "application/json"));
            httpPost.setHeader(new BasicHeader("Cookie", "JSESSIONID=5fed1e68-09b2-4bca-bdfb-e24db50c4b95"));
            log.info(httpPost);
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            log.info(resultString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return resultString;
    }

    public String getCookie(Map<String, String> map, String charset) {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            CookieStore cookieStore = new BasicCookieStore();
            httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
            httpPost = new HttpPost("http://192.168.1.150:8082/elasticsearch/api/getSearch/ec1548f49f4c4995874854bbba2ac82d");
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            httpClient.execute(httpPost);
            String JSESSIONID = null;
            String cookie_user = null;
            List<Cookie> cookies = cookieStore.getCookies();
            for (int i = 0; i < cookies.size(); i++) {
                if (cookies.get(i).getName().equals("JSESSIONID")) {
                    JSESSIONID = cookies.get(i).getValue();
                }
                if (cookies.get(i).getName().equals("cookie_user")) {
                    cookie_user = cookies.get(i).getValue();
                }
            }
            if (cookie_user != null) {
                result = JSESSIONID;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
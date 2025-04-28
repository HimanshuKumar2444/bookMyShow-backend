package com.bms.authentication_api_v1.integrations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

public abstract class RestApiForCrudOperation {

    @Autowired
    RestTemplate restTemplate;

    public String addRequestParams(String url,Map<String,String> addUrl){
        if(addUrl.size()==0){
            return url;
        }
        url+="?";
        int count=1;
        for(String key:addUrl.keySet()){
            url+=key+"="+addUrl.get(key);
            if(count<addUrl.size()){
                url+="&";

            }
            count++;
        }
        return url;
    }

    public Object makePostCall(String baseurl, String endpoint, Object requestbody,Map<String,String> requestParams){

//        1. create  final url...
        String url=this.addRequestParams(baseurl+endpoint,requestParams);
//        2. we have pass final url into uri..
        URI finalURL = URI.create(url);
//        3. we have create request entity and passing object->requestbody into body..
        RequestEntity requestEntity=RequestEntity.post(finalURL).body(requestbody);
//        4.we need to hit point
//        a ->create a object of RestTemplate..
//        b-> call exchage method and passing the finalurl,method for (put,get,post,delete,patch)
//          -> request entity in step 3,type of class we can be send like object or appuser ..
//        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity<Object> responseEntity= restTemplate.exchange(finalURL, HttpMethod.POST,requestEntity,Object.class);
        return responseEntity.getBody();

    }


    public Object makeGetCall(String basrUrl,String endPoint,Map<String,String>requestParams){
        String url=this.addRequestParams(basrUrl+endPoint,requestParams);
        URI finalUrl=URI.create(url);
        RequestEntity requestEntity = RequestEntity.get(finalUrl).build();
//        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<Object> responseEntity=  restTemplate.exchange(finalUrl,HttpMethod.GET,requestEntity,Object.class);
        return responseEntity.getBody();

    }
    public Object makePutCall(String baseurl,String endpoint,Object requestbody,Map<String,String> requestParams){
        String  url=this.addRequestParams(baseurl+endpoint,requestParams);
        URI finalurl=URI.create(url);
        RequestEntity requestEntity=RequestEntity.put(finalurl).body(requestbody);
//        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<Object> responseEntity=  restTemplate.exchange(finalurl,HttpMethod.PUT,requestEntity,Object.class);
        return responseEntity.getBody();
    }
    public Object makeDeleteCall(String basrUrl,String endPoint,Map<String,String>requestParams){
        String url=this.addRequestParams(basrUrl+endPoint,requestParams);
        RequestEntity requestEntity = RequestEntity.delete(url).build();
//        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<Object> responseEntity=  restTemplate.exchange(url,HttpMethod.DELETE,requestEntity,Object.class);
        return responseEntity.getBody();
    }

}
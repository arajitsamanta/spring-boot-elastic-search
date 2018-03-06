package com.arajit.coding.challenge.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.arajit.coding.challenge.domain.Person;

@Service
public class SearchService {
  
  private static Logger logger = LoggerFactory.getLogger(SearchService.class);
  
  private static final String INDEX = "persons";
  
  @Autowired
  RestClient restClient;
  
  public Person searchByEmployeeId(String employeeId) throws Exception {
    
    //Create query param
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("q", "EmployeeId:"+employeeId);
    
    //perform search
    Response response = restClient.performRequest("GET", "/persons/_search",
        paramMap);
    
    //extract source from elastic search response and return the 
    return this.extractSource(EntityUtils.toString(response.getEntity()));
  }
  
  protected Person extractSource(String responseBody) {
    System.out.println("responseBody:"+responseBody);
    JSONObject root = new JSONObject(responseBody);
    JSONObject hits = root.getJSONObject("hits");
    JSONArray hitsArr = hits.getJSONArray("hits");
    JSONObject source=hitsArr.getJSONObject(0).getJSONObject("_source");
    if(null!=source) {
      Person person=new Person();
      person.setEmployeeId(source.getString("EmployeeId"));
      person.setName(source.getString("Name"));
      person.setAge(source.getInt("Age"));
      person.setActive(source.getBoolean("Active"));
      person.setStreetAddress(source.getString("StreetAddress"));
      person.setEmail(source.getString("Email"));
      person.setPhone(source.getString("Phone"));
      person.setLogitude(source.getBigDecimal("Logitude"));
      person.setLatitude(source.getBigDecimal("Latitude"));
      person.setCity(source.getString("City"));
      return person;
    }
    return null;
  }
  
}

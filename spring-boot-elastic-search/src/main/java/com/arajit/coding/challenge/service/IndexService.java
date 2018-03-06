package com.arajit.coding.challenge.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class IndexService {

  private static Logger logger = LoggerFactory.getLogger(IndexService.class);

  @Autowired
  RestClient restClient;

  @Autowired
  ObjectMapper jsonMapper;


  /**
   * Create a new index with the name {@code indexName}. The index settings and mappings can be
   * provided using the {@code requestBody}
   *
   * @param indexName The name of the index to create
   * @param requestBody The mappings and settings for the index to be created
   */
  public void createIndex(String indexName, String requestBody) {
    try {

      // Convert xml to jsn
      String requestBodyJson = converXmlToJson(requestBody);

      // Iterate over json to extract each person details as JSONArray
      JSONObject input = new JSONObject(requestBodyJson);
      JSONObject root = input.getJSONObject("root");
      JSONArray personArr = root.getJSONArray("Person");

      // Extract each json payload
      List<String> payload = getPayload(personArr);
      
      /*
       * create action metadata and populate request paylod for bulk request
       */
      String actionMetaData = getMetadata(indexName);
      StringBuilder bulkRequestBody = new StringBuilder();
      payload.forEach(request -> {
        bulkRequestBody.append(actionMetaData);
        bulkRequestBody.append(request);
        bulkRequestBody.append("\n");
      });
      
      // Send data to elastic search index Persons
      HttpEntity entity = new NStringEntity(bulkRequestBody.toString(), ContentType.APPLICATION_JSON);
      
      Response response = restClient.performRequest("POST", "/persons/_doc/_bulk",
          Collections.<String, String>emptyMap(), entity);
      int statusCode = response.getStatusLine().getStatusCode();
      if (statusCode > 299) {
        logger.error("Problem while creating an index: {}",
            response.getStatusLine().getReasonPhrase());
      }
    } catch (Exception ex) {
      logger.error("Problem in creating new index"+ex.fillInStackTrace());
    }
  }

  /**
   * @param requestXml
   * @return String
   */
  protected String converXmlToJson(final String requestXml) {
    return XML.toJSONObject(requestXml).toString();
  }

  protected List<String> getPayload(JSONArray personArray) {
    List<String> payloadList = null;
    if (null != personArray && personArray.length() > 0) {
      int numberOfPersons = personArray.length();
      payloadList = new ArrayList<>(numberOfPersons);
      for (int idx = 0; idx < numberOfPersons; idx++) {
        payloadList.add(personArray.getJSONObject(idx).toString());
      }
    }
    return payloadList;
  }
  
  protected String getMetadata(String indexName) {
    return String.format("{ \"index\" : { \"_index\" : \"%s\", \"_type\" : \"%s\" } }%n", indexName, "_doc");
  }
}

package com.arajit.coding.challenge.controller;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arajit.coding.challenge.domain.PersonInfo;
import com.arajit.coding.challenge.service.IndexService;
import com.arajit.coding.challenge.service.SearchService;

@RestController
@RequestMapping("/api/person")
public class PersonController {

  private static Logger logger = LoggerFactory.getLogger(PersonController.class);

  private static final String INDEX = "persons";

  @Value(value = "classpath:data/Persons.xml")
  private Resource inputResource;

  @Autowired
  private IndexService indexService;
  
  @Autowired
  private SearchService searchService;

  @PostMapping(path = "/load")
  public void loadData() throws Exception{

      String requestBody =
          StreamUtils.copyToString(inputResource.getInputStream(), Charset.defaultCharset());
      
      if (logger.isDebugEnabled())
        logger.debug("START - Loading data in index: " + INDEX);
      
      indexService.createIndex(INDEX, requestBody);
      
      if (logger.isDebugEnabled())
        logger.debug("END - Data loaded successfully.");

  }

  @GetMapping(path = "/search/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public PersonInfo search(@PathVariable(name = "employeeId") String employeeId) throws Exception {
    if (logger.isDebugEnabled())
      logger.debug("Performing search using employeeId: "+ employeeId);
    PersonInfo personInfo = new PersonInfo();
    personInfo.setPerson(searchService.searchByEmployeeId(employeeId));
    return personInfo;
  }

}

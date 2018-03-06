package com.arajit.coding.challenge.config;

import java.io.IOException;
import java.net.InetAddress;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Elastic search configuration.
 *
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.arajit.coding.challenge.repository")
public class ElasticSearchConfig {
  
  private static Logger logger = LoggerFactory.getLogger(ElasticSearchConfig.class);
  
  private static final String HEADER_CONTENT_TYPE_KEY = "Content-Type";
  private static final String DEFAULT_HEADER_CONTENT_TYPE = "application/json";
  
 /* @Value("${elasticsearch.host}")
  private String EsHost;

  @Value("${elasticsearch.port}")
  private int EsPort;

  @Value("${elasticsearch.clustername}")
  private String EsClusterName;*/

  /*@Bean
  public Client client() {
      try {
        
        Settings esSettings = Settings.builder()
            .put("cluster.name", EsClusterName)
            .build();

    //https://www.elastic.co/guide/en/elasticsearch/guide/current/_transport_client_versus_node_client.html
       
        TransportClient clt=new TransportClient();
        
    return TransportClient.builder()
            .settings(esSettings)
            .build()
            .addTransportAddress(
              new InetSocketTransportAddress(InetAddress.getByName(EsHost), EsPort));
      } catch (final IOException ioex) {
          logger.error("Cannot create temp dir", ioex);
          throw new RuntimeException();
      }
  }*/
  
  @Bean
  public RestClient restClient() {
    Header[] defaultHeaders =
        new Header[] {new BasicHeader(HEADER_CONTENT_TYPE_KEY, DEFAULT_HEADER_CONTENT_TYPE)};

    RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200, "http"))
        .setDefaultHeaders(defaultHeaders).build();
    return restClient;
  }
}

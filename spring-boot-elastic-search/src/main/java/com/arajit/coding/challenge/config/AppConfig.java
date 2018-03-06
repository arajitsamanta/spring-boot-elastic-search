package com.arajit.coding.challenge.config;

import org.springframework.context.annotation.Import;

/**
 * Main application configuration class.
 */
@Import(ElasticSearchConfig.class)
public class AppConfig {

}

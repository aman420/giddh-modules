package com.walkover.user.api.utils.commens;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 11 Jan 2018
 * 
 */
@Component
public class CustomObjectMapper extends ObjectMapper {
  
  public CustomObjectMapper() {
    registerModule(new JodaModule());
  }
}

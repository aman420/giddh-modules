/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walkover.user.api.utils.commens;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author Akash
 */

@Component
public class JsonUtils {
    
    public static <T> T parseJson(String json, Class<T> clazz) throws IOException {
    if (StringUtils.isBlank(json)) {
      return null;
    }
    return new CustomObjectMapper().readValue(json, clazz);
  }

  public static String toJson(Object arg) throws JsonProcessingException {
    return new CustomObjectMapper().writeValueAsString(arg);
  }

  public static String toJsonAsString(Object arg) throws JsonProcessingException {
    if (arg == null) {
      return null;
    }
    return new CustomObjectMapper()
      .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
      .writeValueAsString(arg).replaceAll("\\\\", "");
  }
    
}

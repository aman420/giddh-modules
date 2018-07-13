package com.walkover.user.api.models.commens;

import com.fasterxml.jackson.annotation.JsonView;
import com.walkover.user.api.utils.commens.JsonViews;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 */
@Getter
@AllArgsConstructor
public class ApiResponse {
  
  @JsonView(JsonViews.apiResponse.class)
  private Object body;
  
  @JsonView(JsonViews.apiResponse.class)
  private ApiStatus status;
}

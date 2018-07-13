package com.walkover.user.api.resources.commens;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Aman
 * @version v1
 * @param <T>
 * 
 * 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResource<T> {
  
  @Getter(onMethod = @__(@JsonIgnore))
  @Setter(value = AccessLevel.PUBLIC)
  private T model;
  
}

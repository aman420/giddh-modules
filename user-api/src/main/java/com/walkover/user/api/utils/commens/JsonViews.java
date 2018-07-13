package com.walkover.user.api.utils.commens;

import org.springframework.stereotype.Component;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 13 Jan 2018
 * 
 */
@Component
public class JsonViews {
  
  public interface apiResponse {};
  
  public interface userAuthKey {};
  public interface userEmailId {};
  public interface userName {};
    
  public interface userDetailsExcludingPassword extends apiResponse {};  
}

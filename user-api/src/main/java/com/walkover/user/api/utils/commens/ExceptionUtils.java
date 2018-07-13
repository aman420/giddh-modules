/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.walkover.user.api.utils.commens;

import com.walkover.user.api.exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.walkover.user.api.resources.commens.BaseResource;

/**
 *
 * @author Aman
 */

@Component
public class ExceptionUtils {
    
    private static MessageUtils messageUtils;
  @Autowired
  public ExceptionUtils(MessageUtils messageUtils) {
    ExceptionUtils.messageUtils = messageUtils;
  }
  
  public static void throwInvalidRequestException(BaseResource resource) throws InvalidRequestException {
    if (resource == null) {
      throw new InvalidRequestException(messageUtils.t("request is not valid"));
    }
  }
    
}

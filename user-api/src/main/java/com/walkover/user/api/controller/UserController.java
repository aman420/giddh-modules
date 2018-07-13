/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walkover.user.api.controller;

import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import com.walkover.user.api.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.walkover.user.api.services.v1.UserService;
import com.walkover.user.api.exception.*;
import com.walkover.user.api.models.commens.ApiResponse;
import com.walkover.user.api.models.commens.ApiStatus;
import javax.servlet.http.HttpServletRequest;
import com.walkover.user.api.resources.v1.UserResources;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
/**
 *
 * @author Aman
 */

@Controller
@RequestMapping("/user")
public class UserController {
    
    private final UserService userservice;
    @Autowired
    public UserController(UserService userservice)
    {
     this.userservice=userservice;
    }
     @RequestMapping(method = POST)
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserResources userresource,HttpServletRequest request) throws AlreadyExistException
    {
        //BufferedReader reader= request.getReader();
       // String jsonString=getJsonString(reader);
        //JsonUtils.parseJson(jsonString, clazz)
        
       //User user=userresource.getModel();
        User user = new User();
        user.setEmailId(userresource.getEmailId());
        user.setName(userresource.getName());
        User createuser= (User) userservice.saveUser(user);
        return new ResponseEntity<>(new ApiResponse(createuser, ApiStatus.success), HttpStatus.CREATED);
    }
    
    
    @RequestMapping(method = GET,value="/{emailId:.*}" )
    public ResponseEntity<ApiResponse> getUser(@PathVariable(value="emailId") String emailId,HttpServletRequest request) throws InvalidRequestException, InvalidParameterException, AlreadyExistException, NotFoundException{
    Optional<User> optional = userservice.findUser(emailId);
    User user = optional.get();
    return new ResponseEntity<>(new ApiResponse(new UserResources(user), ApiStatus.success), HttpStatus.OK);
    }
    
    @RequestMapping(method = GET)
    public ResponseEntity<ApiResponse> findAllUser(HttpServletRequest request)
    {
        
        System.out.println("the users details are");
        List<User> all_user=userservice.findAllUser();
       return new ResponseEntity<>(new ApiResponse(all_user, ApiStatus.success), HttpStatus.OK);
    }
    
    @RequestMapping(method=DELETE,value="/{emailId:.*}")
    public ResponseEntity deleteUser(@PathVariable(value="emailId") String emailId , HttpServletRequest request) throws Exception
    {
      User user= userservice.findUser(emailId).get();
      userservice.deleteUser(user);
      return new ResponseEntity<>(new ApiResponse(new UserResources(user), ApiStatus.success), HttpStatus.FOUND);
    }
    @RequestMapping(method=PUT)
    public ResponseEntity updateUser(@RequestBody UserResources userresource,HttpServletRequest request) throws NotFoundException
    {
        User user=userresource.getModel();
        //userservice.updateUser(user);
        return new ResponseEntity<>(new ApiResponse(userservice.updateUser(user), ApiStatus.success), HttpStatus.FOUND);
        
    }
}
    
    


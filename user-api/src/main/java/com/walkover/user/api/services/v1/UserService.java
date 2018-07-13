/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walkover.user.api.services.v1;

import java.util.Optional;
import java.util.List;
import com.walkover.user.api.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.walkover.user.api.dao.repository.UserRepository;
import com.walkover.user.api.exception.AlreadyExistException;
import com.walkover.user.api.exception.InvalidRequestException;
import com.walkover.user.api.exception.NotFoundException;
import com.walkover.user.api.resources.v1.UserResources;

/**
 *
 * @author Aman
 */
@Service
public class UserService {
    private final UserRepository userRepository;
  
  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  @Transactional(rollbackFor = {Throwable.class})
  public User saveUser(User user) throws AlreadyExistException {
    if(doesEmailIdExist(user.getEmailId()))
    {
        //System.out.print("yes m chla");
       throw new AlreadyExistException("mail id already exist"); 
    }
    return userRepository.save(user);
  }
  
  @Transactional
  public void deleteUser(User user){
      userRepository.delete(user);
  }
   @Transactional
   public Optional<User> findUser(String email) throws NotFoundException
   {
       if(!doesEmailIdExist(email))
      {
          throw new NotFoundException("email id does not exist");
      }
      return userRepository.findByEmailId(email);
   }
   @Transactional
   public List<User> findAllUser()
   {
       return (List)userRepository.findAll();
   }
  
  public boolean doesEmailIdExist(String emailId) {
    return userRepository.doesEmailIdExist(emailId);
  }
  
  @Transactional(rollbackFor = {Throwable.class})
  public User updateUser(User user) throws NotFoundException
  {
      if(!doesEmailIdExist(user.getEmailId()))
      {
          throw new NotFoundException("email id does not exist");
      }
      User user1=findUser(user.getEmailId()).get();
      //Boolean b=(Boolean)findUser(user.getEmailId()).get();
        //deleteUser(user1);
        user1.setName(user.getName());
        userRepository.save(user1); 
        return user1;
  }
    
}

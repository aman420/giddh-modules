/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walkover.user.api.dao.repository;
import com.walkover.user.api.dao.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aman
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  
  public Optional<User> findByEmailId(String emailId);
  @Query(value = "select case when count(u.id) > 0 then true else false end as email_id_exists from users u where u.email_id = :emailId", 
    nativeQuery = true)
  public boolean doesEmailIdExist(@Param("emailId") String emailId);
}


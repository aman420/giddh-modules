/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walkover.user.api.dao.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
/**
 *
 * @author Aman
 */

@Table(name = "users")
@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User implements Serializable{
    
    
  @Id
  @NotNull
  @Column(name="id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  
  @NotNull
  @Column(name = "email_id")
  @Email(regexp="[a-zA-Z0-9][a-zA-Z0-9]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")
  private String emailId;
    
  @NotNull(message = "User name cannot be blank.")
  @Column(name = "name")
  private String name;
  
  
        
  public long getId() {
      return id;
  }
  
  public void setId(long id)
  {
      this.id=id;
  }
  
  
  
  public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String id) {
		this.emailId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString(){
		return "id="+emailId+", name="+name;
	}
    
}

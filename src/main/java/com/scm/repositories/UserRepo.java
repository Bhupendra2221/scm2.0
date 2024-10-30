package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.user;





@Repository
public interface UserRepo extends JpaRepository<user,String> {

    //extra DB related Method operations
    //custom Query Method
    // Customer Finder Method
    
   Optional<user> findByEmail(String email);
  
        
}

package com.in28min.rest.webservices.restfulwebservices.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 

@Repository
public interface PostRepositiry extends JpaRepository<Post, Integer> {

}

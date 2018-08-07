package com.myspringboot.stockdata.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.myspringboot.stockdata.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {

    List<User> findByLastName(@Param("lastname") String lastname);

    List<User> findByFirstName(@Param("firstname") String firstname);
    
}

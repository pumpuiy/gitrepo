package com.myspringboot.stockdata.repository;

import com.myspringboot.stockdata.model.User;

public interface UserRepositoryCustom {
    
    boolean isUserExist(User user);

}

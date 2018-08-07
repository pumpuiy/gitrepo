package com.myspringboot.stockdata.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myspringboot.stockdata.model.User;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

	@PersistenceContext
    EntityManager entityManager;
	
	@Override
	public boolean isUserExist(User user) {
		
		Query query = entityManager.createNativeQuery("SELECT * FROM users " + "WHERE first_name = ? and last_name = ?", User.class);
        query.setParameter(1, user.getFirstName().trim());
        query.setParameter(2, user.getLastName().trim());
        
        List<User> rs = query.getResultList();
        if (rs.isEmpty()) {
        	return false;
        }else {
        	return true;
        }
	}

    

}

package com.myspringboot.stockdata.apicontroller;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.myspringboot.stockdata.common.ResponseCode;
import com.myspringboot.stockdata.common.Utils;
import com.myspringboot.stockdata.exception.UserException;
import com.myspringboot.stockdata.model.User;
import com.myspringboot.stockdata.repository.UserRepository;

@RestController()
@RequestMapping("/api/usermgr")
@Transactional(readOnly = false, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    // -------------------Retrieve All Users---------------------------------------------
    
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        Iterable<User> iter = userRepository.findAll();
        List<User> users = (List<User>)Utils.makeCollection(iter);
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
 
    // -------------------Retrieve Single User------------------------------------------
 
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        logger.info("Fetching User with id {}", id);
        Optional<User> opt = userRepository.findById(id);
        if (!opt.isPresent()) {
        	logger.error("User with id {} not found.", id);
        	UserException exception = new UserException("User with id " + id  + " not found");
        	exception.setErrorCode(ResponseCode.DATA_NOT_FOUND);
            return new ResponseEntity(exception, HttpStatus.NOT_FOUND);
        }
        
        User user = opt.get();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
 
    // -------------------Create a User-------------------------------------------
 
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        logger.info("Creating User : {}", user);
 
        if (userRepository.isUserExist(user)) {
        	String name = user.getFirstName() + " " + user.getLastName();
            logger.error("Unable to create. A User with name {} already exist", name);
            UserException exception = new UserException("Unable to create. A User with name " + name + " already exist.");
        	exception.setErrorCode(ResponseCode.DATA_DUPLICATE);
            return new ResponseEntity(exception ,HttpStatus.CONFLICT);
        }
        userRepository.save(user);
        logger.info(user.toString() + " successfully saved into DB");
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
 
    // ------------------- Update a User ------------------------------------------------
 
    @RequestMapping(value = "/user/", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        logger.info("Updating User with id {}", user.getId());
 
        Optional<User> opt = userRepository.findById(user.getId());
        
        if (!opt.isPresent()) {
            logger.error("Unable to update. User with id {} not found.", user.getId());
            UserException exception = new UserException("Unable to upate. User with id " + user.getId() + " not found.");
        	exception.setErrorCode(ResponseCode.DATA_NOT_FOUND);
            return new ResponseEntity(exception,  HttpStatus.NOT_FOUND);
        }
 
        userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
 
    // ------------------- Delete a User-----------------------------------------
 
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting User with id {}", id);
 
        Optional<User> opt = userRepository.findById(id);
        if (!opt.isPresent()) {
            logger.error("Unable to delete. User with id {} not found.", id);
            UserException exception = new UserException("Unable to delete. User with id "+id+" not found.");
        	exception.setErrorCode(ResponseCode.DATA_NOT_FOUND);
            return new ResponseEntity(exception, HttpStatus.NOT_FOUND);
        }
        
        User user = new User("","");
        user.setId(id);
 
        userRepository.delete(user);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
 
    // ------------------- Delete All Users-----------------------------
 
    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        logger.info("Deleting All Users");
 
        userRepository.deleteAll();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

}

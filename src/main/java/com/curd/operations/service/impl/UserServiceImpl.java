package com.curd.operations.service.impl;

import com.curd.operations.entity.User;
import com.curd.operations.exception.CurdOperationsException;
import com.curd.operations.repository.UserRepository;
import com.curd.operations.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /*
    *  Method creates new record in database if not exists or else throw an error
    * @Param(User user)
    * @return (String message)
    * @throw CurdOperationsException
     */
    @Override
    public String addUser(User user) throws CurdOperationsException {
        if(user.getEmail() == null || user.getEmail().isEmpty()){
            throw new CurdOperationsException("Email Must Not Be Empty");
        }
        if(userRepository.findByEmail(user.getEmail()) !=null){
            throw new CurdOperationsException("User Already Exists");
        }
         userRepository.save(user);
        return "User Added Successfully";
    }

    /*
    * Method retrives all users from db
    * @return List<User>
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    /*
    * Method delete user by email
    * @Param(String email)
    * @return(String message)
    *throw CurdOperationsException
     */
    @Override
    public String deleteUserByEmail(String email) throws CurdOperationsException {
        if(email == null || email.isEmpty()){
            throw new CurdOperationsException("Email Id is missing");
        }
        int count = userRepository.deleteByEmail(email);
        if(count != 1){
            throw new CurdOperationsException("User Not Exists");
        }
        return "User Deleted Successfully";
    }


    /*
      * Method Updates user if exists or else create new record
      * @Param(User user)
      * @return(String message)
      * throw throws CurdOperationsException

     */
    @Override
    public String updateUser(User user) throws CurdOperationsException {
        String message = "";
        if(user.getEmail() == null || user.getEmail().isEmpty()){
            throw new CurdOperationsException("Email Must not be Null or Empty");
        }
        User dbUser = userRepository.findByEmail(user.getEmail());
        if(dbUser == null){
            dbUser = user;
            message = "User Created Successfully";
        }
        else {
            dbUser.setName(user.getName());
            dbUser.setPassword(user.getPassword());
            message = "User Updated Successfully";
        }
        userRepository.save(dbUser);
        return message;
    }
}

package com.curd.operations.controller;


import com.curd.operations.entity.User;
import com.curd.operations.exception.CurdOperationsException;
import com.curd.operations.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
    * Method is used to store new Record in database
    * @Param(User user)
    * @return ResponseEntity
     */
    @PostMapping("/adduser")
    public ResponseEntity<String> addNewUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
        } catch (CurdOperationsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    * Method retrives all users from db
    * @return ResponseEntity
    *
     */
    @GetMapping("/get/all")
    public ResponseEntity<Object> getAllUsers(){
        try{
            return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    * Method updates a user in database
    * @Param(User user)
    * @return ResponseEntity
     */

    @PutMapping("/update/user")
    public ResponseEntity<Object> updateUser(@RequestBody User user){
        try{
            return new ResponseEntity<>(userService.updateUser(user),HttpStatus.OK);
        }
        catch (CurdOperationsException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Method deletes a user from the database
     * @Param(User user)
     * @return ResponseEntity
     */

    @DeleteMapping("/user/{email}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "email") String email){
        try{
            return new ResponseEntity<>(userService.deleteUserByEmail(email),HttpStatus.OK);
        }
        catch (CurdOperationsException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

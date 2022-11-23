package com.first.myproject.controller;
import com.first.myproject.exception.ResourseNotFoundException;
import com.first.myproject.model.user;
import com.first.myproject.repository.userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class usercontroller {

       @Autowired
    private userrepo userrepo;
    //get all users
    @GetMapping("/")
   public  List<user> getAllusers(){
        return this.userrepo.findAll();
    }
    //get user by id
    @GetMapping("/{id}")
    public user getById(@PathVariable (value = "id")long userID ){
            return this.userrepo.findById(userID)
                    .orElseThrow(()-> new ResourseNotFoundException("user not found with id :" + userID));
    }
    //create user
    @PostMapping()
    public user creatuser(@RequestBody user user) {
        return this.userrepo.save(user);
    }

    //update user
    @PutMapping("/{id}")
    public user update(@RequestBody user user, @PathVariable ("id") long userID){
       user existinguser = this.userrepo.findById(userID)
                .orElseThrow(()-> new ResourseNotFoundException("user not found with id : " + userID));
        existinguser.setFirstname(user.getFirstname());
        existinguser.setLastname(user.getLastname());
        existinguser.setEmail(user.getEmail());
        return  this.userrepo.save(existinguser);
    }
    //delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<user> deletuser(@PathVariable (value = "id")long userID){
        user existinguser = this.userrepo.findById(userID)
                .orElseThrow(()-> new ResourseNotFoundException("user not found with id : " + userID));
            this.userrepo.delete(existinguser);
            return ResponseEntity.ok(). build();  //It return 200 response
    }
}

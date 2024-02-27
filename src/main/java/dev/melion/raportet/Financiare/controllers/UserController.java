package dev.melion.raportet.Financiare.controllers;

import dev.melion.raportet.Financiare.model.forms.User;
import dev.melion.raportet.Financiare.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reports/user")
    public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<User>>(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody Map<String, String> object){
        User user = new User(object.get("username"), object.get("email"));
        return  new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }
}

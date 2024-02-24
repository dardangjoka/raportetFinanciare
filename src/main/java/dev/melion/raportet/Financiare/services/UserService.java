package dev.melion.raportet.Financiare.services;

import dev.melion.raportet.Financiare.model.User;
import dev.melion.raportet.Financiare.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUser(ObjectId id){
        return userRepository.findById(id);
    }

    public User createUser(User user) {
         return userRepository.insert(user);

    }
}

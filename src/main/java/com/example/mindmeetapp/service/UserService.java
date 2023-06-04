package com.example.mindmeetapp.service;

import com.example.mindmeetapp.model.UserModel;
import com.example.mindmeetapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
private final UserRepository repo;


    public List<UserModel> getAllUsers(){return repo.findAll();}

    public UserModel getUserById(Long id){return repo.findById(id).orElse(null);}

    public void deleteUser(Long id){repo.deleteById(id);}

    public UserModel createUser(UserModel user) {repo.save(user);
        return user;
    }

    public UserModel loginUser(String username, String password) {
        return repo.findByUsernameAndPassword(username, password).orElse(null);
    }


}

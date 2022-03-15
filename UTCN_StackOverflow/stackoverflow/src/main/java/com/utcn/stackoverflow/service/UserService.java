package com.utcn.stackoverflow.service;

import com.utcn.stackoverflow.model.User;
import com.utcn.stackoverflow.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    IUserRepository iUserRepository;

    public List<User> getAllUsers() {
        return (List<User>) iUserRepository.findAll();
    }


    public User getUser(Integer id) {
        Optional<User> user = iUserRepository.findById(id);
        return user.orElse(null);
    }

    public String deleteUser(Integer id) {
        try {
            iUserRepository.delete(this.getUser(id));

            return "Delete success.";
        }catch (Exception e){
            return "Delete failed.";
        }
    }


    public User saveUser(User user){
        return iUserRepository.save(user);
    }

    public User updateUser(Integer id, User user){
        User initialUser = this.getUser(id);

        initialUser.setUsername(user.getUsername());
        initialUser.setPassword(user.getPassword());
        initialUser.setLogged(user.isLogged());
        initialUser.setType(user.getType());
        //initialUser.setQuestions(user.getQuestions());
        //initialUser.setAnswers(user.getAnswers());

        return iUserRepository.save(initialUser);
    }
}

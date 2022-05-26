package com.utcn.stackoverflow.controller;

import com.utcn.stackoverflow.model.User;
import com.utcn.stackoverflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAllUsers")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUser")
    @ResponseBody
    public User getUser(@RequestParam(name = "id") Integer id) {
        return userService.getUser(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestParam(name = "id") Integer id) {
        return userService.deleteUser(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createUser")
    @ResponseBody
    public User createUser(@RequestBody User user) { return userService.saveUser(user); }

    @RequestMapping(method = RequestMethod.POST, value = "/updateUser")
    @ResponseBody
    public User updateUser( @RequestBody User user) { return userService.updateUser(user.getUserID(), user);}
}

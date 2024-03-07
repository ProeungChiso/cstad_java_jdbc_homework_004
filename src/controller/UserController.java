package controller;

import model.User;
import model.UserService;
import model.UserServiceImpl;

import java.util.List;

public class UserController {
    private final UserService userService = new UserServiceImpl();
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
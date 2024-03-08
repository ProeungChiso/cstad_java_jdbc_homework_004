package controller;

import model.User;
import model.UserService;
import model.UserServiceImpl;
import repository.UserRepository;

import java.util.List;

public class UserController {
    private final UserService userService = new UserServiceImpl();
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    public User getUserByID(Integer userId) {
        return userService.getUserByID(userId);
    }
    public void insertNewUser(User user){
        userService.insertNewUser(user);
    }
    public void deleteUser(Integer userId){
        userService.deleteUser(userId);
    }
    public void updateUserById(Integer userId, String userName, String userEmail, String userPass) {
        userService.updateUserById(userId, userName, userEmail, userPass);
    }
}

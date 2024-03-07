package model;

import java.util.List;
public interface UserService {
    List<User> getAllUsers(); //read all data in table users
    User getUserByID(Integer userId); //read one data in table users
}

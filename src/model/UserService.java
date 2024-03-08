package model;

import java.util.List;
public interface UserService {
    List<User> getAllUsers();
    User getUserByID(Integer userId);
    void insertNewUser(User user);
    void deleteUser(Integer userId);
    void updateUserById(Integer userId, String userName, String userEmail, String userPass);
}

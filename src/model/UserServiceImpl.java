package model;

import repository.UserRepository;
import java.util.List;
public class UserServiceImpl implements UserService{
    @Override
    public List<User> getAllUsers() {
        return UserRepository.getAllUsers();
    }
    @Override
    public User getUserByID(Integer userId) {
        return UserRepository.getUserByID(userId);
    }
    @Override
    public void insertNewUser(User user) {
        UserRepository.insertNewUser(user);
    }
}

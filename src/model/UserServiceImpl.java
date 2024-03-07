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
        return null;
    }
}

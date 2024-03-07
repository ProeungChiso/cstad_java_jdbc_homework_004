package repository;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository{
    public static List<User> getAllUsers() {
        String sqlCmd = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();
        PropertiesLoader.loaderPropertiesFile();
        try(
                Connection connection = DriverManager.getConnection(
                    PropertiesLoader.properties.getProperty("DATABASE_URL"),
                    PropertiesLoader.properties.getProperty("DATABASE_USERNAME"),
                    PropertiesLoader.properties.getProperty("DATABASE_PASSWORD")
                );
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlCmd);
                ){
            while (resultSet.next()){
                userList.add(
                        new User(
                                resultSet.getInt("user_id"),
                                resultSet.getString("user_uuid"),
                                resultSet.getString("user_name"),
                                resultSet.getString("user_email"),
                                resultSet.getString("user_password"),
                                resultSet.getBoolean("user_is_deleted"),
                                resultSet.getBoolean("user_is_verify")
                        )
                );
            }
        }catch (SQLException e){
            System.out.println("SQL"+ e.getMessage());
        }
        return userList;
    }
    public static User getUserByID(Integer userId) {
        return null;
    }
}

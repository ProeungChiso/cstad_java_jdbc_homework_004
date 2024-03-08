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
        String sqlCmd = "SELECT * FROM users WHERE user_id = ?";
        User user = null; // Initialize user as null
        PropertiesLoader.loaderPropertiesFile();
        try (
                Connection connection = DriverManager.getConnection(
                        PropertiesLoader.properties.getProperty("DATABASE_URL"),
                        PropertiesLoader.properties.getProperty("DATABASE_USERNAME"),
                        PropertiesLoader.properties.getProperty("DATABASE_PASSWORD")
                );
                PreparedStatement statement = connection.prepareStatement(sqlCmd)
        ) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getInt("user_id"),
                            resultSet.getString("user_uuid"),
                            resultSet.getString("user_name"),
                            resultSet.getString("user_email"),
                            resultSet.getString("user_password"),
                            resultSet.getBoolean("user_is_deleted"),
                            resultSet.getBoolean("user_is_verify")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL " + e.getMessage());
        }
        return user;
    }

    public static void insertNewUser(User user){
        String sqlCmd = "INSERT INTO users (user_uuid, user_name, user_email, user_password, user_is_deleted, user_is_verify) VALUES (?,?,?,?,?,?)";
        PropertiesLoader.loaderPropertiesFile();
        try(
                Connection connection = DriverManager.getConnection(
                        PropertiesLoader.properties.getProperty("DATABASE_URL"),
                        PropertiesLoader.properties.getProperty("DATABASE_USERNAME"),
                        PropertiesLoader.properties.getProperty("DATABASE_PASSWORD")
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sqlCmd);
        ){
            preparedStatement.setString(1, user.getUserUUID());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getUserEmail());
            preparedStatement.setString(4, user.getUserPassword());
            preparedStatement.setBoolean(5, user.isUserIsDeleted());
            preparedStatement.setBoolean(6, user.isUserIsVerify());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("User inserted successfully.");
            } else {
                System.out.println("Failed to insert user.");
            }
        }catch (SQLException e){
            System.out.println("SQL "+e.getMessage());
        }
    }
}

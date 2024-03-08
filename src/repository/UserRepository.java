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
            System.out.println("❌SQL"+ e.getMessage());
        }
        return userList;
    }
    public static User getUserByID(Integer userId) {
        String sqlCmd = "SELECT * FROM users WHERE user_id = ?";
        User user = null;
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
            System.out.println("❌SQL " + e.getMessage());
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
                System.out.println("✅User inserted successfully.");
            } else {
                System.out.println("❌Failed to insert user.");
            }
        }catch (SQLException e){
            System.out.println("❌SQL "+e.getMessage());
        }
    }
    public static void deleteUser(Integer userId){
        String sqlCmd = "DELETE FROM users WHERE user_id = ?";
        PropertiesLoader.loaderPropertiesFile();
        try(
                Connection connection = DriverManager.getConnection(
                        PropertiesLoader.properties.getProperty("DATABASE_URL"),
                        PropertiesLoader.properties.getProperty("DATABASE_USERNAME"),
                        PropertiesLoader.properties.getProperty("DATABASE_PASSWORD")
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sqlCmd);
                ){
            preparedStatement.setInt(1, userId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅User with ID " + userId + " deleted successfully.");
            } else {
                System.out.println("❌User with ID " + userId + " not found.");
            }
        }catch (SQLException e){
            System.out.println("❌SQL " + e.getMessage());
        }
    }
    public static void updateUserById(Integer userId, String userName, String userEmail, String userPass){
        String sqlCmd = "UPDATE users SET user_name = ?, user_email = ?, user_password = ? WHERE user_id = ?";
        PropertiesLoader.loaderPropertiesFile();
        try(
                Connection connection = DriverManager.getConnection(
                        PropertiesLoader.properties.getProperty("DATABASE_URL"),
                        PropertiesLoader.properties.getProperty("DATABASE_USERNAME"),
                        PropertiesLoader.properties.getProperty("DATABASE_PASSWORD")
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sqlCmd);
                ){
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userEmail);
            preparedStatement.setString(3, userPass);
            preparedStatement.setInt(4, userId);
            preparedStatement.executeUpdate();
            System.out.println("✅User updated successfully.");
        } catch (SQLException e) {
            System.out.println("❌SQL " + e.getMessage());
        }
    }
}

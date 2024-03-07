package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class User {
    private Integer userId;
    private String userUUID;
    private String userName;
    private String userEmail;
    private String userPassword;
    private boolean userIsDeleted;
    private boolean userIsVerify;
}

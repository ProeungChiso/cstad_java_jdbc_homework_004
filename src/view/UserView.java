package view;

import controller.UserController;
import model.User;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UserView {
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    private final static UserController userController = new UserController();
    public static void menuView(){
        System.out.println(ANSI_CYAN + "+ ".repeat(13)+ANSI_CYAN+ANSI_RESET);
        System.out.println(ANSI_CYAN + "CREATE-READ-UPDATE-DELETE"+ANSI_CYAN+ANSI_RESET);
        System.out.println(ANSI_CYAN + "+ ".repeat(13)+ANSI_CYAN+ANSI_RESET);
        System.out.println(ANSI_YELLOW + "ℹ\uFE0FOPTIONS" + ANSI_YELLOW+ANSI_YELLOW);
        System.out.println(ANSI_CYAN + """
                (1). READ    (2). SEARCH BY ID    (3). CREATE    (4). UPDATE    (5). DELETE    (6). EXIT"""+ANSI_CYAN+ANSI_RESET);
        System.out.println("+ ".repeat(13));
    }

    public static void option(){
        int op;
        do{
            menuView();
            System.out.print("➡\uFE0FINSERT AN OPTION: ");
            op = new Scanner(System.in).nextInt();
            switch (op){
                case 1 -> readAllUsers();
                case 2 -> searchUserByID();
                case 3 -> insertNewUser();
                case 4 -> updateUserById();
                case 5 -> deleteUser();
                case 6 -> System.exit(0);
                default -> System.out.println("❌Invalid option. Please try again.");
            }
        }while (true);
    }

    public static void readAllUsers(){
        Table table = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        List<User> users = userController.getAllUsers();
        System.out.println("+ ".repeat(13));
        System.out.println(ANSI_YELLOW + "✅ALL USERS" + ANSI_YELLOW+ANSI_RESET);
        table.addCell("  USER ID  ");
        table.addCell("  USER UUID  ");
        table.addCell("     USER NAME     ");
        table.addCell("     USER EMAIL     ");
        table.addCell("     USER PASSWORD     ");
        table.addCell("     USER IS DELETED     ");
        table.addCell("     USER IS VERIFY     ");
        for (User user : users) {
            table.addCell(String.valueOf(user.getUserId()), cellStyle);
            table.addCell(user.getUserUUID(), cellStyle);
            table.addCell(user.getUserName(), cellStyle);
            table.addCell(user.getUserEmail(), cellStyle);
            table.addCell(user.getUserPassword(), cellStyle);
            table.addCell(String.valueOf(user.isUserIsDeleted()), cellStyle);
            table.addCell(String.valueOf(user.isUserIsVerify()), cellStyle);
        }
        System.out.println(table.render());
    }
    public static void searchUserByID() {
        Table table = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        System.out.print("➡\uFE0FINSERT ID TO SEARCH: ");
        int userId = new Scanner(System.in).nextInt();
        User user = userController.getUserByID(userId);
        System.out.println("+ ".repeat(13));
        System.out.println(ANSI_YELLOW + "✅USER FOUND" + ANSI_YELLOW+ANSI_RESET);
        table.addCell("  USER ID  ");
        table.addCell("  USER UUID  ");
        table.addCell("     USER NAME     ");
        table.addCell("     USER EMAIL     ");
        table.addCell("     USER PASSWORD     ");
        table.addCell("     USER IS DELETED     ");
        table.addCell("     USER IS VERIFY     ");
        if (user != null) {
            table.addCell(String.valueOf(user.getUserId()), cellStyle);
            table.addCell(user.getUserUUID(), cellStyle);
            table.addCell(user.getUserName(), cellStyle);
            table.addCell( user.getUserEmail(), cellStyle);
            table.addCell(user.getUserPassword(), cellStyle);
            table.addCell(String.valueOf(user.isUserIsDeleted()), cellStyle);
            table.addCell(String.valueOf(user.isUserIsVerify()), cellStyle);
            System.out.println(table.render());
        } else {
            System.out.println("❌User not found with ID: " + userId);
        }
    }
    public static void insertNewUser() {
        User insertUser = new User();
        UUID uuid = UUID.randomUUID();
        String UUID = uuid.toString().substring(0,9);
        insertUser.setUserUUID(UUID);
        System.out.print("➡\uFE0FNAME: ");
        insertUser.setUserName(new Scanner(System.in).nextLine());
        System.out.print("➡\uFE0FEMAIL: ");
        insertUser.setUserEmail(new Scanner(System.in).nextLine());
        System.out.print("➡\uFE0FPASSWORD: ");
        insertUser.setUserPassword(new Scanner(System.in).nextLine());
        insertUser.setUserIsDeleted(false);
        insertUser.setUserIsVerify(true);
        userController.insertNewUser(insertUser);
    }
    public static void deleteUser(){
        System.out.print("➡\uFE0FINSERT ID TO DELETE: ");
        int userId = new Scanner(System.in).nextInt();
        userController.deleteUser(userId);
    }
    public static void updateUserById(){
        System.out.print("➡\uFE0FINSERT ID TO UPDATE: ");
        int userId = new Scanner(System.in).nextInt();
        System.out.print("➡\uFE0FName: ");
        String userName = new Scanner(System.in).nextLine();
        System.out.print("➡\uFE0FEMAIL: ");
        String userEmail = new Scanner(System.in).nextLine();
        System.out.print("➡\uFE0FPASSWORD: ");
        String userPass = new Scanner(System.in).nextLine();
        userController.updateUserById(userId, userName, userEmail, userPass);
    }
}

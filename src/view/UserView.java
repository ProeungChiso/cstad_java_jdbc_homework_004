package view;

import controller.UserController;
import model.User;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private final static UserController userController = new UserController();
    public static void menuView(){
        System.out.println("+ ".repeat(13));
        System.out.println("CREATE-READ-UPDATE-DELETE");
        System.out.println("+ ".repeat(13));
        System.out.println("OPTIONS");
        System.out.println("""
                1). READ
                2). CREATE
                3). UPDATE
                4). DELETE
                5). EXIT""");
        System.out.println("+ ".repeat(13));
        option();
    }

    public static void option(){
        int op;
        do{
            System.out.print("INSERT AN OPTION: ");
            op = new Scanner(System.in).nextInt();
            switch (op){
                case 1 -> readAllUsers();
                case 2 -> System.out.println("Create!");
                case 3 -> System.out.println("Update!");
                case 4 -> System.out.println("Delete!");
                case 5 -> System.exit(0);
                default -> System.out.println("Invalid option. Please try again.");
            }
        }while (true);
    }

    public static void readAllUsers(){
        Table table = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        List<User> users = userController.getAllUsers();
        System.out.println("ALL USERS");
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
}

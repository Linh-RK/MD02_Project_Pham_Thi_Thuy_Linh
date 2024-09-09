package presentation.admin;

import business.entity.User;
import business.service.UserService;

import java.util.Scanner;

import static business.Data.*;

public class UserManagement {
    public static void userManagement(Scanner sc) {
        boolean flag = true;
        do {
            System.out.println("----------------------------------ADMIN USER----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Hiển thị người dùng (phân trang)                                 |");
            System.out.println("|        2. Sửa thay đổi trạng thái                                          |");
            System.out.println("|        3. Xóa người dùng                                                   |");
            System.out.println("|        4. Tìm kiếm người dùng                                              |");
            System.out.println("|        5. Sắp xếp theo tên theo chiều giảm dần hoặc tăng dần               |");
            System.out.println("|        6. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Please enter your choice");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    UserService.showAllUserInfo(userList);
                    break;
                }
                case "2":{
                    UserService.changeUserStatus(sc);
                    break;
                }
                case "3":{
                    UserService.deleteUser(sc);
                    break;
                }
                case "4":{
                    UserService.searchUser(sc);
                    break;
                }
                case "5":{
                   sortUserMenu(sc);
                    break;
                }
                case "6":{
                    flag = false;
                    break;
                }
                default:{
                    System.err.println("Please enter a choice from 1 to 6");
                    break;
                }
            }
        }while (flag);
    }

    private static void sortUserMenu(Scanner sc) {
        boolean flag = true;
        do {
            System.out.println("----------------------------------ADMIN USER----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Sắp xếp theo tên tăng dần                                        |");
            System.out.println("|        2. Sắp xếp theo tên giảm dần                                       |");
            System.out.println("|        3. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Please enter your choice");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    UserService.sortUserByNameIncrease();
                    break;
                }
                case "2":{
                    UserService.sortUserByNameDecrease();
                    break;
                }
                case "3":{
                    flag = false;
                    break;
                }
                default:{
                    System.err.println("Please enter a choice from 1 to 6");
                    break;
                }
            }
        }while (flag);
    }
}

package presentation.admin;

import java.util.Scanner;

import static presentation.admin.CategoryManagement.categoryManagement;
import static presentation.admin.OrderManagement.orderManagement;
import static presentation.admin.ProductManagement.productManagement;
import static presentation.admin.Statistic.statistics;
import static presentation.admin.UserManagement.userManagement;

public class AdminMenu {
    public static void Admin_Menu(Scanner sc) {
        boolean flag = true;
        do {
            System.out.println("----------------------------------ADMIN MENU----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Quản lý danh mục                                                 |");
            System.out.println("|        2. Quản lý sản phẩm                                                 |");
            System.out.println("|        3. Quản lý đơn hàng                                                 |");
            System.out.println("|        4. Quản lý người dùng                                               |");
            System.out.println("|        5. Thống kê                                                         |");
            System.out.println("|        6. Đăng xuất                                                        |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Please enter your choice");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    categoryManagement(sc);
                    break;
                }
                case "2":{
                    productManagement(sc);
                    break;
                }
                case "3":{
                    orderManagement(sc);
                    break;
                }
                case "4":{
                    userManagement(sc);
                    break;
                }
                case "5":{
                    statistics(sc);
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
}

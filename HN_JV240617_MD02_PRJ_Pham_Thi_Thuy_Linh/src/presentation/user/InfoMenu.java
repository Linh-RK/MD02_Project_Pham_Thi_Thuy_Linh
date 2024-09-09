package presentation.user;

import business.service.UserService;

import java.util.Scanner;

import static business.Data.user;
import static presentation.user.AddressMenu.addressMenu;

public class InfoMenu {

    public static void infoMenu(Scanner sc) {
        boolean flag = true;
        do {
            System.out.println("----------------------------------INFO MENU-----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Hiển thị thông tin                                               |");
            System.out.println("|        2. Cập nhật thông tin                                               |");
            System.out.println("|        3. Thay đổi mật khẩu                                                |");
            System.out.println("|        4. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Please enter your choice");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    user.toString();
                    break;
                }
                case "2":{
                    menuEditInfo(sc);
                    break;
                }
                case "3":{
                    user.updatePassword(sc);
                    break;
                }
                case "4":{
                    flag=false;
                    break;
                }
                default:{
                    break;
                }
            }
        }while (true);
    }

    private static void menuEditInfo(Scanner sc) {
        boolean flag = true;
            do {
                System.out.println("-------------------------------EDIT INFO MENU---------------------------------");
                System.out.println("|                                                                            |");
                System.out.println("|        1. Cập nhật địa chỉ                                                 |");
                System.out.println("|        2. Thay đổi mật khẩu                                                |");
                System.out.println("|        3. Quay lại                                                         |");
                System.out.println("|                                                                            |");
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("Please enter your choice");
                String choice = sc.nextLine();
                switch (choice) {
                    case "1":{
                        addressMenu(sc);
                        break;
                    }
                    case "2":{
                        user.updatePassword(sc);
                        break;
                    }
                    case "3":{
                        flag=false;
                        break;
                    }
                    default:{
                        System.out.println("Please enter your choice");
                        break;
                    }
                }
            }while (flag);
    }
}

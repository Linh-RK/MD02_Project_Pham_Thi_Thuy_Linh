package presentation.user;

import business.service.UserService;

import java.util.Scanner;

import static business.Data.currentUser;
import static business.Data.user;
import static business.entity.User.displayDetails;
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
                    displayDetails(currentUser);
                    break;
                }
                case "2":{
                    menuEditInfo(sc);
                    break;
                }
                case "3":{
                    currentUser.updatePassword(sc);
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
        }while (flag);
    }

    private static void menuEditInfo(Scanner sc) {
        boolean flag = true;
            do {
                System.out.println("-------------------------------EDIT INFO MENU---------------------------------");
                System.out.println("|                                                                            |");
                System.out.println("|        1. Cập nhật địa chỉ                                                 |");
                System.out.println("|        2. Thay đổi các thông tin khác                                      |");
                System.out.println("|        3. Thay đổi mật khẩu                                                |");
                System.out.println("|        4. Quay lại                                                         |");
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
                        currentUser.updateUserInfo(sc);
                        break;
                    }
                    case "3":{
                        currentUser.updatePassword(sc);
                        break;
                    }
                    case "4":{
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

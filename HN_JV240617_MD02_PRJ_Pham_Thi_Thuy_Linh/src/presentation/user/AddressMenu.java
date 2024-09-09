package presentation.user;

import business.service.AddressService;

import java.util.Scanner;

public class AddressMenu {
    public static void addressMenu(Scanner sc) {
        boolean flag = true;
        do {
            System.out.println("----------------------------------CART MENU-----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Lấy ra địa chỉ người dùng theo ID                                |");
            System.out.println("|        2. Hiển thị dang sách địa chỉ người dùng                            |");
            System.out.println("|        3. Xoá địa chỉ theo mã ID                                           |");
            System.out.println("|        4. Thêm địa chỉ mới                                                 |");
            System.out.println("|        5. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Please enter your choice");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    AddressService.searchAddressById(sc);
                    break;
                }
                case "2":{
                    AddressService.showAllAddress();
                    break;
                }
                case "3":{
                    AddressService.deleteAddressById(sc);
                    break;
                }
                case "4":{
                    AddressService.addNewAddress(sc);
                    break;
                }
                case "5":{
                    flag = false;
                    break;
                }
                default:{
                    System.err.println("Please enter a choice from 1 to 5");
                    break;
                }
            }
        }while (flag);
    }
}

package presentation.user;

import business.service.ProductService;
import presentation.admin.ProductManagement;

import java.util.Scanner;

import static business.service.ProductService.showAllProduct;

public class ProductMenu {
    public static void productMenu(Scanner sc) {
        boolean flag = true;
        do {
            System.out.println("--------------------------------PRODUCT MENU----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Hiển thị sản phẩm (phân trang)                                   |");
            System.out.println("|        2. Tìm kiếm sản phẩm                                                |");
            System.out.println("|        3. Sắp xếp                                                          |");
            System.out.println("|        4. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Please enter your choice");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    ProductService.showAllProduct();
                    break;
                }
                case "2":{
                    ProductService.searchProduct(sc);
                    break;
                }
                case "3":{
                    ProductManagement.sortMenu(sc);
                    break;
                }
                case "4":{
                    flag=false;
                    break;
                }
                default:{
                    System.err.println("Please enter a choice from the menu");
                    break;
                }
            }
        }while (flag);
    }
}

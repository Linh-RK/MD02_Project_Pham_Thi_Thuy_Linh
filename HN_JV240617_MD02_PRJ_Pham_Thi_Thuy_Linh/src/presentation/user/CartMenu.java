package presentation.user;

import business.service.CartService;
import business.service.OrderService;

import java.util.Scanner;

import static business.Data.currentUser;
import static business.Data.order;
import static business.service.OrderService.addOrderCheckOut;

public class CartMenu {
    public static void cartMenu(Scanner sc) {
        boolean flag = true;
        do {
            System.out.println("----------------------------------CART MENU-----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Hiển thị danh sách giỏ hàng                                      |");
            System.out.println("|        2. Thêm mới sản phẩm vào giỏ hàng                                   |");
            System.out.println("|        3. Thay đổi số lượng sản phẩm trong giỏ hàng                        |");
            System.out.println("|        4. Xóa sản phẩm trong giỏ hàng                                      |");
            System.out.println("|        5. Xóa toàn bộ sản phẩm trong giỏ hàng                              |");
            System.out.println("|        6. Đặt hàng                                                         |");
            System.out.println("|        7. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Please enter your choice");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    CartService.showAllCart();
                    break;
                }
                case "2":{
                    CartService.addToCart(sc);
                    break;
                }
                case "3":{
                    CartService.changeQtyProductInCart(sc);
                    break;
                }
                case "4":{
                    CartService.deleteProductInCart(sc);
                    break;
                }
                case "5":{
                    CartService.clearCart(sc);
                    break;
                }
                case "6":{
                    addOrderCheckOut(sc,currentUser.getCartList());
                    break;
                }
                case "7":{
                    flag = false;
                    break;
                }
                default:{
                    System.err.println("Please enter a choice from 1 to 7");
                    break;
                }
            }
        }while (flag);
    }
}

package presentation.user;

import business.entity.Order;
import business.service.OrderService;

import java.util.Scanner;

import static business.Data.*;

public class OrderMenu {
    public static void orderMenu(Scanner sc) {
        boolean flag = true;
        do {
            System.out.println("----------------------------------ORDER MENU----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Hiển thị toàn bộ đơn hàng                                        |");
            System.out.println("|        2. Xem chi tiết đơn hàng theo ID                                    |");
            System.out.println("|        3. Hủy đơn hàng                                                     |");
            System.out.println("|        4. Tìm kiếm đơn hàng theo ngày tao a -> b                               |");
            System.out.println("|        5. Tìm kiếm đơn hàng theo trạng thái                                |");
            System.out.println("|        6. Tìm kiếm đơn hàng theo mã đơn hàng                               |");
            System.out.println("|        7. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Please enter your choice");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":{
                    OrderService.showAllOrder(currentUser.getHistoryOrder());
                    break;
                }
                case "2":{
                    OrderService.showDetailById(sc,currentUser.getHistoryOrder());
                    break;
                }
                case "3":{
                    OrderService.cancelOrder(sc,currentUser.getHistoryOrder());
                    break;
                }
                case "4":{
                    OrderService.filterByDate(sc,currentUser.getHistoryOrder());
                    break;
                }
                case "5":{
                    OrderService.findByOrderStatus(sc,currentUser.getHistoryOrder());
                    break;
                }
                case "6":{
                    OrderService.findOrderById(sc,currentUser.getHistoryOrder());
                    break;
                }
                case "7":{
                    flag=false;
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

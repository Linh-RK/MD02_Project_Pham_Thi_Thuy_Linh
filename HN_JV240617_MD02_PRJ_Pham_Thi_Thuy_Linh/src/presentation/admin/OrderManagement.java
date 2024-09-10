package presentation.admin;

import business.entity.Order;
import business.service.OrderService;

import java.util.Scanner;

import static business.Data.orderList;

public class OrderManagement {
    public static void orderManagement(Scanner sc) {
        boolean flag = true;
        do {
            System.out.println("---------------------------------ADMIN ORDER----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Hiển thị danh sách đơn hàng (phân trang)                         |");
            System.out.println("|        2. Tìm kiếm theo mã đơn hàng                                        |");
            System.out.println("|        3. Tìm kiếm theo trạng thái đơn hàng                                |");
            System.out.println("|        4. Xem chi tiết đơn hàng theo mã đơn hàng                           |");
            System.out.println("|        5. Thay đổi trạng thái đơn hàng                                     |");
            System.out.println("|        6. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Please enter your choice");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    OrderService.showAllOrder( orderList);
                    break;
                }
                case "2":{
                    OrderService.findOrderById(sc,orderList);
                    break;
                }
                case "3":{
                    OrderService.findByOrderStatus(sc,orderList);
                    break;
                }
                case "4":{
                    OrderService.orderDetailById(sc,orderList);
                    break;
                }
                case "5":{
                    OrderService.changeOrderStatus(sc,orderList);
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

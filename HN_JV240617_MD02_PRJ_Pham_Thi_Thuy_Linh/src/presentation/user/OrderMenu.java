package presentation.user;

import business.entity.Cart;
import business.entity.Order;
import business.entity.User;
import business.service.OrderService;
import business.ultil.enumList.IOFile;

import java.util.List;
import java.util.Scanner;

import static business.Data.*;

public class OrderMenu {
    public static void orderMenu(Scanner sc) {
        //    List<Category> categoryList= IOFile.readObjectFromFile(IOFile.PATH_CATEGORY);
//    List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
//    List<Order> orderList= IOFile.readObjectFromFile(IOFile.PATH_ORDER);
//    List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
//    User currentUser= IOFile.readObjectFromFile(IOFile.PATH_USERCURRENT);
//    List<Cart> cartList= IOFile.readObjectFromFile(IOFile.PATH_CART);
//    IOFile.writeObjectToFile(categoryList, IOFile.PATH_CATEGORY);
//    IOFile.writeObjectToFile(productList, IOFile.PATH_PRODUCT);
//    IOFile.writeObjectToFile(orderList, IOFile.PATH_ORDER);
//    IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
//    IOFile.writeObjectToFile(userList, IOFile.PATH_USERCURRENT);
//    IOFile.writeObjectToFile(cartList, IOFile.PATH_CART);
        boolean flag = true;
        do {
            System.out.println("----------------------------------ORDER MENU----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Hiển thị toàn bộ đơn hàng                                        |");
            System.out.println("|        2. Xem chi tiết đơn hàng theo ID                                    |");
            System.out.println("|        3. Hủy đơn hàng                                                     |");
            System.out.println("|        4. Tìm kiếm đơn hàng theo ngày tao a -> b                           |");
            System.out.println("|        5. Tìm kiếm đơn hàng theo trạng thái                                |");
            System.out.println("|        6. Tìm kiếm đơn hàng theo mã đơn hàng                               |");
            System.out.println("|        7. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Please enter your choice");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":{
                    List<Order> orderList= IOFile.readObjectFromFile(IOFile.PATH_ORDER);
                    List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
                    currentUser = userList.get(currentIndex);
                    OrderService.showAllOrder(orderList.stream().filter(e->e.getUserId()==currentUser.getUserId()).toList());
                    break;
                }
                case "2":{
                    List<Order> orderList= IOFile.readObjectFromFile(IOFile.PATH_ORDER);
                    List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
                    User curUser = userList.get(currentIndex);
                    System.out.println(currentIndex);
                    List<Order> o = orderList.stream().filter(e->e.getUserId()==curUser.getUserId()).toList();

                    if(o.isEmpty()){
                        System.out.println("cart blank");
                    }else {
                        OrderService.showDetailById(sc, o);
                        break;
                    }
                }
                case "3":{
                    List<Order> orderList= IOFile.readObjectFromFile(IOFile.PATH_ORDER);
                    List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
                    currentUser = userList.get(currentIndex);
                    OrderService.cancelOrder(sc,orderList.stream().filter(e->e.getUserId()==currentUser.getUserId()).toList());
                    break;
                }
                case "4":{
                    List<Order> orderList= IOFile.readObjectFromFile(IOFile.PATH_ORDER);
                    List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
                    currentUser = userList.get(currentIndex);
                    OrderService.filterByDate(sc,orderList.stream().filter(e->e.getUserId()==currentUser.getUserId()).toList());
                    break;
                }
                case "5":{
                    List<Order> orderList= IOFile.readObjectFromFile(IOFile.PATH_ORDER);
                    List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
                    currentUser = userList.get(currentIndex);
                    OrderService.findByOrderStatus(sc,orderList.stream().filter(e->e.getUserId()==currentUser.getUserId()).toList());
                    break;
                }
                case "6":{
                    List<Order> orderList= IOFile.readObjectFromFile(IOFile.PATH_ORDER);
                    List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
                    currentUser = userList.get(currentIndex);
                    OrderService.findOrderById(sc,orderList.stream().filter(e->e.getUserId()==currentUser.getUserId()).toList());
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

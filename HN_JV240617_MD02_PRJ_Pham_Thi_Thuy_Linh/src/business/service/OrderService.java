package business.service;

import business.entity.*;
import business.ultil.enumList.OrderStatus;
import business.ultil.enumList.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.Data.orderList;
import static business.service.ProductService.showAllProduct;
import static business.ultil.enumList.Common.*;
import static presentation.admin.OrderManagement.orderManagement;
import static presentation.user.CartMenu.cartMenu;

public class OrderService {
    public static void addOrderCheckOut(Scanner sc) {
        System.out.println("Check out:");
        Order orderCheckOut = new Order();
        orderCheckOut.inputOrder(sc);
        int n = orderCheckOut.getOrderCartList().size();
        System.out.println("so san pham trong order "+ n);
        System.err.println("Please confirm your order:");
        orderCheckOut.displayOrderDetails();
        System.err.println("Enter your choice (Y/N):");
        String answer = inputAnswer(sc);
        if (answer.equalsIgnoreCase("Y")) {
//            subtract qty from stock
//            orderCheckOut.getOrderCartList().forEach()
            Product p;
           for(Cart cart : orderCheckOut.getOrderCartList()){
               p =cart.getProductInCart();
               p.setProductStock(p.getProductStock() - cart.getQty());
           }
//           khong biet da cap nhat len tren userList chua
            orderList.add(orderCheckOut);
            currentUser.getCartList().clear();
            userList.set(currentIndex, currentUser);
            System.out.println("Order successfully, thank you");
        }else{
            System.out.println("Canceled order");
            cartMenu(sc);
        }

//        showAllCategory();
    }
    public static void showAllOrder(List<Order> orderList){
        if(orderList == null || orderList.isEmpty()){
            System.err.println("Order list is empty");
        }else {
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-10s | %-5s | %-10s | %-10s | %-10s | \n","ID", "Serial","User ID","Total","Status","Created Date");
        orderList.stream().filter(e->e.getUserId()==currentUser.getUserId()).forEach(Order::displayOrder);
        System.out.println("--------------------------------------------------------------------------");
        }
    }
    public static void findOrderById(Scanner sc,List<Order> orderList){
        if(orderList == null || orderList.isEmpty()){
            System.err.println("Order list is empty");
        }else {
            System.out.println("Please enter the order ID");
            int id = inputNum(sc);
            if (orderList.stream().noneMatch(e -> e.getOrderId() == id)) {
                System.err.println("Order ID not found");
            } else {
                System.out.println("Result");
                System.out.println("-------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-10s | %-5s | %-10s | %-10s | %-11s | \n", "ID", "Serial", "User ID", "Total", "Status", "Created Date");
                orderList.stream().filter(e -> e.getOrderId() == id).forEach(Order::displayOrder);
                System.out.println("--------------------------------------------------------------------------");
            }
        }
    }
    public static void findByOrderStatus(Scanner sc,List<Order> orderList){
        if(orderList == null || orderList.isEmpty()){
            System.err.println("Order list is empty");
        }else {
            System.out.println("List if order status");
            for (OrderStatus element : OrderStatus.values())
                System.out.println(element);
            System.out.println("Please enter the order status you want to search");
            String status = inputString(sc);
            if (!isExistInEnum(status)) {
                System.err.println("Order status not found");
            } else {
                System.out.println(status);
                System.out.println("Result");
                System.out.println("--------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-10s | %-5s | %-10s | %-10s | %-10s | \n","ID", "Serial","User ID","Total","Status","Created Date");
                orderList.stream().filter(e -> e.getOrderStatus().name().equalsIgnoreCase(status)).forEach(Order::displayOrder);
                System.out.println("--------------------------------------------------------------------------");
                }
        }
    }

    private static boolean isExistInEnum(String status) {
        return currentUser.getHistoryOrder().stream().map(e->e.getOrderStatus()).toList().stream().anyMatch(e->e.name().equalsIgnoreCase(status));
    }

    public static void orderDetailById(Scanner sc, List<Order> orderList){
        if(orderList == null || orderList.isEmpty()){
            System.err.println("Order list is empty");
        }else {
            System.out.println("Please enter the order ID you want to show detail");
            int id = inputNum(sc);
            if (orderList.stream().noneMatch(e -> e.getOrderId() == id)) {
                System.err.println("Order ID not found");
            } else {
                System.out.println("Result");
                orderList.stream().filter(e -> e.getOrderId() == id).forEach(e -> e.toString());
            }
        }
    };
    public static void cancelOrder(Scanner sc, List<Order> orderList){
        if(orderList == null || orderList.isEmpty()){
            System.err.println("Order list is empty");
        }else {
            System.out.println("Please enter the order ID you want to show change status");
            int id = inputNum(sc);
            if (orderList.stream().noneMatch(e -> e.getOrderId() == id)) {
                System.err.println("Order ID not found");
            } else {
                if (orderList.stream().filter(e -> e.getOrderId() == id).findFirst().get().getOrderStatus().equals(OrderStatus.WAITING)) {
                    System.out.println("Do you want to cancel this order ?");
                    System.out.println("(Y/N)");
                    String answer = inputAnswer(sc);
                    if (answer.equalsIgnoreCase("y")) {
                        orderList.stream().filter(e -> e.getOrderId() == id).findFirst().get().setOrderStatus(OrderStatus.CANCEL);
//                    update lai ca currentUser
//                    currentUser.getHistoryOrder() =  orderList.stream().filter(e->e.getUserId()==currentUser.getUserId()).toList();
                    } else if (answer.equalsIgnoreCase("n")) {
                        orderManagement(sc);
                    }
                }
            }
        }
    }
    public static void changeOrderStatus(Scanner sc, List<Order> orderList){
        if(orderList == null || orderList.isEmpty()){
            System.err.println("Order list is empty");
        }else {
            System.out.println("Please enter the order ID you want to show change status");
            int id = inputNum(sc);
            if (orderList.stream().noneMatch(e -> e.getOrderId() == id)) {
                System.err.println("Order ID not found");
            } else {
                Order orderChange = orderList.stream().filter(e -> e.getOrderId() == id).findFirst().get();
                if (orderChange.getOrderStatus().equals(OrderStatus.WAITING)) {
                    System.out.println("Enter the new order status");
                    System.out.println("1.  CONFIRM");
                    System.out.println("2.  CANCEL");
                    System.out.println("3.  BACK");
                    String answer;
                    do {
                        answer = sc.nextLine();
                    } while (!(answer.equals("1") || answer.equals("2") || answer.equals("3")));
                    switch (answer) {
                        case "1": {
                            orderChange.setOrderStatus(OrderStatus.CONFIRM);
                            System.out.println("Confirm order successfully");
                            break;
                        }
                        case "2": {
                            orderChange.setOrderStatus(OrderStatus.CANCEL);
//                      update lai gio hang
                            List<Cart> cancelCart = orderChange.getOrderCartList();
                            for (Cart cart : cancelCart) {
                                Product p = productList.stream().filter(e -> e.getProductId() == cart.getProductInCart().getProductId()).findFirst().get();
                                p.setProductStock(p.getProductStock() + cart.getQty());
                            }
                            System.out.println("Cancel order successfully");
                            showAllProduct();
                            break;
                        }
                        case "3": {
                            return;
                        }
                        default: {
                            System.err.println("Please enter validate choice!");
                            break;
                        }
                    }
                } else if (orderChange.getOrderStatus().equals(OrderStatus.CONFIRM)) {
                    System.out.println("Enter the new order status");
                    System.out.println("1.  DELIVERY");
                    System.out.println("2.  BACK");
                    String answer;
                    do {
                        answer = sc.nextLine();
                    } while (!(answer.equals("1") || answer.equals("2")));
                    switch (answer) {
                        case "1": {
                            orderChange.setOrderStatus(OrderStatus.DELIVERY);
                            break;
                        }
                        case "2": {
                            return;
                        }
                        default: {
                            System.err.println("Please enter validate choice!");
                            break;
                        }
                    }
                } else if (orderChange.getOrderStatus().equals(OrderStatus.DELIVERY)) {
                    System.out.println("Enter the new order status");
                    System.out.println("1.  SUCCESS");
                    System.out.println("2.  DENIED");
                    System.out.println("3.  BACK");
                    String answer;
                    do {
                        answer = sc.nextLine();
                    } while (!(answer.equals("1") || answer.equals("2") || answer.equals("3")));
                    switch (answer) {
                        case "1": {
                            orderChange.setOrderStatus(OrderStatus.SUCCESS);
                            System.out.println("Order successfully");
                            break;
                        }
                        case "2": {
                            orderChange.setOrderStatus(OrderStatus.DENIED);
                            List<Cart> cancelCart = orderChange.getOrderCartList();
                            for (Cart cart : cancelCart) {
                                Product p = productList.stream().filter(e -> e.getProductId() == cart.getProductInCart().getProductId()).findFirst().get();
                                p.setProductStock(p.getProductStock() + cart.getQty());
                            }
                            System.out.println("Order be delivery failed");
                            showAllProduct();
                            break;
                        }
                        case "3": {
                            return;
                        }
                        default: {
                            System.err.println("Please enter validate choice!");
                            break;
                        }
                    }
                }
            }
        }
    }
    public static void filterByDate(Scanner sc, List<Order> orderList){
        if(orderList == null || orderList.isEmpty()){
            System.err.println("Order list is empty");
        }else {
            System.out.println("Enter the start date you want to search (yyyy-mm-dd)");
            LocalDate startDate = inputDate(sc);
            System.out.println("Enter the end date you want to search (yyyy-mm-dd)");
            LocalDate endDate = inputDate(sc);

            List<Order> resultOrder = orderList.stream().filter(e -> {
                return e.getOrderCreateDate().isAfter(startDate) && e.getOrderCreateDate().isBefore(endDate);
            }).toList();
            if(resultOrder.isEmpty()){
                System.err.println("None order found");
            }else {
                System.out.println("Result");
                System.out.println("--------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-10s | %-5s | %-10s | %-10s | %-11s | \n", "ID", "Serial", "User ID", "Total", "Status", "Created Date");
                resultOrder.forEach(Order::displayOrder);
                System.out.println("--------------------------------------------------------------------------");
            }
        }
    }

    public static void showDetailById(Scanner sc,List<Order> orderList) {
        if(orderList == null || orderList.isEmpty()){
            System.err.println("Order list is empty");
        }else {
            showAllOrder(orderList);
            System.out.println("Enter the ID order you want to show detail");
            int id = inputNum(sc);
            if (orderList.stream().noneMatch(e -> e.getOrderId() == id)) {
                System.err.println("Order ID not found");
            }else {
                System.out.println("Result");
                orderList.stream().filter(e -> e.getOrderId() == id).forEach(Order::displayOrderDetails);
                }
        }
    }


}

package business.entity;

import business.ultil.enumList.OrderStatus;

import java.time.LocalDate;
import java.util.*;

import static business.Data.*;
import static business.service.AddressService.showAllAddress;
import static business.ultil.enumList.Common.*;

public class Order {
    private int orderId;
    private String orderSerial;
    private int userId;
    private List<Cart> orderCartList;
    private int orderTotal;
    private OrderStatus orderStatus;
    private Address orderReceiveAddress;
    private String orderReceivePhone;
    private String orderReceiveName;
    private LocalDate orderCreateDate;
    private LocalDate orderUpdatedDate;

    public Order() {}

    public Order(int orderId, String orderSerial, int userId, List<Cart> orderCartList, int orderTotal, OrderStatus orderStatus, Address orderReceiveAddress, String orderReceivePhone, String orderReceiveName, LocalDate orderCreateDate, LocalDate orderUpdatedDate) {
        this.orderId = orderId;
        this.orderSerial = orderSerial;
        this.userId = userId;
        this.orderCartList = orderCartList;
        this.orderTotal = orderTotal;
        this.orderStatus = orderStatus;
        this.orderReceiveAddress = orderReceiveAddress;
        this.orderReceivePhone = orderReceivePhone;
        this.orderReceiveName = orderReceiveName;
        this.orderCreateDate = orderCreateDate;
        this.orderUpdatedDate = orderUpdatedDate;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Cart> getOrderCartList() {
        return orderCartList;
    }

    public void setOrderCartList(List<Cart> orderCartList) {
        this.orderCartList = orderCartList;
    }

    public int getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Address getOrderReceiveAddress() {
        return orderReceiveAddress;
    }

    public void setOrderReceiveAddress(Address orderReceiveAddress) {
        this.orderReceiveAddress = orderReceiveAddress;
    }

    public String getOrderReceivePhone() {
        return orderReceivePhone;
    }

    public void setOrderReceivePhone(String orderReceivePhone) {
        this.orderReceivePhone = orderReceivePhone;
    }

    public String getOrderReceiveName() {
        return orderReceiveName;
    }

    public void setOrderReceiveName(String orderReceiveName) {
        this.orderReceiveName = orderReceiveName;
    }

    public LocalDate getOrderCreateDate() {
        return orderCreateDate;
    }

    public void setOrderCreateDate(LocalDate orderCreateDate) {
        this.orderCreateDate = orderCreateDate;
    }

    public LocalDate getOrderUpdatedDate() {
        return orderUpdatedDate;
    }

    public void setOrderUpdatedDate(LocalDate orderUpdatedDate) {
        this.orderUpdatedDate = orderUpdatedDate;
    }

    public void inputOrder(Scanner sc){
        this.orderId = autoOrderId();
        this.orderSerial = inputOrderSerial(sc);//random
        this.userId = currentUser.getUserId();
        this.orderCartList = inputCartList();//
        this.orderStatus = OrderStatus.WAITING;
        this.orderTotal = inputOrderTotal(sc);//
        this.orderCreateDate = currentDate();
        this.orderReceiveAddress = addAddress(sc);
        this.orderUpdatedDate = currentDate();
    }

    private Address addAddress(Scanner sc) {
        showAllAddress(currentUser);
        Address newAddress = new Address();
        if(currentUser.getUserAddressList().isEmpty()) {
            System.err.println("Address list is empty. Please add new address");
            newAddress.inputAddress(sc);
            currentUser.getUserAddressList().add(newAddress);
//                    update userList
            userList.set(currentIndex, currentUser);
            System.out.println("Address list");
            currentUser.getUserAddressList().forEach(Address::displayAddress);
            return newAddress;
        }else {
            System.out.println((currentUser.getUserAddressList().getLast().getIdAddress() + 1) + ". Add new address");
            System.out.println("Enter your choice:");
            int addressId = inputNum(sc);
            if(addressId == currentUser.getUserAddressList().getLast().getIdAddress() + 1) {
                newAddress.inputAddress(sc);
                currentUser.getUserAddressList().add(newAddress);
//                    update userList
                userList.set(currentIndex, currentUser);
                System.out.println("Address list");
                currentUser.getUserAddressList().forEach(Address::displayAddress);
                return newAddress;
            }else{
                if(currentUser.getUserAddressList().stream().anyMatch(e->e.getIdAddress()==addressId)) {
                    return currentUser.getUserAddressList().stream().filter(e->e.getIdAddress()==addressId).findFirst().get();
                }else {
                    System.err.println("Address not found. Please try again");
                }
            }
        }
        return newAddress;
    }

    public List<Cart> inputCartList() {
        List<Cart> orderProductList = currentUser.getCartList();
//        currentUser.getCartList().clear();
//        userList.set(currentIndex, currentUser);
        return orderProductList;
    }

    public String inputPhone(Scanner sc) {
        System.out.println("Enter Phone Number: ");
        do{
            String phoneNumber = sc.nextLine();
            if(phoneNumber.isBlank()){
                System.err.println("Data can't be blank. Please try again.");
            }else {
                if(!phoneNumber.matches("/((09|03|07|08|05)+([0-9]{8})\\b)/g")){
                    System.err.println("Phone number is incorrect format. Please try again.");
                } else {
                    return phoneNumber;
                }
            }
        }while(true);
    }
    public int autoOrderId() {
//        List<Order> orderList= IOFile.readObjectFromFile("src/business/data/order.txt");
        return orderList.stream().map(Order::getOrderId).max(Comparator.comparingInt(Integer::intValue)).orElse(0)+1;
    }

    public String inputOrderSerial(Scanner sc) {
        Random r = new Random();
//        List<Order> orderList= IOFile.readObjectFromFile("src/business/data/order.txt");

        do {
            StringBuilder orderSerial = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                orderSerial.append(r.nextInt(0, 9));
            }
            return orderSerial.toString();
        }while (orderList.stream().anyMatch(e->e.getOrderSerial().contentEquals(orderSerial)));
    }

    public User inputUser(Scanner sc) {
//        List<User> userCurrent= IOFile.readObjectFromFile("/Users/phamlinh/Desktop/PRJ_MD02/src/business/data/currentUser.txt");
        return currentUser;
    }

    public int inputOrderTotal(Scanner sc) {
//        List<Order> orderList= IOFile.readObjectFromFile("src/business/data/order.txt");
        return orderList.stream().mapToInt(Order::getOrderTotal).sum();
    }

    public void displayOrder() {
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("| %-5d | %-10s | %-7s | %-10d | %-10s |%-13s | \n", this.orderId,this.orderSerial,this.userId,this.orderTotal,this.orderStatus,this.orderCreateDate);

    }
    public  void displayOrderDetails() {
        System.out.println("YOUR ORDER:");
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-25s | %-34d |\n", "ID: ", this.orderId);
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-25s | %-34s |\n", "Serial: ", this.orderSerial);
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-25s | %-34s |\n", "Product List: ", "");
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-5s | %-10s | %-5s | %-15s | %-15s |\n", "ID", "Product", "Qty", "Price", "Total");
        this.orderCartList.forEach(Cart::displayCart);
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-25s | %-34d |\n", "Total: ", this.orderTotal);
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-25s | %-34s |\n", "Receive Address: ", this.orderReceiveAddress.getAddress());
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-25s | %-34s |\n", "Receive person: ", this.orderReceiveAddress.getReceiver());
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-25s | %-34s |\n", "Receive phone number: ", this.orderReceiveAddress.getPhoneNumber());
        System.out.println("------------------------------------------------------------------");
    }
    @Override
    public String toString() {
        return "Order{" +"\n"+
                "orderId=" + orderId + "\n"+
                ", orderSerial='" + orderSerial + '\'' +"\n"+
                ", userId=" + userId +"\n"+
                ", orderCartList=" + orderCartList +"\n"+
                ", orderTotal=" + orderTotal +"\n"+
                ", orderStatus=" + orderStatus +"\n"+
                ", orderReceiveAddress=" + orderReceiveAddress +"\n"+
                ", orderReceivePhone='" + orderReceivePhone + '\'' +"\n"+
                ", orderReceiveName='" + orderReceiveName + '\'' +"\n"+
                ", orderCreateDate=" + orderCreateDate +"\n"+
                ", orderUpdatedDate=" + orderUpdatedDate +"\n"+
                '}';
    }
}

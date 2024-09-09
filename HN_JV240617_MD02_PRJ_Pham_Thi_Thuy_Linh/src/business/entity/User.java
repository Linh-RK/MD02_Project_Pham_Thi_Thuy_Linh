package business.entity;

import business.ultil.enumList.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.ultil.enumList.Common.*;

public class User {
    private Role role;                      //1
    private int userId;                     //2
    private String userName;                //3
    private String userEmail;               //4
    private String userFullName;            //5
    private boolean userStatus =true;             //6
    private String userPassword;            //7
    private String userPhoneNumber;         //8
    private List<Address> userAddressList=new ArrayList<>();      //9
    private LocalDate userCreatedDate;      //10
    private LocalDate userUpdatedDate;      //11
    private List<Cart> cartList= new ArrayList<>();            //13
    private List<Product> wishList= new ArrayList<>();            //13

    public User() {
    }

    public User(Role role, int userId, String userName, String userEmail, String userFullName, boolean userStatus,
                String userPassword, String userPhoneNumber, LocalDate userCreatedDate, LocalDate userUpdatedDate) {
        this.role = role;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userFullName = userFullName;
        this.userStatus = userStatus;
        this.userPassword = userPassword;
        this.userPhoneNumber = userPhoneNumber;
        this.userCreatedDate = userCreatedDate;
        this.userUpdatedDate = userUpdatedDate;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public List<Address> getUserAddressList() {
        return userAddressList;
    }

    public void setUserAddressList(List<Address> userAddressList) {
        this.userAddressList = userAddressList;
    }

    public LocalDate getUserCreatedDate() {
        return userCreatedDate;
    }

    public void setUserCreatedDate(LocalDate userCreatedDate) {
        this.userCreatedDate = userCreatedDate;
    }

    public LocalDate getUserUpdatedDate() {
        return userUpdatedDate;
    }

    public void setUserUpdatedDate(LocalDate userUpdatedDate) {
        this.userUpdatedDate = userUpdatedDate;
    }

    public List<Order> getHistoryOrder() {
        return orderList.stream().filter(e->e.getUserId()==currentUser.getUserId()).toList();
    }

    public void setHistoryOrder(List<Order> historyOrder) {
        return;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public List<Product> getWishList() {
        return wishList;
    }

    public void setWishList(List<Product> wishList) {
        this.wishList = wishList;
    }

    public void signIn(Scanner sc) {
        this.role = Role.USER;
        this.userId = autoUserId(sc);
//        this.userName = inputUserName(sc);
        this.userEmail = inputUserEmail(sc);
//        this.userFullName = inputUserFullName(sc);
        this.userStatus = true;
        this.userPassword = inputUserPassword(sc);
//        this.userPhoneNumber = inputUserPhoneNumber(sc);
//        this.userAddress = inputUserAddress(sc);
        this.userCreatedDate = currentDate();
//        this.userUpdatedDate = inputUserUpdatedDate(sc);
//        this.historyOrder = null;
//        this.cartList = null;
    }
//    public void login(Scanner sc) {
//        this.userEmail = inputUserEmail(sc);
//        this.userPassword = inputUserPassword(sc);
//    }
    public void updateUserInfo(Scanner sc) {
        this.userName = inputUserName(sc);
        this.userEmail = inputUserEmail(sc);
        System.out.println("Please enter your full name: ");
        this.userFullName = inputString(sc);
        this.userStatus = inputStatus(sc);
        this.userPassword = inputUserPassword(sc);
        this.userPhoneNumber = inputPhoneNumber(sc);
//        this.userCreatedDate = inputUserCreatedDate(sc);
        this.userUpdatedDate = currentDate();
    }

    public void updatePassword(Scanner sc) {
        System.out.println("Please enter your old password: ");
        String oldPassword = inputString(sc);
        if(!currentUser.getUserPassword().equals(oldPassword)) {
            System.out.println( "Password doesn't match!" );
//            quen mat khau

        }else{
            System.out.println("Please enter your new password: ");
            String newPassword = inputUserPassword(sc);
            currentUser.setUserPassword(newPassword);
            userList.set(currentIndex,currentUser);
            System.out.println("Password updated!");
        }
    }


    private int autoUserId(Scanner sc) {
        return userList.stream().map(User::getUserId).max(Comparator.comparingInt(Integer::intValue)).orElse(0)+1;
    }
//"   Tối thiểu 6 ký tự, tối đa 100 kí tự
//    Không có ký tự đặc biệt, không trùng lặp"
    private String inputUserName(Scanner sc) {
        System.out.println("Enter user name: ");
        do {
            String userName = sc.nextLine();
            if(userName.length() <6 || userName.length() > 100) {
                System.err.println("Username must be between 6 and 100 characters");
            }else {
                if(!userName.matches("^[a-zA-Z0-9]*$")) {
                    System.err.println("Username must contain only letters and numbers");
                }else{
                    if(userList.stream().anyMatch(user -> user.getUserName().equals(userName))) {
                        System.err.println("Username is already in use");
                    }else {
                        return userName;
                    }
                }
            }
        }while (true);
    }

    private String inputUserEmail(Scanner sc) {
        System.out.println("Enter email address: ");
        do{
            String email = sc.nextLine();
            if(email.isBlank()){
                System.err.println("Data can't be blank. Please try again.");
            }else {
                if(!email.matches("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$")){
                    System.err.println("Email is incorrect format. Please try again.");
                } else {
                    return email;
                }
            }
        }while(true);
    }
    private String inputUserPassword(Scanner sc) {
        System.out.println("Enter password: ");
        do{
            String password = sc.nextLine();
            if(password.isBlank()){
                System.err.println("Data can't be blank. Please try again.");
            }else {
//                if(!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{6,}$")){
                if(!password.matches("^(?=.*[a-zA-Z])(?=.*\\d).{6,}$")){
                    System.err.println("Password is incorrect format. Please try again.");
                } else {
                    return password;
                }
            }
        }while(true);
    }


    public static void displayDetails(User user) {
        System.out.println("ID: " + user.getUserId());
        System.out.println("Name: " + user.getUserName());
        System.out.println("Email: " + user.getUserEmail());
        System.out.println("FullName: " + user.getUserFullName());
        System.out.println("Status: " + user.getUserStatus());
        System.out.println("Password: " + user.getUserPassword());
        System.out.println("PhoneNumber: " + user.getUserPhoneNumber());
        System.out.println("CreatedDate: " + user.getUserCreatedDate());
        System.out.println("UpdatedDate: " + user.getUserUpdatedDate());
        if(user.getUserAddressList()==null || user.getUserAddressList().isEmpty()){
            System.out.println("Address list is empty");
        }else{
        System.out.println("AddressList: ");
        user.getUserAddressList().forEach(Address::displayAddress);
        }

        if(user.getHistoryOrder().isEmpty()||user.getHistoryOrder()==null){
            System.out.println("History order is empty");
        }else{
            System.out.println("historyOrder: ");
            user.getHistoryOrder().forEach(Order::displayOrder);
        }

        if(user.getCartList()==null||user.getCartList().isEmpty()){
            System.out.println("Cart is empty");
        }else{
            System.out.println("cartList: ");
            user.getCartList().forEach(Cart::displayCart);
        }

    }
    public  void displayUser() {
        System.out.println("--------------------------------------------------------------");
        System.out.printf("| %-5d | %-20s | %-20s | %-10s | %-20s | %-20s |\n",this.userId,this.userName,this.userEmail,this.userStatus?"Active":"Block",this.userPhoneNumber,this.userCreatedDate);
    }
    public static void switchUserStatus(Scanner sc, User user){
        user.setUserStatus(!user.getUserStatus());
    }
}

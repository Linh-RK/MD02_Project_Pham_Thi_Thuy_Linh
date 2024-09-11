package presentation;

import business.entity.*;
import business.service.ProductService;
import business.ultil.enumList.Common;
import business.ultil.enumList.IOFile;
import business.ultil.enumList.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.ultil.enumList.Common.inputString;
import static presentation.admin.AdminMenu.Admin_Menu;
import static presentation.user.UserMenu.UserMenuDisplay;

public class Main_Menu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("-------------------------------------MENU-------------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Đăng nhập                                                        |");
            System.out.println("|        2. Đăng ký                                                          |");
            System.out.println("|        3. Hiển thị sản phẩm được bán                                       |");
            System.out.println("|        4. Quên mật khẩu                                                    |");
            System.out.println("|        5. Thoát                                                            |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Please enter your choice");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    logIn(sc);
                    break;
                }
                case "2":{
                    user.signIn(sc);
                    break;
                }
                case "3":{
                    ProductService.showAllProduct();
                    break;
                }
                case "4":{
                    forgotPassword(sc);
                    break;
                }
                case "5":{
                    System.exit(0);
                    break;
                }
                default:{
                    System.out.println("Please enter a choice form 1 -> 5");
                    break;
                }
            }
        }while (true);
    }

    private static LocalDate currentdate() {
        LocalDate today = LocalDate.now( );
        System.out.println(today);
        return today;
    }
    //    List<Category> categoryList= IOFile.readObjectFromFile(IOFile.PATH_CATEGORY);
//    List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
//    List<Order> orderList= IOFile.readObjectFromFile(IOFile.PATH_ORDER);
//    List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
//    List<User> currentUser= IOFile.readObjectFromFile(IOFile.PATH_CURRENTUSER);
//    List<Cart> cartList= IOFile.readObjectFromFile(IOFile.PATH_CART);
//    IOFile.writeObjectToFile(categoryList, IOFile.PATH_CATEGORY);
//    IOFile.writeObjectToFile(productList, IOFile.PATH_PRODUCT);
//    IOFile.writeObjectToFile(orderList, IOFile.PATH_ORDER);
//    IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
//    IOFile.writeObjectToFile(cartList, IOFile.PATH_CART);
    private static void logIn(Scanner sc) {

        System.out.println("Please enter your email");
        String email = inputString(sc);
        System.out.println("Please enter your password");
        String password = inputString(sc);
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        if(userList.stream().noneMatch(e->e.getUserEmail().equals(email))){
            System.err.println("Email or password incorrect");
        }else{
            if(!userList.stream().filter(e->e.getUserEmail().equals(email)).findFirst().get().getUserPassword().equals(password)){
                System.err.println("Email or password incorrect");
            } else {

                currentUser = userList.get(currentIndex);
                currentUser=userList.stream().filter(e->e.getUserEmail().equals(email)).findFirst().get();
                for (int i = 0; i < userList.size(); i++) {
                    if(userList.get(i).getUserEmail().equals(email)){
                        currentIndex=i;
                        break;
                    }
                }
//                Nho luu currentIndex
                System.out.println("Login Successful");
                if(currentUser.getRole()==Role.ADMIN){
                    Admin_Menu(sc);
                }else if(currentUser.getRole()==Role.USER){
                    if(!currentUser.getUserStatus()){
                        System.err.println("Your account has been locked");
                    }else {
                        UserMenuDisplay(sc);
                    }
                }
            }
        }
    }

    private static void forgotPassword(Scanner sc) {

    }
}

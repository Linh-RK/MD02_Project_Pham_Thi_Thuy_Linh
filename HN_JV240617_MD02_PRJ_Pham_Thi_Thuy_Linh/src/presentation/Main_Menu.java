package presentation;

import business.entity.Category;
import business.entity.Order;
import business.entity.Product;
import business.entity.User;
import business.service.ProductService;
import business.ultil.enumList.Common;
import business.ultil.enumList.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.ultil.enumList.Common.inputString;
import static presentation.admin.AdminMenu.Admin_Menu;
import static presentation.user.UserMenu.UserMenuDisplay;

public class Main_Menu {
    static {
        Category category1 = new Category(1,"cate1",true, "description1");
        Category category2 = new Category(2,"cate2",true, "description2");
        Category category3 = new Category(3,"cate3",true, "description2");
        Category category4 = new Category(4,"cate4",true, "description4");
        categoryList.add(category1);
        categoryList.add(category2);
        categoryList.add(category3);
        categoryList.add(category4);

        Product product1 = new Product(1,"p1",category1,30,10,"description p","pink",LocalDate.of(2023, 5, 1),"M",null,false);
        Product product2 = new Product(2,"p2",category1,80,10,"description p","red",LocalDate.of(2023, 5, 1),"S",null,false);
        Product product3 = new Product(3,"p3",category2,50,10,"description p","pink",LocalDate.of(2023, 5, 1),"M",null,false);
        Product product4 = new Product(4,"p4",category3,40,10,"description p","blue",LocalDate.of(2023, 5, 1),"XL",null,true);
        Product product5 = new Product(5,"p5",category4,70,10,"description p","pink",LocalDate.of(2023, 5, 1),"L",null,false);
        Product product6 = new Product(6,"p6",category4,90,10,"description p","black",LocalDate.of(2023, 5, 1),"M",null,true);
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
        productList.add(product6);


        User admin = new User(Role.ADMIN, 1, "user1", "admin@gmail.com", "Nguyen Van Admin", true, "123abc", "0987654321", null, LocalDate.of(2023, 5, 1));
        User user2 = new User(Role.USER, 2,"user2","user2@gmail.com","Nguyen Van 1",true,"123abc","0987654321",null,LocalDate.of(2023, 5, 1) );
        User user3 = new User(Role.USER, 3,"user3","user3@gmail.com","Nguyen Van 2",true,"123abc","0987654321",null,LocalDate.of(2023, 5, 1) );
        User user4 = new User(Role.USER, 4,"user4","user4@gmail.com","Nguyen Van 3",true,"123abc","0987654321",null,LocalDate.of(2023, 5, 1) );
        User user5 = new User(Role.USER, 5,"user5","user5@gmail.com","Nguyen Van 4",true,"123abc","0987654321",null,LocalDate.of(2023, 5, 1));
        userList.add(admin);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);

    }
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

    private static void logIn(Scanner sc) {
        System.out.println("Please enter your email");
        String email = inputString(sc);
        System.out.println("Please enter your password");
        String password = inputString(sc);
        if(userList.stream().noneMatch(e->e.getUserEmail().equals(email))){
            System.err.println("Email or password incorrect");
        }else{
            if(!userList.stream().filter(e->e.getUserEmail().equals(email)).findFirst().get().getUserPassword().equals(password)){
                System.err.println("Email or password incorrect");
            } else {
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
                }else{
                    UserMenuDisplay(sc);
                }
            }
        }
    }

    private static void forgotPassword(Scanner sc) {
    }
}

package presentation;

import business.entity.*;
import business.service.ProductService;
import business.ultil.enumList.Common;
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
    static {
        Category cate1 = new Category(1,"cate1",true, "description1");
        Category cate2 = new Category(2,"cate2",true, "description2");
        Category cate3 = new Category(3,"cate3",true, "description2");
        Category cate4 = new Category(4,"cate4",true, "description4");
        categoryList.add(cate1);
        categoryList.add(cate2);
        categoryList.add(cate3);
        categoryList.add(cate4);

        Product p1 = new Product(1,"p1",cate1,30,10,"description p","pink",LocalDate.of(2023, 5, 1),"M",null,false);
        Product p2 = new Product(2,"p2",cate1,80,10,"description p","red",LocalDate.of(2023, 5, 1),"S",null,false);
        Product p3 = new Product(3,"p3",cate2,50,10,"description p","pink",LocalDate.of(2023, 5, 1),"M",null,false);
        Product p4 = new Product(4,"p4",cate3,40,10,"description p","blue",LocalDate.of(2023, 5, 1),"XL",null,true);
        Product p5 = new Product(5,"p5",cate4,70,10,"description p","pink",LocalDate.of(2023, 5, 1),"L",null,false);
        Product p6 = new Product(6,"p6",cate4,90,10,"description p","black",LocalDate.of(2023, 5, 1),"M",null,true);
        productList.add(p1);
        productList.add(p2);
        productList.add(p3);
        productList.add(p4);
        productList.add(p5);
        productList.add(p6);

        Address ad1= new Address(1,1,"HN","0987654321","linh");
        Address ad2= new Address(2,1,"TB","0987654321","huong");
        Address ad3= new Address(3,1,"QN","0987654321","trung");
        Address ad4= new Address(4,2,"ND","0987654321","vi");
        Address ad5= new Address(5,2,"TQ","0987654321","vu");
        Address ad6= new Address(6,3,"BN","0987654321","ha");
        Address ad7= new Address(7,3,"BG","0987654321","lien");
        Address ad8= new Address(8,4,"HN","0987654321","duyen");
        Address ad9= new Address(9,4,"PT","0987654321","ngoc");
        addressList.add(ad1);
        addressList.add(ad2);
        addressList.add(ad3);
        addressList.add(ad4);
        addressList.add(ad4);
        addressList.add(ad5);
        addressList.add(ad6);
        addressList.add(ad7);
        addressList.add(ad8);
        addressList.add(ad9);

        List<Address> addressList1 = new ArrayList<>(List.of(ad1,ad2,ad3));
        List<Address> addressList2 = new ArrayList<>(List.of(ad4,ad5));
        List<Address> addressList3 = new ArrayList<>(List.of(ad6,ad7));
        List<Address> addressList4 = new ArrayList<>(List.of(ad8,ad9));
        List<Address> addressList5 = new ArrayList<>();

        Cart c1 = new Cart(1,p1,2);
        Cart c2 = new Cart(1,p3,2);
        Cart c3 = new Cart(1,p2,2);
        Cart c4 = new Cart(2,p4,2);
        Cart c5 = new Cart(2,p1,2);
        Cart c6 = new Cart(3,p3,2);
        Cart c7 = new Cart(4,p2,1);
        List<Cart> cartList1 = new ArrayList<>(List.of(c1,c2,c3));
        List<Cart> cartList2 = new ArrayList<>(List.of(c4,c5));
        List<Cart> cartList3 = new ArrayList<>(List.of(c6));
        List<Cart> cartList4 = new ArrayList<>(List.of(c7));
        List<Cart> cartList5 = new ArrayList<>();

        User admin = new User(Role.ADMIN, 6, "admin", "admin@gmail.com", "Nguyen Van Admin", true, "123abc", "0987654321", null, LocalDate.of(2023, 5, 1),null,null);
        User user1 = new User(Role.USER, 1, "user1", "user1@gmail.com", "Nguyen Van 1", true, "123abc", "0987654321", null, LocalDate.of(2023, 5, 1),cartList1,addressList1);
        User user2 = new User(Role.USER, 2,"user2","user2@gmail.com","Nguyen Van 2",true,"123abc","0987654321",null,LocalDate.of(2023, 5, 1),cartList2,addressList2 );
        User user3 = new User(Role.USER, 3,"user3","user3@gmail.com","Nguyen Van 3",true,"123abc","0987654321",null,LocalDate.of(2023, 5, 1),cartList3,addressList3);
        User user4 = new User(Role.USER, 4,"user4","user4@gmail.com","Nguyen Van 4",true,"123abc","0987654321",null,LocalDate.of(2023, 5, 1) ,cartList4,addressList4);
        User user5 = new User(Role.USER, 5,"user5","user5@gmail.com","Nguyen Van 5",true,"123abc","0987654321",null,LocalDate.of(2023, 5, 1),cartList5,addressList5);
        userList.add(admin);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
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

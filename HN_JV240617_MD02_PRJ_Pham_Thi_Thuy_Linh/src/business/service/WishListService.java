package business.service;

import business.entity.Product;
import business.entity.User;

import java.util.Scanner;

import static business.Data.*;
import static business.service.ProductService.showAllProduct;
import static business.ultil.enumList.Common.inputNum;

public class WishListService {
    public static void showAllWishList(Scanner sc, User user){
        if(user.getWishList().isEmpty()){
            System.err.println("WishList is empty");
        }else {
            System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n ", "ID", "Product","Category","Price","Stock","Color","Size","Created Date","Updated Date","Wish List");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");

            user.getWishList().forEach(Product::display);
        }
    }
    public static void addNewProductIWishList(Scanner sc,User user){
        showAllProduct();
        System.out.println("Enter product ID you want to add: ");
        int id = inputNum(sc);
        if(productList.stream().noneMatch(e->e.getProductId()==id)){
            System.err.println("The product ID you want to add does not exist");
        }else{
            if(currentUser.getWishList().stream().anyMatch(e->e.getProductId()==id)){
                System.err.println("The product ID you want to add already exists");
            }else {
                Product product = productList.stream().filter(e -> e.getProductId() == id).findFirst().get();
                user.getWishList().add(product);
                userList.set(currentIndex, user);
                System.out.println("Add product in wish list successfully");
            }
        }
        showAllWishList(sc,currentUser);
    }
    public static void deleteProductInWisList(Scanner sc, User user){
        System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n ", "ID", "Product","Category","Price","Stock","Color","Size","Created Date","Updated Date","Wish List");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        user.getWishList().forEach(Product::display);
        System.out.println("Enter product ID you want to delete: ");
        int id = inputNum(sc);
        if(user.getWishList().stream().noneMatch(e->e.getProductId()==id)){
            System.err.println("The product ID you want to delete does not exist");
        }else{
            Product product = user.getWishList().stream().filter(e->e.getProductId()==id).findFirst().get();
            user.getWishList().remove(product);
            userList.set(currentIndex,user);
            System.out.println("Delete product in wish list successfully");
        }
        showAllWishList(sc,currentUser);
    }
}

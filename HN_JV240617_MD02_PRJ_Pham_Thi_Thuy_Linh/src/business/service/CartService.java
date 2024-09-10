package business.service;

import business.entity.Cart;
import business.entity.Product;
import business.entity.User;

import java.util.Scanner;

import static business.Data.*;
import static business.service.ProductService.showAllProduct;
import static business.ultil.enumList.Common.inputNum;

public class CartService {
public static void showAllCart(User user) {
   if(user.getCartList()==null||user.getCartList().isEmpty()) {
      System.err.println("Cart is empty");
   }else{
      System.out.println("------------------------------------------------------------------");
      System.out.printf("| %-5s | %-10s | %-5s | %-15s | %-15s |\n","ID", "Product", "Qty", "Price", "Total");
      user.getCartList().forEach(Cart::displayCart);
      System.out.println("------------------------------------------------------------------");
   }

}
public static void addToCart(Scanner sc,User user) {
   Cart cartNew = new Cart();
   int productID;
   showAllProduct();
   System.out.println("Enter product ID you want to add to cart: ");
   do {
       productID = inputNum(sc);
      int finalProductID = productID;
      if(productList.stream().noneMatch(p->p.getProductId()== finalProductID)) {
            System.err.println("Product ID does not match");
      }else {
//         ton tai id nhap vao
         int finalProductID1 = productID;
         Product productAdd = productList.stream().filter(p->p.getProductId()== finalProductID1).toList().getFirst();
//         if product want to add to cart not in cart
         int quantity;
         if(currentUser.getCartList().stream().noneMatch(e->e.getProductInCart().getProductId()==finalProductID1)){
             quantity= inputQty(sc,0,productAdd.getProductStock());
            cartNew.setProductInCart(productAdd);
            cartNew.setQty(quantity);
            currentUser.getCartList().add(cartNew);
         }else{
//            if product want to add to cart already exist in cart
            Cart cartUpdate = currentUser.getCartList().stream().filter(e->e.getProductInCart().getProductId()==finalProductID1).toList().getFirst();
            quantity= inputQty(sc,cartUpdate.getQty(), productAdd.getProductStock());
            cartUpdate.setQty(cartUpdate.getQty()+quantity);
         }
         currentUser.setCartList(currentUser.getCartList());
         userList.set(currentIndex,currentUser);
         currentUser=userList.get(currentIndex);
         System.out.println("Added product to the cart");
         showAllCart(currentUser);
         return;
      }
   }while(true);
}

   private static int inputQty(Scanner sc, int i, int productStock) {
      System.out.println("Enter product Qty: ");
   do {
      int addQty = inputNum(sc);
      if(addQty + i> productStock){
         System.err.println("SORRY! We don't have enough product stock");
      }else{
         return addQty;
      }
   }while(true);
   }

public static void changeQtyProductInCart(Scanner sc, User user) {
   System.out.println("Enter product ID you want to edit: ");
      int productID = inputNum(sc);
      if(currentUser.getCartList().stream().noneMatch(c->c.getProductInCart().getProductId()==productID)){
         System.err.println("Product does not exist in cart");
      }else{
         Cart productOldCart = currentUser.getCartList().stream().filter(c->c.getProductInCart().getProductId()==productID).findFirst().get();
         Product productOld = productOldCart.getProductInCart();
//              Display
         System.out.println("Product you want to edit: ");
         System.out.println("------------------------------------------------------------------");
         System.out.printf("| %-5s | %-10s | %-5s | %-15s | %-15s |\n","ID", "Product", "Qty", "Price", "Total");
         productOldCart.displayCart();
         System.out.println("------------------------------------------------------------------");
         System.out.println("Enter new quantity of product you want to edit: ");
         int quantity = inputNum(sc);

         System.out.println("New cart:");
         productOldCart.setQty(quantity);
         System.out.println("Edited successfully");
         showAllCart(currentUser);

         return;
      }
}
public static void deleteProductInCart(Scanner sc,User user) {
   showAllCart(currentUser);
   System.out.println("Enter product ID you want to delete: ");
         int productID = inputNum(sc);
         if(user.getCartList().stream().noneMatch(c->c.getProductInCart().getProductId()==productID)) {
            System.err.println("Product does not exist in cart");
            return;
         }else{
            Cart productOldCart = user.getCartList().stream().filter(c->c.getProductInCart().getProductId()==productID).toList().getFirst();
            Product productOld = productOldCart.getProductInCart();
//              Update Cart
            currentUser.getCartList().remove(productOldCart);
//              Update Product List
//            productOld.setProductStock(productOld.getProductStock() + productOldCart.getQty());

            System.out.println("Delete successfully");
            showAllCart(currentUser);
            return;
         }
}
public static void clearCart(Scanner sc,User user) {
   if(user.getCartList().isEmpty()) {
      System.err.println("Cart is empty");
   }else{
      userList.stream().filter(e->e.getUserId()== currentUser.getUserId()).findFirst().get().getCartList().clear();
      showAllCart(currentUser);
   }
}
}

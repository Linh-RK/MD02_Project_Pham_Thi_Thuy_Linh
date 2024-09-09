package business.service;

import business.entity.Cart;
import business.entity.Product;
import business.entity.User;

import java.util.Scanner;

import static business.Data.*;
import static business.service.ProductService.showAllProduct;
import static business.ultil.enumList.Common.inputNum;
import static java.util.stream.Collectors.toList;

public class CartService {
public static void showAllCart(User user) {
   if(user.getCartList()==null||user.getCartList().isEmpty()) {
      System.err.println("Cart is empty");
   }else{

      System.out.println("--------------------------------------------------------------------------------------");
      System.out.printf("| %15s | %20s | %5s | %-15s | %-15s |\n","Product ID", "Product", "Qty", "Price", "Total");
      user.getCartList().forEach(Cart::displayCart);
      System.out.println("--------------------------------------------------------------------------------------");
   }

}
public static void addToCart(Scanner sc,User user) {
   Cart cartNew = new Cart();
   showAllProduct();
   System.out.println("Enter product ID you want to add to cart: ");
   do {
      int productID = inputNum(sc);
         if(productList.stream().noneMatch(p->p.getProductId()==productID)) {
            System.err.println("Product ID does not match");
         }else {
            Product productAdd = productList.stream().filter(p->p.getProductId()==productID).toList().getFirst();
            System.out.println("Enter quantity of product you want to add to cart: ");
            do {
               int quantity = inputNum(sc);
               if(quantity > productAdd.getProductStock()) {
                  System.out.println("SORRY ! we don't have enough product in stock to add to cart");
               }else {
//                  Product already in cart

                  if(currentUser.getCartList().stream().anyMatch(p->p.getProductInCart().getProductId()==productID)){
                     Cart cartOld = currentUser.getCartList().stream().filter(p->p.getProductInCart().getProductId()==productID).findFirst().get();
                     cartOld.setQty(cartOld.getQty() + quantity);
//                     update lai userList
//                     index of currentUser in userList
                     userList.set(currentIndex,currentUser);
                     showAllCart(user);
                     return;
                  }else{
//                     Product not exist in cart

                     cartNew.setProductInCart(productAdd);
                     cartNew.setQty(quantity);
                     currentUser.getCartList().add(cartNew);
                     cartNew.getProductInCart().setProductStock(productAdd.getProductStock() - quantity);
                  }
                  System.out.println("Added product to the cart");
                  showAllCart(currentUser);
                  return;
               }
            }while(true);
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
         productOldCart.displayCart();
         System.out.println("Enter new quantity of product you want to edit: ");
         int quantity = inputNum(sc);

//              Update Product List
         productOld.setProductStock(productOld.getProductStock() + productOldCart.getQty() - quantity);
//              Update Cart
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
            productOld.setProductStock(productOld.getProductStock() + productOldCart.getQty());

            System.out.println("Delete successfully");
            showAllCart(currentUser);
            return;
         }
}
public static void clearCart(Scanner sc,User user) {
   if(user.getCartList().isEmpty()) {
      System.err.println("Cart is empty");
   }else{
      Product productBeforeClear ;
      for(Cart cart: currentUser.getCartList()) {
         int index = productList.indexOf(cart.getProductInCart());
         productBeforeClear =productList.get(index);
         productList.get(index).setProductStock(productBeforeClear.getProductStock()+ cart.getQty());
      }
      userList.stream().filter(e->e.getUserId()== currentUser.getUserId()).findFirst().get().getCartList().clear();
      showAllCart(currentUser);
   }
}
}

package business.entity;

import java.util.Scanner;

import static business.Data.*;
import static business.ultil.enumList.Common.inputNum;

public class Cart {
    private int userId;
    private Product productInCart;
    private int qty;

    public Cart() {
    }

    public Cart(int userId,Product productInCart, int qty) {
        this.productInCart = productInCart;
        this.qty = qty;
    }

    public Product getProductInCart() {
        return productInCart;
    }

    public void setProductInCart(Product productInCart) {
        this.productInCart = productInCart;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    public void inputCart(Scanner sc) {
        this.productInCart = inputProductInCart(sc);
        this.qty = inputNum(sc);
    }

    private Product inputProductInCart(Scanner sc) {
        productService.showAllProduct();
//        showAllProduct();
        System.out.println("Enter product id from list before: ");
        do {
            int input = inputNum(sc);
            if(productList.stream().anyMatch(product -> product.getProductId() == input)) {
                return productList.stream().filter(product -> product.getProductId() == input).findAny().get();
            }else{
                System.err.println("Product id is not exist. Please try again.");
            }
        }while (true);
    }

    public void displayCart(){
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-5d | %-10s | %-5d | %-15f | %-15f |\n",this.productInCart.getProductId(), this.productInCart.getProductName(), this.qty, this.productInCart.getProductPrice(), this.productInCart.getProductPrice()*qty);

    }
}

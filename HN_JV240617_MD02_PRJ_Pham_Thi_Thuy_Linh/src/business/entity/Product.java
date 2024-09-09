package business.entity;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Scanner;

import static business.Data.*;
import static business.service.ProductService.showAllProduct;
import static business.ultil.enumList.Common.*;

public class Product {
    private int productId;
    private String productName;
    private String productDescription;
    private Category productCate;
    private int productStock;
    private double productPrice;
    private String productColor;
    private String productSize;
    private LocalDate productCreatedDate;
    private LocalDate productUpdatedDate;
    private boolean productWishList;
    public Product() {}

    public Product(int productId, String productName,Category productCate, double productPrice,int productStock,String productDescription,
                   String productColor, LocalDate productCreatedDate, String productSize,
                    LocalDate productUpdatedDate, boolean productWishList) {

        this.productId = productId;
        this.productName = productName;
        this.productCate = productCate;
        this.productPrice = productPrice;
        this.productColor = productColor;
        this.productCreatedDate = productCreatedDate;
        this.productDescription = productDescription;
        this.productSize = productSize;
        this.productStock = productStock;
        this.productUpdatedDate = productUpdatedDate;
        this.productWishList = productWishList;
    }



    public Category getProductCate() {
        return productCate;
    }

    public void setProductCate(Category productCate) {
        this.productCate = productCate;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public LocalDate getProductCreatedDate() {
        return productCreatedDate;
    }

    public void setProductCreatedDate(LocalDate productCreatedDate) {
        this.productCreatedDate = productCreatedDate;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public LocalDate getProductUpdatedDate() {
        return productUpdatedDate;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductUpdatedDate(LocalDate productUpdatedDate) {
        this.productUpdatedDate = productUpdatedDate;
    }

    public boolean isProductWishList() {
        return productWishList;
    }

    public void setProductWishList(boolean productWishList) {
        this.productWishList = productWishList;
    }

    public void inputProduct(Scanner sc) {
        this.productId = autoProductId(sc);
        this.productName = inputProductName(sc);
        this.productCate = inputProductCate(sc);
        this.productPrice = inputProductPrice(sc);
        System.out.println("Enter stock of product: ");
        this.productStock = inputNum(sc);//>10
        System.out.println("Enter product description: ");
        this.productDescription = inputString(sc);
        this.productColor = "Color";//enum
        this.productCreatedDate = currentDate();
        this.productSize = "Size";//enum
        this.productUpdatedDate = currentDate();
        this.productWishList = false;
    }

    public void inputUpdateProduct(Scanner sc) {
        this.productName = inputProductName(sc);
        this.productCate = inputProductCate(sc);
        this.productPrice = inputProductPrice(sc);
        System.out.println("Enter stock of product: ");
        this.productStock = inputNum(sc);
        System.out.println("Enter product description: ");
        this.productDescription = inputString(sc);
        this.productColor = "Color";//enum
        this.productCreatedDate = currentDate();
        this.productSize = "Size";//enum
        this.productUpdatedDate = currentDate();
        this.productWishList = false;
    }

    public void display(){
        System.out.printf("| %-5d | %-20s | %-15s | %-10f | %-10d |  %-10s | %-10s |  %-10s | %-10s | %-10s |\n ", productId, productName,productCate.getCateName(),productPrice,productStock,productColor,productSize,productCreatedDate,productUpdatedDate,productWishList);
        System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------------------");
    }

    private double inputProductPrice(Scanner sc) {
        System.out.println("Enter the price of the product");
        do{
            String input= sc.nextLine();
            if(input.isBlank()){
                System.out.println("Data cannot be empty");
            }else{
                try{
                    double price = Double.parseDouble(input);
                    if(price<=0){
                        System.out.println("Price must grater than 0");
                    }else{
                        return price;
                    }
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
            }
        }while(true);
    }

    private Category inputProductCate(Scanner sc) {
        System.out.println("Categories list:");
        categoryList.forEach(Category::displayCategory);
        System.out.println("Enter category ID:");
        do {
            int categoryId = inputNum(sc);
            if (categoryList.stream().noneMatch(e->e.getCateId()==categoryId)) {
                System.out.println("Category does not exist. Please try again!");
            }else{
                return categoryList.stream().filter(e->e.getCateId()==categoryId).findFirst().get();
            }
        }while (true);
    }

    private int autoProductId(Scanner sc) {
        return productList.stream().map(Product::getProductId).max(Comparator.comparingInt(Integer::intValue)).orElse(0)+1;
    }

    private String inputProductName(Scanner sc) {
        System.out.println("Product name:");
        do {
            String input = inputString(sc);
            if(productList.stream().anyMatch(e->e.getProductName().equals(input))) {
                System.out.println("Product already exist. Please try again!");
            }else{
                return input;
            }
        }while(true);
    }

    public static void addWishList(Scanner sc) {
        showAllProduct();
        System.out.println("Enter product ID to add to wish list: ");
        int productId = inputNum(sc);
        if(productList.stream().noneMatch(e->e.getProductId()==productId)) {
            System.err.println("Product ID does not exist. Please try again!");
        }
    }
}

package business.service;

import business.entity.Product;

import java.util.Scanner;

import static business.Data.productList;
import static business.ultil.enumList.Common.inputNum;

public class ProductService {
    public static void showAllProduct() {
        if(productList.isEmpty()){
            System.err.println("Product list is empty");
        }else{
            System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------------------");
            productList.forEach(Product::display);
        }
    }
    public void addProduct(Scanner sc) {
        System.out.println("Enter the number of products you want to add");
        int number = inputNum(sc);
        for (int i = 0; i < number; i++) {
            Product newProduct = new Product();
            newProduct.inputProduct(sc);
            productList.add(newProduct);
        }
        System.out.println("Add product successfully");
    }
    public void updateProduct(Scanner sc) {
        System.out.println("Enter the id of the product ID you want to edit");
            int id = inputNum(sc);
            if(productList.stream().noneMatch(e->e.getProductId()==id)){
                System.err.println("Product ID does not exist");
            }else{
                Product product = productList.stream().filter(e->e.getProductId()==id).findFirst().get();
                product.inputUpdateProduct(sc);
                product.setProductId(product.getProductId());
                System.out.println("Update product successfully");
                product.display();
            }
    }

    public void deleteProduct(Scanner sc) {
//        List<Product> productList= IOFile.readObjectFromFile("src/business/data/product.txt");
        // thực hiện logic delete
        System.out.println("Enter the id of the product ID you want to delete");
            int id = inputNum(sc);
            if(productList.stream().noneMatch(e->e.getProductId()==id)){
                System.err.println("Product ID does not exist");
            }else{
                Product product = productList.stream().filter(e->e.getProductId()==id).findFirst().get();
                productList.remove(product);
                // xóa xong thì lưu lại dữ liệu vào file
//                IOFile.writeObjectToFile(productList, IOFile.PATH_PRODUCT);
            }
    }

    public static void searchProduct(Scanner sc) {
//        List<Product> productList= IOFile.readObjectFromFile("src/business/data/product.txt");
        System.out.println("Enter the keyword you want to search:");
            String keyword = sc.nextLine();
            if(keyword.isBlank()){
                System.err.println("Keyword is empty. Please try again");
            }else {
                if(productList.stream().noneMatch(e->e.getProductId()==Integer.parseInt(keyword))){
                    System.err.println("No result matching keyword " + keyword);
                }else{
                    System.out.println("Result:");
                    productList.stream().filter(e->e.getProductName().contains(keyword)).forEach(Product::display);
                }
            }
    }
}

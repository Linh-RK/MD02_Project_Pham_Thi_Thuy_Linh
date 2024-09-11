package business.service;

import business.entity.Product;
import business.ultil.enumList.IOFile;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import static business.Data.productList;
import static business.ultil.enumList.Common.inputNum;
import static business.ultil.enumList.Common.inputString;

public class ProductService implements Serializable {

    public static void showAllProduct() {
            List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        if(productList.isEmpty()){
            System.err.println("Product list is empty");
        }else{
            System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n ", "ID", "Product","Category","Price","Stock","Color","Size","Created Date","Updated Date","Wish List");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
//            productList.forEach(Product::display);
            for(Product product : productList){
                product.display();
            }
        }
    }
    public void addProduct(Scanner sc) {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        System.out.println("Enter the number of products you want to add");
        int number = inputNum(sc);
        for (int i = 0; i < number; i++) {
            Product newProduct = new Product();
            newProduct.inputProduct(sc);
            productList.add(newProduct);
            IOFile.writeObjectToFile(productList, IOFile.PATH_PRODUCT);
        }
        System.out.println("Add product successfully");
    }
    public void updateProduct(Scanner sc) {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        System.out.println("Enter the id of the product ID you want to edit");
            int id = inputNum(sc);
            if(productList.stream().noneMatch(e->e.getProductId()==id)){
                System.err.println("Product ID does not exist");
            }else{
                Product product = productList.stream().filter(e->e.getProductId()==id).findFirst().get();
                product.inputUpdateProduct(sc);
                product.setProductId(product.getProductId());
                IOFile.writeObjectToFile(productList, IOFile.PATH_PRODUCT);
                System.out.println("Update product successfully");
                product.display();
            }
    }

    public void deleteProduct(Scanner sc) {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        // thực hiện logic delete
        System.out.println("Enter the id of the product ID you want to delete");
        int id = inputNum(sc);
        if(productList.stream().noneMatch(e->e.getProductId()==id)){
            System.err.println("Product ID does not exist");
        }else{
            Product product = productList.stream().filter(e->e.getProductId()==id).findFirst().get();
            productList.remove(product);
            // xóa xong thì lưu lại dữ liệu vào file
            IOFile.writeObjectToFile(productList, IOFile.PATH_PRODUCT);
        }
    }

    public static void searchProduct(Scanner sc) {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        System.out.println("Enter the keyword you want to search:");
        String keyword = inputString(sc);
        if(productList.stream().noneMatch(e->e.getProductName().contains(keyword))){
            System.err.println("No result matching keyword " + keyword);
        }else {
            System.out.println("Result:");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n ", "ID", "Product", "Category", "Price", "Stock", "Color", "Size", "Created Date", "Updated Date", "Wish List");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
            productList.stream().filter(e -> e.getProductName().contains(keyword)).forEach(Product::display);
        }
    }
}

package business.service;

import business.design.iGeneric.IGenericProduct;
import business.entity.Category;
import business.entity.Product;
import business.ultil.enumList.IOFile;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static business.Data.categoryService;
import static business.ultil.enumList.Common.inputNum;
import static business.ultil.enumList.Common.inputString;

public class ProductService implements IGenericProduct, Serializable {
    @Override
    public void showAllProduct(Scanner sc) {
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

    @Override
    public void filterProductByCate(Scanner sc) {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        List<Category> categoryList= IOFile.readObjectFromFile(IOFile.PATH_CATEGORY);
        categoryService.showAllCategory();
        System.out.println("Enter category you want to search: ");
        int categoryId;
        do {
            categoryId = inputNum(sc);
            if(categoryList.isEmpty()){
                System.err.println("Category list is empty");
                return;
            }else{
                if (categoryList.stream().noneMatch(e->e.getCateId()==categoryId)) {
                    System.err.println("Category ID does not exist");
                    return;
                }else{
                    if(productList.isEmpty()){
                        System.err.println("Product list is empty");
                        return;
                    }else{
                        if(productList.stream().noneMatch(e->e.getProductCate().getCateId()==categoryId)){
                            System.err.println("None product in this category");
                            return;
                        }else{
                            System.out.println("Result:");
                            System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.printf("| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n ", "ID", "Product","Category","Price","Stock","Color","Size","Created Date","Updated Date","Wish List");
                            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
                            productList.stream().filter(e->e.getProductCate().getCateId()==categoryId).forEach(Product::display);
                            return;
                        }
                    }
                }
            }
        }while(true);
    }

    @Override
    public void searchProductById(Scanner sc) {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        if(productList.isEmpty()){
            System.err.println("Product list is empty");
        }else {
            System.out.println("Enter product ID you want to search:");
            int id = inputNum(sc);
            if (productList.stream().noneMatch(e -> e.getProductId() == id)) {
                System.err.println("Product ID does not exist");
            } else {
                System.out.println("Result:");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n ", "ID", "Product", "Category", "Price", "Stock", "Color", "Size", "Created Date", "Updated Date", "Wish List");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
                productList.stream().filter(e -> e.getProductId()==id).forEach(Product::display);
            }
        }
    }

    @Override
    public void top5NewProduct(Scanner sc) {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        if(productList.isEmpty()){
            System.err.println("Product list is empty");
        }else{
            List<Product> list = productList.stream().sorted(Comparator.comparing(Product::getProductCreatedDate)).toList().reversed();
            System.out.println("Result");
            if(productList.size()<=5){
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n ", "ID", "Product", "Category", "Price", "Stock", "Color", "Size", "Created Date", "Updated Date", "Wish List");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
                list.forEach(Product::display);
            }else{
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n ", "ID", "Product", "Category", "Price", "Stock", "Color", "Size", "Created Date", "Updated Date", "Wish List");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
                for (int i = 0; i < 5; i++) {
                    list.get(i).display();
                }
            }
        }
    }

    @Override
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

    @Override
    public void onSaleProduct(Scanner sc) {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        if(productList.isEmpty()){
            System.err.println("Product list is empty");
        }else{
            List<Product> list = productList.stream().filter(e->e.getProductStock()>0).toList();
            if(list.isEmpty()){
                System.err.println("No product onsale !");
            }else{
                System.out.println("Result:");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n ", "ID", "Product", "Category", "Price", "Stock", "Color", "Size", "Created Date", "Updated Date", "Wish List");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
                list.forEach(Product::display);

            }
        }
    }

    @Override
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

    @Override
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

    @Override
    public void searchProduct(Scanner sc) {
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

//    public static void showAllProduct()
//    public static void filterProductByCate(Scanner sc)
//    public static void searchProductById(Scanner sc)
//    public static void top5NewProduct(Scanner sc)
//    public static void onSaleProduct(Scanner sc)
//    public void addProduct(Scanner sc)
}

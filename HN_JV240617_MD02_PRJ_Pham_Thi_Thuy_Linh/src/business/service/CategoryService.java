package business.service;

import business.entity.Category;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static business.Data.categoryList;
import static business.Data.productList;
import static business.ultil.enumList.Common.inputNum;
import static business.ultil.enumList.Common.inputString;

public class CategoryService {
    public static void addCategory(Scanner sc) {
        System.out.println("Enter the number of categories you want to add");
        int number = inputNum(sc);
        for (int i = 0; i < number; i++) {
            System.out.println("Category " + (i + 1));
            Category category = new Category();
            category.inputCategory(sc);
            categoryList.add(category);
        }
        System.out.println("Added Category successfully");
        showAllCategory();
    }
    public static void showAllCategory() {
        if(categoryList.isEmpty()){
            System.err.println("Category list is empty");
        }else{
            System.out.println("------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-15s | %-10s | %-35s |\n","ID","Category","Status","Description");
            categoryList.forEach(Category::displayCategory);
            System.out.println("------------------------------------------------------------------------------");
        }
    }
    public  static void updateCategory(Scanner sc) {
        System.out.println("Enter the category ID you want to update");
            int id = inputNum(sc);
            if(categoryList.stream().noneMatch(e->e.getCateId()==id)){
                System.err.println("Category ID does not exist");
            }else {
                categoryList.stream().filter(e -> e.getCateId() == id).findFirst().get().inputUpdateCategory(sc);
                categoryList.stream().filter(e -> e.getCateId() == id).findFirst().get().setCateId(id);
                System.out.println("Update Category successfully");
                showAllCategory();
            }
    }
    public static void deleteCategory(Scanner sc) {
        System.out.println("Enter the category ID you want to delete");
        int id = inputNum(sc);
        if (categoryList.stream().noneMatch(e -> e.getCateId() == id)) {
            System.err.println("Category ID does not exist");
        } else {
            if (productList.stream().anyMatch(e -> e.getProductCate().getCateId() == id)) {
                System.err.println("Category have product inside, cannot delete");
            } else {
            categoryList.remove(categoryList.stream().filter(e -> e.getCateId() == id).findFirst().get());
            System.out.println("Update Category successfully");
            showAllCategory();
            }
        }
    }
    public static void searchCate(Scanner sc) {
//        List<Category> categoryList= IOFile.readObjectFromFile("src/business/data/category.txt");
        System.out.println("Please enter search keys:");
            String key = inputString(sc);
            if(categoryList.stream().noneMatch(e->e.getCateName().contains(key))){
                System.err.println("No result match");
            }else{
                System.out.println("Result:");
                System.out.println("------------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-15s | %-10s | %-35s |\n","ID","Category","Status","Description");
                categoryList.stream().filter(e->e.getCateName().contains(key)).toList().forEach(Category::displayCategory);
                System.out.println("------------------------------------------------------------------------------");
            }
    }
    public static void sortCate(Scanner sc) {
//        List<Category> categoryList= IOFile.readObjectFromFile("src/business/data/category.txt");
//		categories.stream().sorted((a,b)-> a.getCateName().compareTo(b.getCateName())).forEach(Category::displayCategory);
        System.out.println("Result sort by name increasing order:");
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-15s | %-10s | %-35s |\n","ID","Category","Status","Description");
        categoryList.stream().sorted(Comparator.comparing(Category::getCateName)).forEach(Category::displayCategory);
        System.out.println("------------------------------------------------------------------------------");
    }
}

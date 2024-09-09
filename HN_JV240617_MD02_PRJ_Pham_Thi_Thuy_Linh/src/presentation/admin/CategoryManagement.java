package presentation.admin;

import business.service.CategoryService;

import java.util.Scanner;

import static business.Data.categoryService;

public class CategoryManagement {
    public static void categoryManagement (Scanner sc){
        boolean flag = true;
        do {
            System.out.println("--------------------------------ADMIN CATEGORY--------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Hiển thị danh mục (phân trang)                                   |");
            System.out.println("|        2. Thêm mới danh mục                                                |");
            System.out.println("|        3. Sửa thông tin danh mục                                           |");
            System.out.println("|        4. Xóa danh mục                                                     |");
            System.out.println("|        5. Tìm kiếm danh mục                                                |");
            System.out.println("|        6. Sắp xếp theo chiều giảm dần hoặc tăng dần                        |");
            System.out.println("|        7. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Please enter your choice");

            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    CategoryService.showAllCategory();
                    break;
                }
                case "2":{
                    CategoryService.addCategory(sc);
                    break;
                }
                case "3":{
                    CategoryService.updateCategory(sc);
                    break;
                }
                case "4":{
                    CategoryService.deleteCategory(sc);
                    break;
                }
                case "5":{
                    CategoryService.searchCate(sc);
                    break;
                }
                case "6":{
                    CategoryService.sortCate(sc);
                    break;
                }
                case "7":{
                    flag = false;
                    break;
                }
                default:{
                    System.err.println("Please enter a choice from 1 to 7");
                    break;
                }
            }
        }while (flag);
    }
}

package presentation.admin;

import java.util.Scanner;

public class UserManagement {
    public static void userManagement(Scanner sc) {
        boolean flag = true;
        do {
            System.out.println("----------------------------------ADMIN USER----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Hiển thị người dùng (phân trang)                                 |");
            System.out.println("|        2. Sửa thay đổi trạng thái                                          |");
            System.out.println("|        3. Xóa người dùng                                                   |");
            System.out.println("|        4. Tìm kiếm người dùng                                              |");
            System.out.println("|        5. Thêm địa chỉ người dùng                                          |");
            System.out.println("|        6. Sắp xếp theo tên theo chiều giảm dần hoặc tăng dần               |");
            System.out.println("|        7. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Please enter your choice");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{

                    break;
                }
                case "2":{

                    break;
                }
                case "3":{

                    break;
                }
                case "4":{

                    break;
                }
                case "5":{

                    break;
                }
                case "6":{

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

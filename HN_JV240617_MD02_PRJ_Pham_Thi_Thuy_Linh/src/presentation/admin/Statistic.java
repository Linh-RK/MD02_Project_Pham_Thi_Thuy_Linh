package presentation.admin;

import java.util.Scanner;

public class Statistic {
    public static void statistics(Scanner sc) {
        boolean flag = true;
        do {
            System.out.println("-------------------------------ADMIN STATISTIC--------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Thống kê doanh thu theo tháng                                    |");
            System.out.println("|        2. Thông kê số lượng đơn hàng thành công, hủy theo tháng            |");
            System.out.println("|        3. Thông kê doanh thu từ ngày a đến ngày b                          |");
            System.out.println("|        4. Quay lại                                                         |");
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
                    flag = false;
                    break;
                }
                default:{
                    System.err.println("Please enter a choice from 1 to 4");
                    break;
                }
            }
        }while (flag);
    }
}

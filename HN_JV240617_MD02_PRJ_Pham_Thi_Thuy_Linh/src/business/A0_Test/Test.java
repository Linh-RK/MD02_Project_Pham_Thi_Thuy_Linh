package business.A0_Test;

import business.entity.Order;
import business.ultil.enumList.IOFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String n = inputPhoneNumber(sc);
//        System.out.println(n);
//        System.out.println(currentDate());
        List<Order> orderList= IOFile.readObjectFromFile(IOFile.PATH_ORDER);
        orderList.forEach(Order::displayOrder);
    }
    public static int inputNum(Scanner sc){
        System.out.println("Enter number: ");
        String input;
        do {
            input = sc.nextLine();
            if(input.isBlank()){
                System.err.println("Data cannot be empty.Please try again");
            }else{
                try{
                    int num = Integer.parseInt(input);
                    if(num <= 0){
                        System.out.println("Number cannot be less than or equal to zero.Please try again");
                    }else{
                        return num;
                    }
                }catch(NumberFormatException e){
                    System.err.println("Must input a number.");
                }
            }
        }while(true);
    }
    public  static LocalDate currentDate(){
        LocalDate today = LocalDate.now( );
        System.out.println(today);
        return today;
    }
    public static String inputPhoneNumber(Scanner sc) {
        System.out.println("Enter Phone Number: ");
        do{
            String phoneNumber = sc.nextLine();
            if(phoneNumber.isBlank()){
                System.err.println("Data can't be blank. Please try again.");
            }else {
                if(!phoneNumber.matches("[0-9]{10,11}")){
                    System.err.println("Phone number is incorrect format. Please try again.");
                } else {
                    return phoneNumber;
                }
            }
        }while(true);
    }
}

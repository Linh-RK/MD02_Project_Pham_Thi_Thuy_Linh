package business.A0_Test;

import java.time.LocalDate;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = inputNum(sc);
//        System.out.println(n);
//        System.out.println(currentDate());
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
}

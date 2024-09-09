package business.ultil.enumList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Common {
    public static String inputString(Scanner sc){
        String input ;
        do {
            input = sc.nextLine();
            if(input.isBlank()){
                System.err.println("Data cannot be empty.Please try again");
            }else{
                return input;
            }
        }while(true);
    }
    public static int inputNum(Scanner sc){
        String input;
        do {
            input = sc.nextLine();
            if(input.isBlank()){
                System.err.println("Data cannot be empty.Please try again");
            }else{
                try{
                    int num = Integer.parseInt(input);
                    if(num <= 0){
                        System.err.println("Number cannot be less than or equal to zero.Please try again");
                    }else{
                        return num;
                    }
                }catch(NumberFormatException e){
                    System.err.println("Must input a number.");
                }
            }
        }while(true);
    }
    public static boolean inputStatus(Scanner sc){
        do {
            String input = sc.nextLine();
            if(input.equalsIgnoreCase("true" )||input.equalsIgnoreCase("false" )){
                return Boolean.parseBoolean(input);
            }else{
                System.err.println("Status must be 'true' or 'false'. Please try again");
            }
        }while(true);
    }
    public static String inputPhoneNumber(Scanner sc) {
        System.out.println("Enter Phone Number: ");
        do{
            String phoneNumber = sc.nextLine();
            if(phoneNumber.isBlank()){
                System.err.println("Data can't be blank. Please try again.");
            }else {
                if(!phoneNumber.matches("/((09|03|07|08|05)+([0-9]{8})\\b)/g")){
                    System.err.println("Phone number is incorrect format. Please try again.");
                } else {
                    return phoneNumber;
                }
            }
        }while(true);
    }
    public  static LocalDate currentDate(){
        LocalDate today = LocalDate.now( );
        return today;
    }
    public static String inputAnswer(Scanner sc){
        System.out.println("Enter Answer (Y/N): ");
        do {
            String answer = sc.nextLine();
            if(!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n"))){
                System.err.println("Answer must be 'y' or 'n'. Please try again.");
            }else{
                return answer;
            }
        }while(true);
    }

    public static LocalDate inputDate(Scanner sc){
        do {
            String input = sc.nextLine();
            if(!input.matches("\\d{4}-\\d{2}-\\d{2}")){
                System.err.println("Date format is incorrect. Please try again.");
            }else {
                String[] inputArr = input.split("-");
                LocalDate day = LocalDate.of(Integer.parseInt(inputArr[0]),Integer.parseInt(inputArr[1]),Integer.parseInt(inputArr[2]));
                return day;
            }
        }while(true);
    }
}

package business.service;

import business.entity.User;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.ultil.enumList.Common.inputNum;

public class UserService {
    public static void showAllUserInfo(List<User> userList){
        if(userList.isEmpty()){
            System.err.println("User list is empty");
        }else {

            System.out.println("------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-20s | %-20s |\n", "ID", "Name", "Email", "Status", "PhoneNumber", "CreatedDate");
            userList.forEach(User::displayUser);
            System.out.println("------------------------------------------------------------------------------------------------------------------");
        }
    }
    public static void changeUserStatus(Scanner sc){
        System.out.println("Please enter ID user you want to change status");
            int id = inputNum(sc);
            if(userList.stream().noneMatch(e->e.getUserId()==id)){
                System.err.println("User ID does not exist");
            }else{
                User.switchUserStatus(sc,userList.stream().filter(e->e.getUserId()==id).findFirst().get());
            }
          showAllUserInfo(userList);
    }

    public static void deleteUser(Scanner sc){
        System.out.println("Please enter ID user you want to delete");
            int id = inputNum(sc);
            if(userList.stream().noneMatch(e->e.getUserId()==id)){
                System.err.println("User ID does not exist");
            }else{
                userList.remove(userList.stream().filter(e->e.getUserId()==id).findFirst().get());
                System.out.println("User successfully deleted");
            }
            showAllUserInfo(userList);
    }
    public static void searchUser(Scanner sc){
        System.out.println("Please enter ID user you want to search:");
            int id = inputNum(sc);
            if(userList.stream().noneMatch(e->e.getUserId()==id)){
                System.err.println("User ID does not exist");
            }else{
                System.out.println("Result:");
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-20s | %-20s |\n", "ID", "Name", "Email", "Status", "PhoneNumber", "CreatedDate");
                userList.stream().filter(e->e.getUserId()==id).findFirst().get().displayUser();
                System.out.println("------------------------------------------------------------------------------------------------------------------");
            }
    }
    public static void sortUserByNameIncrease(){
    if(userList.isEmpty()){
        System.err.println("User list is empty");
    }else {
        List<User> resultList = userList.stream().sorted(Comparator.comparing(User::getUserName)).toList();
        showAllUserInfo(resultList);
    }
    }
    public static void sortUserByNameDecrease(){
        if(userList.isEmpty()){
            System.err.println("User list is empty");
        }else {
            List<User> resultList = userList.stream().sorted(Comparator.comparing(User::getUserName)).toList().reversed();
            showAllUserInfo(resultList);
        }
    }

}

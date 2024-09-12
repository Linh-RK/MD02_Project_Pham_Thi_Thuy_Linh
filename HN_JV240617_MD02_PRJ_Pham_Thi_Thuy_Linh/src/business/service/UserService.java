package business.service;

import business.design.iGeneric.IGenericUser;
import business.entity.User;
import business.ultil.enumList.IOFile;
import business.ultil.enumList.Role;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.ultil.enumList.Common.inputNum;

public class UserService implements IGenericUser, Serializable {
    @Override
    public void showAllUserInfo() {
            List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        if(userList.isEmpty()){
            System.err.println("User list is empty");
        }else {
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-20s | %-20s |\n", "ID", "Name", "Email", "Status", "PhoneNumber", "CreatedDate");
            userList.forEach(User::displayUser);
            System.out.println("------------------------------------------------------------------------------------------------------------------");
        }
    }

    @Override
    public void changeUserStatus(Scanner sc) {
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        System.out.println("Please enter ID user you want to change status");
            int id = inputNum(sc);
            if(userList.stream().noneMatch(e->e.getUserId()==id)){
                System.err.println("User ID does not exist");
            }else{
                User.switchUserStatus(sc,userList.stream().filter(e->e.getUserId()==id).findFirst().get());
                IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
            }
          showAllUserInfo();
    }

    @Override
    public void deleteUser(Scanner sc) {
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        System.out.println("Please enter ID user you want to delete");
            int id = inputNum(sc);
            if(userList.stream().noneMatch(e->e.getUserId()==id)){
                System.err.println("User ID does not exist");
            }else{
                userList.remove(userList.stream().filter(e->e.getUserId()==id).findFirst().get());
                IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
                System.out.println("User successfully deleted");
            }
            showAllUserInfo();
    }

    @Override
    public void searchUser(Scanner sc) {
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
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

    @Override
    public void sortUserByNameIncrease() {
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
    if(userList.isEmpty()){
        System.err.println("User list is empty");
    }else {
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-20s | %-20s |\n", "ID", "Name", "Email", "Status", "PhoneNumber", "CreatedDate");
        userList.stream().sorted(Comparator.comparing(User::getUserName)).toList().forEach(User::displayUser);
        System.out.println("------------------------------------------------------------------------------------------------------------------");

    }
    }

    @Override
    public void sortUserByNameDecrease() {
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        if(userList.isEmpty()){
            System.err.println("User list is empty");
        }else {
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-20s | %-20s |\n", "ID", "Name", "Email", "Status", "PhoneNumber", "CreatedDate");
            userList.stream().sorted(Comparator.comparing(User::getUserName)).toList().reversed().forEach(User::displayUser);
            System.out.println("------------------------------------------------------------------------------------------------------------------");

        }
    }

    @Override
    public void listRole(Scanner sc) {
        Role[] roles = Role.values();
        for (Role role : roles) {
            System.out.println(role);
        }
    }
}

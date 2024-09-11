package business.service;

import business.entity.Address;
import business.entity.User;
import business.ultil.enumList.IOFile;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.ultil.enumList.Common.inputNum;

public class AddressService implements Serializable {
    public static void searchAddressById(Scanner sc){
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        currentUser = userList.get(currentIndex);
        if(currentUser.getUserAddressList().isEmpty()){
            System.err.println("Address list is empty");
        }else {
            showAllAddress();
            System.out.println("Enter the id of address you want to search");
            int id = inputNum(sc);
            if (currentUser.getUserAddressList().stream().noneMatch(e -> e.getIdAddress() == id)) {
                System.err.println("The address ID does not exist");
            } else {
                System.out.println("RESULT");
                System.out.println("-------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-20s | %-15s | %-20s |\n","ID","Receiver","Phone","Address");
                System.out.println("-------------------------------------------------------------------------");
                currentUser.getUserAddressList().stream().filter(e -> e.getIdAddress() == id).findFirst().get().displayAddress();
            }
        }
    }
    public static void showAllAddress(){
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        currentUser = userList.get(currentIndex);
        if(currentUser.getUserAddressList().isEmpty()){
            System.err.println("Address is empty");
        }else {
            System.out.println("Address List:");
            System.out.println("-------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-15s | %-20s |\n","ID","Receiver","Phone","Address");
            System.out.println("-------------------------------------------------------------------------");
            currentUser.getUserAddressList().forEach(Address::displayAddress);
        }
    }
    public static void deleteAddressById(Scanner sc){
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        currentUser = userList.get(currentIndex);
        showAllAddress();
        System.out.println("Enter the id of address you want to delete");
        int id = inputNum(sc);
        if(currentUser.getUserAddressList().stream().noneMatch(e->e.getIdAddress()==id)){
            System.err.println("The address ID does not exist");
        }else{
            Address daleteAddress=currentUser.getUserAddressList().stream().filter(e->e.getIdAddress()==id).findFirst().get();
            currentUser.getUserAddressList().remove(daleteAddress);
            userList.set(currentIndex,currentUser);
            IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
            System.out.println("Deleted the address successfully");
            showAllAddress();
        }
    }
    public static void addNewAddress(Scanner sc){
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        currentUser = userList.get(currentIndex);
        System.out.println("Enter the number of address you want to add");
        int number = inputNum(sc);
        for(int i = 0; i < number; i++){
            System.out.println("The address"+(i+1));
            Address newAddress=new Address();
            newAddress.inputAddress(sc);
            currentUser.getUserAddressList().add(newAddress);

            userList.set(currentIndex, currentUser);
            IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
        }
        System.out.println("Add address successfully");
        showAllAddress();
    }
//    public static void (Scanner sc){}
}

//    List<Category> categoryList= IOFile.readObjectFromFile(IOFile.PATH_CATEGORY);
//    List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
//    List<Order> orderList= IOFile.readObjectFromFile(IOFile.PATH_ORDER);
//    List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
//    User currentUser= IOFile.readObjectFromFile(IOFile.PATH_USERCURRENT);
//    List<Cart> cartList= IOFile.readObjectFromFile(IOFile.PATH_CART);
//    IOFile.writeObjectToFile(categoryList, IOFile.PATH_CATEGORY);
//    IOFile.writeObjectToFile(productList, IOFile.PATH_PRODUCT);
//    IOFile.writeObjectToFile(orderList, IOFile.PATH_ORDER);
//    IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
//    IOFile.writeObjectToFile(userList, IOFile.PATH_USERCURRENT);
//    IOFile.writeObjectToFile(cartList, IOFile.PATH_CART);
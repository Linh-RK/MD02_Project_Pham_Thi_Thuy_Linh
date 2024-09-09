package business.service;

import business.entity.Address;

import java.util.Scanner;

import static business.Data.addressList;
import static business.ultil.enumList.Common.inputNum;

public class AddressService {
    public static void searchAddressById(Scanner sc){
        showAllAddress();
        System.out.println("Enter the id of address you want to search");
        int id = inputNum(sc);
        if(addressList.stream().noneMatch(e->e.getIdAddress()==id)){
            System.err.println("The address ID does not exist");
        }else{
            System.out.println("RESULT");
            addressList.stream().filter(e->e.getIdAddress()==id).findFirst().get().displayAddress();
        }
    }
    public static void showAllAddress(){

        addressList.forEach(Address::displayAddress);
        System.out.println("");
    }
    public static void deleteAddressById(Scanner sc){
        System.out.println("Enter the id of address you want to delete");
        int id = inputNum(sc);
        if(addressList.stream().noneMatch(e->e.getIdAddress()==id)){
            System.err.println("The address ID does not exist");
        }else{
            Address daleteAddress=addressList.stream().filter(e->e.getIdAddress()==id).findFirst().get();
            addressList.remove(daleteAddress);
            System.out.println("Deleted the address successfully");
            showAllAddress();
        }
    }
    public static void addNewAddress(Scanner sc){
        System.out.println("Enter the number of address you want to add");
        int number = inputNum(sc);
        for(int i = 0; i < number; i++){
            System.out.println("The address"+(i+1));
            Address newAddress=new Address();
            newAddress.inputAddAddress(sc);
            addressList.add(newAddress);
        }
        System.out.println("Add address successfully");
        showAllAddress();
    }
//    public static void (Scanner sc){}
}


package business.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.ultil.enumList.Common.inputString;

public class Address {
    private int idAddress;
    private int userId;
    private String address;
    private String phoneNumber;
    private String receiveName;

    public Address() {
    }

    public Address(int idAddress, int userId, String address, String phoneNumber, String receiveName) {
        this.idAddress = idAddress;
        this.userId = userId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.receiveName = receiveName;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public void inputAddress(Scanner sc) {
        this.idAddress = autoIdAddress();
        this.userId = currentUser.getUserId();
        this.address = inputAddAddress(sc);
    }

    public int autoIdAddress() {
        return currentUser.getUserAddressList().stream().map(Address::getIdAddress).max(Integer::compareTo).orElse(0)+1;
    }

    public String inputAddAddress(Scanner sc) {
        do {
            String newAddress =  inputString(sc);
            if(addressList.stream().filter(e->e.getUserId()==userId).anyMatch(a -> a.getAddress().equals(newAddress))) {
                System.err.println("This address is already exist");
            }else{
                return address;
            }
        }while (true);
    }
    public void displayAddress() {
        System.out.println("------------------------------------------------------------");
        System.out.printf("| %-5s | %-10s | %-50s |\n","ID","User ID", "Address");
        System.out.printf("| %-5s | %-10s | %-50s |\n",this.idAddress,this.userId, this.address);
    }
}

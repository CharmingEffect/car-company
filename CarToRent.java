/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

import java.io.*;

/**
 *
 * @author Archie
 */
public class CarToRent extends Car implements Serializable {

    private String rentalDate;
    private String returnDate;
    private int adminFee;
    private int numberOfDays;
    private int dailyRate;
    private int totalAccumulated;
    private boolean onLoan;

    public CarToRent(int adminFee, int dailyRate, String carName, String description) {
       
        super(description, carName);
        this.rentalDate = "";
        this.returnDate = "";
        this.adminFee = adminFee;
        this.numberOfDays = 0;
        this.dailyRate = dailyRate;
        this.totalAccumulated = 0;
        this.onLoan = false;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getAdminFee() {
        return adminFee;
    }

    public void setAdminFee(int adminFee) {
        this.adminFee = adminFee;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public int getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(int dailyRate) {
        this.dailyRate = dailyRate;
    }

    public int getTotalAccumulated() {
        return totalAccumulated;
    }

    public void setTotalAccumulated(int totalAccumulated) {
        this.totalAccumulated = totalAccumulated;
    }

    public boolean isOnLoan() {
        return onLoan;
    }

    public void setOnLoan(boolean onLoan) {
        this.onLoan = onLoan;
    }
    
    

}

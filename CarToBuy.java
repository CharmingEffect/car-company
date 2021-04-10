import java.io.*;

public class CarToBuy extends Car implements Serializable {
    
    private double price;
    private int regYear;
    private int mileage;
    private boolean sold;

    public CarToBuy(double price, int regYear, int mileage, boolean sold, String carName, String description) {
        super(description, carName);  
        this.price = price;
        this.regYear = regYear;
        this.mileage = mileage;
        this.sold = false;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRegYear() {
        return regYear;
    }

    public void setRegYear(int regYear) {
        this.regYear = regYear;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
    
    
}

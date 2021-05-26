import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

    public CarToRent(String rentalDate, String returnDate, int adminFee,  int numberOfDays, int dailyRate, int totalAccumulated, boolean onLoan,  String carName, String description, String customerName) {
       
        super(description, carName, customerName);
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.adminFee = adminFee;
        this.numberOfDays = numberOfDays;
        this.dailyRate = dailyRate;
        this.totalAccumulated = totalAccumulated;
        this.onLoan = onLoan;
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

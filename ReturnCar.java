
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

public class ReturnCar extends JFrame {

    // init variables

    ArrayList<CarToRent> carsToRent;
    private JButton returnCarButton;
    private JLabel labelInfo, rentalDateLbl, returnDateLbl;
    private JList<String> carList;
    private JScrollPane carListScroll;
    private JLabel carAvailable, toPayLbl, toPayCurrentLbl;
    private JLabel rentalDateFld, returnDateFld;
    String totalToBePaid;

    DefaultListModel listModel = new DefaultListModel();

    public ReturnCar() {

        initGui();

        carsToRent = new ArrayList<CarToRent>();
        populateArrayList();
        String[] carsToRentArray = new String[carsToRent.size()];

        for (int i = 0; i < carsToRent.size(); i++) {
           
            carsToRentArray[i] = carsToRent.get(i).getCarName();

            listModel.addElement(carsToRentArray[i]);
            
        }

        carList.setModel(listModel);
        carList.setSelectedIndex(0);

    }

    public void populateArrayList() { // populate array list

        try {

            FileInputStream file = new FileInputStream("carsToRent.dat");
            ObjectInputStream inputFile = new ObjectInputStream(file);

            boolean endOfFile = false;

            while (!endOfFile) {

                try {

                    carsToRent.add((CarToRent) inputFile.readObject());

                } catch (EOFException e) {

                    endOfFile = true;

                } catch (Exception f) {

                    JOptionPane.showMessageDialog(null, f.getMessage());
                }
            }
            inputFile.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }

    private void initGui() {
        this.setPreferredSize(new Dimension(450, 450));
        this.setTitle("Return car");
        // this.setResizable(false);
        carListScroll = new JScrollPane();
        carList = new JList<>();

        JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel);

        carListScroll = new JScrollPane();
        carList = new JList<>();

        labelInfo = new JLabel("<html>Choose a car from the <br> list to see the details:<html>");
        panel.add(labelInfo);
        labelInfo.setBounds(25, 0, 200, 100);

        // change for formatted jlabel 

        rentalDateLbl = new JLabel("This car has been rented on: ");
        panel.add(rentalDateLbl);
        rentalDateLbl.setBounds(200, 20, 200, 100);

        rentalDateFld = new JLabel();
        panel.add(rentalDateFld);
        rentalDateFld.setBounds(200, 40, 200, 100);

        returnDateLbl = new JLabel("This car should be returned on :");
        panel.add(returnDateLbl);
        returnDateLbl.setBounds(200, 60, 200, 100);

        returnDateFld = new JLabel();
        panel.add(returnDateFld);
        returnDateFld.setBounds(200, 120, 100, 20);

        //-----------

        carListScroll.setViewportView(carList);
        panel.add(carListScroll);
        carListScroll.setBounds(25, 70, 150, 300);

        returnCarButton = new JButton("Return Car");
        panel.add(returnCarButton);
        returnCarButton.setBounds(260, 350, 150, 30);

        
        carAvailable = new JLabel("This car is available");
        panel.add(carAvailable);
        carAvailable.setBounds(200, 20, 200, 100);

        toPayLbl = new JLabel("For this car shoud be paid in total: ");
        panel.add(toPayLbl);
        toPayLbl.setBounds(200, 100, 200, 100);

        toPayCurrentLbl = new JLabel();
        panel.add(toPayCurrentLbl);
        toPayCurrentLbl.setBounds(280, 160, 100, 20);



        carListScroll.setViewportView(carList);

        carList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                carListAction();
            }

        }

        );

        returnCarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
                rentCarButtonAction(evt);
            }
        });

        pack();
    }

    private void carListAction() {
        int selectedIndex = carList.getSelectedIndex();
        
        
        if(carsToRent.get(selectedIndex).isOnLoan() == true){
            rentalDateFld.setText(carsToRent.get(selectedIndex).getRentalDate() + "");
            returnDateFld.setText(carsToRent.get(selectedIndex).getReturnDate() + "");
            toPayCurrentLbl.setText("£" + getTotalForPay());
            //System.out.print(carsToRent.get(selectedIndex).getRentalDate() + "  ");
            //System.out.print(carsToRent.get(selectedIndex).getReturnDate() + "  ");
            
            System.out.println(getTotalForPay());

            
            toPayCurrentLbl.setVisible(true);
            rentalDateLbl.setVisible(true);
            returnDateLbl.setVisible(true);   
            rentalDateFld.setVisible(true);
            returnDateFld.setVisible(true);  
            returnCarButton.setVisible(true);
            carAvailable.setVisible(false);
            toPayLbl.setVisible(true);
        } else {
            rentalDateLbl.setVisible(false);
            returnDateLbl.setVisible(false);
            rentalDateFld.setVisible(false);
            returnDateFld.setVisible(false);
            toPayLbl.setVisible(false);
            toPayCurrentLbl.setVisible(false);
            returnCarButton.setVisible(false);
            carAvailable.setVisible(true);
            toPayCurrentLbl.setVisible(false);


            

        }
    }

    private void rentCarButtonAction(ActionEvent evt) {
        int selectedIndex = carList.getSelectedIndex();
        carsToRent.get(selectedIndex).setRentalDate(""); // tutaj dodaje do listy 
        carsToRent.get(selectedIndex).setReturnDate("");
        carsToRent.get(selectedIndex).setCustomerName("");
        carsToRent.get(selectedIndex).setOnLoan(false); 
        
        updateCarToRentToFile();

    }

    // The format for date is YYYY-MM-DD. 
    // Will not work if the format is incorrect.


    public int getDaysBetween(String rentalDate, String returnDate) {

        LocalDate dateBefore = LocalDate.parse(rentalDate);
        LocalDate dateAfter = LocalDate.parse(returnDate);

        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);

        return (int) noOfDaysBetween;
    }


public String getTotalForPay(){

    int selectedIndex = carList.getSelectedIndex(); 
    int numberOfDays = getDaysBetween(carsToRent.get(selectedIndex).getRentalDate(), carsToRent.get(selectedIndex).getReturnDate());
    int total = numberOfDays * carsToRent.get(selectedIndex).getDailyRate() + carsToRent.get(selectedIndex).getAdminFee();
    String totalForPay = Integer.toString(total);

    return totalForPay;

}


    public void updateCarToRentToFile() {

        try {
            FileOutputStream file = new FileOutputStream("carsToRent.dat"); // try to create a file if not created
            ObjectOutputStream outputFile = new ObjectOutputStream(file);

            for (int i = 0; i < carsToRent.size(); i++) {

                outputFile.writeObject(carsToRent.get(i));

            }

            outputFile.close();

           

            JOptionPane.showMessageDialog(null, "Car to rent has been succesfully returned!.");
            this.dispose();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }

}

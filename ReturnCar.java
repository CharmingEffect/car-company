
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.awt.*;
import java.beans.XMLDecoder;
import java.awt.event.*;

public class ReturnCar extends JFrame {

    // init variables

    ArrayList<CarToRent> carsToRent;

    private JButton returnCarButton;

    private JLabel labelInfo;

    private JLabel rentalDateLbl;
    private JLabel returnDateLbl;
    private JLabel clientNameLbl;

    private JList<String> carList;

    private JScrollPane carListScroll;

    private JTextArea descriptionCurrentLbl;
    private JLabel adminFeeCurrentLbl;
    private JLabel dailyRateCurrentLbl;
    private JLabel rentalDateFld;
    private JLabel returnDateFld;
    private JLabel clientNameFld;

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

        clientNameLbl = new JLabel("Rented by: ");
        panel.add(clientNameLbl);
        clientNameLbl.setBounds(200, 100, 200, 100);

        clientNameFld = new JLabel();
        panel.add(clientNameFld);
        clientNameFld.setBounds(280, 140, 100, 20);

        carListScroll.setViewportView(carList);
        panel.add(carListScroll);
        carListScroll.setBounds(25, 70, 150, 300);

        returnCarButton = new JButton("Return Car");
        panel.add(returnCarButton);
        returnCarButton.setBounds(260, 350, 150, 30);

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

            rentalDateFld.setText(carsToRent.get(selectedIndex).getRentalDate() + "");
            returnDateFld.setText(carsToRent.get(selectedIndex).getReturnDate() + "");
            clientNameFld.setText(carsToRent.get(selectedIndex).getCustomerName() + "");

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


    public void updateCarToRentToFile() {

        try {
            FileOutputStream file = new FileOutputStream("carsToRent.dat"); // try to create a file if not created
            ObjectOutputStream outputFile = new ObjectOutputStream(file);

            for (int i = 0; i < carsToRent.size(); i++) {

                outputFile.writeObject(carsToRent.get(i));

            }

            outputFile.close();

            JOptionPane.showMessageDialog(null, "Car to rent has been succesfully rented!");
            this.dispose();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }

}

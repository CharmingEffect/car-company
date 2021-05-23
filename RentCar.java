
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

public class RentCar extends JFrame {

    // init variables

    ArrayList<CarToRent> carsToRent;

    private JButton rentButton;

    private JLabel labelInfo;

    private JLabel descriptionLbl;
    private JLabel adminFeeLbl;
    private JLabel dailyRateLbl;
    private JLabel rentalDateLbl;
    private JLabel returnDateLbl;
    private JLabel clientNameLbl;

    private JList<String> carList;

    private JScrollPane carListScroll;

    private JTextArea descriptionCurrentLbl;
    private JLabel adminFeeCurrentLbl;
    private JLabel dailyRateCurrentLbl;
    private JTextField rentalDateFld;
    private JTextField returnDateFld;
    private JTextField clientNameFld;

    DefaultListModel listModel = new DefaultListModel();

    public RentCar() {

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
        this.setTitle("Choose and rent the car");
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

        descriptionLbl = new JLabel("Description:");
        panel.add(descriptionLbl);
        descriptionLbl.setBounds(200, 60, 200, 100);

        descriptionCurrentLbl = new JTextArea();
        panel.add(descriptionCurrentLbl);
        descriptionCurrentLbl.setBounds(200, 120, 200, 100);
        descriptionCurrentLbl.setLineWrap(true);
        descriptionCurrentLbl.setEditable(false);

        // na gorze

        adminFeeLbl = new JLabel("Admin Fee: ");
        panel.add(adminFeeLbl);
        adminFeeLbl.setBounds(200, 20, 200, 100);

        adminFeeCurrentLbl = new JLabel();
        panel.add(adminFeeCurrentLbl);
        adminFeeCurrentLbl.setBounds(310, 20, 200, 100);

        dailyRateLbl = new JLabel("Daily Rate:");
        panel.add(dailyRateLbl);
        dailyRateLbl.setBounds(200, 40, 200, 100);

        dailyRateCurrentLbl = new JLabel();
        panel.add(dailyRateCurrentLbl);
        dailyRateCurrentLbl.setBounds(310, 40, 200, 100);

        rentalDateLbl = new JLabel("Rental date:");
        panel.add(rentalDateLbl);
        rentalDateLbl.setBounds(200, 200, 200, 100);

        rentalDateFld = new JTextField(15);
        panel.add(rentalDateFld);
        rentalDateFld.setBounds(310, 242, 100, 20);

        returnDateLbl = new JLabel("Return date:");
        panel.add(returnDateLbl);
        returnDateLbl.setBounds(200, 230, 200, 100);

        returnDateFld = new JTextField(15);
        panel.add(returnDateFld);
        returnDateFld.setBounds(310, 272, 100, 20);

        clientNameLbl = new JLabel("Customer's name:");
        panel.add(clientNameLbl);
        clientNameLbl.setBounds(200, 260, 200, 100);

        clientNameFld = new JTextField(15);
        panel.add(clientNameFld);
        clientNameFld.setBounds(310, 302, 100, 20);

        carListScroll.setViewportView(carList);
        panel.add(carListScroll);
        carListScroll.setBounds(25, 70, 150, 300);

        rentButton = new JButton("Rent This Car");
        panel.add(rentButton);
        rentButton.setBounds(260, 350, 150, 30);

        carListScroll.setViewportView(carList);

        carList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                carListAction();
            }

        }

        );

        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                rentCarButtonAction(evt);
            }
        });

        pack();
    }

    private void carListAction() {
        int selectedIndex = carList.getSelectedIndex();

        if (carsToRent.get(selectedIndex).isOnLoan() == false) {

            descriptionCurrentLbl.setText(carsToRent.get(selectedIndex).getDescription());
            adminFeeCurrentLbl.setText(carsToRent.get(selectedIndex).getAdminFee() + "");
            dailyRateCurrentLbl.setText(carsToRent.get(selectedIndex).getDailyRate() + "");

        } else {

            //JOptionPane.showMessageDialog(null, "This car has been bought already");
        }
    }

    private void rentCarButtonAction(ActionEvent evt) {
        int selectedIndex = carList.getSelectedIndex();

        carsToRent.remove(selectedIndex).setOnLoan(true);
        updateCarToRentToFile();

    }

    public int daysBetween(String rentalDate, String returnDate) {

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

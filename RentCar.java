
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.awt.*;
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
    private JLabel dailyRateCurrentLbl, returnDateLblInfo, returnDateLblCurrentInfo;
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
        this.setResizable(false);
        carListScroll = new JScrollPane();
        carList = new JList<>();

        JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel);

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

        // change for formatted jlabel 

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

        //-----------

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

        //info afer clear

        returnDateLblInfo = new JLabel("<html>This car is not available. <br> Should be returned on :<html>");
        panel.add(returnDateLblInfo);
        returnDateLblInfo.setBounds(230, 30, 200, 100);

        returnDateLblCurrentInfo = new JLabel();
        panel.add(returnDateLblCurrentInfo);
        returnDateLblCurrentInfo.setBounds(250, 100, 100, 20);

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

private void clear(){

    descriptionLbl.setVisible(false);
    descriptionCurrentLbl.setVisible(false);
    adminFeeCurrentLbl.setVisible(false);
    adminFeeLbl.setVisible(false);
    rentalDateFld.setVisible(false);
    rentalDateLbl.setVisible(false);
    returnDateLbl.setVisible(false);
    returnDateFld.setVisible(false);
    dailyRateCurrentLbl.setVisible(false);
    dailyRateLbl.setVisible(false);
    clientNameFld.setVisible(false);
    clientNameLbl.setVisible(false);
    rentButton.setVisible(false);
}

private void display(){
    descriptionLbl.setVisible(true);
    descriptionCurrentLbl.setVisible(true);
    adminFeeCurrentLbl.setVisible(true);
    adminFeeLbl.setVisible(true);
    rentalDateFld.setVisible(true);
    rentalDateLbl.setVisible(true);
    returnDateLbl.setVisible(true);
    returnDateFld.setVisible(true);
    dailyRateCurrentLbl.setVisible(true);
    dailyRateLbl.setVisible(true);
    clientNameFld.setVisible(true);
    clientNameLbl.setVisible(true);
    rentButton.setVisible(true);
}






    private void carListAction() {
        int selectedIndex = carList.getSelectedIndex();
        
        if (carsToRent.get(selectedIndex).isOnLoan() == false){
   
            descriptionCurrentLbl.setText(carsToRent.get(selectedIndex).getDescription());
            adminFeeCurrentLbl.setText(carsToRent.get(selectedIndex).getAdminFee() + "");
            dailyRateCurrentLbl.setText(carsToRent.get(selectedIndex).getDailyRate() + "");
            display();

            returnDateLblInfo.setVisible(false);
            returnDateLblCurrentInfo.setVisible(false);

        }else{
            clear();
            returnDateLblCurrentInfo.setText(carsToRent.get(selectedIndex).getReturnDate());
            returnDateLblInfo.setVisible(true);
            returnDateLblCurrentInfo.setVisible(true);


        }
        
    }

    private void rentCarButtonAction(ActionEvent evt) {
        int selectedIndex = carList.getSelectedIndex();
        carsToRent.get(selectedIndex).setRentalDate(rentalDateFld.getText()); // tutaj dodaje do listy 
        carsToRent.get(selectedIndex).setReturnDate(returnDateFld.getText());
        carsToRent.get(selectedIndex).setCustomerName(clientNameFld.getText());
        carsToRent.get(selectedIndex).setOnLoan(true);
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

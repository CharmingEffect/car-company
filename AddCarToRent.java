import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddCarToRent extends JFrame
{
   // initialization of the variables / components

    //labels
    private JLabel carNameLbl;
    private JLabel descriptionLbl;

    private JLabel rentalDateLbl;
    private JLabel returnDateLbl;
    private JLabel adminFeeLbl;
    private JLabel numberOfDaysLbl;
    private JLabel dailyRateLbl;

    //fields
    private JTextField carNameFld;
    private JTextField descriptionFld;

    private JTextField rentalDateFld;
    private JTextField returnDateFld;
    private JTextField adminFeeFld;
    private JTextField numberOfDaysFld;
    private JTextField dailyRateFld;
    
    private JButton addButton;
    
    ArrayList<CarToRent> carsToRent; // it creates set with the cars to rent
        

    AddCarToRent(){
    
    initGui(); 
    carsToRent = new ArrayList<CarToRent>();
    populateArrayList();
    
    
    }
    
    // intittalization of the components of the graphical user interface
    private void initGui(){
        
        //labels
        
        carNameLbl = new JLabel("Car's name:");
        descriptionLbl = new JLabel("Description:");
        adminFeeLbl = new JLabel("Admin Fee:");
        dailyRateLbl = new JLabel("Daily Rate:");
      
        
        //fields
        carNameFld = new JTextField();
        descriptionFld = new JTextField();
        adminFeeFld = new JTextField();
        dailyRateFld = new JTextField();
      
        
        addButton = new JButton("Add");
        
        //settings for the window
       
        setTitle("Add car to rent");
        setResizable(false);
        
       
    
        
        // set up leayout for the components
        
   GroupLayout jPanel1Layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(addButton)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)    
                            .addComponent(adminFeeLbl)
                            .addComponent(descriptionLbl)
                            .addComponent(dailyRateLbl)
                            .addComponent(carNameLbl))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(descriptionFld)
                            .addComponent(adminFeeFld)
                            .addComponent(dailyRateFld, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(carNameFld))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(carNameLbl))
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(carNameFld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionLbl)
                    .addComponent(descriptionFld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
               
               
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(adminFeeFld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminFeeLbl))
                    .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dailyRateFld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(dailyRateLbl))
                    .addGap(20, 20, 20)
                .addComponent(addButton)
                .addGap(20, 20, 20))
        );
        
         addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addButtonActionPerformed(e);
            }
        });
        
        pack();
    }
    
      private void addButtonActionPerformed(ActionEvent e) {                                         
        if (carNameFld.getText().isEmpty() || descriptionFld.getText().isEmpty() || rentalDateFld.getText().isEmpty() || returnDateFld.getText().isEmpty() || adminFeeFld.getText().isEmpty() || numberOfDaysFld.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Please fill all fields");

        }
        else {
        
        String carName = carNameFld.getText().trim();
        String description = descriptionFld.getText().trim();
        String rentalDate = rentalDateFld.getText().trim();
        String returnDate = returnDateFld.getText().trim();
        int adminFee = Integer.parseInt(adminFeeFld.getText().trim());
        int dailyRate = Integer.parseInt(dailyRateFld.getText().trim());
        int numberOfDays = Integer.parseInt(numberOfDaysFld.getText().trim());
        boolean onLoan = false; 

        int totalAccumulated = numberOfDays * dailyRate + adminFee;
        
        CarToRent carToRent = new CarToRent(rentalDate, returnDate, adminFee, numberOfDays, dailyRate, totalAccumulated, onLoan, carName, description); //change here for car to rent 
        carsToRent.add(carToRent);     
        saveCarToRentToFile();       
        
        }
    }                                        

    // descibe this 
    public void populateArrayList() {   //populate array list 

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
    
    
      public void saveCarToRentToFile() {

        try {
            FileOutputStream file = new FileOutputStream("carsToRent.dat"); // try to create a file if not created
            ObjectOutputStream outputFile = new ObjectOutputStream(file);

            for (int i = 0; i < carsToRent.size(); i++) {

                outputFile.writeObject(carsToRent.get(i));

            }

            outputFile.close();

            JOptionPane.showMessageDialog(null, "Car to rent has been succesfully added!");
            this.dispose();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }
    
    
}

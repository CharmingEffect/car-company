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
    private JLabel carNameLbl, descriptionLbl, adminFeeLbl, dailyRateLbl;
    private JTextField carNameFld,  descriptionFld, adminFeeFld, dailyRateFld;
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
        carNameFld = new JTextField(15);
        descriptionFld = new JTextField(15);
        adminFeeFld = new JTextField(15);
        dailyRateFld = new JTextField(15);
      
        
        addButton = new JButton("Add");
        
        //settings for the window
       
        setTitle("Add car to rent");
        setResizable(false);
        
        JPanel newPanel = new JPanel(new GridBagLayout());
         
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(10, 10, 10, 10);
         
        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;     
        newPanel.add(carNameLbl, constraints);
 
        constraints.gridx = 1;
        newPanel.add(carNameFld, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 1;     
        newPanel.add( descriptionLbl, constraints);
         
        constraints.gridx = 1;
        newPanel.add(descriptionFld, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;     
        newPanel.add(adminFeeLbl, constraints);
         
        constraints.gridx = 1;
        newPanel.add(adminFeeFld, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;     
        newPanel.add( dailyRateLbl, constraints);
         
        constraints.gridx = 1;
        newPanel.add(dailyRateFld, constraints);

       
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.EAST;
        newPanel.add(addButton, constraints);
    
         
        // add the panel to this frame
        add(newPanel);
        
  
        
         addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addButtonActionPerformed(e);
            }
        });
        
        pack();
    }
    
      private void addButtonActionPerformed(ActionEvent e) {                                         
        if (carNameFld.getText().isEmpty() || descriptionFld.getText().isEmpty() ||  adminFeeFld.getText().isEmpty() || dailyRateFld.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Please fill all fields");

        }
        else {
        
        String carName = carNameFld.getText().trim();
        String description = descriptionFld.getText().trim();
        String rentalDate = "";
        String returnDate = "";
        String customerName = "";
        int adminFee = Integer.parseInt(adminFeeFld.getText().trim());
        int dailyRate = Integer.parseInt(dailyRateFld.getText().trim());
        int numberOfDays = 0;
        boolean onLoan = false; 
        

        int totalAccumulated = numberOfDays * dailyRate + adminFee;
        
        CarToRent carToRent = new CarToRent(rentalDate, returnDate, adminFee, numberOfDays, dailyRate, totalAccumulated, onLoan, carName, description, customerName); 
        carsToRent.add(carToRent);     
        saveCarToRentToFile();       
        
        }
    }                                        


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

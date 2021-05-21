import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Write a description of class addCarToBuy here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AddCarToBuy extends JFrame
{
   // initialization of the variables / components

    private JLabel nameLabel;
    private JLabel descriptionLabel;
    private JLabel priceLabel;
    private JLabel regYearLabel;
    private JLabel mileageLabel;
    private JTextField carNameField;
    private JTextField descriptionField;
    private JTextField mileageField;
    private JTextField priceField;
    private JTextField regYearField;
    
    private JButton addButton;
    
    ArrayList<CarToBuy> carsToBuy; // it creates set with the cars to buy
        

    AddCarToBuy(){
    
    initGui(); 
    carsToBuy = new ArrayList<CarToBuy>();
    populateArrayList();
    
    
    }
    
    // intittalization of the components of the graphical user interface
    private void initGui(){
        
        //labels
        
        nameLabel = new JLabel("Car's name:");
        descriptionLabel = new JLabel("Description:");
        priceLabel= new JLabel("Price:");
        regYearLabel= new JLabel("Registration year:");
        mileageLabel = new JLabel("Mileage:");
        
        //fields
        descriptionField = new JTextField(15);
        priceField = new JTextField(15);
        regYearField = new JTextField(15);
        mileageField = new JTextField(15);
        carNameField = new JTextField(15);
        
        addButton = new JButton("Add");
        
        //settings for the window
  
        setTitle("Add car to buy");
        setResizable(false);
        
       
    
        

         
        // create a new panel with GridBagLayout manager
        JPanel newPanel = new JPanel(new GridBagLayout());
         
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(10, 10, 10, 10);
         
        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;     
        newPanel.add(nameLabel, constraints);
 
        constraints.gridx = 1;
        newPanel.add(carNameField, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 1;     
        newPanel.add(descriptionLabel, constraints);
         
        constraints.gridx = 1;
        newPanel.add(descriptionField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;     
        newPanel.add(priceLabel, constraints);
         
        constraints.gridx = 1;
        newPanel.add(priceField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;     
        newPanel.add(priceLabel, constraints);
         
        constraints.gridx = 1;
        newPanel.add(priceField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;     
        newPanel.add(regYearLabel, constraints);
         
        constraints.gridx = 1;
        newPanel.add(regYearField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;     
        newPanel.add(mileageLabel, constraints);
         
        constraints.gridx = 1;
        newPanel.add(mileageField, constraints);



       
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.EAST;
        newPanel.add(addButton, constraints);
    
         
        // add the panel to this frame
        add(newPanel);
         
        pack();
  
         addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addButtonActionPerformed(e);
            }
        });
        
        pack();
    }
    
      private void addButtonActionPerformed(ActionEvent e) {                                         
        if (descriptionField.getText().isEmpty() || mileageField.getText().isEmpty() || priceField.getText().isEmpty() || regYearField.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Please fill all fields");

        }

        else {
        
        String carName = carNameField.getText().trim();
        String description = descriptionField.getText().trim();
        int mileage = Integer.parseInt(mileageField.getText().trim());
        double price = Double.parseDouble(priceField.getText().trim());
        int regYear = Integer.parseInt(regYearField.getText().trim());
        boolean sold = false; 
        
        CarToBuy carToBuy = new CarToBuy(price, regYear, mileage, sold, carName, description);   
        carsToBuy.add(carToBuy);     
        saveCarToBuyToFile();       
        
        }
    }                                        

    // describe this 
    public void populateArrayList() {   //populate array list 

        try {

            FileInputStream file = new FileInputStream("carsToBuy.dat");
            ObjectInputStream inputFile = new ObjectInputStream(file);

            boolean endOfFile = false;

            while (!endOfFile) {

                try {

                    carsToBuy.add((CarToBuy) inputFile.readObject());

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
    
    
      public void saveCarToBuyToFile() {

        try {
            FileOutputStream file = new FileOutputStream("carsToBuy.dat"); // try to create a file if not created
            ObjectOutputStream outputFile = new ObjectOutputStream(file);

            for (int i = 0; i < carsToBuy.size(); i++) {

                outputFile.writeObject(carsToBuy.get(i));

            }

            outputFile.close();

            JOptionPane.showMessageDialog(null, "Car to buy has been succesfully added!");
            this.dispose();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }
    
    
}

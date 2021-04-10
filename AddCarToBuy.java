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
        descriptionField = new JTextField();
        priceField = new JTextField();
        regYearField = new JTextField();
        mileageField = new JTextField();
        carNameField = new JTextField();
        
        addButton = new JButton("Add");
        
        //settings for the window
  
        setTitle("Add car to buy");
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
                            .addComponent(mileageLabel)
                            .addComponent(regYearLabel)
                            .addComponent(priceLabel)
                            .addComponent(descriptionLabel)
                            .addComponent(nameLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(descriptionField)
                            .addComponent(priceField)
                            .addComponent(regYearField)
                            .addComponent(mileageField, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .addComponent(carNameField))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(nameLabel))
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(carNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionLabel)
                    .addComponent(descriptionField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(priceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(regYearLabel)
                    .addComponent(regYearField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mileageField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(mileageLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(addButton)
                .addGap(35, 35, 35))
        );
        
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
        
        CarToBuy carToBuy = new CarToBuy(price, regYear, mileage,  sold, carName, description);   
        carsToBuy.add(carToBuy);     
        saveCarToBuyToFile();       
        
        }
    }                                        

    // descibe this 
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

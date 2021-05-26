

import javax.swing.*;
import java.util.*;  // for arraylist
import java.io.*;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.*;
import java.awt.*;

public class EditCarToBuy extends JFrame{


//initialize the variables 

ArrayList<CarToBuy> carsToBuy;

private JButton deleteBtn;
private JTextField descriptionField;
private JComboBox<String> listCarsComboBox;
private JLabel descriptionLabel;
private JLabel priceLabel;
private JLabel regYearLabel;
private JLabel mileageLabel;
private JLabel nameLabel;
private JTextField mileageField;
private JTextField priceField;
private JTextField regYearField;
private JButton saveBtn;



public EditCarToBuy() {

    initGui();

    carsToBuy = new ArrayList<CarToBuy>();
    populateArrayList();

    String[] carsToBuyArray = new String[carsToBuy.size()];
        for (int i = 0; i < carsToBuy.size(); i++) {

            carsToBuyArray[i] = carsToBuy.get(i).getCarName();

        }

        listCarsComboBox.setModel(new DefaultComboBoxModel<>(carsToBuyArray));
        listCarsComboBox.setSelectedIndex(0);


}
public void populateArrayList() {   

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
        FileOutputStream file = new FileOutputStream("carsToBuy.dat"); // try to create a file if not created, if already created save the car to the file
        ObjectOutputStream outputFile = new ObjectOutputStream(file);

        for (int i = 0; i < carsToBuy.size(); i++) {

            outputFile.writeObject(carsToBuy.get(i));

        }

        outputFile.close();

        JOptionPane.showMessageDialog(null, "Sucessfully edited");
        this.dispose();

    } catch (IOException e) {

        JOptionPane.showMessageDialog(null, e.getMessage());

    }

}


public void deleteCarFromFile() {

    try {
        FileOutputStream file = new FileOutputStream("carsToBuy.dat"); // try to create a file if not created
        ObjectOutputStream outputFile = new ObjectOutputStream(file);

        for (int i = 0; i < carsToBuy.size(); i++) {

            outputFile.writeObject(carsToBuy.get(i));

        }

        outputFile.close();

        JOptionPane.showMessageDialog(null, "Sucessfully deleted");
        this.dispose();

    } catch (IOException e) {

        JOptionPane.showMessageDialog(null, e.getMessage());

    }

}



private void initGui(){

   

    //labels
    descriptionLabel = new JLabel("Description:");
    priceLabel = new JLabel("Price:");
    regYearLabel = new JLabel("Registration year:");
    mileageLabel = new JLabel("Mileage:");
    nameLabel = new JLabel("Car's name:");
    listCarsComboBox = new JComboBox<>();
    
    //fields

    descriptionField = new JTextField(15);
    priceField = new JTextField(15);
    regYearField = new JTextField(15);
    mileageField = new JTextField(15);

    
    //buttons
    deleteBtn = new JButton("Delete");
    saveBtn = new JButton("Save");



    // options 
    setTitle("Edit car to buy");
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  

 // Layout for the components 

  // create a new panel with GridBagLayout manager
  JPanel newPanel = new JPanel(new GridBagLayout());
         
  GridBagConstraints constraints = new GridBagConstraints();
  constraints.anchor = GridBagConstraints.EAST;
  constraints.insets = new Insets(10, 10, 10, 10);
   
  // add components to the panel
  constraints.gridx = 0;
  constraints.gridy = 0;     
  newPanel.add(nameLabel, constraints);

  constraints.anchor = GridBagConstraints.WEST;
  constraints.gridx = 1;

 
  newPanel.add(listCarsComboBox, constraints);

 constraints.anchor = GridBagConstraints.EAST;

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
  constraints.anchor = GridBagConstraints.WEST;
  newPanel.add(saveBtn, constraints);

  constraints.gridx = 1;
  constraints.gridy = 5;
  constraints.gridwidth = 1;
  constraints.anchor = GridBagConstraints.EAST;
  newPanel.add(deleteBtn , constraints);



   
  // add the panel to this frame
  add(newPanel);

    listCarsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    listCarsComboBox.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            listCarsActionPerformed(evt);
        }
    });

    saveBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            saveBtnActionPerformed(evt);
        }
    });

    deleteBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            deleteBtnActionPerformed(evt);
        }
    });

    pack();



}

 // action method for save button

private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {                                        
    if (descriptionField.getText().isEmpty() || mileageField.getText().isEmpty() || priceField.getText().isEmpty() || regYearField.getText().isEmpty()) {

        JOptionPane.showMessageDialog(null, "Please fill all fields");

    } else {
        int selectedIndex = listCarsComboBox.getSelectedIndex();
        carsToBuy.get(selectedIndex).setDescription(descriptionField.getText().trim());
        carsToBuy.get(selectedIndex).setPrice(Double.parseDouble(priceField.getText().trim()));
        carsToBuy.get(selectedIndex).setRegYear(Integer.parseInt(regYearField.getText().trim()));
        carsToBuy.get(selectedIndex).setMileage(Integer.parseInt(mileageField.getText().trim()));

        //CarToBuy carToBuy = carsToBuy.get(jComboBox1.getSelectedIndex()); 
        saveCarToBuyToFile();

    }

} 

 // action method for delete  button

private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
    int selectedIndex = listCarsComboBox.getSelectedIndex();
    carsToBuy.remove(selectedIndex);
    deleteCarFromFile();

} 


//action method for 

private void listCarsActionPerformed(ActionEvent evt) {   
    

    int selectedIndex = listCarsComboBox.getSelectedIndex();
    descriptionField.setText(carsToBuy.get(selectedIndex).getDescription());
    priceField.setText(carsToBuy.get(selectedIndex).getPrice() + "");
    regYearField.setText(carsToBuy.get(selectedIndex).getRegYear() + "");
    mileageField.setText(carsToBuy.get(selectedIndex).getMileage() + "");

   
        
    
}

}   








import javax.swing.*;
import java.util.*;  // for arraylist
import java.io.*;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.*;

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

    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    //labels
    descriptionLabel = new JLabel("Description:");
    priceLabel = new JLabel("Price:");
    regYearLabel = new JLabel("Registration year:");
    mileageLabel = new JLabel("Mileage:");
    nameLabel = new JLabel("Car's name:");
    listCarsComboBox = new JComboBox<>();
    
    //fields

    descriptionField = new JTextField();
    priceField = new JTextField();
    regYearField = new JTextField();
    mileageField = new JTextField();

    
    //buttons
    deleteBtn = new JButton("Delete");
    saveBtn = new JButton("Save");



    // options 
    setTitle("Edit car to buy");
    setResizable(false);


 // Layout for the components 

    GroupLayout groupLayout = new GroupLayout(getContentPane());
    getContentPane().setLayout(groupLayout);
    groupLayout.setHorizontalGroup(
        groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
            .addGap(65, 65, 65)
            .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addComponent(saveBtn)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(deleteBtn))
                .addGroup(groupLayout.createSequentialGroup()
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(nameLabel)
                        .addComponent(mileageLabel)
                        .addComponent(regYearLabel)
                        .addComponent(priceLabel)
                        .addComponent(descriptionLabel))
                    .addGap(18, 18, 18)
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(descriptionField)
                        .addComponent(priceField)
                        .addComponent(regYearField)
                        .addComponent(mileageField)
                        .addComponent(listCarsComboBox, 0, 257, Short.MAX_VALUE))))
            .addContainerGap(64, Short.MAX_VALUE))
    );
    groupLayout.setVerticalGroup(
        groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
            .addGap(37, 37, 37)
            .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(nameLabel)
                .addComponent(listCarsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(descriptionLabel)
                .addComponent(descriptionField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(priceLabel)
                .addComponent(priceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(regYearLabel)
                .addComponent(regYearField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(mileageLabel)
                .addComponent(mileageField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGap(32, 32, 32)
            .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(deleteBtn)
                .addComponent(saveBtn))
            .addContainerGap(49, Short.MAX_VALUE))
    );

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



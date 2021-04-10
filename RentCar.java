
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.beans.XMLDecoder;
import java.awt.event.*;

public class RentCar extends JFrame {

    ArrayList<CarToBuy> carsToBuy;
    
    private JButton buyButton;

    private JLabel labelInfo;

    private JLabel descriptionLbl;
    private JLabel priceLbl;
    private JLabel regYearLbl;
    private JLabel mileageLbl;

    private JList<String> carList;

    private JScrollPane carListScroll;

    private JLabel descriptionCurrentLbl;
    private JLabel priceCurrentLbl;
    private JLabel regYearCurrentLbl;
    private JLabel mileageCurrentLbl;
    DefaultListModel listModel = new DefaultListModel();
    public RentCar() {

        initGui();
     
        carsToBuy = new ArrayList<CarToBuy>();
        populateArrayList();

        String[] carsToBuyArray = new String[carsToBuy.size()];
       
        
       
        for (int i = 0; i < carsToBuy.size(); i++) {
            
            
            carsToBuyArray[i] = carsToBuy.get(i).getCarName();
            
            listModel.addElement(carsToBuyArray[i]);
           
            
        
        }

        carList.setModel(listModel);
        carList.setSelectedIndex(0);

    }

    public void populateArrayList() { // populate array list

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

    private void initGui() {
        this.setTitle("Rent car");
        this.setResizable(false);
        carListScroll = new JScrollPane();
        carList = new JList<>();

        labelInfo = new JLabel("Available cars to buy:");

        descriptionLbl = new JLabel("Description:");
        priceLbl = new JLabel("Price:");
        regYearLbl = new JLabel("Registration Year:");
        mileageLbl = new JLabel("Mileage:");

        descriptionCurrentLbl = new JLabel();
        priceCurrentLbl = new JLabel();
        regYearCurrentLbl = new JLabel();
        mileageCurrentLbl = new JLabel();

        // descriptionLbl.setFont (descriptionLbl.getFont ().deriveFont (64.0f));

        buyButton = new JButton("Buy This Car");

       

        carListScroll.setViewportView(carList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(60, 60, 60).addComponent(labelInfo)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup().addGap(24, 24, 24)
                        .addComponent(carListScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 193,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                                .createSequentialGroup().addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup().addComponent(descriptionLbl)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(descriptionCurrentLbl))
                                        .addGroup(layout.createSequentialGroup().addComponent(priceLbl)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(priceCurrentLbl))
                                        .addGroup(layout.createSequentialGroup().addComponent(regYearLbl)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(regYearCurrentLbl))
                                        .addGroup(layout.createSequentialGroup().addComponent(mileageLbl)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(mileageCurrentLbl)))
                                .addContainerGap(210, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                        layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(buyButton).addGap(35, 35, 35)))));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup().addGap(33, 33, 33).addComponent(labelInfo).addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(carListScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 306,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(22, Short.MAX_VALUE))
                        .addGroup(
                                layout.createSequentialGroup().addGap(41, 41, 41)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(descriptionLbl).addComponent(descriptionCurrentLbl))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(priceLbl).addComponent(priceCurrentLbl))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(regYearLbl).addComponent(regYearCurrentLbl))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(mileageLbl).addComponent(mileageCurrentLbl))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buyButton).addGap(45, 45, 45)))));

        

        carList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                carListAction();
            }

        }

        );

        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buyCarButtonAction(evt);
            }
        });

        pack();
    }

    private void carListAction() {
        int selectedIndex = carList.getSelectedIndex();

        if (carsToBuy.get(selectedIndex).isSold() == false) {

            descriptionCurrentLbl.setText(carsToBuy.get(selectedIndex).getDescription());
            priceCurrentLbl.setText(carsToBuy.get(selectedIndex).getPrice() + "");
            regYearCurrentLbl.setText(carsToBuy.get(selectedIndex).getRegYear() + "");
            mileageCurrentLbl.setText(carsToBuy.get(selectedIndex).getMileage() + "");
            

        } else {

            JOptionPane.showMessageDialog(null, "This car has been bought already");
        }
    }

    private void buyCarButtonAction(ActionEvent evt) {
        int selectedIndex = carList.getSelectedIndex();
       carsToBuy.remove(selectedIndex).setSold(true);
       removeCarFromFile();

      
    
   }


   public void removeCarFromFile() {

    try {
        FileOutputStream file = new FileOutputStream("carsToBuy.dat"); // try to create a file if not created
        ObjectOutputStream outputFile = new ObjectOutputStream(file);

        for (int i = 0; i < carsToBuy.size(); i++) {

            outputFile.writeObject(carsToBuy.get(i));

        }

        outputFile.close();

        JOptionPane.showMessageDialog(null, "You have bought this car saccessfully!. Thank you");
        this.dispose();

    } catch (IOException e) {

        JOptionPane.showMessageDialog(null, e.getMessage());

    }


}

}

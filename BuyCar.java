
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.beans.XMLDecoder;
import java.awt.event.*;

public class BuyCar extends JFrame {

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
    private DefaultListModel listModel;

    public BuyCar() {

        initGui();
        
        listModel = new DefaultListModel();
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
        this.setPreferredSize(new Dimension(600, 300));
        this.setTitle("Choose and buy the car");
        //this.setResizable(false);
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


        JPanel leftPanel = new JPanel(new GridLayout());
        leftPanel.add(labelInfo);
        leftPanel.add(carList);
       
       

        JPanel rightPanel = new JPanel(new GridBagLayout());


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(10, 10, 10, 10);
         
        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;     
        rightPanel.add(descriptionLbl, constraints);
 
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 1;
        rightPanel.add(descriptionCurrentLbl, constraints);

        constraints.anchor = GridBagConstraints.EAST; 
        constraints.gridx = 0;
        constraints.gridy = 1;     
        rightPanel.add( priceLbl, constraints);

        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 1;
        rightPanel.add(priceCurrentLbl, constraints);

        constraints.anchor = GridBagConstraints.EAST; 
        constraints.gridx = 0;
        constraints.gridy = 2;     
        rightPanel.add(regYearLbl, constraints);
         
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 1;
        rightPanel.add(regYearCurrentLbl, constraints);

        constraints.anchor = GridBagConstraints.EAST; 
        constraints.gridx = 0;
        constraints.gridy = 3;     
        rightPanel.add( mileageLbl, constraints);

        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 1;
        rightPanel.add(mileageCurrentLbl, constraints);

       
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.EAST;
        rightPanel.add(buyButton, constraints);
    



        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,carList, rightPanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);
        this.getContentPane().add(splitPane, BorderLayout.CENTER);  
        
        rightPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Information about chosen car", TitledBorder.LEFT, TitledBorder.TOP));





        carList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                carListAction();
            }

        }

        );

        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                rentCarButtonAction(evt);
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

    private void rentCarButtonAction(ActionEvent evt) {
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

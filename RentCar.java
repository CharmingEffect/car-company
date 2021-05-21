
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.*;
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

    private JLabel descriptionCurrentLbl;
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
        this.setPreferredSize(new Dimension(600, 400));
        this.setTitle("Choose and rent the car");
        //this.setResizable(false);
        carListScroll = new JScrollPane();
        carList = new JList<>();

        
        carListScroll = new JScrollPane();
        carList = new JList<>();

        labelInfo = new JLabel("Available cars to buy:");

        descriptionLbl = new JLabel("Description:");
        adminFeeLbl = new JLabel("Admin Fee:");
        dailyRateLbl = new JLabel("Daily Rate:");
        rentalDateLbl = new JLabel("Rental date:");
        returnDateLbl = new JLabel("Return date:");
        clientNameLbl = new JLabel("Your name:");
   

        descriptionCurrentLbl = new JLabel();
        adminFeeCurrentLbl = new JLabel();
        dailyRateCurrentLbl = new JLabel();
        rentalDateFld = new JTextField(15);
        returnDateFld = new JTextField(15);
        clientNameFld = new JTextField(15);
        

       

        // descriptionLbl.setFont (descriptionLbl.getFont ().deriveFont (64.0f));

        rentButton = new JButton("Rent This Car");

       

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
        rightPanel.add( adminFeeLbl, constraints);

        constraints.anchor = GridBagConstraints.WEST; 
         
        constraints.gridx = 1;
        rightPanel.add(adminFeeCurrentLbl, constraints);

        constraints.anchor = GridBagConstraints.EAST; 
        constraints.gridx = 0;
        constraints.gridy = 2;     
        rightPanel.add(dailyRateLbl, constraints);

        constraints.anchor = GridBagConstraints.WEST; 
        
        constraints.gridx = 1;
        rightPanel.add(dailyRateCurrentLbl, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;     
        rightPanel.add(rentalDateLbl, constraints);
         
        constraints.gridx = 1;
        rightPanel.add(rentalDateFld, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;     
        rightPanel.add(returnDateLbl, constraints);
         
        constraints.gridx = 1;
        rightPanel.add(returnDateFld, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;     
        rightPanel.add(clientNameLbl, constraints);
         
        constraints.gridx = 1;
        rightPanel.add(clientNameFld, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.EAST;
        rightPanel.add(rentButton, constraints);
    



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

            JOptionPane.showMessageDialog(null, "This car has been bought already");
        }
    }

    private void rentCarButtonAction(ActionEvent evt) {
        int selectedIndex = carList.getSelectedIndex();
       carsToRent.remove(selectedIndex).setOnLoan(true);
      

      
    
   }


   public void removeCarFromFile() {

    try {
        FileOutputStream file = new FileOutputStream("carsToRent.dat"); // try to create a file if not created
        ObjectOutputStream outputFile = new ObjectOutputStream(file);

        for (int i = 0; i < carsToRent.size(); i++) {

            outputFile.writeObject(carsToRent.get(i));

        }

        outputFile.close();

        JOptionPane.showMessageDialog(null, "You have rented this car saccessfully!. Thank you");
        this.dispose();

    } catch (IOException e) {

        JOptionPane.showMessageDialog(null, e.getMessage());

    }


}

}

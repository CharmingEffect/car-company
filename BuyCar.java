
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;


public class BuyCar extends JFrame {

    ArrayList<CarToBuy> carsToBuy;
    
    private JButton buyButton;

    private JLabel labelInfo;

    private JLabel descriptionLbl;
    private JLabel priceLbl;
    private JLabel regYearLbl;
    private JLabel mileageLbl;
    private JLabel customerNameLbl;

    private JList<String> carList;

    private JScrollPane carListScroll;

    private JTextArea descriptionCurrentLbl;
    private JLabel priceCurrentLbl;
    private JLabel regYearCurrentLbl;
    private JLabel mileageCurrentLbl;
    private DefaultListModel listModel;
    private JTextField customerNameFld;

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
        this.setPreferredSize(new Dimension(450, 400));
        this.setTitle("Choose and buy the car");
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel);

        carListScroll = new JScrollPane();
        carList = new JList<>();

        labelInfo = new JLabel("<html>Choose a car from the <br> list to see the details:<html>");
        panel.add(labelInfo);
        labelInfo.setBounds(25, 0, 200, 100);


        descriptionLbl = new JLabel("Description:");
        panel.add(descriptionLbl);
        descriptionLbl.setBounds(200, 100, 200, 100);
       

        descriptionCurrentLbl = new JTextArea();
        panel.add(descriptionCurrentLbl);
        descriptionCurrentLbl.setBounds(200, 170, 200, 100);
        descriptionCurrentLbl.setLineWrap(true);
        descriptionCurrentLbl.setEditable(false);


        priceLbl = new JLabel("Price:");
        panel.add(priceLbl);
        priceLbl.setBounds(200, 40, 200, 100);

        priceCurrentLbl = new JLabel();
        panel.add(priceCurrentLbl);
        priceCurrentLbl.setBounds(310, 40, 200, 100);

        
        regYearLbl = new JLabel("Registration Year:");
        panel.add(regYearLbl);
        regYearLbl.setBounds(200, 60, 200, 100);

        regYearCurrentLbl = new JLabel();
        panel.add(regYearCurrentLbl);
        regYearCurrentLbl.setBounds(310, 60, 200, 100);


        mileageLbl = new JLabel("Mileage:");
        panel.add(mileageLbl);
        mileageLbl.setBounds(200, 20, 200, 100);

        mileageCurrentLbl = new JLabel();
        panel.add(mileageCurrentLbl);     
        mileageCurrentLbl.setBounds(310, 20, 200, 100);

        customerNameLbl = new JLabel("Customer name: ");
        panel.add(customerNameLbl);
        customerNameLbl.setBounds(40, 240, 200, 100);

        customerNameFld = new JTextField(15);
        panel.add(customerNameFld);
        customerNameFld.setBounds(40, 300, 100, 20);

        buyButton = new JButton("Buy This Car");
        panel.add(buyButton); 
        buyButton.setBounds(250, 300, 150, 30);

        carListScroll.setViewportView(carList);
        panel.add(carListScroll);
        carListScroll.setBounds(25, 70, 150, 200);

     

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
            priceCurrentLbl.setText("£" + carsToBuy.get(selectedIndex).getPrice() + "");
            regYearCurrentLbl.setText(carsToBuy.get(selectedIndex).getRegYear() + "");
            mileageCurrentLbl.setText(carsToBuy.get(selectedIndex).getMileage() + "");
            

        } else {

            JOptionPane.showMessageDialog(null, "This car has been bought already");
        }
    }

    private void buyCarButtonAction(ActionEvent evt) {
        if(customerNameFld.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "Please enter your name");

        } else {

       int selectedIndex = carList.getSelectedIndex();
       carsToBuy.get(selectedIndex).setCustomerName(customerNameFld.getText());
       carsToBuy.remove(selectedIndex).setSold(true);
       updateCarToBuyToFile();
    
    }
    
   }

   public void updateCarToBuyToFile() {

    try {
        FileOutputStream file = new FileOutputStream("carsToBuy.dat"); // try to create a file if not created
        ObjectOutputStream outputFile = new ObjectOutputStream(file);

        for (int i = 0; i < carsToBuy.size(); i++) {

            outputFile.writeObject(carsToBuy.get(i));

        }

        outputFile.close();

        JOptionPane.showMessageDialog(null, "You have bought this car saccessfully!. Thank you " + customerNameFld.getText());
        this.dispose();

    } catch (IOException e) {

        JOptionPane.showMessageDialog(null, e.getMessage());

    }

}

}


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.event.*;

public class Menu extends JFrame {

  ImageIcon iconMain = new ImageIcon("icons/icon.png");

  private JLabel background;
  private JMenu fileMenu, addMenu, editMenu, clientMenu, about;
  private JMenuBar menuBar;
  private JMenuItem exitItem, ctbItem, ctbItem2, buyCarItem;
  private JPopupMenu.Separator jSeparator2;
  private JMenuItem carToRentItem;
  private JMenuItem aboutItem;
  private JMenuItem returnCarItem;

  private JMenuItem rentCarItem;
  private ArrayList<CarToBuy> carsToBuy;
  private ImageIcon iconMsg;
  private File carsToBuyFile;
  private File carsToRentFile;
  /**
   * Creates new form Menu
   */
  public Menu() {
    initGui();
    setTitle("Car Company");
    setIconImage(iconMain.getImage());
    carsToBuy = new ArrayList<CarToBuy>();
    iconMsg = new ImageIcon("icons/face_palm.png");
    carsToBuyFile = new File("carsToBuy.dat");
    carsToRentFile = new File("carsToRent.dat");
  }

  private void initGui() {

    background = new JLabel();
    menuBar = new JMenuBar();
    fileMenu = new JMenu();
    exitItem = new JMenuItem();
    addMenu = new JMenu();
    ctbItem = new JMenuItem();
    editMenu = new JMenu();
    jSeparator2 = new JPopupMenu.Separator();
    ctbItem2 = new JMenuItem();
    clientMenu = new JMenu();
    buyCarItem = new JMenuItem();
    about = new JMenu();
    aboutItem = new JMenuItem();
    rentCarItem = new JMenuItem();
    returnCarItem = new JMenuItem();

    carToRentItem = new JMenuItem();

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setResizable(false);

    fileMenu.setText("File");
    exitItem.setText("Exit");

    fileMenu.add(exitItem);
    menuBar.add(fileMenu);
    addMenu.setText("Add");

    ctbItem.setText("Car to buy");
    addMenu.add(ctbItem);
    menuBar.add(addMenu);
    editMenu.setText("Edit");
    editMenu.add(jSeparator2);

    ctbItem2.setText("Car to buy");
    editMenu.add(ctbItem2);
    menuBar.add(editMenu);

    clientMenu.setText("Client");
    buyCarItem.setText("Buy Car");
    clientMenu.add(buyCarItem);

    rentCarItem.setText("Rent car");
    clientMenu.add(rentCarItem);

    returnCarItem.setText("Return Car");
    clientMenu.add(returnCarItem);

    menuBar.add(clientMenu);
    setJMenuBar(menuBar);

   carToRentItem.setText("Car to rent");
   addMenu.add(carToRentItem);

   about.setText("Info");
   
   aboutItem.setText("About");
   about.add(aboutItem);
   
   menuBar.add(about);



    // set up icons for the labels 
    exitItem.setIcon(new ImageIcon(getClass().getResource("/icons/close.png")));
    fileMenu.setIcon(new ImageIcon(getClass().getResource("/icons/file.png")));
    addMenu.setIcon(new ImageIcon(getClass().getResource("/icons/add-main.png")));
    ctbItem.setIcon(new ImageIcon(getClass().getResource("/icons/cartobuy.png")));
    ctbItem2.setIcon(new ImageIcon(getClass().getResource("/icons/cartobuy.png")));
    editMenu.setIcon(new ImageIcon(getClass().getResource("/icons/edit.png")));
    clientMenu.setIcon(new ImageIcon(getClass().getResource("/icons/person-icon.png")));
    carToRentItem.setIcon(new ImageIcon(getClass().getResource("/icons/cartorent.png")));
    buyCarItem.setIcon(new ImageIcon(getClass().getResource("/icons/buycar.png")));
    about.setIcon(new ImageIcon(getClass().getResource("/icons/about.png")));
    aboutItem.setIcon(new ImageIcon(getClass().getResource("/icons/about.png")));
    rentCarItem.setIcon(new ImageIcon(getClass().getResource("/icons/rentcar.png")));
    returnCarItem.setIcon(new ImageIcon(getClass().getResource("/icons/returnCar.png")));
    getContentPane().add(background);

    background.setIcon(new ImageIcon(getClass().getResource("/icons/car.png")));

   

    pack();

    exitItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        System.exit(0);
      }

    });



    ctbItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        AddCarToBuy addCar = new AddCarToBuy();
        addCar.setVisible(true);
        addCar.setLocationRelativeTo(null);
        
      }


    });

    ctbItem2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {


       
        if(carsToBuyFile.exists() && !carsToBuyFile.isDirectory()) { 

        EditCarToBuy editCar = new EditCarToBuy();
        editCar.setVisible(true);
        editCar.setLocationRelativeTo(null);
 
        } else {

          JOptionPane.showMessageDialog(null, "The file does not exists. Please add a car", "Error. File not found", JOptionPane.PLAIN_MESSAGE, iconMsg );

        }

        
    }
    });
  

    buyCarItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {

        if(carsToBuyFile.exists() && !carsToBuyFile.isDirectory()) { 
        
         

          BuyCar buyCar = new BuyCar();
          buyCar.setVisible(true);
          buyCar.setLocationRelativeTo(null);
        } else {

          JOptionPane.showMessageDialog(null, "The file does not exists. Please add a car", "Error. File not found", JOptionPane.PLAIN_MESSAGE, iconMsg );
        }
      }

    });


    carToRentItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        AddCarToRent addCarRent = new AddCarToRent();
        addCarRent.setVisible(true);
        addCarRent.setLocationRelativeTo(null);
       
      }

    });

    
    rentCarItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {

        if(carsToRentFile.exists() && !carsToRentFile.isDirectory()) {
       
        RentCar rentCar = new RentCar();
        rentCar.setVisible(true);
        rentCar.setLocationRelativeTo(null);
        } else {

          JOptionPane.showMessageDialog(null, "The file does not exists. Please add a car", "Error. File not found", JOptionPane.PLAIN_MESSAGE, iconMsg );
        }
      }

    });

    returnCarItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        if(carsToRentFile.exists() && !carsToRentFile.isDirectory()) {

        
        ReturnCar returnCar = new ReturnCar ();
        returnCar.setVisible(true);
        returnCar.setLocationRelativeTo(null);

         } else {

          JOptionPane.showMessageDialog(null, "The file does not exists. Please add a car", "Error. File not found", JOptionPane.PLAIN_MESSAGE, iconMsg );

        }
       
      }

    });

    aboutItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "Car Company 1.0 Created by Arkadiusz Grudzien");
      }

    });
  }

}

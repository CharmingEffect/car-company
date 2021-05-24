
import javax.swing.*;
import java.awt.event.ActionEvent;
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


  /**
   * Creates new form Menu
   */
  public Menu() {
    initGui();
   
    setTitle("Car Company");
    setIconImage(iconMain.getImage());
    
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

   about.setText("Help");
   
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
        EditCarToBuy editCar = new EditCarToBuy();
        editCar.setVisible(true);
        editCar.setLocationRelativeTo(null);
      }

    });

    buyCarItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        BuyCar buyCar = new BuyCar();
        buyCar.setVisible(true);
        buyCar.setLocationRelativeTo(null);
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
        RentCar rentCar = new RentCar();
        rentCar.setVisible(true);
        rentCar.setLocationRelativeTo(null);
       
      }

    });

    returnCarItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        ReturnCar returnCar = new ReturnCar ();
        returnCar.setVisible(true);
        returnCar.setLocationRelativeTo(null);
       
      }

    });

    aboutItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "Car Company 1.0 Created by Arkadiusz Grudzien");
      }

    });
  }

}

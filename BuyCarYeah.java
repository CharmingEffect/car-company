import java.awt.GridLayout;
import javax.swing.*;

public class BuyCarYeah extends javax.swing.JFrame{


    private JLabel[] combo1; //array of JComboBoxes
    private JLabel[] combo2; //array of JComboBoxes
    private JTextField[] text1; //array of JTextFields
    private int count = -1; 
    private int max_row = 10;
    private int empty_count = 0;    
    public BuyCarYeah() {
        initGui(); 


        setTitle("Adding and Removing Components Dynamically From a JPanel in Java");
        combo1_combo2_text1_array(); // declaring array for new row addition
    }


    private void initGui(){
        
        //labels
        
        combo1= new JLabel("");
        descriptionLbl = new JLabel("Description:");
        rentalDateLbl = new JLabel("Rental Date:");
   
        
        //fields
        carNameFld = new JTextField();
        descriptionFld = new JTextField();
        rentalDateFld = new JTextField();
     
        
        addButton = new JButton("Add");
        
        //settings for the window
       
        setTitle("Add car to rent");
      
        
       
    
        
        // set up leayout for the components
        
   GroupLayout jPanel1Layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(addButton)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(combo1)
                            .addComponent(adminFeeLbl)
                            .addComponent(returnDateLbl)
                       
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(descriptionFld)
                            .addComponent(adminFeeFld)
                            .addComponent(dailyRateFld)
                          
                          
                            
                           )))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(carNameLbl))
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(carNameFld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionLbl)
                    .addComponent(descriptionFld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(rentalDateFld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(rentalDateLbl))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(returnDateLbl)
                    .addComponent(returnDateFld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(adminFeeFld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminFeeLbl))
                    .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dailyRateFld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(dailyRateLbl))
                    .addGap(20, 20, 20)

                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(numberOfDaysFld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(numberOfDaysLbl))

                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(addButton)
                .addGap(20, 20, 20))
        );
        
         addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addButtonActionPerformed(e);
            }
        });
        
        pack();
    }


    private void combo1_combo2_text1_array(){
        combo1 = new JComboBox[10];
        combo2 = new JComboBox[10];
        text1 = new JTextField[10];        
    }
    
    private void addrowActionPerformed(java.awt.event.ActionEvent evt) {  // Add one row at a time                                     
        if(count == max_row-1){
            JOptionPane.showMessageDialog(null, "Maximum of 10 rows can be added","Failed!!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        count++;        
        combo1[count] = new javax.swing.JComboBox(); 
        for(int i=1;i<=5;i++){
            combo1[count].addItem("Item " + i);
        }       
        combo2[count] = new javax.swing.JComboBox(); 
        for(int i=1;i<=5;i++){
            combo2[count].addItem("Item " + i);
        }   
        text1[count] = new javax.swing.JTextField();        
        getContentPane().setLayout(new GridLayout(0,3,20,20));
        getContentPane().add(combo1[count]);
        getContentPane().add(combo2[count]);
        getContentPane().add(text1[count]);
        getContentPane().revalidate();
        getContentPane().repaint();
    }


    private void removerowActionPerformed(java.awt.event.ActionEvent evt) {   // Remove Row Action                                       
        if(count > -1){ // Deleting one row at a time
            jPanel2.remove(combo1[count]);
            jPanel2.remove(combo2[count]);
            jPanel2.remove(text1[count]);
            count--;
            jPanel2.revalidate();
            jPanel2.repaint();
        }
    }
    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {   // Cancel Button Action                                     
        System.exit(0);
    }
    private void resetActionPerformed(java.awt.event.ActionEvent evt) { //Reset Button Action                                     
        jTextField1.setText("");
        for(int i=count;i>-1;i--){
            jPanel2.remove(combo1[i]);
            jPanel2.remove(combo2[i]);
            jPanel2.remove(text1[i]);
            jPanel2.revalidate();
            jPanel2.repaint();
        }
        count = -1;
    }                                   
    private void submitActionPerformed(java.awt.event.ActionEvent evt) {                                       
        if(jTextField1.getText().length()==0)  // Submit button for the register form
            JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields","Failed!!",JOptionPane.ERROR_MESSAGE);      
        else if(count > -1){            
            for(int i=0;i<=count;i++){
                if(text1[count].getText().length()==0)  
                    empty_count++;                
            }
            if(empty_count > 0){
                JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields","Failed!!",JOptionPane.ERROR_MESSAGE); 
                empty_count = 0; // resetting the count
            }
            else
                JOptionPane.showMessageDialog(null, "No database connection for this application","Failed!!",JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "No database connection for this application","Failed!!",JOptionPane.ERROR_MESSAGE);        
    }
                                      
    public static void main(String args[]) {
      java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuyCarYeah().setVisible(true);
            }
        });
    }
     // Variables declaration - do not modify                     
    private javax.swing.JButton addrow;
    private javax.swing.JButton cancel;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton removerow;
    private javax.swing.JButton reset;
    private javax.swing.JButton submit;
    // End of variables declaration                   
}
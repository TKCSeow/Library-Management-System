/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiView;

import LibraryModel.Client;
import LibraryModel.Item;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author tseow
 */
public class ClientWindow extends javax.swing.JFrame {

    private Client c;
    private ArrayList<Item> Items = new ArrayList<>();
    private ArrayList<Item> BorrowedItems;
    private Item selectedItem;
    private Item selectedBorrowedItem;
    private LoginWindow logwin;
    /**
     * Creates new form ClientWindow
     */
    public ClientWindow(Client clnt, ArrayList<Item> items, LoginWindow lw) {
        initComponents();
        groupButton();
        logwin = lw;
        c = clnt;
        Items = items;
        BorrowedItems = new ArrayList<>();
        setupWindow();
        updateBorrowedItems();
        updateBorrowedItemsDisplay();
    }

    private void groupButton() {

        ButtonGroup bg1 = new ButtonGroup();
        ButtonGroup bgBorrowLength = new ButtonGroup();
        ButtonGroup bgExtension = new ButtonGroup();
        ButtonGroup bgRatings = new ButtonGroup();

        bg1.add(jSerachIdRadioButton);
        bg1.add(jSearchNameRadioButton);
        
        bgBorrowLength.add(jBorrowLength1);
        bgBorrowLength.add(jBorrowLength2);

        bgExtension.add(j3DayExtension);
        bgExtension.add(j1WeekExtension);
        bgExtension.add(j2WeekExtension);
        
        bgRatings.add(jRating1);
        bgRatings.add(jRating2);
        bgRatings.add(jRating3);
        bgRatings.add(jRating4);
        bgRatings.add(jRating5);
}
    
    private void setupWindow()
    {
        // Displays Welcome message with user's names
         String welcomeText = "Welcome " + c.getFirstName() + " " + c.getLastName();
         jWelcomeLabel.setText(welcomeText);
         
         String listData = "";
         DefaultListModel  dataList = new DefaultListModel();
         for (Item i : Items)
         {
             
             listData = i.getId() + ", " + i.getTitle();
             dataList.addElement(listData);
         }
         
         //Display all resources
         jResourceList.setModel(dataList);
    }
    
    private void updateBorrowedItems()
    {
        BorrowedItems.clear();
        
        for (Item i : Items)
        {
            if (i.getBorrowInfo().getUserID() != null) 
            {
                if (i.getBorrowInfo().getUserID() == c.getId()) {
                    BorrowedItems.add(i);
                }
            }
        }
        updateBorrowedItemsDisplay();
    }
    
     private void updateBorrowedItemsDisplay()
    {
        String listData = "";
         DefaultListModel  dataList = new DefaultListModel();
         for (Item i : BorrowedItems)
         {
             
             listData = i.getId() + ", " + i.getTitle();
             dataList.addElement(listData);
         }
         
         //Display all resources
         jBorrowedItemsList.setModel(dataList);
         
    }
    
     private void updateAvaliabilityDisplay()
    {
        
        String infoText = "";        
        String[] info = jResourceList.getSelectedValue().split(", ");
        
        for (Item i : Items)
        {
            if (Integer.parseInt(info[0]) == i.getId())
            {
                selectedItem = i;
                
                infoText+= i.getTitle() + "\n";
                
                if (i.getBorrowInfo().getIsBorrowed() == false) {
                    infoText += "*In Stock*";
                    jBorrowItem.setEnabled(true);
                    
                }
                else
                {
                    infoText += "*Not in Stock*\nDue to be returned on " + i.getBorrowInfo().getReturnDate(); 
                }
                
                if (i.getRating().getUserRatingList().isEmpty() == true)
                {
                    infoText += "\n\nUser Rating: 0 (No Ratings Yet)";
                }
                else
                {
                    infoText += "\n\nUser Rating: " + i.getRating().getAverageScore() + " (" + i.getRating().getTotalUsersThatRated() + ")";
                }
                
                if (i.getRating().searchRatingById(c.getId()) == 0) {
                    infoText += "\nYour rating: 0 (You have not rated this item yet)";
                }
                else
                {
                    infoText += "\nYour rating: " + i.getRating().searchRatingById(c.getId());
                }
            }
        }
        
        jResourceCheckTextbox.setText(infoText);
        jResourceCheckTextbox.setCaretPosition(0);
    }
     
     private void updateStatusDisplay()
     {
        String infoText = "";        
        
        if (jBorrowedItemsList.getSelectedValue() != null) {
            String[] info = jBorrowedItemsList.getSelectedValue().split(", ");
            
            for (Item i : BorrowedItems)
            {
                if (Integer.parseInt(info[0]) == i.getId())
                {
                    selectedBorrowedItem = i;
                    selectedBorrowedItem.getBorrowInfo().checkOverdue();
                    infoText += selectedBorrowedItem.getTitle();
                    infoText += "\nBorrowed on: " + i.getBorrowInfo().getStartDate();
                    infoText += "\nReturn Date: " + i.getBorrowInfo().getReturnDate();
                    jReturnItem.setEnabled(true);
                    jExtensionButton.setEnabled(true);
                    jGiveRating.setEnabled(true);
                
                    if (i.getBorrowInfo().getIsOverdue() == true)
                    {
                        jPaymentField.setEnabled(true);
                        int pound;
                        int pence;
                        
                        pound = (int)i.getBorrowInfo().getOverdueAmount() / 100;
                        pence = (int) i.getBorrowInfo().getOverdueAmount() - (pound * 100);
                        
                        infoText += "\n\nItem is Overdue! Please return as soon as possible"; 
                        infoText += "\nOverdue Amount Owned: £" + pound + "." + pence;
                    }
                }
            }
        }
        
        jStatusTextbox.setText(infoText);
        jStatusTextbox.setCaretPosition(0);
     }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jWelcomeLabel = new javax.swing.JLabel();
        jLogOut = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jResourceCheckTextbox = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jBorrowedItemsList = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jStatusTextbox = new javax.swing.JTextArea();
        jReturnItem = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jResourceList = new javax.swing.JList<>();
        jBorrowItem = new javax.swing.JButton();
        jSerachIdRadioButton = new javax.swing.JRadioButton();
        jSearchNameRadioButton = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jBorrowLength1 = new javax.swing.JRadioButton();
        jBorrowLength2 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jCheckStatus = new javax.swing.JButton();
        jPaymentField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        j3DayExtension = new javax.swing.JRadioButton();
        j1WeekExtension = new javax.swing.JRadioButton();
        j2WeekExtension = new javax.swing.JRadioButton();
        jExtensionButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jRating1 = new javax.swing.JRadioButton();
        jRating2 = new javax.swing.JRadioButton();
        jRating3 = new javax.swing.JRadioButton();
        jRating4 = new javax.swing.JRadioButton();
        jRating5 = new javax.swing.JRadioButton();
        jGiveRating = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jWelcomeLabel.setText("Welcome");

        jLogOut.setText("Log Out");
        jLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLogOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jWelcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLogOut)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jWelcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLogOut))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Search");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Book", "DVD", "Newspaper" }));

        jResourceCheckTextbox.setEditable(false);
        jResourceCheckTextbox.setColumns(20);
        jResourceCheckTextbox.setLineWrap(true);
        jResourceCheckTextbox.setRows(5);
        jScrollPane1.setViewportView(jResourceCheckTextbox);

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Resource Type");

        jLabel4.setText("Borrowed Resources");

        jBorrowedItemsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jBorrowedItemsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jBorrowedItemsList);

        jStatusTextbox.setEditable(false);
        jStatusTextbox.setColumns(20);
        jStatusTextbox.setLineWrap(true);
        jStatusTextbox.setRows(5);
        jScrollPane3.setViewportView(jStatusTextbox);

        jReturnItem.setText("Return");
        jReturnItem.setEnabled(false);
        jReturnItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jReturnItemActionPerformed(evt);
            }
        });

        jResourceList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jResourceList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jResourceList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jResourceListValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(jResourceList);

        jBorrowItem.setText("Borrow");
        jBorrowItem.setEnabled(false);
        jBorrowItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBorrowItemActionPerformed(evt);
            }
        });

        jSerachIdRadioButton.setSelected(true);
        jSerachIdRadioButton.setText("Search by ID");

        jSearchNameRadioButton.setText("Search by Name");

        jLabel5.setText("Avaliability");

        jLabel6.setText("Status");

        jLabel7.setText("Request New Resouce");

        jButton4.setText("Request");

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane5.setViewportView(jTextArea3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton4)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4))
        );

        jBorrowLength1.setSelected(true);
        jBorrowLength1.setText("2 Weeks");

        jBorrowLength2.setText("6 Months");

        jLabel1.setText("Borrow Length");

        jButton5.setText("Check Avaliability");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jCheckStatus.setText("Check Status");
        jCheckStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckStatusActionPerformed(evt);
            }
        });

        jPaymentField.setText("0.00");
        jPaymentField.setEnabled(false);
        jPaymentField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPaymentFieldActionPerformed(evt);
            }
        });

        jLabel8.setText("£");

        j3DayExtension.setSelected(true);
        j3DayExtension.setText("3 Days");

        j1WeekExtension.setText("1 Week");

        j2WeekExtension.setText("2 Weeks");

        jExtensionButton.setText("Request Extension");
        jExtensionButton.setEnabled(false);
        jExtensionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jExtensionButtonActionPerformed(evt);
            }
        });

        jLabel9.setText("Ask for Extension");

        jRating1.setText("1");
        jRating1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRating1ActionPerformed(evt);
            }
        });

        jRating2.setText("2");

        jRating3.setText("3");

        jRating4.setText("4");

        jRating5.setText("5");

        jGiveRating.setText("Give Rating");
        jGiveRating.setEnabled(false);
        jGiveRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGiveRatingActionPerformed(evt);
            }
        });

        jLabel10.setText("Give your rating on this item");

        jLabel11.setText("Bad");

        jLabel12.setText("Good");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(131, 131, 131))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jCheckStatus))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jBorrowLength1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBorrowLength2)
                                .addGap(27, 27, 27)
                                .addComponent(jBorrowItem))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(56, 56, 56)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jButton5))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jSerachIdRadioButton)
                                    .addGap(18, 18, 18)
                                    .addComponent(jSearchNameRadioButton))
                                .addComponent(jLabel1)
                                .addComponent(jLabel5)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(107, 107, 107))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(227, 227, 227)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel11)
                                                    .addComponent(jGiveRating))
                                                .addGap(49, 49, 49)
                                                .addComponent(jLabel12))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jRating1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jRating2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jRating3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jRating4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jRating5))
                                            .addComponent(jLabel10))
                                        .addGap(0, 15, Short.MAX_VALUE)))))))
                .addGap(20, 20, 20))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(229, 229, 229)
                                .addComponent(jExtensionButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(j3DayExtension)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(j1WeekExtension)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(j2WeekExtension)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPaymentField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jReturnItem)
                        .addGap(20, 20, 20))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jSerachIdRadioButton)
                                    .addComponent(jSearchNameRadioButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckStatus)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(6, 6, 6)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRating1)
                            .addComponent(jRating2)
                            .addComponent(jRating3)
                            .addComponent(jRating4)
                            .addComponent(jRating5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBorrowLength2)
                    .addComponent(jBorrowItem)
                    .addComponent(jBorrowLength1)
                    .addComponent(jGiveRating))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jReturnItem)
                            .addComponent(jPaymentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(j2WeekExtension)
                            .addComponent(j1WeekExtension)
                            .addComponent(j3DayExtension))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jExtensionButton)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        
        if (jSerachIdRadioButton.isSelected() == true)
        {
            
        }
        else
        {
            
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jResourceListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jResourceListValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jResourceListValueChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        updateAvaliabilityDisplay();
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jBorrowItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBorrowItemActionPerformed
        
        int borrowLength;
        
        if (jBorrowLength1.isSelected() == true)
        {
            borrowLength = 0;
        }
        else
        {
            borrowLength = 1;
        }
        
        selectedItem.borrowItem(c, borrowLength);
        updateBorrowedItems();
        updateAvaliabilityDisplay();
        jBorrowItem.setEnabled(false);
        
    }//GEN-LAST:event_jBorrowItemActionPerformed

    private void jCheckStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckStatusActionPerformed
        updateStatusDisplay();
    }//GEN-LAST:event_jCheckStatusActionPerformed

    private void jReturnItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jReturnItemActionPerformed
        
        
        if (selectedBorrowedItem.getBorrowInfo().getIsOverdue() == false) {
            selectedBorrowedItem.returnItem(selectedBorrowedItem);
            updateBorrowedItems();
            updateStatusDisplay();
            updateAvaliabilityDisplay();
            jReturnItem.setEnabled(false);
            jExtensionButton.setEnabled(false);
            jGiveRating.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Successfully Returned :)");
        }
        else
        {
        
            if (selectedBorrowedItem.getBorrowInfo().getIsOverdue() == true) {
                
                if (jPaymentField.getText().equals(""))
                {
                    jPaymentField.setText("0.00");
                }
                
                int amount = (int) (Float.parseFloat(jPaymentField.getText()) * 100);
                selectedBorrowedItem.getBorrowInfo().payOverdueAmount(amount);
                if (selectedBorrowedItem.getBorrowInfo().getOverdueAmount() == 0)
                {
                    
                    selectedBorrowedItem.returnItem(selectedBorrowedItem);
                    updateBorrowedItems();
                    updateStatusDisplay();
                    updateAvaliabilityDisplay();
                    jReturnItem.setEnabled(false);
                    jExtensionButton.setEnabled(false);
                    jGiveRating.setEnabled(false);
                    jPaymentField.setEnabled(false);
                    jPaymentField.setText("0.00");
                }
                else
                {
                JOptionPane.showMessageDialog(this, "Please pay overdue amount!");
                }
            }
            
        }
        
        
    }//GEN-LAST:event_jReturnItemActionPerformed

    private void jPaymentFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPaymentFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPaymentFieldActionPerformed

    private void jExtensionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jExtensionButtonActionPerformed
        
        int extension;
        selectedBorrowedItem.getBorrowInfo().checkOverdue();
        if (selectedBorrowedItem.getBorrowInfo().getIsOverdue() == true) {
            JOptionPane.showMessageDialog(this, "Cannot request extension! Item is overdue, please return.");
        }
        else
        {
            if (selectedBorrowedItem.getBorrowInfo().getIsExtensionPending() == false) {
                
            
                if (j3DayExtension.isSelected() == true) {
                    extension = 3;
                }
                else if (j1WeekExtension.isSelected() == true)
                {
                    extension = 7;
                }
                else
                {
                    extension = 14;
                }
            
                selectedBorrowedItem.getBorrowInfo().requestExtension(extension);
                JOptionPane.showMessageDialog(this, "Extension request sent!");
            }
            else if (selectedBorrowedItem.getBorrowInfo().getIsExtensionPending() == true)
            {
                JOptionPane.showMessageDialog(this, "Extension is pending.");
            }
            else if (selectedBorrowedItem.getBorrowInfo().getExtension() > 0);
            {
                JOptionPane.showMessageDialog(this, "There is already an extension on this item!");
            }
            
        }
    }//GEN-LAST:event_jExtensionButtonActionPerformed

    private void jRating1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRating1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRating1ActionPerformed

    private void jGiveRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGiveRatingActionPerformed
        if (jRating1.isSelected() == false && jRating2.isSelected() == false && jRating3.isSelected() == false && jRating4.isSelected() == false && jRating5.isSelected() == false)
        {
            JOptionPane.showMessageDialog(this, "Please select a rating");
        }
        else
        {
            if (jRating1.isSelected() == true)
            {
                selectedBorrowedItem.getRating().addUserRating(c.getId(), 1);
            }
            else if (jRating2.isSelected() == true)
            {
                selectedBorrowedItem.getRating().addUserRating(c.getId(), 2);
            }
            else if (jRating3.isSelected() == true)
            {
                selectedBorrowedItem.getRating().addUserRating(c.getId(), 3);
            }
            else if (jRating4.isSelected() == true)
            {
                selectedBorrowedItem.getRating().addUserRating(c.getId(), 4);
            }
            else if (jRating5.isSelected() == true)
            {
                selectedBorrowedItem.getRating().addUserRating(c.getId(), 5);
            }
            
            updateAvaliabilityDisplay();
            JOptionPane.showMessageDialog(this, "Rating Updated!");
            
            
            
        }
    }//GEN-LAST:event_jGiveRatingActionPerformed

    private void jLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLogOutActionPerformed
        this.setVisible(false);
        logwin.setVisible(true);
    }//GEN-LAST:event_jLogOutActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton j1WeekExtension;
    private javax.swing.JRadioButton j2WeekExtension;
    private javax.swing.JRadioButton j3DayExtension;
    private javax.swing.JButton jBorrowItem;
    private javax.swing.JRadioButton jBorrowLength1;
    private javax.swing.JRadioButton jBorrowLength2;
    private javax.swing.JList<String> jBorrowedItemsList;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jCheckStatus;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JButton jExtensionButton;
    private javax.swing.JButton jGiveRating;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jLogOut;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jPaymentField;
    private javax.swing.JRadioButton jRating1;
    private javax.swing.JRadioButton jRating2;
    private javax.swing.JRadioButton jRating3;
    private javax.swing.JRadioButton jRating4;
    private javax.swing.JRadioButton jRating5;
    private javax.swing.JTextArea jResourceCheckTextbox;
    private javax.swing.JList<String> jResourceList;
    private javax.swing.JButton jReturnItem;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JRadioButton jSearchNameRadioButton;
    private javax.swing.JRadioButton jSerachIdRadioButton;
    private javax.swing.JTextArea jStatusTextbox;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel jWelcomeLabel;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiView;

import Controller.ClientController;
import LibraryModel.User.Client;
import LibraryModel.Item.Item;
import LibraryModel.Message;
import LibraryModel.NewsletterSingleton;
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
    private ArrayList<Message> adminMessages;
    private ArrayList<Message> userMessages;
    private Item selectedItem;
    private Item selectedBorrowedItem;
    private ClientController cController;
    
    /**
     * Creates new form ClientWindow
     * @param cCtrl
     */
    public ClientWindow(ClientController cCtrl) {
        cController = cCtrl;
        
        initComponents();
        groupButton();
        
        cController.setupWindow(jWelcomeLabel, jResourceList, jNewsletterDisplay);
        cController.updateBorrowedItems();
        cController.updateBorrowedItemsDisplay(jBorrowedItemsList);
        cController.loadMessages(jMessageDisplayTextbox);
        

        
    }

    private void groupButton() {

        ButtonGroup bgBorrowLength = new ButtonGroup();
        ButtonGroup bgExtension = new ButtonGroup();
        ButtonGroup bgRatings = new ButtonGroup();


        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jResourceCheckTextbox = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jBorrowedItemsList = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jStatusTextbox = new javax.swing.JTextArea();
        jReturnItem = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jResourceList = new javax.swing.JList<>();
        jBorrowItem = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jResourceRequestBtn = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jResourceRequestTextbox = new javax.swing.JTextArea();
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
        jScrollPane6 = new javax.swing.JScrollPane();
        jMessageDisplayTextbox = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jNewsletterDisplay = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jWelcomeLabel.setText("Welcome");

        jLogOut.setText("Logout");
        jLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLogOutActionPerformed(evt);
            }
        });

        jResourceCheckTextbox.setEditable(false);
        jResourceCheckTextbox.setColumns(20);
        jResourceCheckTextbox.setLineWrap(true);
        jResourceCheckTextbox.setRows(5);
        jScrollPane1.setViewportView(jResourceCheckTextbox);

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

        jLabel5.setText("Avaliability");

        jLabel6.setText("Status");

        jLabel7.setText("Request New Resouce");

        jResourceRequestBtn.setText("Request");
        jResourceRequestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jResourceRequestBtnActionPerformed(evt);
            }
        });

        jResourceRequestTextbox.setColumns(20);
        jResourceRequestTextbox.setRows(5);
        jScrollPane5.setViewportView(jResourceRequestTextbox);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jResourceRequestBtn)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jResourceRequestBtn)
                .addContainerGap())
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

        j1WeekExtension.setText("7 Days");

        j2WeekExtension.setText("14 Days");

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

        jMessageDisplayTextbox.setColumns(20);
        jMessageDisplayTextbox.setLineWrap(true);
        jMessageDisplayTextbox.setRows(5);
        jScrollPane6.setViewportView(jMessageDisplayTextbox);

        jLabel13.setText("Messages");

        jLabel2.setText("Resource List");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(162, 162, 162))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(jBorrowLength1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jBorrowLength2)
                            .addGap(27, 27, 27)
                            .addComponent(jBorrowItem)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(131, 131, 131))
                    .addComponent(jCheckStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(j3DayExtension)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(j1WeekExtension)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(j2WeekExtension))
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jRating1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRating2)
                                        .addGap(15, 15, 15))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(jGiveRating)))
                                .addGap(3, 3, 3)
                                .addComponent(jRating3)
                                .addGap(23, 23, 23)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jRating4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRating5))
                                    .addComponent(jLabel12)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jExtensionButton)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPaymentField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jReturnItem))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 32, Short.MAX_VALUE)))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel13))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jCheckStatus))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jBorrowLength2)
                                    .addComponent(jBorrowItem)
                                    .addComponent(jBorrowLength1)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jGiveRating)
                                .addGap(38, 38, 38)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jReturnItem)
                                    .addComponent(jPaymentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(j2WeekExtension)
                            .addComponent(j1WeekExtension)
                            .addComponent(j3DayExtension))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jExtensionButton)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane6))
        );

        jNewsletterDisplay.setEditable(false);
        jNewsletterDisplay.setColumns(20);
        jNewsletterDisplay.setLineWrap(true);
        jNewsletterDisplay.setRows(5);
        jScrollPane7.setViewportView(jNewsletterDisplay);

        jLabel3.setText("News:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jWelcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(14, 14, 14)
                        .addComponent(jWelcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jResourceListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jResourceListValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jResourceListValueChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        cController.updateAvaliabilityDisplay(jResourceList, jBorrowItem, jResourceCheckTextbox);
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jBorrowItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBorrowItemActionPerformed
        
        cController.borrowItem(jBorrowLength1, jResourceList, jBorrowItem, jResourceCheckTextbox);
        cController.updateBorrowedItems();
        cController.updateBorrowedItemsDisplay(jBorrowedItemsList);
        cController.updateAvaliabilityDisplay(jResourceList, jBorrowItem, jResourceCheckTextbox);
        
    }//GEN-LAST:event_jBorrowItemActionPerformed

    private void jCheckStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckStatusActionPerformed
        cController.updateStatusDisplay(jBorrowedItemsList, jReturnItem, jExtensionButton, jGiveRating, jPaymentField, jStatusTextbox);
    }//GEN-LAST:event_jCheckStatusActionPerformed

    private void jReturnItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jReturnItemActionPerformed
        
        
        cController.returnItem(jReturnItem, jExtensionButton, jGiveRating, jPaymentField);
        cController.updateBorrowedItems();
        cController.updateBorrowedItemsDisplay(jBorrowedItemsList);
        cController.updateAvaliabilityDisplay(jResourceList, jBorrowItem, jResourceCheckTextbox);
        cController.updateStatusDisplay(jBorrowedItemsList, jReturnItem, jExtensionButton, jGiveRating, jPaymentField, jStatusTextbox);
        
    }//GEN-LAST:event_jReturnItemActionPerformed

    private void jPaymentFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPaymentFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPaymentFieldActionPerformed

    private void jExtensionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jExtensionButtonActionPerformed
        
        cController.requestExtension(j3DayExtension, j1WeekExtension);
        
    }//GEN-LAST:event_jExtensionButtonActionPerformed

    private void jRating1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRating1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRating1ActionPerformed

    private void jGiveRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGiveRatingActionPerformed

        cController.giveRating(jRating1, jRating2, jRating3, jRating4, jRating5);
        cController.updateAvaliabilityDisplay(jResourceList, jBorrowItem, jResourceCheckTextbox);
    }//GEN-LAST:event_jGiveRatingActionPerformed

    private void jLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLogOutActionPerformed
        cController.logout();
    }//GEN-LAST:event_jLogOutActionPerformed

    private void jResourceRequestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jResourceRequestBtnActionPerformed
        
        cController.requestResource(jResourceRequestTextbox);
        
    }//GEN-LAST:event_jResourceRequestBtnActionPerformed

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
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jCheckStatus;
    private javax.swing.JButton jExtensionButton;
    private javax.swing.JButton jGiveRating;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jLogOut;
    private javax.swing.JTextArea jMessageDisplayTextbox;
    private javax.swing.JTextArea jNewsletterDisplay;
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
    private javax.swing.JButton jResourceRequestBtn;
    private javax.swing.JTextArea jResourceRequestTextbox;
    private javax.swing.JButton jReturnItem;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea jStatusTextbox;
    private javax.swing.JLabel jWelcomeLabel;
    // End of variables declaration//GEN-END:variables
}

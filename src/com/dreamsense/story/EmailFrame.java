package com.dreamsense.story;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kegg on 5/11/19 at 10:52 PM.
 * Project: Life
 */
class EmailFrame extends JInternalFrame {
  
  private static final int xOffset = 30, yOffset = 30;
  private JTextArea content;
  
  EmailFrame() {
    super("Email",
        true,
        true,
        true,
        true);
    setSize(700,550);
    setLocation(xOffset, yOffset);
    setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    setLayout(new BorderLayout());
   
    content = new JTextArea();
    content.setWrapStyleWord(true);
    content.setLineWrap(true);
    content.setFont(new Font("monospaced", Font.PLAIN, 12));
    
    getContentPane().add(new JScrollPane(generateEmailTable()), BorderLayout.NORTH);
    getContentPane().add(new JScrollPane(content), BorderLayout.CENTER);
  }
  
  private JTable generateEmailTable() {
    String[] columnNames = {"From","Subject","Date","File"};
    Object[][] data = {
        {"Fred Jones", "re: Game on Saturday?", "2002-01-23","20020123.txt"},
        {"Grace Smith", "Sunday Funday", "2002-01-22","20020122.txt"},
        {"Fred Jones", "Plane Crash", "2002-01-20","20020120.txt"},
        {"Joe Thompson", "Church", "2002-01-19","20020119.txt"}
    };
    
    JTable table = new JTable(data, columnNames) {
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    table.setPreferredScrollableViewportSize(new Dimension(500, 70));
    table.setFillsViewportHeight(true);
    table.setSelectionModel(new ForcedListSelectionModel());
    ListSelectionModel selectionModel = table.getSelectionModel();
    selectionModel.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        handleSelectionEvent(e);
      }
  
      void handleSelectionEvent(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
          return;
    
        int selectedRow = table.getSelectedRow();
        String fileName = (String) table.getModel().getValueAt(selectedRow, 3);
  
        String file = "resources/email/" + fileName;
  
        InputStream inputStream = getClass()
            .getClassLoader().getResourceAsStream(file);
  
        StringBuilder sb = new StringBuilder();
  
        int data;
        char c;
  
        try {
          data = inputStream.read();
          while(data != -1) {
            c = (char)data;
            data = inputStream.read();
      
            sb.append(c);
          }
          inputStream.close();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
        
        content.setText(sb.toString());
      }
    });
    TableColumnModel tcm = table.getColumnModel();
    tcm.removeColumn(tcm.getColumn(3));
    
    return table;
  }
  
  class ForcedListSelectionModel extends DefaultListSelectionModel {
    
    ForcedListSelectionModel() {
      setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    @Override
    public void clearSelection() {
    }
    
    @Override
    public void removeSelectionInterval(int index0, int index1) {
    }
    
  }
}

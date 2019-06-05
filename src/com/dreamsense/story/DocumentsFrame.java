package com.dreamsense.story;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kegg on 5/11/19 at 4:44 PM.
 * Project: jubilant-octo-rotary-phone
 */
class DocumentsFrame extends JInternalFrame {
  
  private static final int xOffset = 30, yOffset = 30;
  private JList<String> fileList;
  private JEditorPane document;
  private HTMLEditorKit kit;
  
  private String[] files = {
      "chat-20020118.html",
      "letter.txt",
      "poem.txt"
  };
  
  DocumentsFrame() {
    super("Documents",
        true,
        true,
        true,
        true);
    setSize(700,550);
    setLocation(xOffset, yOffset);
    setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    
    setupDocument();
    setupFileList();
    setLayout(new BorderLayout());
    
    JScrollPane fileScroll = new JScrollPane(fileList);
    fileScroll.setViewportBorder(BorderFactory.createEmptyBorder(20, 5, 20, 5));
    
    JScrollPane docScroll = new JScrollPane(document);
    docScroll.setViewportBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
    getContentPane().add(fileScroll, BorderLayout.WEST);
    getContentPane().add(docScroll, BorderLayout.CENTER);
  }
  
  private void setupDocument() {
    document = new JEditorPane();
    document.setEditable(false);
    kit = new HTMLEditorKit();
    document.setEditorKit(kit);
    StyleSheet styleSheet = new StyleSheet();
    styleSheet.addRule("body {color:#000; font-family:arial;}");
    styleSheet.addRule("p {margin:4px;}");
    kit.setStyleSheet(styleSheet);
  }
  
  private void setupFileList() {
    fileList = new JList<>(files);
    fileList.setCellRenderer(new DocumentListRenderer());
    
    fileList.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
        JList list = (JList)evt.getSource();
        
        if (evt.getClickCount() == 2) {
          int index = list.locationToIndex(evt.getPoint());
  
          String file = "resources/documents/" + String.valueOf(list.getModel().getElementAt(index));
          
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
          } catch (IOException e) {
            e.printStackTrace();
          }
          
          Document doc = kit.createDefaultDocument();
          document.setDocument(doc);
          document.setText(sb.toString());
        }
      }
    });
  }
}

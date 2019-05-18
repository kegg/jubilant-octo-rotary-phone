package com.dreamsense.story;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.UIManager;
import java.awt.Component;

/**
 * Created by kegg on 5/11/19 at 7:17 PM.
 * Project: Life
 */
public class DocumentListRenderer extends DefaultListCellRenderer {
  
  
  @Override
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    label.setIcon(UIManager.getIcon("FileView.fileIcon"));
    return label;
  }
}

//    UIManager.getIcon("FileView.directoryIcon");
//    UIManager.getIcon("FileView.fileIcon");
//    UIManager.getIcon("FileView.computerIcon");
//    UIManager.getIcon("FileView.hardDriveIcon");
//    UIManager.getIcon("FileView.floppyDriveIcon");
//
//    UIManager.getIcon("FileChooser.newFolderIcon");
//    UIManager.getIcon("FileChooser.upFolderIcon");
//    UIManager.getIcon("FileChooser.homeFolderIcon");
//    UIManager.getIcon("FileChooser.detailsViewIcon");
//    UIManager.getIcon("FileChooser.listViewIcon");

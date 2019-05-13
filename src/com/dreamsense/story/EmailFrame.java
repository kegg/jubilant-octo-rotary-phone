package com.dreamsense.story;

import javax.swing.*;

/**
 * Created by kegg on 5/11/19 at 10:52 PM.
 * Project: Life
 */
class EmailFrame extends JInternalFrame {
  
  private static final int xOffset = 30, yOffset = 30;
  
  EmailFrame() {
    super("Email",
        true,
        true,
        true,
        true);
    setSize(700,550);
    setLocation(xOffset, yOffset);
    setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
  }
  
  

}

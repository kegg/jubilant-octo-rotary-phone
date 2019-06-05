package com.dreamsense.story;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import java.awt.Dimension;
import java.awt.FlowLayout;

/**
 * Created by kegg on 2019-06-05 at 09:19.
 * Project: jubilant-octo-rotary-phone
 */
public class TestFrame extends JInternalFrame {
  
  private static final int xOffset = 30, yOffset = 30;
  
  TestFrame() {
    super("Test",
        true,
        true,
        true,
        true);
    setSize(700,550);
    setLocation(xOffset, yOffset);
    setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    setLayout(new FlowLayout());
    JButton button = new JButton("Files");
    button.setPreferredSize(new Dimension(100, 100));
    add(button);
  }
  
}

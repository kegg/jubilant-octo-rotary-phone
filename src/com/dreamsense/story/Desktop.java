package com.dreamsense.story;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 * Created by kegg on 2019-05-22 at 22:32.
 * Project: Clicker
 */
public class Desktop extends JDesktopPane {
  private Image backImage = null; //member variable
  
  public Desktop() {
    setBackgroundImage("/resources/images/enterprise.jpg");
  }
  
  public void setBackgroundImage(String imageFileName) {
    try {
      backImage = new ImageIcon(this.getClass().getResource(imageFileName)).getImage();
    }
    catch(Exception e) {
      System.out.println("Could not find file in folder: " + this.getClass().getResource("."));
    }
  }
  
  public void paintComponent( Graphics g ) {
    if(backImage == null) {
      super.paintComponent(g);
    } else {
      Graphics2D g2d = (Graphics2D)g;
      g2d.drawImage(backImage, 0, 0, this);
    }
  }
}
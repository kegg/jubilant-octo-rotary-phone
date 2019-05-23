package com.dreamsense.story;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.awt.event.KeyEvent.VK_E;
import static java.awt.event.KeyEvent.VK_Q;
import static java.awt.event.KeyEvent.VK_V;

/**
 * Created by kegg on 5/11/19 at 4:26 PM.
 * Project: Life
 */
public class Life extends JFrame {
  
  private Desktop desktop;
  
  private Life() {
    super("Life");
    int inset = 50;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds(inset, inset,
        screenSize.width - inset*2,
        screenSize.height - inset*2);
    desktop = new Desktop();
    System.setProperty("apple.laf.useScreenMenuBar", "true");
    getContentPane().add(desktop, BorderLayout.CENTER);
    getContentPane().add(createButtonPanel(), BorderLayout.SOUTH);
    createPopupMenu();
    desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
  }
  
  private JToolBar createButtonPanel() {
    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);
    toolbar.add(new JButton("Start"));
    toolbar.add(Box.createHorizontalGlue());
  
    JLabel timeLabel = new JLabel(new SimpleDateFormat("HH:mm:ss").format(new Date()));
    toolbar.add(timeLabel);
  
    return toolbar;
  }
  
  private void createPopupMenu() {
    final JPopupMenu popupmenu = new JPopupMenu("Navigation");

    JMenuItem menuItem = new JMenuItem("Documents");
    menuItem.setMnemonic(VK_V);
    menuItem.setActionCommand("documents.view");
    menuItem.addActionListener(new ActionListener() {
  
      @Override
      public void actionPerformed(ActionEvent e) {
        documentsView();
        desktop.repaint();
      }
    });
    popupmenu.add(menuItem);

    menuItem = new JMenuItem("Email");
    menuItem.setMnemonic(VK_E);
    menuItem.setActionCommand("email.view");
    menuItem.addActionListener(new ActionListener() {
  
      @Override
      public void actionPerformed(ActionEvent e) {
        emailView();
        desktop.repaint();
      }
    });
    popupmenu.add(menuItem);
    
    menuItem = new JMenuItem("Quit");
    menuItem.setMnemonic(VK_Q);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.META_MASK));
    menuItem.setActionCommand("quit");
    menuItem.addActionListener(new ActionListener() {
  
      @Override
      public void actionPerformed(ActionEvent e) {
        quit();
      }
    });
    popupmenu.add(menuItem);

    desktop.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent ev) {
        if (ev.isPopupTrigger()) {
          popupmenu.show(desktop, ev.getX(), ev.getY());
        }
      }
  
      public void mouseReleased(MouseEvent ev) {
        if (ev.isPopupTrigger()) {
          popupmenu.show(desktop, ev.getX(), ev.getY());
        }
      }
      
      public void mouseClicked(MouseEvent e) {}
    });
    desktop.add(popupmenu);
  }
  
  private void emailView() {
    EmailFrame frame = new EmailFrame();
    frame.setVisible(true);
    desktop.add(frame);
  
    try {
      frame.setSelected(true);
    } catch (java.beans.PropertyVetoException e) {
      System.err.println("Error Selecting Frame");
    }
  }
  
  private void quit() {
    System.exit(0);
  }
  
  private void documentsView() {
    DocumentsFrame frame = new DocumentsFrame();
    frame.setVisible(true);
    desktop.add(frame);
    
    try {
      frame.setSelected(true);
    } catch (java.beans.PropertyVetoException e) {
      System.err.println("Error Selecting Frame");
    }
    
  }
  
  private static void createAndShowGUI() {
    JFrame.setDefaultLookAndFeelDecorated(true);
    
    Life life = new Life();
    life.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
    life.setVisible(true);
  }
  
  public static void main(String[] args) {
    SwingUtilities.invokeLater(Life::createAndShowGUI);
  }
}

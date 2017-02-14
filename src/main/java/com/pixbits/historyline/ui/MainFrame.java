package com.pixbits.historyline.ui;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.*;

public class MainFrame extends JFrame implements ComponentListener
{
  private final Canvas canvas;
  
  public MainFrame()
  {	
  	super("History");

    setLayout(new BorderLayout());
    canvas = new Canvas();
    add(canvas, BorderLayout.CENTER);
    canvas.init();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    this.addComponentListener(this);
	    
    pack();
    setVisible(true);
    setLocationRelativeTo(null);
  }
  
  @Override public void componentHidden(ComponentEvent e) { }
  @Override public void componentMoved(ComponentEvent e) { }
  @Override public void componentShown(ComponentEvent e) { }
  
  @Override public void componentResized(ComponentEvent e)
  {
    Dimension size = this.getSize();
    Insets insets = this.getInsets();
    
    int width = size.width-insets.left-insets.right;
    int height = size.height-insets.bottom-insets.top;
    
    System.out.println(width+"x"+height);
    
    canvas.setSize(width, height);
    canvas.redraw();
  }
}
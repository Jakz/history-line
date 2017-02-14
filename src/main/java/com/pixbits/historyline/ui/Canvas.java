package com.pixbits.historyline.ui;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

import java.util.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.pixbits.historyline.data.dates.TimeSpan;
import com.pixbits.historyline.data.dates.Year;
import com.pixbits.historyline.ui.elements.TimeBar;
import com.pixbits.historyline.ui.elements.TimeGraph;

import processing.core.PApplet;
import processing.core.PFont;


public class Canvas extends PApplet implements ChangeListener
{
  public int WIDTH = 1024, HEIGHT = 600;

  int MARGIN_TOP = 10;
  int MARGIN_SIDES = 10;
  int YEAR_HEADER_SIZE = 50;
  int BAR_HEIGHT = 15;
  
  TimeGraph graph = new TimeGraph(new Year(1300), TimeGraph.Scale.TENTHS);
  
  public void setup()
  {
    //smooth();
    size(WIDTH, HEIGHT);
    setSize(WIDTH, HEIGHT);
    noLoop();
  }
  
  @Override public void setSize(int w, int h)
  {
    WIDTH = w;
    HEIGHT = h;
    super.setSize(w,h);
  }

  public int getColumnSpacing() { return (WIDTH - MARGIN_SIDES*2) / (graph.getColumns()-1); }
  public int getBarHeight() { return BAR_HEIGHT; }
  
  public int getSideMargin() { return MARGIN_SIDES; }
  public int getTopMargin() { return MARGIN_TOP; }
  public int getCanvasWidth() { return WIDTH - MARGIN_SIDES*2; }
  public int getCanvasHeight() { return HEIGHT - YEAR_HEADER_SIZE - MARGIN_TOP; }
  
  public int getRowHeight() { return (int) (getCanvasHeight()/(float)graph.getRows()); }
  
  public float gridX(int index)
  {
    int SPACING = getColumnSpacing();
    return index*SPACING;
  }
    
  public void draw()
  {
  	int SPACING = getColumnSpacing();
    
    background(220); 

  	stroke(0);
  	fill(0);

    stroke(180);
  	for (int x = MARGIN_SIDES; x <= WIDTH - MARGIN_SIDES; x += SPACING)
  	{
  	  this.line(x, 0, x, HEIGHT-YEAR_HEADER_SIZE);
  	}
  	  	
  	textAlign(CENTER);
  	for (int x = MARGIN_SIDES, yx = 0; x <= WIDTH - MARGIN_SIDES; x += SPACING, ++yx)
    {
  	  
  	  pushMatrix();
      translate(x-textAscent()/2,HEIGHT-YEAR_HEADER_SIZE/2);
      rotate(HALF_PI);
      
      int y = graph.getBase().year + yx*graph.getScale().delta;
      
      if ((y % graph.getScale().marksDelta) == 0)
        fill(180,0,0);
      else
        fill(140);

      text(y,0,0);
      popMatrix();
    }
  	
  	test();
  }
  
  public void test()
  {
    TimeBar span = new TimeBar(new TimeSpan(new Year(1305), new Year(1388)));
    span.render(graph, this, 0);
    TimeBar span2 = new TimeBar(new TimeSpan(new Year(1360), new Year(1600)));
    span2.render(graph, this, 1);
    TimeBar span3 = new TimeBar(new TimeSpan(new Year(1000), new Year(1100)));
    span3.render(graph, this, 2);

  }
  
  public void keyPressed()
  {

  }

  public void mouseReleased()
  {    	

  }
  
  public void mousePressed()
  {    	

  }
  
  public void mouseMoved()
  { 
  	
  }
  
  
  public void mouseDragged()
  { 	

    mousePressed();
  }
  
  public void stateChanged(ChangeEvent e)
  {
  	
  }
  
  void reset()
  {

  }
  
  public void fill(Color c) { fill(c.getRed(),c.getGreen(),c.getBlue(),c.getAlpha()); }
  public void stroke(Color c) { stroke(c.getRed(),c.getGreen(),c.getBlue(),c.getAlpha()); }

}

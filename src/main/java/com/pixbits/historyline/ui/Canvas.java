package com.pixbits.historyline.ui;

import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.pixbits.historyline.data.RenderableAsBar;
import com.pixbits.historyline.data.dates.TimeSpan;
import com.pixbits.historyline.data.dates.Year;
import com.pixbits.historyline.ui.elements.TimeBar;
import com.pixbits.historyline.ui.elements.TimeGraph;
import com.pixbits.historyline.ui.elements.Tooltip;
import com.pixbits.lib.ui.color.Color;

import processing.core.PApplet;
import processing.core.PFont;

public class Canvas extends PApplet implements ChangeListener
{
  public int WIDTH = 1024, HEIGHT = 640;

  int MARGIN_TOP = 10;
  int MARGIN_SIDES = 10;
  int YEAR_HEADER_SIZE = 50;
  int BAR_HEIGHT = 15;

  TimeGraph graph = new TimeGraph(new Year(-500), TimeGraph.Scale.TENTHS, 70, 30);
  Tooltip tooltip = new Tooltip(this, -1);
  
  Canvas()
  {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
  }

  public void setup()
  {
    // smooth();
    size(WIDTH, HEIGHT);
    setSize(WIDTH, HEIGHT);
    noLoop();
  }

  @Override
  public void setSize(int w, int h)
  {
    WIDTH = w;
    HEIGHT = h;
    super.setSize(w, h);
  }

  public int getColumnSpacing()
  {
    return (WIDTH - MARGIN_SIDES * 2) / (graph.getColumns() - 1);
  }

  public int getBarHeight()
  {
    return BAR_HEIGHT;
  }

  public int getSideMargin()
  {
    return MARGIN_SIDES;
  }

  public int getTopMargin()
  {
    return MARGIN_TOP;
  }

  public int getCanvasWidth()
  {
    return WIDTH - MARGIN_SIDES * 2;
  }

  public int getCanvasHeight()
  {
    return HEIGHT - YEAR_HEADER_SIZE - MARGIN_TOP;
  }

  public int getRowHeight()
  {
    return (int) (getCanvasHeight() / (float) graph.getRows());
  }

  public float gridX(int index)
  {
    int SPACING = getColumnSpacing();
    return index * SPACING;
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
      this.line(x, 0, x, HEIGHT - YEAR_HEADER_SIZE);
    }

    textAlign(CENTER);
    for (int x = MARGIN_SIDES, yx = 0; x <= WIDTH - MARGIN_SIDES; x += SPACING, ++yx)
    {

      pushMatrix();
      translate(x - textAscent() / 2, HEIGHT - YEAR_HEADER_SIZE / 2);
      rotate(HALF_PI/2);

      int y = graph.getBase().year + yx * graph.getScale().delta;

      if ((y % graph.getScale().marksDelta) == 0)
        fill(180, 0, 0);
      else
        fill(140);

      text(y, 0, 0);
      popMatrix();
    }

    test();
    
    //tooltip.setText("antani\nfoti");
    //tooltip.draw(mouseX, mouseY - 50);
  }

  public void test()
  {
   /* for (int k = 0; k < 30; ++k)
    {
      int a = ThreadLocalRandom.current().nextInt(30);
      int s = ThreadLocalRandom.current().nextInt(1300, 1400);
      
      RenderableAsBar data = RenderableAsBar.of(new TimeSpan(new Year(s), new Year(s+a+20)), "Leonardo da Vinci");
      TimeBar bar = new TimeBar(data);
      graph.add(bar);
    }*/
    
    graph.add(new TimeBar(RenderableAsBar.of(new TimeSpan(new Year(-500), new Year(-200)), "Colosseo")));

    
    graph.add(new TimeBar(RenderableAsBar.of(new TimeSpan(new Year(-101), new Year(-44)), "Giulio Cesare")));
    graph.add(new TimeBar(RenderableAsBar.of(new TimeSpan(new Year(-84), new Year(-54)), "Catullo")));
    graph.add(new TimeBar(RenderableAsBar.of(new TimeSpan(new Year(-70), new Year(-30)), "Cleopatra")));
    graph.add(new TimeBar(RenderableAsBar.of(new TimeSpan(new Year(-65), new Year(-8)), "Orazio")));

    graph.add(new TimeBar(RenderableAsBar.of(new TimeSpan(new Year(-287), new Year(-212)), "Archimede")));
    graph.add(new TimeBar(RenderableAsBar.of(new TimeSpan(new Year(-4), new Year(65)), "Seneca")));
    graph.add(new TimeBar(RenderableAsBar.of(new TimeSpan(new Year(37), new Year(68)), "Nerone")));

    
    graph.add(new TimeBar(RenderableAsBar.of(new TimeSpan(new Year(72), new Year(80)), "Colosseo")));

    
    graph.deploy();
    
    for (int i = 0; i < graph.getRows(); ++i)
    {
      final int j = i;
      List<TimeBar> row = graph.row(i);
      row.stream().forEach(r -> r.render(graph, this, j));
    }
    
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
  
  public void textBounded(char[] chars, float x, float y, float w)
  {
    int i = 0, cw = 0;
    float nw = this.textWidth(chars[i]);
    
    while (i < chars.length - 1 && cw + nw < w)
    {
      ++i;
      cw += nw;
      nw = textWidth(chars[i]);
    }
    
    if (i == chars.length - 1 && cw + nw < w)
      ++i;
    
    if (i >= 1 && chars[i-1] == ' ')
      --i;
    
    this.text(chars, 0, i, x, y);
  }
  
  public void rect(Rectangle2D.Float rect) { rect(rect.x, rect.y, rect.width, rect.height); }

  public void fill(java.awt.Color c) { fill(c.getRed(),c.getGreen(),c.getBlue(),c.getAlpha()); }
  public void stroke(java.awt.Color c) { stroke(c.getRed(),c.getGreen(),c.getBlue(),c.getAlpha()); }

  public void fill(Color c) { fill(c.r(), c.g(), c.b(), c.a()); }
  public void stroke(Color c) { stroke(c.r(), c.g(), c.b(), c.a()); }
  
  public void background(Color c) { super.background(c.toInt()); }

}

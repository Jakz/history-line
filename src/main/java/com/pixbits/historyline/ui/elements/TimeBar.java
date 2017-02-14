package com.pixbits.historyline.ui.elements;

import java.awt.Color;

import com.pixbits.historyline.data.dates.TimeSpan;
import com.pixbits.historyline.ui.Canvas;

import processing.core.*;

public class TimeBar
{
  private final TimeBarColor color;
  private final TimeSpan span;
    
  public TimeBar(TimeSpan span)
  {
    this.span = span;
    this.color = TimeBarColor.randomColor();
  }
  
  public void render(TimeGraph graph, Canvas c, int row)
  {
    c.stroke(color.stroke);
    c.fill(color.fill);
    
    float ustart = graph.percentForDate(span.getBegin());
    float uend = graph.percentForDate(span.getEnd());
    
    System.out.println("SPAN: "+ustart+", "+uend);
    
    /* skip rendering if bar falls outside graph */
    if ((ustart < 1.0 && uend > 0.0f) || (uend >= 0.0f && ustart < 1.0f))
    {   
      System.out.println("Drawing "+row);
      
      float start = Math.max(ustart, 0.0f);
      float end = Math.min(uend, 1.0f);
      
      float width = c.getCanvasWidth()*(end-start);
      float height = c.getRowHeight();
      
      c.rect(c.getSideMargin() + c.getCanvasWidth()*start, c.getTopMargin()+height*row, width, height);
      
      c.textAlign(Canvas.CENTER);
      c.fill(0);
      c.text("Leonardo Da Vinci", c.getSideMargin() + c.getCanvasWidth()*start+width/2, c.getTopMargin() + height/2 + height*row + c.textAscent()/2);
    }
  }
  
}

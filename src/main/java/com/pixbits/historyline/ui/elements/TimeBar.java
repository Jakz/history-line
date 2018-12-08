package com.pixbits.historyline.ui.elements;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import com.pixbits.historyline.data.RenderableAsBar;
import com.pixbits.historyline.data.dates.TimeSpan;
import com.pixbits.historyline.ui.Canvas;

import processing.core.*;

public class TimeBar
{
  private final TimeBarColor color;
  private final RenderableAsBar data;
  private Rectangle2D.Float bounds;
  
  public TimeBar(RenderableAsBar data)
  {
    this.data = data;
    this.color = TimeBarColor.randomColor();
  }
  
  public RenderableAsBar data() { return data; }
  public TimeSpan span() { return data.getTimeSpan(); }

  
  
  
  public void render(TimeGraph graph, Canvas c, int row)
  {
    c.stroke(color.stroke);
    c.fill(color.fill);
    
    if (bounds == null)
    {
      final float ustart = graph.percentForDate(span().getBegin());
      final float uend = graph.percentForDate(span().getEnd());

      final float start = Math.max(ustart, 0.0f);
      final float end = Math.min(uend, 1.0f);
  
      final float width = c.getCanvasWidth()*(end-start);
      final float height = c.getRowHeight();
      
      final float x = c.getSideMargin() + c.getCanvasWidth()*start;
      final float y = c.getTopMargin()+height*row;
  
      if ((ustart < 1.0 && uend > 0.0f) || (uend >= 0.0f && ustart < 1.0f))
        bounds = new Rectangle2D.Float(x, y, width, height);
    }

    //System.out.println("SPAN: "+ustart+", "+uend);
    
    /* skip rendering if bar falls outside graph */
    if (bounds != null)
    {   
      //System.out.println("Drawing "+row);
      
      c.rect(bounds);
      
      c.textAlign(Canvas.CENTER);
      c.fill(0);
             
      c.textBounded(data.getCaption().toCharArray(), (float)bounds.getCenterX(), (float)bounds.getCenterY() + c.textAscent()/2, bounds.width);
    }
  }
  
}

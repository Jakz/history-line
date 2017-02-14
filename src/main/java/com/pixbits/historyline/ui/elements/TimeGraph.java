package com.pixbits.historyline.ui.elements;

import java.util.List;

import com.pixbits.historyline.data.dates.Date;
import com.pixbits.historyline.data.dates.TimeSpan;
import com.pixbits.historyline.data.dates.Year;
import com.pixbits.historyline.ui.Canvas;

import java.util.ArrayList;

public class TimeGraph
{
  public static enum Scale
  {
    UNITS(1, 10),
    TENTHS(10, 100),
    FIFTIES(50, 1000),
    ;
    
    public final int delta;
    public final int marksDelta;
    
    Scale(int delta, int marksDelta)
    { 
      this.delta = delta;
      this.marksDelta = marksDelta;
    }
  };
  
  private Scale scale;
  private Year base;
  private int columns;
  private int rows;
  
  private final List<TimeBar> buffer;
  private List<List<TimeBar>> bars;
  
  public TimeGraph(Year base, Scale scale)
  {
    this.base = base;
    this.scale = scale;
    this.columns = 10;
    this.rows = 10;
    
    buffer = new ArrayList<>();
    bars = new ArrayList<>();
    reset();
  }
  
  public void reset()
  {
    buffer.clear();
    bars.clear();
    
    for (int i = 0; i < rows; ++i)
      bars.add(new ArrayList<>());
  }
  
  public void deploy()
  {
    
    for (TimeBar bar : buffer)
    {
      
      
      
    }
    
    
  }
  
  public float percentForDate(Date date)
  {
    return ((float)((Year)date).year - base.year) / ((columns-1)*scale.delta);
  }
  
  public boolean isVisible(TimeSpan span)
  {
    return percentForDate(span.getBegin()) >= 0.0f || percentForDate(span.getEnd()) <= 1.0f;
  }
  
  public void render(Canvas canvas)
  {
    
  }
  
  public Scale getScale() { return scale; }
  public Year getBase() { return base; }
  
  public int getColumns() { return columns; }
  public int getRows() { return rows; }
}

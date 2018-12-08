package com.pixbits.historyline.ui.elements;

import java.util.List;
import java.util.stream.Collectors;

import com.pixbits.historyline.data.TimeSpanDeployer;
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
    TWENTY(20, 100),
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
  
  private final TimeSpanDeployer<TimeBar> buffer;
  private List<List<TimeBar>> bars;
  
  public TimeGraph(Year base, Scale scale, int columns, int rows)
  {
    this.base = base;
    this.scale = scale;
    this.columns = columns;
    this.rows = rows;
    
    buffer = new TimeSpanDeployer<>();
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
  
  public void add(TimeBar bar)
  {
    buffer.add(bar, bar.span());
  }
  
  public List<TimeBar> row(int index) { return bars.get(index); }
  
  public void deploy()
  {
    buffer.deploy(rows);
    bars = buffer.rows().stream()
      .map(row -> row.stream()
          .map(span -> span.second)
          .collect(Collectors.toList())
      )
      .collect(Collectors.toList()
    );
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

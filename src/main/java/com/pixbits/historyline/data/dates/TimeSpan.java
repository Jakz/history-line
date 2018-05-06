package com.pixbits.historyline.data.dates;

import com.pixbits.historyline.ui.elements.TimeBar;

public class TimeSpan
{
  public static enum Order
  {
    LESS,
    LESS_OVERLAP,
    GREATER,
    GREATER_OVERLAP,
  }
  
  private final Date begin;
  private final Date end;
  
  public TimeSpan(Date begin, Date end)
  {
    this.begin = begin;
    this.end = end;
  }
  
  public Date getBegin() { return begin; }
  public Date getEnd() { return end; }
  
  public boolean overlap(TimeSpan other)
  {
    return !(end.compareTo(other.begin) <= 0 || begin.compareTo(other.end) > 0); 
  }
  
  public Order compare(TimeSpan other)
  {
    Date obegin = other.getBegin();
    Date oend = other.getEnd();
    
    if (end.compareTo(obegin) <= 0)
      return Order.LESS;
    else if (begin.compareTo(obegin) <= 0 && end.compareTo(oend) <= 0)
      return Order.LESS_OVERLAP;
    else if (begin.compareTo(oend) <= 0 && end.compareTo(oend) >= 0)
      return Order.GREATER_OVERLAP;
    else if (begin.compareTo(oend) >= 0)
      return Order.GREATER;
    else
      throw new RuntimeException("Date Comparison error"); //TODO: custom exception
  }
}

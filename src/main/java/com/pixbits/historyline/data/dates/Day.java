package com.pixbits.historyline.data.dates;

public class Day extends Month
{
  public final int day;
  
  public Day(int day, int month, int year)
  {
    super(year, month);
    this.day = day;
  }
  
  @Override public int compareTo(Date date)
  {
    int sc = super.compareTo(date);
    
    if (sc == 0 && date instanceof Day)
      return day - ((Day)date).day;
    else
      return sc;
  }
  
  @Override public boolean equals(Object date)
  {
    return date instanceof Day && super.equals(date) && ((Day)date).day == day;
  }
}

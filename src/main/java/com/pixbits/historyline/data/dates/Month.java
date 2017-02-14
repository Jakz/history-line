package com.pixbits.historyline.data.dates;

public class Month extends Year
{
  public final static int JANUARY = 1;
  public final static int FEBRUARY = 2;
  public final static int MARCH = 3;
  public final static int APRIL = 4;
  public final static int MAY = 5;
  public final static int JUNE = 6;
  public final static int JULY = 7;
  public final static int AUGUST = 8;
  public final static int SEPTEMBER = 9;
  public final static int OCTOBER = 10;
  public final static int NOVEMBER = 11;
  public final static int DECEMBER = 12;

  public final int month;
  
  Month(int month, int year)
  {
    super(year);
    this.month = month;
  }
  
  @Override public int compareTo(Date date)
  {
    int sc = super.compareTo(date);
    
    if (sc == 0 && date instanceof Month)
      return month - ((Month)date).month;
    else
      return sc;
  }
  
  @Override public boolean equals(Object date)
  {
    return date instanceof Month && super.equals(date) && ((Month)date).month == month;
  }
}

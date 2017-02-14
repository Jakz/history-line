package com.pixbits.historyline.data.dates;

public interface Date extends Comparable<Date>
{
  class DateUnknown implements Date
  { 
    @Override public int compareTo(Date date)
    {
      return date == Date.UNKNOWN ? 0 : 1;
    }
    
    @Override public boolean equals(Object date)
    {
      return date == Date.UNKNOWN;
    }
    
  }
  
  class NotYetDate implements Date
  { 
    @Override public int compareTo(Date date)
    {
      return date == Date.NOT_YET ? 0 : 1;
    }
    
    @Override public boolean equals(Object date)
    {
      return date == Date.NOT_YET;
    }
  }
  
  public final static Date UNKNOWN = new DateUnknown();
  public final static Date NOT_YET = new NotYetDate();
}

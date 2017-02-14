package com.pixbits.historyline.data.dates;

public class Year implements Date
{
  public final int year;
  
  public Year(int year)
  {
    this.year = year;
  }
  
  @Override public int compareTo(Date date)
  {
    if (date == Date.NOT_YET || date == Date.UNKNOWN)
      return -1;
    else if (date instanceof Year)
      return year - ((Year)date).year;
    else
      throw new RuntimeException("Date Comparison failed"); // TODO: custom exception
  }
  
  @Override public boolean equals(Object date)
  {
    return date instanceof Year && ((Year)date).year == year;
  }
}

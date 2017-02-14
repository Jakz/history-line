package com.pixbits.historyline.data.people;

import com.pixbits.historyline.data.dates.Date;
import com.pixbits.historyline.data.places.Place;

public class Event
{
  private final Date date;
  private final Place place;
  
  public Event(Place place, Date date)
  {
    this.place = place;
    this.date = date;
  }
  
  public Date getDate() { return date; }
  public Place getPlace() { return place; }
}

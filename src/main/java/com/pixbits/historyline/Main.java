package com.pixbits.historyline;

import com.pixbits.historyline.data.dates.Day;
import com.pixbits.historyline.data.dates.Month;
import com.pixbits.historyline.data.dates.Year;
import com.pixbits.historyline.data.people.Architect;
import com.pixbits.historyline.data.people.Event;
import com.pixbits.historyline.data.places.City;
import com.pixbits.historyline.data.places.Continent;
import com.pixbits.historyline.data.places.Country;
import com.pixbits.historyline.data.places.Place;
import com.pixbits.historyline.ui.MainFrame;

public class Main
{
  Continent europe = new Continent("Europe");
  Country italy = new Country("Italy", europe);
  
  City florence = new City("Florence", italy);
  
  Event birth = new Event(florence, new Year(1377));
  Event death = new Event(Place.unknown, new Day(15, Month.APRIL, 1446));
  
  Architect architect = new Architect("Filippo Brunelleschi", birth, death);
  
  public static void main(String[] args)
  {
    MainFrame frame = new MainFrame();
  }
}

package com.pixbits.historyline.data.places;

public class Country
{
  public final Continent continent;
  public final String name;
  
  public Country(String name, Continent continent)
  {
    this.continent = continent;
    this.name = name;
  }
}

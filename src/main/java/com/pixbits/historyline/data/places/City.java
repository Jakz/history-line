package com.pixbits.historyline.data.places;

public class City implements Place
{
  final Country country;
  final String name;
  
  public City(String name, Country country)
  {
    this.country = country;
    this.name = name;
  }
  
}

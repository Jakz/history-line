package com.pixbits.historyline.data.people;

import com.pixbits.historyline.data.dates.TimeSpan;

public class Person
{
  final Event birth;
  final Event death;
  final String name;
  
  Person(String name, Event birth, Event death)
  {
    this.name = name;
    this.birth = birth;
    this.death = death;
  }
  
  TimeSpan getLifeSpan()
  {
    return new TimeSpan(birth.getDate(), death.getDate());
  }
}

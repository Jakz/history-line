package com.pixbits.historyline.data.people;

import java.util.*;

import com.pixbits.historyline.data.architecture.Product;

public class Architect extends Person
{
  public final ArrayList<Product> products = new ArrayList<Product>();
  
  public Architect(String name, Event birth, Event death)
  {
    super(name, birth, death);
  }
}

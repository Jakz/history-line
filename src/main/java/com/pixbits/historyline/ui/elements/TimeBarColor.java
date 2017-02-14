package com.pixbits.historyline.ui.elements;

import java.awt.Color;

public class TimeBarColor
{
  public final Color stroke;
  public final Color fill;
  
  TimeBarColor(Color stroke, Color fill)
  {
    this.stroke = stroke;
    this.fill = fill;
  }
  
  public static final TimeBarColor GREEN = new TimeBarColor(new Color(0,160,0), new Color(180,255,180));
  public static final TimeBarColor RED = new TimeBarColor(new Color(160,0,0), new Color(255, 153, 153));

}

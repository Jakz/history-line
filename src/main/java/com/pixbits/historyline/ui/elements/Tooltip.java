package com.pixbits.historyline.ui.elements;

import java.util.Arrays;
import java.util.function.Function;

import com.pixbits.historyline.ui.Canvas;
import com.pixbits.lib.lang.Size;
import com.pixbits.lib.ui.color.Color;

public class Tooltip
{
  private int maxWidth;
  private int margin;
  private int marginFromBorder;
  
  private String[] lines;
  private int[] widths;
  private Size.Int size;
  
  private Color background;
  private Color borderColor;
  private float borderWidth;
  
  private final Canvas canvas;
  
  private int width, height;
  
  public Tooltip(Canvas canvas, int maxWidth)
  {
    this.maxWidth = maxWidth;
    this.canvas = canvas;
    
    this.margin = 5;
    this.marginFromBorder = 10;
    this.background = new Color(251, 244, 161);
    this.borderColor = new Color(0, 0, 0);
    this.borderWidth = 1.2f;
  }
  
  public void setText(String text)
  {
    if (maxWidth == -1)
    {
      lines = text.split("\\n");
      widths = Arrays.stream(lines).mapToDouble(s -> canvas.textWidth(s)).mapToInt(f -> (int)f).toArray();
      
      width = Arrays.stream(widths).max().getAsInt() + margin*2;
      height = (int)(lines.length * (canvas.textAscent() + canvas.textDescent()) + margin*2);
    }
  }
  
  public void draw(int x, int y)
  {    
    if (x - width/2 < marginFromBorder)
      x = marginFromBorder + width/2;
    else if (x + width/2 > canvas.getWidth() - marginFromBorder)
      x = canvas.getWidth() - marginFromBorder - width/2;
    
    if (y - width/2 < marginFromBorder)
      y = marginFromBorder + height/2;
    else if (y + width/2 > canvas.getHeight() - marginFromBorder)
      y = canvas.getHeight() - marginFromBorder - height/2;
    
    canvas.fill(background);
    canvas.stroke(borderColor);
    canvas.strokeWeight(borderWidth);
    canvas.rect(x - width/2, y - height/2, width, height);
    
    canvas.fill(0);
    for (int i = 0; i < lines.length; ++i)
      canvas.text(
          lines[i],
          x, 
          y + height/2 - margin - (canvas.textAscent()+canvas.textDescent())*i - canvas.textDescent()
      );

  }
  
}

package com.pixbits.historyline.data;

import com.pixbits.historyline.data.dates.TimeSpan;

public interface RenderableAsBar
{
   TimeSpan getTimeSpan();
   String getCaption();
   
   public static RenderableAsBar of(final TimeSpan span, final String caption)
   {
     return new RenderableAsBar() {
       @Override public TimeSpan getTimeSpan() { return span; }
       @Override public String getCaption() { return caption; }
     };
   }
}

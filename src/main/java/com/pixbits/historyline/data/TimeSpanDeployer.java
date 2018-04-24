package com.pixbits.historyline.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.pixbits.historyline.data.dates.TimeSpan;
import com.pixbits.lib.functional.StreamUtil;
import com.pixbits.lib.lang.Pair;

public class TimeSpanDeployer<T>
{
  private final Stack<Pair<TimeSpan, T>> data;
  private List<List<Pair<TimeSpan, T>>> rows;
  
  TimeSpanDeployer()
  {
    data = new Stack<>();
  }
  
  private boolean hasFreeSpan(List<Pair<TimeSpan, T>> row, TimeSpan span)
  {
    return !row.stream().anyMatch(e -> e.first.overlap(span));
  }
  
  public void add(T d, TimeSpan span)
  {
    data.add(new Pair<>(span, d));
  }
  
  public void deploy(int rows)
  {
    this.rows = Stream
      .generate(() -> new ArrayList<Pair<TimeSpan, T>>())
      .limit(rows)
      .collect(Collectors.toList());
    
    while (!data.empty())
    {
      Pair<TimeSpan, T> e = data.pop();
      
      Optional<List<Pair<TimeSpan, T>>> row = this.rows.stream()
        .filter(r -> hasFreeSpan(r, e.first))
        .findFirst();
    }
  }
}

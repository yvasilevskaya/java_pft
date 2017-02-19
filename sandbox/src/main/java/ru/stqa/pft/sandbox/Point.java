package ru.stqa.pft.sandbox;

/**
 * Created by Yulia on 2/17/2017.
 */

public class Point {

  public double x;
  public double y;
  public double x2;
  public double y2;


  public Point(double х, double y, double х2, double y2) {
    this.x = х;
    this.y = y;
    this.x2 = х2;
    this.y2 = y2;
  }

  public double distance() {
    double dx = this.x2 - this.x;
    double dy = this.y2 - this.y;
    return Math.sqrt((dx * dx + dy * dy));
  }

}

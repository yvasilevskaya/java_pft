package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Yulia on 2/20/2017.
 */
public class PointTests {

  @Test
  public void testDistance(){
    Point p1 = new Point(1, 2);
    Point p2 = new Point(3, 4);
    Assert.assertEquals(p1.distance(p2),2.8284271247461903);
  }

  @Test
  public void testDistance2(){
    Point p1 = new Point(0, 0);
    Point p2 = new Point(5, 5);
    Assert.assertEquals(p1.distance(p2),7.0710678118654755);
  }

  @Test
  public void testDistance3(){
    Point p1 = new Point(-1, -1);
    Point p2 = new Point(5, 5);
    Assert.assertEquals(p1.distance(p2),8.48528137423857);
  }

}
